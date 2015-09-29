package me.panavtec.katas.numerals;

public class RomanSymbol {
  private char symbol;
  private int value;
  private boolean isTypeFive;

  public RomanSymbol(char symbol, int value) {
    this.symbol = symbol;
    this.value = value;
    this.isTypeFive = value / (5 * Math.pow(10, (int) Math.log10(value))) == 1d;
  }

  public char getSymbol() {
    return symbol;
  }

  public int getValue() {
    return value;
  }

  public boolean isTypeFive() {
    return isTypeFive;
  }
}
