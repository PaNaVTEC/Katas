package me.panavtec.katas.gameoflife;

public class Game {

  public static void main(String[] args) {
    Board board = new Board(50, 50);

    createGlider(board);

    System.out.println("Initial stage");
    System.out.println(board.toString());

    for (int j = 0; j < 50; j++) {
      Stage stage = new Stage(board);
      board = stage.nextStage();
      System.out.println("Stage " + (j + 1));
      System.out.println(board.toString());
    }
  }

  private static void createGlider(Board board) {
    board.setCellState(1, 3, CellState.ALIVE);
    board.setCellState(2, 4, CellState.ALIVE);
    board.setCellState(3, 2, CellState.ALIVE);
    board.setCellState(3, 3, CellState.ALIVE);
    board.setCellState(3, 4, CellState.ALIVE);
  }
}
