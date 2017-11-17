package me.panavtec.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

public class NextGenerationTest {

  /**
   * 1 1 0
   * 1 0 0
   * 0 0 0
   */
  @Test public void topLeftAliveDiagonalDie_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should be alive", CellState.ALIVE, boardStageOne.getCellSate(0, 0));
  }

  /**
   * 1 1 0
   * 1 1 0
   * 0 0 0
   */
  @Test public void topLeftAliveCellAllNeighboursLive_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell be alive", CellState.ALIVE, boardStageOne.getCellSate(0, 0));
  }

  /**
   * 0 1 1
   * 0 0 1
   * 0 0 0
   */
  @Test public void topRightAliveDiagonalDie_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should be alive", CellState.ALIVE, boardStageOne.getCellSate(0, 2));
  }

  /**
   * 0 1 1
   * 0 1 1
   * 0 0 0
   */
  @Test public void topRightAliveCellAllNeighboursLive_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell be alive", CellState.ALIVE, boardStageOne.getCellSate(0, 2));
  }

  /**
   * 0 0 0
   * 1 0 0
   * 1 1 0
   */
  @Test public void bottomLeftAliveDiagonalDie_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should be alive", CellState.ALIVE, boardStageOne.getCellSate(2, 0));
  }

  /**
   * 0 0 0
   * 1 1 0
   * 1 1 0
   */
  @Test public void bottomLeftAliveCellAllNeighboursLive_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell be alive", CellState.ALIVE, boardStageOne.getCellSate(2, 0));
  }

  /**
   * 0 0 0
   * 0 0 1
   * 0 1 1
   */
  @Test public void bottomRightAliveDiagonalDie_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(2, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should be alive", CellState.ALIVE, boardStageOne.getCellSate(2, 2));
  }

  /**
   * 0 0 0
   * 0 1 1
   * 0 1 1
   */
  @Test public void bottomRightAliveCellAllNeighboursLive_shouldSurvive() {
    Board board = new Board(3, 3);
    board.setCellState(2, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    Stage stage = new Stage(board);
    Board boardStageOne = stage.nextStage();
    Assert.assertEquals("Cell should be alive", CellState.ALIVE, boardStageOne.getCellSate(2, 2));
  }
}
