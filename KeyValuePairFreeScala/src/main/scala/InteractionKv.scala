import cats.free.Free
import scala.collection.mutable
import cats.free.Free.liftF
import cats.{~>, Id}

object InteractionKv {

  // DSL Interaction
  sealed trait Interaction[A]
  case object Ask extends Interaction[String]
  case class Tell(input: String) extends Interaction[Unit]
  type InteractionAlgebra[A] = Free[Interaction, A]

  def ask(question: String): InteractionAlgebra[String] =
    tell(question).flatMap(_ => Free.liftF(Ask))
  def tell(input: String): InteractionAlgebra[Unit] = Free.liftF(Tell(input))

  // Interpretation of our program
  def interactionInterpreter: (Interaction ~> Id) = new (Interaction ~> Id) {
    def apply[A](fa: Interaction[A]): Id[A] = fa match {
      case Ask => scala.io.StdIn.readLine
      case Tell(input) => println(input)
    }
  }

  // DSL KV
  sealed trait KVStoreA[A]
  case class Put[T](key: String, value: T) extends KVStoreA[Unit]
  case class Get[T](key: String) extends KVStoreA[Option[T]]
  case class Delete(key: String) extends KVStoreA[Unit]

  type KVStore[A] = Free[KVStoreA, A]
  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreA, Unit](Put[T](key, value))
  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreA, Option[T]](Get[T](key))
  def delete(key: String): KVStore[Unit] = liftF(Delete(key))

  def kvInterpreter: (KVStoreA ~> Id) = new (KVStoreA ~> Id) {
    val kvs = mutable.Map.empty[String, Any]

    def apply[A](fa: KVStoreA[A]): Id[A] = fa match {
      case Put(key, value) => kvs(key) = value
      case Get(key) => kvs.get(key)
      case Delete(key) => kvs.remove(key); ()
    }
  }

}
