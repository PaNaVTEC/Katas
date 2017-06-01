import org.scalatest.{FlatSpec, Matchers}

class BooleanCalculatorTest extends FlatSpec with Matchers {

  "T" should "evaluate to true" in {
    BooleanCalculator.buildTree("T") should be(Right(True))
  }

  "F" should "evaluate to false" in {
    BooleanCalculator.buildTree("F") should be(Right(False))
  }

  "NOT F" should "evaluate to true" in {
    BooleanCalculator.buildTree("NOT F") should be(Right(Not(False)))
  }

  "NOT T" should "evaluate to false" in {
    BooleanCalculator.buildTree("NOT T") should be(Right(Not(True)))
  }

  "T AND T" should "evaluate to true" in {
    BooleanCalculator.buildTree("T AND T") should be(Right(And(True, True)))
  }

  "T AND F" should "evaluate to false" in {
    BooleanCalculator.buildTree("T AND F") should be(Right(And(True, False)))
  }

  "F AND T" should "evaluate to false" in {
    BooleanCalculator.buildTree("F AND T") should be(Right(And(False, True)))
  }

  "F AND F" should "evaluate to false" in {
    BooleanCalculator.buildTree("F AND F") should be(Right(And(False, False)))
  }

  "NOT F AND T" should "evalue to true" in {
    BooleanCalculator.buildTree("NOT F AND T") should be(Right(And(Not(False), True)))
  }

  "NOT F AND F" should "evalue to false" in {
    BooleanCalculator.buildTree("NOT F AND F") should be(Right(And(Not(False), False)))
  }

  "NOT F OR T" should "evalue to true" in {
    BooleanCalculator.buildTree("NOT F OR T") should be(Right(Or(Not(False), True)))
  }

  "F OR T" should "evalue to true" in {
    BooleanCalculator.buildTree("F OR T") should be(Right(Or(False, True)))
  }

  "F AND NOT F" should "evalue to false" in {
    BooleanCalculator.buildTree("F AND NOT F") should be(Right(And(False, Not(False))))
  }

  "T AND T AND T" should "evalue to false" in {
    BooleanCalculator.buildTree("T AND T AND T") should be(Right(And(And(True, True), True)))
  }

  "NOT T AND F AND T OR T" should "evalue to true" in {
    BooleanCalculator.buildTree("NOT T AND F AND T OR T") should be(Right(
      Or(And(And(Not(True), False), True), True)
    ))
  }

  "(T AND T)" should "evaluate to true" in {
    BooleanCalculator.buildTree("(T AND T)") should be(Right(And(True, True)))
  }

  "NOT (T AND F AND T OR T)" should "evalue to true" in {
    BooleanCalculator.buildTree("NOxT (T AND F AND T OR T)") should be(Right(
      Not(Or(And(And(True, False), True), True))
    ))
  }

  "F OR T AND T" should "evalue to true" in {
    BooleanCalculator.buildTree("F OR T AND T") should be(Right(
      Or(False, And(True, True))
    ))
  }

  "NOT (T AND (F AND (T OR T)))" should "evalue to true" in {
    BooleanCalculator.buildTree("NOT (T AND (F AND (T OR T)))") should be(Right(
      Not(And(True, And(False, Or(True, True))))
    ))
  }

}