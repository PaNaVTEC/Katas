package me.panavtec

import cats.free.Free
import cats.{Id, ~>}

import scala.io.StdIn

object GuessTheSecretNumber {

  sealed trait UserInteraction[A]
  case object Ask extends UserInteraction[String]
  case class Tell(what: String) extends UserInteraction[Unit]
  case object ForgetWhatISaid extends UserInteraction[Unit]

  implicit def liftFree[A](u: UserInteraction[A]): Free[UserInteraction, A] = Free.liftF(u)

  def interpreter = new (UserInteraction ~> Id) {
    override def apply[A](fa: UserInteraction[A]): Id[A] = fa match {
      case Ask => StdIn.readLine
      case Tell(what: String) => println(what)
      case ForgetWhatISaid => println(Array.fill(100)("\n").foldLeft("")(_ + _))
    }
  }

  type Input = Free[UserInteraction, String]

  def introduceSecretNumber: Input = for {
    _ <- Tell("Player One: Input the secret number")
    secret <- Ask
    _ <- ForgetWhatISaid
  } yield secret

  def guessSecretNumber: Input = for {
    _ <- Tell("Player Two: What is the secret number?")
    input <- Ask
  } yield input

  private def program = {
    def untilUserDoesNotGuess(secret: String): (String => Boolean) = input => input != secret

    def repeat[A[_], B](free: Free[A, B])(pred: B => Boolean): Free[A, B] =
      free.flatMap(a => if (!pred(a)) Free.pure(a) else repeat(free)(pred))

    for {
      secret <- introduceSecretNumber
      _ <- repeat(guessSecretNumber)(untilUserDoesNotGuess(secret))
    } yield ()
  }

  def main(args: Array[String]): Unit = program.foldMap(interpreter)

}
