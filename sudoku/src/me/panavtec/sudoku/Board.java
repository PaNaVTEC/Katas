package me.panavtec.sudoku;

import java.util.Arrays;

public class Board {

  private static final int HEIGHT = 9;
  private static final int WIDTH = 9;
  private final int[][] board = new int[WIDTH][HEIGHT];

  public Board() {

  }

  public Board(Board board) {
    fillWith(board);
  }

  private void fillWith(Board board) {
    for (int i = 0; i < board.width(); i++) {
      for (int j = 0; j < board.height(); j++) {
        putValueAtPosition(i, j, board.getValueAtPosition(i, j));
      }
    }
  }

  public int getValueAtPosition(int x, int y) {
    return board[x][y];
  }

  public void putValueAtPosition(int x, int y, int value) {
    board[x][y] = value;
  }

  private int width() {
    return WIDTH;
  }

  private int height() {
    return HEIGHT;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Board board1 = (Board) o;

    return Arrays.deepEquals(board, board1.board);
  }

  @Override public String toString() {
    return new SudokuFormatter(new SudokuConfig(WIDTH)).formatBoard(this);
  }

  @Override public int hashCode() {
    return Arrays.deepHashCode(board);
  }

  public Quadrant getQuadrant(int i) {
    Quadrant quadrant = new Quadrant();
    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 3; k++) {

      }
    }
    return quadrant;
  }
}
