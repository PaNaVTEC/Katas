package me.panavtec.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

  @Test public void givenANumberOfRowsAndCols_boardShouldBeCreated() {
    Board board = new Board(4, 8);
    Assert.assertEquals("Board size", 32, board.getTotalSize());
    Assert.assertEquals("Rows", 4, board.getRows());
    Assert.assertEquals("Columns", 8, board.getCols());
  }

  @Test public void putACellInState_shouldPutThisCellInState() {
    Board board = new Board(4, 8);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 2, CellState.DEAD);
    Assert.assertEquals("Cell State", board.getCellSate(0, 0), CellState.ALIVE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void putACellInStateOnIncorrectRow_shouldThrowIllegalArgumentException() {
    Board board = new Board(4, 8);
    board.setCellState(-1, 0, CellState.ALIVE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void putACellInStateOnIncorrectCol_shouldThrowIllegalArgumentException() {
    Board board = new Board(4, 8);
    board.setCellState(0, 9, CellState.ALIVE);
  }

  @Test public void generateGame_shouldHaveAllDead() {
    Board board = new Board(4, 8);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        Assert.assertEquals("Cell is not dead", CellState.DEAD, board.getCellSate(i, j));
      }
    }
  }

  @Test public void invalidCoordinates_shouldReturnFalse() {
    Board board = new Board(3, 3);
    Assert.assertEquals("Top left invalid", false, board.areValidCoordinates(-1, -1));
    Assert.assertEquals("Top invalid", false, board.areValidCoordinates(-1, 0));
    Assert.assertEquals("Top right invalid", false, board.areValidCoordinates(-1, 3));
    Assert.assertEquals("Right invalid", false, board.areValidCoordinates(0, 3));
    Assert.assertEquals("Left invalid", false, board.areValidCoordinates(0, -1));
    Assert.assertEquals("Bottom Left invalid", false, board.areValidCoordinates(3, -1));
    Assert.assertEquals("Bottom invalid", false, board.areValidCoordinates(3, -1));
    Assert.assertEquals("Center ok", true, board.areValidCoordinates(1, 1));
    Assert.assertEquals("Bottom Left ok", true, board.areValidCoordinates(2, 0));
    Assert.assertEquals("Bottom Right ok", true, board.areValidCoordinates(2, 2));
    Assert.assertEquals("Top Right ok", true, board.areValidCoordinates(0, 2));
    Assert.assertEquals("Top Left ok", true, board.areValidCoordinates(0, 0));
  }

  @Test public void printEmptyBoard() {
    Board board = new Board(3, 3);
    Assert.assertEquals("Expected another board", "0 0 0\n0 0 0\n0 0 0", board.toString());
  }

  @Test public void printCornersBoard() {
    Board board = new Board(3, 3);
    board.setCellState(0, 0, CellState.ALIVE);
    board.setCellState(0, 2, CellState.ALIVE);
    board.setCellState(2, 0, CellState.ALIVE);
    board.setCellState(2, 2, CellState.ALIVE);
    Assert.assertEquals("Expected another board", "1 0 1\n0 0 0\n1 0 1", board.toString());
  }

  @Test public void printCrossBoard() {
    Board board = new Board(3, 3);
    board.setCellState(0, 1, CellState.ALIVE);
    board.setCellState(1, 1, CellState.ALIVE);
    board.setCellState(1, 0, CellState.ALIVE);
    board.setCellState(1, 2, CellState.ALIVE);
    board.setCellState(2, 1, CellState.ALIVE);
    Assert.assertEquals("Expected another board", "0 1 0\n1 1 1\n0 1 0", board.toString());
  }
}
