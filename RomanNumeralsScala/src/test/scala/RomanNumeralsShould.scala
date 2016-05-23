import org.scalatest.{FunSuite, Matchers}

class RomanNumeralsShould extends FunSuite with Matchers {

  test("convert I to 1") {
    assertResult("I")(romansNumeral.from(1))
  }

  test("convert II to 2") {
    assertResult("II")(romansNumeral.from(2))
  }

  test("convert III to 3") {
    assertResult("III")(romansNumeral.from(3))
  }

  test("convert IV to 4") {
    assertResult(romansNumeral.from(4))("IV")
  }

  test("convert V to 5") {
    assertResult("V")(romansNumeral.from(5))
  }

  test("convert VI to 6") {
    assertResult("VI")(romansNumeral.from(6))
  }

  test("convert VII to 7") {
    assertResult("VII")(romansNumeral.from(7))
  }

  test("convert VIII to 8") {
    assertResult("VIII")(romansNumeral.from(8))
  }

  test("convert X to 10") {
    assertResult("X")(romansNumeral.from(10))
  }

  test("convert XII to 12") {
    assertResult("XII")(romansNumeral.from(12))
  }

  test("convert XIII to 13") {
    assertResult("XIII")(romansNumeral.from(13))
  }

  test("convert MCMXC to 1990") {
    assertResult("MCMXC")(romansNumeral.from(1990))
  }

}