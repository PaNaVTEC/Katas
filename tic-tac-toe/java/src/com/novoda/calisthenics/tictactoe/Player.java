package com.novoda.calisthenics.tictactoe;

public enum Player {
  Player1("X"),
  Player2("Y");

  private String symbol;

  Player(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}