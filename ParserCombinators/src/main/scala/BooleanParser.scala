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
class BooleanParser {

}
