package me.panavtec.katas.bowling;

public enum BowlingSymbols {
  SPARE('/'),
  STRIKE('X'),
  GUTTER('-');

  private char symbol;

  BowlingSymbols(char symbol) {
    this.symbol = symbol;
  }

  public char symbol() {
    return symbol;
  }
}
