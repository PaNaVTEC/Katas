package me.panavtec

import cats.data.Coproduct
import cats.free.{Free, Inject}
import cats.{Foldable, Id, ~>}
import cats.syntax._

import scala.io.StdIn
import scala.util.matching.Regex

object Main {

  type Board = (Row[Top], Row[Middle], Row[Bottom])

  object Board {

    def asString(board: Board): String =
      Row.asString(board._1) + "\n" + Row.asString(board._2) + "\n" + Row.asString(board._3)

    def hasPlayerWon(board: Board): Boolean = {
      def combination(c: Cell[_], c2: Cell[_], c3: Cell[_]): Boolean = {
        val maybeMarks = List(c.mark, c2.mark, c3.mark)
        maybeMarks.takeWhile(_.getOrElse(X) == O).size == 3 || maybeMarks.takeWhile(_.getOrElse(O) == X).size == 3
      }

      board match {
        case
          ((l1, m1, t1),
          (l2, m2, t2),
          (l3, m3, t3)) =>
          combination(l1, m1, t1) || combination(l2, m2, t2) || combination(l3, m3, t3) ||
            combination(l1, m2, t3) || combination(t1, m2, l3) || combination(l1, l2, l3) ||
            combination(m1, m2, m3) || combination(t1, t2, t3)
        case _ => false
      }
    }

    def isBoardFull(board: Board): Boolean = board match {
      case (r1, r2, r3) =>
        (r1.productIterator.toList ++ r2.productIterator.toList ++ r3.productIterator.toList)
          .takeWhile(_.isInstanceOf[Some[_]]).size == 9
    }

    def apply(): Board = (Row[Top](), Row[Middle](), Row[Bottom]())

    def playAt(board: Board, coordinate: Coordinate, player: Player): Board = {
      def cellAt[R <: RowPosition](row: Row[R], cellPosition: CellPosition): Row[R] = (row, cellPosition) match {
        case ((_, c, r), Left()) => (Cell[Left](Some(player)), c, r)
        case ((l, _, r), Center()) => (l, Cell[Center](Some(player)), r)
        case ((l, c, _), Right()) => (l, c, Cell[Right](Some(player)))
      }

      (board, coordinate) match {
        case ((top, mid, bot), (Top(), c)) => (cellAt(top, c), mid, bot)
        case ((top, mid, bot), (Middle(), c)) => (top, cellAt(mid, c), bot)
        case ((top, mid, bot), (Bottom(), c)) => (top, mid, cellAt(bot, c))
      }
    }
  }

  sealed trait RowPosition
  case class Top() extends RowPosition
  case class Middle() extends RowPosition
  case class Bottom() extends RowPosition
  object RowPosition {
    def apply(input: String): RowPosition = input match {
      case "1" => Top();
      case "2" => Middle();
      case "3" => Bottom()
    }
  }

  type Row[A <: RowPosition] = (Cell[Left], Cell[Center], Cell[Right])

  object Row {
    def apply[A <: RowPosition](): Row[A] = (Cell[Left](None), Cell[Center](None), Cell[Right](None))

    def asString(row: Row[_]): String = row._1.mark.getOrElse(" ") + " | " + row._2.mark.getOrElse(" ") + " | " + row._3.mark.getOrElse(" ")
  }

  sealed trait CellPosition
  case class Left() extends CellPosition
  case class Center() extends CellPosition
  case class Right() extends CellPosition
  object CellPosition {
    def apply(input: String): CellPosition = input match {
      case "1" => Left()
      case "2" => Center()
      case "3" => Right()
    }
  }

  case class Cell[A <: CellPosition](mark: Option[Mark])

  type Player = Mark
  sealed trait Mark {
    val nextPlayer: Mark = this match {
      case X => O
      case O => X
    }

  }
  case object X extends Mark
  case object O extends Mark


  sealed trait Console[A]
  case object ReadLine extends Console[Coordinate]
  case class PrintLine(line: String) extends Console[Unit]

  class ConsoleOps[F[_]](implicit I: Inject[Console, F]) {
    def readLn: Free[F, Coordinate] = Free.inject(ReadLine)

    def printLn(line: String): Free[F, Unit] = Free.inject(PrintLine(line))
  }

  object ConsoleOps {
    implicit def console[F[_]](implicit I: Inject[Console, F]): ConsoleOps[F] = new ConsoleOps[F]
  }

  type Coordinate = (RowPosition, CellPosition)
  type PlayerInput = (Coordinate, Mark)
  type GameState = (Player, Board)

  sealed trait Turn[A]
  case object Start extends Turn[GameState]
  case class PlayAt(board: Board, playerInput: PlayerInput) extends Turn[GameState]

  class TurnOps[F[_]](implicit I: Inject[Turn, F]) {
    def startGame: Free[F, GameState] = Free.inject(Start)

    def playNext(board: Board, playerInput: PlayerInput): Free[F, GameState] = Free.inject(PlayAt(board, playerInput))
  }

  object TurnOps {
    implicit def turn[F[_]](implicit I: Inject[Turn, F]): TurnOps[F] = new TurnOps[F]
  }

  val consoleInterpreter: Console ~> Id = new (Console ~> Id) {
    val inputFormat: Regex = "\\s*([1-3]{1})\\s*,\\s*([1-3]{1})\\s*".r

    def normalizePosition(input: String): Coordinate = input match {
      case inputFormat(x, y) => (RowPosition(x), CellPosition(y))
    }

    override def apply[A](fa: Console[A]): Id[A] = {
      fa match {
        case ReadLine => normalizePosition(StdIn.readLine)
        case PrintLine(line) => println(line)
      }
    }
  }

  val turnInterpreter: Turn ~> Id = new (Turn ~> Id) {
    override def apply[A](fa: Turn[A]): Id[A] = {
      fa match {
        case Start => (X, Board())
        case PlayAt(board, (coordinate, player)) => (player.nextPlayer, Board.playAt(board, coordinate, player))
      }
    }
  }

  type TicTacToeApp[A] = Coproduct[Console, Turn, A]
  val interpreter: TicTacToeApp ~> Id = consoleInterpreter or turnInterpreter

  def startTicTacToe(implicit I: ConsoleOps[TicTacToeApp], D: TurnOps[TicTacToeApp]): Free[TicTacToeApp, GameState] = {
    import D._
    import I._

    for {
      _ <- printLn("== Game Starts ==")
      gameState <- startGame
    } yield gameState
  }

  def turn(gameState: GameState)(implicit I: ConsoleOps[TicTacToeApp], D: TurnOps[TicTacToeApp]): Free[TicTacToeApp, GameState] = {
    import D._
    import I._
    for {
      _ <- printLn(s"Turn Of Player ${gameState._1}. Input next move [X,Y]:")
      coordinate <- readLn
      gameState <- playNext(gameState._2, (coordinate, gameState._1))
      _ <- printLn(Board.asString(gameState._2))
    } yield gameState
  }

  def iterateUntil(f: GameState => Boolean)(fa: Free[TicTacToeApp, GameState]): Free[TicTacToeApp, GameState] =
    fa.flatMap(gs => if (f(gs)) Free.pure(gs) else iterateUntil(f)(turn(gs)))

  def finishGame(gs: GameState): Boolean = Board.hasPlayerWon(gs._2) || Board.isBoardFull(gs._2)

  def main(args: Array[String]): Unit = iterateUntil(finishGame)(startTicTacToe).foldMap(interpreter)

}
