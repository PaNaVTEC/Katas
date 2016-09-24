import Bowling._
import org.scalatest._

class BowlingTest extends FlatSpec with Matchers {

  behavior of "A bowling Game roller"

  it should "return a list of one frame when rolling 2 balls" in {
    assert(NewGame().rollFrame(0, 0) == InCourseGame(List(NumberFrame(0, 0))))
  }

  object Roller {
    def roll(frame: Frame): Game = {
      def go(times: Int, lastGame: Game): Game =
        if (times > 0) go(times - 1, lastGame.rollFrame(frame)) else lastGame
      go(10, NewGame())
    }

    lazy val rollZeroes = roll(NumberFrame(0, 0))
  }

  it should "throw an exception if you try to add a 11th frame" in {
    assertThrows[EndOfGameException](Roller.rollZeroes.rollFrame(0, 0))
  }

  behavior of "A bowling Game calculator"

  it should "score 0 if 10 frames are 0" in {
    assert(Roller.rollZeroes.score == 0)
  }

  it should "score 10 if 10 frames are 1, 0" in {
    assert(Roller.roll(NumberFrame(1, 0)).score == 10)
  }

  it should "score 12 if rolls a spare (5, 5) followed by 1" in {
    assert(NewGame()
      .rollFrame(5, 5)
      .rollFrame(1, 0).score == 12)
  }

  it should "score 10 if rolls a X,0" in {
    assert(NewGame()
      .rollFrame(10, 0)
      .rollFrame(0, 0).score == 10)
  }

  it should "score 30 if rolls a X,X,0" in {
    assert(NewGame()
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(0, 0).score == 30)
  }

  it should "score 60 if rolls a X,X,X,0" in {
    assert(NewGame()
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0).score == 60)
  }

  it should "score 63 if rolls a X,X,X,1" in {
    assert(NewGame()
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(1, 0).score == 63)
  }

  it should "score 30 if rolls a 0/,X,0" in {
    assert(NewGame().rollFrame(0, 10).rollFrame(10, 0).rollFrame(0, 0).score == 30)
  }

  it should "score 300 if rolls a perfect game" in {
    assert(NewGame()
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(FinalFrame(10, 10, 10)).score == 300)
  }

  it should "score a spare in the final frame" in {
    assert(NewGame()
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(FinalFrame(5, 5, 5)).score == 15)
  }

  it should "score a 2 rolls in the final frame" in {
    assert(NewGame()
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(0, 0)
      .rollFrame(FinalFrame(0, 5, 0)).score == 5)
  }

  it should "acceptance test" in {
    assert(NewGame()
      .rollFrame(5, 3)
      .rollFrame(3, 3)
      .rollFrame(3, 4)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(5, 3)
      .rollFrame(3, 7)
      .rollFrame(10, 0)
      .rollFrame(FinalFrame(10, 4, 3)).score == 163)
  }

  it should "acceptance test 2" in {
    assert(NewGame()
      .rollFrame(7, 1)
      .rollFrame(3, 3)
      .rollFrame(4, 5)
      .rollFrame(4, 5)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(10, 0)
      .rollFrame(5, 5)
      .rollFrame(FinalFrame(2, 3, 0)).score == 154)
  }

}
