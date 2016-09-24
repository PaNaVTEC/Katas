import java.util.List;

public class MovesPrinter {

  private final Console console;

  public MovesPrinter(Console console) {
    this.console = console;
  }

  public void print(List<Move> moves) {
    moves.stream().forEach(move -> console.print(format(move)));
  }

  private String format(Move move) {
    return String.format("Move %d to pile %d", move.getDiskNumber(), move.getDestPile());
  }
}
