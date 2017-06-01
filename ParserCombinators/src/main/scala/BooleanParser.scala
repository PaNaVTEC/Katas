import scala.util.parsing.combinator.RegexParsers

//*************************************************************************
//BOOLEAN CALCULATOR KATA
//*************************************************************************
//*************************************************************************
//1. GOAL
//  *************************************************************************
//Write a Boolean calculator that takes string as input
//and returns the result as Boolean.
//The operators are:
//"NOT", "AND", "OR"
//while the values could be "T" for True and "F" for False.
//  These are the truth tables:
//  NOT       AND          OR
//-------   ----------   ----------
//  ¬T -> F   T & T -> T   T | T -> T
//  ¬F -> T   T & F -> F   T | F -> T
//  F & T -> F   F | T -> T
//  F & F -> F   F | F -> F
//
//and this is the precedence order for operators:
//  Operator | Precedence
//---------------------
//NOT      | HIGH
//AND      | MID
//OR       | LOW
//---------------------
// TEST: NOT F AND F
// And(Not(False), False)
// >>> Not(And(False,False))
//
//For example the following:
//"NOT T AND F AND T OR T"
//evaluates to:
//  True
//
//*************************************************************************
//2. TRICKY
//  *************************************************************************
//Include "(" and ")" to override the precedence of operations
//  so that:
//"NOT (T AND F AND T OR T)"
//evaluates to:
//  False
//
//*************************************************************************
//3. BONUS
//  *************************************************************************
//Print the Abstract Syntax Tree
//for the string in input so that
//  "NOT (T AND F AND T OR T)"
//is printed as:
//  NOT
//|
//OR
///  \
//  AND    T
//  /   \
//    AND     T
//  /   \
//    T     F
object BooleanCalculator extends RegexParsers {

  private val T: String = "T"
  private val F: String = "F"
  private val NOT: String = "NOT"
  private val AND: String = "AND"
  private val OR: String = "OR"

  private val trueParser: Parser[Term] = T ^^^ True
  private val falseParser: Parser[Term] = F ^^^ False
  private val constant: Parser[Term] = trueParser | falseParser
  private val factor: Parser[Expression] = constant | NOT ~> factor ^^ Not | "(" ~> expression <~ ")"
  private val term: Parser[Expression] = factor ~ rep(AND ~> factor) ^^ {
    case b ~ list => (b /: list) (And)
  }
  private val expression: Parser[Expression] = term ~ rep(OR ~> term) ^^ {
    case b ~ list => (b /: list) (Or)
  }

  //      private val notOperator: Parser[Not] = NOT ~> factor ^^ Not
  //      private val factor: Parser[Expression] = notOperator | constant | "(" ~> expression <~ ")"
  //      private val twoArgOp: Parser[Expression] = factor ~ rep1(AND ~ factor | OR ~ factor) ^^ {
  //        case b ~ list => list.foldLeft(b)((a, b) => expressionFor(b._1, a, b._2))
  //      }
  //      private val expression: Parser[Expression] = twoArgOp | factor
  //
  //      private def expressionFor(op: String, b: Expression, b2: Expression): Operator = op match {
  //        case OR => Or(b, b2)
  //        case AND => And(b, b2)
  //      }

  def buildTree(input: String): Either[String, Expression] =
    parse(expression, input) match {
      case Success(result, _) => Right(result)
      case NoSuccess(e) => Left(s"Error: $e._1 \nwhile parsing: $e._2")
    }

  def evaluate(input: String): Either[String, Boolean] = buildTree(input).map(_.evaluate)

  def printTree(input: String): Unit = buildTree(input).foreach(printTree)

  def printTree(input: Expression): Unit = {
    def go(expression: Expression, deep: Int): String = {
      def padding: String = " " * deep

      def format(op: String, branch: String, expansion: String) =
        s"$op\n" +
          s"$padding$branch \n" +
          s"$padding$expansion"

      expression match {
        case True => "T "
        case False => "F "
        case And(b, b1) => format("AND", "/ \\", go(b, deep + 1) + go(b1, deep + 1))
        case Or(b, b1) => format("OR ", "/ \\", go(b, deep + 1) + go(b1, deep + 1))
        case Not(b) => format("NOT", " | ", go(b, deep))
      }
    }

    println(go(input, 0))
  }
}

object Main {
  def main(args: Array[String]): Unit =
  println(
    BooleanCalculator.evaluate(
      "NOT (T AND (F AND (T OR T)))"
    ).right.get
  )
//    BooleanCalculator.printTree(
//    Not(And(True, And(False, Or(True, True))))
//  )

}

sealed trait Expression {
  def evaluate: Boolean
}

sealed trait Term extends Expression

case object True extends Term {
  override def evaluate: Boolean = true
}

case object False extends Term {
  override def evaluate: Boolean = false
}

sealed trait Operator extends Expression

case class Not(b: Expression) extends Operator {
  override def evaluate: Boolean = !b.evaluate
}
case class And(b: Expression, b1: Expression) extends Operator {
  override def evaluate: Boolean = b.evaluate && b1.evaluate
}

case class Or(b: Expression, b1: Expression) extends Operator {
  override def evaluate: Boolean = b.evaluate || b1.evaluate
}