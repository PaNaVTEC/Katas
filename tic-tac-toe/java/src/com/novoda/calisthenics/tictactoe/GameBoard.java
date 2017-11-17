package com.novoda.calisthenics.tictactoe;

public class GameBoard {

  private static final String PLAYER_1 = "X";
  private static final String[][] board = new String[3][3];

  // What is this pattern called and why is it important?
  public void display(GameBoardDisplay display) {
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[i].length; j++)
        display.updateCell(new Location(i, j), PLAYER_1.equals(board[i][j]) ? Player.Player1 : Player.Player2);
  }

  public void updatePlayer1Cell(int x, int y) {
    board[x][y] = PLAYER_1;
  }
}
