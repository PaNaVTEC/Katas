package me.panavtec

import cats.kernel.Monoid

object RomanNumerals {

  case class RomanSymbol(value: Int, symbol: String)
  val romanNumerals: List[RomanSymbol] = List[RomanSymbol](
    RomanSymbol(1000, "M"),
    RomanSymbol(500, "D"),
    RomanSymbol(100, "C"),
    RomanSymbol(50, "L"),
    RomanSymbol(10, "X"),
    RomanSymbol(5, "V"),
    RomanSymbol(1, "I")
  )

  def romanMonoid = new Monoid[RomanSymbol] {
    override def empty: RomanSymbol = RomanSymbol(0, "")

    override def combine(x: RomanSymbol, y: RomanSymbol) = RomanSymbol(x.value + y.value, x.symbol + y.symbol)
  }

  sealed trait RomanNumeralConversions[A] {
    def convert(decimal: Int): Option[RomanSymbol]
  }

  case object Addition extends RomanNumeralConversions[String] {
    override def convert(decimal: Int): Option[RomanSymbol] = {

      //      implicit val romanMonoid = new Monoid[RomanSymbol] {
      //        override def empty: RomanSymbol = RomanSymbol(0, "")
      //
      //        override def combine(x: RomanSymbol, y: RomanSymbol): RomanSymbol =
      //          RomanSymbol(x.value + y.value, x.symbol + y.symbol)
      //      }

      def some: Option[RomanSymbol] = {
        val upperBound = romanNumerals.dropWhile(_.value > decimal).headOption
        upperBound.flatMap(a => Some(romanMonoid.combine(a, RomanSymbol(decimal, ""))))
      }

      some
    }
  }

  case object Substraction extends RomanNumeralConversions[String] {
    override def convert(decimal: Int): Option[RomanSymbol] = ???
  }

  case object ExactSymbol extends RomanNumeralConversions[String] {
    override def convert(decimal: Int): Option[RomanSymbol] =
      romanNumerals.find(p => p.value == decimal)
  }

  def main(args: Array[String]): Unit =
    println(Addition.convert(2).getOrElse(0))

}
