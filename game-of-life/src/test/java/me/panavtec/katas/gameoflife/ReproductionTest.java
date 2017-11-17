package me.panavtec.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */
public class ReproductionTest {

  /**
   * 0 0 0
   * 0 0 1
   * 0 1 1
   */
  @Test public void bottomRightLiveNeighbours_shouldReproduceInCenter() {
    Board board = new Board(3, 3);
    board.setCellState(1, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    board.setCellState(2, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should born", CellState.ALIVE, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 1 0 0
   * 1 1 0
   */
  @Test public void bottomLeftLiveNeighbours_shouldReproduceInCenter() {
    Board board = new Board(3, 3);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should born", CellState.ALIVE, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 1 1 0
   * 1 0 0
   * 0 0 0
   */
  @Test public void topLeftLiveNeighbours_shouldReproduceInCenter() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should born", CellState.ALIVE, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 1 1
   * 0 0 1
   * 0 0 0
   */
  @Test public void topRightLiveNeighbours_shouldReproduceInCenter() {
    Board board = new Board(3, 3);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should born", CellState.ALIVE, boardStageOne.getCellSate(1, 1));
  }
}