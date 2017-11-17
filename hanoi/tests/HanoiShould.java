import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class HanoiShould {

  @Mock MovesPrinter movesPrinter;

  @Test public void print_one_movement_for_one_disk() {
    new Hanoi(1, movesPrinter).print();

    verify(movesPrinter).print(singletonList(new Move(1, 3)));
  }

  @Test public void print_three_movements_for_two_disks() {
    new Hanoi(2, movesPrinter).print();

    verify(movesPrinter).print(asList(new Move(1, 2), new Move(2, 3), new Move(1, 3)));
  }
}