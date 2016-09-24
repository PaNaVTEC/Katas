import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hanoi {
  private final int numDisks;
  private final MovesPrinter movesPrinter;

  public Hanoi(int numDisks, MovesPrinter movesPrinter) {
    this.numDisks = numDisks;
    this.movesPrinter = movesPrinter;
  }

  public void print() {
    movesPrinter.print(resolve());
  }

  private List<Move> resolve() {
    Stack<Integer>[] towers = new Stack[3];
    for (int i = 0; i < towers.length; i++) {
      towers[i] = new Stack();
    }
    for (int disk = numDisks; disk >= 1; disk--) {
      towers[0].push(disk);
    }
    return resolve2(0, towers, new ArrayList<>());
  }

  private List<Move> resolve2(int numTower, Stack<Integer>[] towers, List<Move> moves) {
    if (towers[2].size() == numDisks) {
      return moves;
    }

    int nextIndex = nextIndexOfHead(numTower, towers[numTower].size());
    if (towers[numTower].empty() || !canMove(towers, numTower, nextIndex)) {
      return resolve2(correctIndex(numTower - 1), towers, moves);
    } else {
      Integer disk = towers[numTower].pop();
      towers[nextIndex].push(disk);
      Move move = new Move(disk, nextIndex + 1);
      moves.add(move);
      return resolve2(correctIndex(nextIndex - 1), towers, moves);
    }
  }

  private int correctIndex(int index) {
    if (index < 0) {
      return 3 + index;
    } else if (index > 2) {
      return index - 3;
    } else {
      return index;
    }
  }

  private boolean canMove(Stack<Integer>[] towers, int currentIndex, int nextIndex) {
    return towers[nextIndex].empty() || towers[currentIndex].peek() < towers[nextIndex].peek();
  }

  private int nextIndexOfHead(int numTower, int numElem) {
    int nextIndex = 1 + numElem % 2;
    if (numTower % 2 == 0) {
      return correctIndex(numTower + nextIndex);
    } else {
      return correctIndex(numTower - nextIndex);
    }
  }
}
