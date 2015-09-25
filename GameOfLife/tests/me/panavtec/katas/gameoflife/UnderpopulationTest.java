package me.panavtec.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 */
public class UnderpopulationTest {

  /**
   * 0 0 0
   * 0 1 1
   * 0 0 0
   */
  @Test public void centerAliveCellWithRightLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 1 1 0
   * 0 0 0
   */
  @Test public void centerAliveCellWithLeftLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 1 0 0
   * 1 0 0
   * 0 0 0
   */
  @Test public void topLeftAliveCellBottomLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 1 0 0
   * 0 1 0
   * 0 0 0
   */
  @Test public void topLeftAliveCellDiagonalLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 1 1 0
   * 0 0 0
   * 0 0 0
   */
  @Test public void topLeftAliveCellRightLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 1 1
   * 0 0 0
   * 0 0 0
   */
  @Test public void topRightAliveCellLeftLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 1
   * 0 0 1
   * 0 0 0
   */
  @Test public void topRightAliveCellBottomLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 1
   * 0 1 0
   * 0 0 0
   */
  @Test public void topRightAliveCellDiagonalLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 1 0 0
   * 1 0 0
   */
  @Test public void bottomLeftAliveCellTopLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 0 0 0
   * 1 1 0
   */
  @Test public void bottomLeftAliveCellRightLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 0 1 0
   * 1 0 0
   */
  @Test public void bottomLeftAliveCellDiagonalLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 0 0 0
   * 0 1 1
   */
  @Test public void bottomRightAliveCellLeftLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 0 0 1
   * 0 0 1
   */
  @Test public void bottomRightAliveCellTopLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 2, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }

  /**
   * 0 0 0
   * 0 1 0
   * 0 0 1
   */
  @Test public void bottomRightAliveCellDiagonalLive_shouldDie() {
    Board board = new Board(3, 3);
    board.setCellState(2, 2, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should die", CellState.DEAD, boardStageOne.getCellSate(1, 1));
  }
}
