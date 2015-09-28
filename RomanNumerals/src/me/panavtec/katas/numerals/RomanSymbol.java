package me.panavtec.katas.numerals;

public class RomanSymbol {
  private String symbol;
  private int value;

  public RomanSymbol(String symbol, int value) {
    this.symbol = symbol;
    this.value = value;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean isTypeOne() {
    return Integer.toString(value).charAt(0) == '1';
  }

  public boolean isTypeFive() {
    return Integer.toString(value).charAt(0) == '5';
  }

  public boolean hasValue() {
    return value > 0;
  }

  @Override public String toString() {
    return symbol;
  }
}
