package com.novoda.calisthenics.tictactoe;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

public class GameBoardTest {

  @Test public void showTheBoardOnADisplay() {
    GameBoard board = new GameBoard();
    Console console = Mockito.mock(Console.class);
    board.display(new ConsoleBoardDisplay(console));
    Mockito.verify(console, times(3)).print("| | | |");
  }

  @Test public void player1_updates_cell_to_0() {
    GameBoard board = new GameBoard();
    Console console = Mockito.mock(Console.class);
    board.updatePlayer1Cell(0, 0);
    board.display(new ConsoleBoardDisplay(console));
    Mockito.verify(console).print("|X| | |");
    Mockito.verify(console).print("| | | |");
    Mockito.verify(console).print("| | | |");
  }
}
