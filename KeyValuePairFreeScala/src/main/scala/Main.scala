import cats.free.Free
import cats.free.Free.liftF
import cats.arrow.FunctionK
import cats.{Id, ~>}
import scala.collection.mutable

object Main extends App {
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
  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      oldValue <- get[T](key)
      _ <- oldValue.map(oldV => put[T](key, f(oldV))).getOrElse(Free.pure(()))
    } yield ()

  type KVSInterpreter = FunctionK[KVStoreA, Id]
  def interpreter: KVSInterpreter = new (KVStoreA ~> Id) {
    val kvs = mutable.Map.empty[String, Any]

    def apply[A](fa: KVStoreA[A]): Id[A] = fa match {
      case Put(key, value) => kvs(key) = value
      case Get(key) => kvs.get(key)
      case Delete(key) => kvs.remove(key); ()
    }
  }

  def program: KVStore[Option[Int]] =
    for {
      _ <- put("Key", 2)
      _ <- update[Int]("Key", i => i + 1)
      _ <- put("Another Key", 3)
      _ <- delete("Another Key")
      n <- get[Int]("Another Key")
      m <- get[Int]("Key")
    } yield n

  def interpretedProgram: Option[Int] = program.foldMap(interpreter)

  override def main(args: Array[String]): Unit = println(interpretedProgram)
}
