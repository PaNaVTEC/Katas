package me.panavtec

import cats.free.Free
import cats.{Id, ~>}

import scala.io.StdIn

object UserInteractionApplication {

  sealed trait UserInteraction[A]
  case object Ask extends UserInteraction[String]
  case class Tell(what: String) extends UserInteraction[Unit]

  implicit def liftFree[A](u: UserInteraction[A]): Free[UserInteraction, A] = Free.liftF(u)

  def interpreter = new (UserInteraction ~> Id) {
    override def apply[A](fa: UserInteraction[A]): Id[A] = fa match {
      case Ask => StdIn.readLine
      case Tell(what: String) => println(what)
    }
  }

  def program: Free[UserInteraction, Unit] = for {
    _ <- Tell("Introduce any input")
    input <- Ask
    _ <- Tell(input)
  } yield ()

  def main(args: Array[String]): Unit = repeat(program)(3).foldMap(interpreter)

  def repeat[A[_], B](free: Free[A, B])(times: Int): Free[A, B] =
    free.flatMap(a => if (times == 0) Free.pure(a) else repeat(free)(times - 1))

}
