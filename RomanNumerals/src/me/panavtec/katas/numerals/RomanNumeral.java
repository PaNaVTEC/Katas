package me.panavtec.katas.numerals;

public enum RomanNumeral {
  I(1, 'I'),
  V(5, 'V'),
  X(10, 'X'),
  L(50, 'L'),
  C(100, 'C'),
  D(500, 'D'),
  M(1000, 'M');

  private final int value;
  private final char symbol;
  private boolean isTypeFive;

  RomanNumeral(int value, char symbol) {
    this.value = value;
    this.symbol = symbol;
    this.isTypeFive = value / (5 * Math.pow(10, (int) Math.log10(value))) == 1d;
  }

  public boolean isTypeFive() {
    return isTypeFive;
  }

  public int getValue() {
    return value;
  }

  public char getSymbol() {
    return symbol;
  }

  public static int indexOf(RomanNumeral romanNumeral) {
    return RomanNumeral.valueOf(romanNumeral.toString()).ordinal();
  }
}
