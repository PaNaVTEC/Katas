package me.panavtec.katas.gameoflife;

public class Stage {
  private Board board;

  public Stage(Board board) {
    this.board = board;
  }

  public Board nextStage() {
    Board nextStageBoard = board.clone();
    for (int row = 0; row < board.getRows(); row++) {
      for (int col = 0; col < board.getCols(); col++) {
        int liveNeighbours = board.getAliveNeighbours(row, col);
        int currentCell = board.getCellSate(row, col);

        if (currentCell == CellState.ALIVE) {
          if (liveNeighbours < 2) {
            nextStageBoard.setCellState(row, col, CellState.DEAD);
          } else if (liveNeighbours > 3) {
            nextStageBoard.setCellState(row, col, CellState.DEAD);
          }
        } else {
          if (liveNeighbours == 3) {
            nextStageBoard.setCellState(row, col, CellState.ALIVE);
          }
        }
      }
    }

    return nextStageBoard;
  }
}
