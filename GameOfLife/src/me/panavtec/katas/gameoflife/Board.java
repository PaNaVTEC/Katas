package me.panavtec.katas.gameoflife;

public class Board implements Cloneable {

  private final int[][] board;

  public Board(int rows, int cols) {
    board = new int[rows][cols];
  }

  public Board(int[][] board) {
    this.board = board;
  }

  public void setCellState(int row, int col, int state) {
    if (!areValidCoordinates(row, col)) {
      throw new IllegalArgumentException(row + ", " + col + " are not valid coordinates");
    }
    board[row][col] = state;
  }

  public int getCellSate(int row, int col) {
    if (!areValidCoordinates(row, col)) {
      throw new IllegalArgumentException(row + ", " + col + " are not valid coordinates");
    }
    return board[row][col];
  }

  public boolean areValidCoordinates(int row, int col) {
    if (row >= getRows() || row < 0) {
      return false;
    } else if (col >= getCols() || col < 0) {
      return false;
    }
    return true;
  }

  public int getTotalSize() {
    return board.length * board[0].length;
  }

  public int getRows() {
    return board.length;
  }

  public int getCols() {
    return board[0].length;
  }

  public boolean isBottomRightAlive(int row, int col) {
    return isCellAlive(row + 1, col + 1);
  }

  public boolean isBottomAlive(int row, int col) {
    return isCellAlive(row + 1, col);
  }

  public boolean isBottomLeftAlive(int row, int col) {
    return isCellAlive(row + 1, col - 1);
  }

  public boolean isRightAlive(int row, int col) {
    return isCellAlive(row, col + 1);
  }

  public boolean isLeftAlive(int row, int col) {
    return isCellAlive(row, col - 1);
  }

  public boolean isTopRightAlive(int row, int col) {
    return isCellAlive(row - 1, col + 1);
  }

  public boolean isTopAlive(int row, int col) {
    return isCellAlive(row - 1, col);
  }

  public boolean isTopLeftAlive(int row, int col) {
    return isCellAlive(row - 1, col - 1);
  }

  private boolean isCellAlive(int row, int col) {
    return areValidCoordinates(row, col) && getCellSate(row, col) == CellState.ALIVE;
  }

  public int getAliveNeighbours(int row, int col) {
    int liveNeighbours = 0;

    if (isTopLeftAlive(row, col)) {
      liveNeighbours++;
    }

    if (isTopAlive(row, col)) {
      liveNeighbours++;
    }

    if (isTopRightAlive(row, col)) {
      liveNeighbours++;
    }

    if (isLeftAlive(row, col)) {
      liveNeighbours++;
    }

    if (isRightAlive(row, col)) {
      liveNeighbours++;
    }

    if (isBottomLeftAlive(row, col)) {
      liveNeighbours++;
    }

    if (isBottomAlive(row, col)) {
      liveNeighbours++;
    }

    if (isBottomRightAlive(row, col)) {
      liveNeighbours++;
    }
    return liveNeighbours;
  }

  @Override public Board clone() {
    Board newBoard = new Board(getRows(), getCols());
    for (int row = 0; row < getRows(); row++) {
      for (int col = 0; col < getCols(); col++) {
        newBoard.setCellState(row, col, getCellSate(row, col));
      }
    }
    return newBoard;
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < getRows(); row++) {
      for (int col = 0; col < getCols(); col++) {
        sb.append(getCellSate(row, col));
        sb.append(col + 1 == getCols() ? "\n" : " ");
      }
    }
    return sb.toString().trim();
  }
}
