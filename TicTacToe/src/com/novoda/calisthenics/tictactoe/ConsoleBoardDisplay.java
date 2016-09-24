package com.novoda.calisthenics.tictactoe;

public class ConsoleBoardDisplay implements GameBoardDisplay {
  private final Console console;

  public ConsoleBoardDisplay(Console console) {
    this.console = console;
  }

  @Override public void updateCell(Location location, Player player) {
    printEmptyLine();
    printEmptyLine();
    printEmptyLine();
  }

  private void printEmptyLine() {
    console.print("| | | |");
  }
}
