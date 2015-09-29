package me.panavtec.katas.numerals;

public class RomanSymbol {
  private char symbol;
  private int value;

  public RomanSymbol(char symbol, int value) {
    this.symbol = symbol;
    this.value = value;
  }

  public char getSymbol() {
    return symbol;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean isTypeFive() {
    return value / (5 * Math.pow(10, (int) Math.log10(value))) == 1d;
  }

  public boolean hasValue() {
    return value > 0;
  }
}
