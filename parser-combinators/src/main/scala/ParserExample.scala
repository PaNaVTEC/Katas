import scala.util.parsing.combinator.RegexParsers

class ParserExample extends RegexParsers {

  def name: Parser[String] = """[a-z]+""".r

  def action: Parser[Boolean] =
    """has\s+not|has""".r ^^ {
      case "has" => true
      case _ => false
    }

  def number: Parser[Double] =
    """[0-9]+""".r ^^ {
      _.toDouble
    }

  def endOfLine: Parser[String] = " " | ""

  def person: Parser[Person] = name ~ action ~ number <~ endOfLine ^^ {
    case name ~ action ~ number => Person(name, action, number)
  }

  def people: Parser[List[Person]] = rep(person)

  case class Person(name: String, action: Boolean, number: Double) {
    override val toString = s"$name $action $number"
  }
}

object some {

  val p = new ParserExample()
  val input = "johnny has 121 jimmy has not 1"

  def main(args: Array[String]): Unit = p.parseAll(p.people, input) match {
    case p.Success(people, _) => println(people)
    case p.Failure(msg, _) => println("FAILURE: " + msg)
    case p.Error(msg, _) => println("ERROR: " + msg)
  }

}












