package me.panavtec.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Any live cell with more than three live neighbours dies, as if by overcrowding.
 */
public class OvercrowdingTest {

  /**
   * 0 1 0
   * 1 1 1
   * 0 1 0
   */
  @Test public void centerAliveWithCross_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 1 0 1
   * 0 1 0
   * 1 0 1
   */
  @Test public void centerAliveWithDiagonals_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(2, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }
}
