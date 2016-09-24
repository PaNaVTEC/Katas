import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class) public class HanoiFeature {

  @Mock Console console;

  @Test public void move_disks_to_the_last_pile() {
    new Hanoi(3, new MovesPrinter(console)).print();

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).print("Move 1 to pile 3");
    inOrder.verify(console).print("Move 2 to pile 2");
    inOrder.verify(console).print("Move 1 to pile 2");
    inOrder.verify(console).print("Move 3 to pile 3");
    inOrder.verify(console).print("Move 1 to pile 1");
    inOrder.verify(console).print("Move 2 to pile 3");
    inOrder.verify(console).print("Move 1 to pile 3");
  }
}
