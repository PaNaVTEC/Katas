import org.scalatest._


class IniParserTest extends FlatSpec with Matchers {

  val parser = new IniParser()

  it should "parse a header" in {
    val input = "[section]"

    val headerParser = parser.headerParser

    parser.parse(headerParser, input).get should be(Header("section"))
  }

  it should "parse a comment" in {
    val input = "; comment"

    val commentParser = parser.commentParser

    parser.parse(commentParser, input).get should
      be(Comment("comment"))
  }

  it should "parse two comments" in {
    val input =
      "; comment\n" +
        "; acomment asdf"

    val commentsParser = parser.commentsParser

    parser.parse(commentsParser, input).get should
      be(List(Comment("comment"), Comment("acomment asdf")))
  }

}
