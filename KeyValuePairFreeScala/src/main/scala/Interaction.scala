import cats.free.Free
import cats.{~>, Id}

object Interaction {

  // DSL or Algebra
  sealed trait Interaction[A]
  case object Ask extends Interaction[String]
  case class Tell(input: String) extends Interaction[Unit]
  type InteractionAlgebra[A] = Free[Interaction, A]

  // Smart constructors
  def ask(question: String): InteractionAlgebra[String] =
    tell(question).flatMap(_ => Free.liftF(Ask))
  def tell(input: String): InteractionAlgebra[Unit] = Free.liftF(Tell(input))

  // Domain Logic
  def yourBandIsShit: InteractionAlgebra[Unit] =
    for {
      userMsg <- ask("Tell me your favourite artist")
      _ <- tell(s"$userMsg is shit")
    } yield ()

  // Interpretation of our program
  def interpreter: (Interaction ~> Id) = new (Interaction ~> Id) {
    def apply[A](fa: Interaction[A]): Id[A] = fa match {
      case Ask => scala.io.StdIn.readLine
      case Tell(input) => println(input)
    }
  }

  def main(args: Array[String]): Unit = yourBandIsShit.foldMap(interpreter)
}
