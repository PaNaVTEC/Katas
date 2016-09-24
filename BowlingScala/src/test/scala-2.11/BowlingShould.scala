import org.scalatest.{FunSuite, Matchers}

class BowlingShould extends FunSuite with Matchers {

  test("Roll 1 and score 1") {
    bowling.roll(1)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)

    bowling.roll(0)
    bowling.roll(0)
    bowling.roll(0)

    assertResult("1- -- -- -- -- -- -- -- -- ---")(bowling.score())
  }

  test("Roll 1 on all rolls") {
    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)

    bowling.roll(1)
    bowling.roll(1)
    bowling.roll(1)

    assertResult("11 11 11 11 11 11 11 11 11 11-")(bowling.score())


    bowling.roll(
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    )(score)

  }

}
