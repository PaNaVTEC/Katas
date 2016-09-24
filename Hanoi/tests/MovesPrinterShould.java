import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class) public class MovesPrinterShould {
  @Mock Console console;

  @Test public void print_moves() {
    List<Move> moves = asList(
        new Move(1, 3),
        new Move(2, 1)
    );

    new MovesPrinter(console).print(moves);

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).print("Move 1 to pile 3");
    inOrder.verify(console).print("Move 2 to pile 1");
  }
}