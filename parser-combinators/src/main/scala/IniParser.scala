import scala.util.parsing.combinator.RegexParsers

case class Header(section: String)
case class Comment(comment: String)

class IniParser extends RegexParsers {

  private val space = """\s""".r
  private val spaces = rep(space)
  private val word = """\w+""".r ||| space ^^ identity
  private val words = rep(word)
  implicit private def charParser(char: Char): String = s"""$char"""

  def headerParser: Parser[Header] =
    '[' ~ words ~ ']' ^^ {
      case _ ~ section ~ _ => Header(foldWords(section))
    }

  def commentParser: Parser[Comment] =
    ';' ~ spaces ~ words ^^ {
      case _ ~ _ ~ comment => Comment(foldWords(comment))
    }

  def commentsParser: Parser[List[Comment]] = rep(commentParser)

  private def foldWords(words: List[String]) = ("" /: words) {
    case ("", s2) => s2
    case (s1, "") => s1
    case (s1, s2) => s"$s1 $s2"
  }

}
