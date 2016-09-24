object Bowling {

  type Roll = Int
  type BowlingScore = Int

  sealed abstract class Game {
    def score: BowlingScore = {
      def calculate(frames: List[Frame]): BowlingScore = {
        def isSpare(r1: Roll, r2: Roll) = r1 + r2 == 10
        def nextRoll(l: List[Frame]) = l match {
          case Nil => 0
          case FinalFrame(r1, _, _) :: tail => r1
          case NumberFrame(r1, _) :: tail => r1
        }
        val X = 10
        def nextTwoRolls(l: List[Frame]) = l match {
          case Nil => 0
          case FinalFrame(r1, r2, _) :: tail => r1 + r2
          case FrameP(X) :: FrameP(X) :: tail => X + X
          case NumberFrame(r1, r2) :: tail => r1 + r2
        }
        def go(acc: BowlingScore, fs: List[Frame]): BowlingScore = fs match {
          case Nil => acc
          case FrameP(r1, r2, r3) :: Nil => go(acc + r1 + r2 + r3, fs.tail)
//          case FrameP(X) :: FrameP(X) :: tail => go(acc + X + X + nextRoll(tail), fs.tail)
          case FrameP(X) :: tail => go(acc + X + nextTwoRolls(tail), tail)
          case FrameP(r1, r2) :: tail if isSpare(r1, r2) => go(acc + r1 + r2 + nextRoll(tail), tail)
          case FrameP(r1, r2) :: tail => go(acc + r1 + r2, tail)
        }
        go(0, frames)
      }

      this match {
        case NewGame() => 0
        case InCourseGame(fs) => calculate(fs)
        case CompletedGame(fs) => calculate(fs)
      }
    }

    def rollFrame(f: Roll*): Game = f match {
      case r1 +: r2 +: _ => rollFrame(NumberFrame(r1, r2))
      case r1 +: r2 +: r3 +: _ => rollFrame(FinalFrame(r1, r2, r3))
      case _ => throw new IllegalStateException("Roll not allowed")
    }

    def rollFrame(f: Frame): Game
  }

  case class NewGame() extends Game {
    override def rollFrame(f: Frame): Game = InCourseGame(List(f))
  }

  case class CompletedGame(frames: List[Frame]) extends Game {
    override def rollFrame(f: Frame): Game = throw new EndOfGameException()
  }

  case class InCourseGame(frames: List[Frame]) extends Game {
    override def rollFrame(f: Frame): Game = this match {
      case InCourseGame(current) if current.size < 9 => InCourseGame(current ++ List(f))
      case InCourseGame(current) if current.size < 10 => CompletedGame(current ++ List(f))
    }
  }

  sealed abstract class Frame()

  case class NumberFrame(roll1: Roll, roll2: Roll) extends Frame

  case class FinalFrame(roll1: Roll, roll2: Roll, roll3: Roll) extends Frame

  object FrameP {
    def unapplySeq(f: Frame): Option[Seq[Int]] = f match {
      case NumberFrame(r1, _) if r1 == 10 => Some(List(10))
      case NumberFrame(r1, r2) => Some(List(r1, r2))
      case FinalFrame(r1, r2, r3) => Some(List(r1, r2, r3))
      case _ => None
    }
  }

  class EndOfGameException(msg: String = "The game has ended, can't roll more balls") extends IllegalStateException

}
