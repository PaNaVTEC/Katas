package bankkata;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;

public class StatePrinterShould {
  @Mock Console console;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test public void should_print_all_transactions() throws Exception {
    List<Transaction> transactions =
        Arrays.asList(new Transaction("10/01/2012", 1000), new Transaction("13/01/2012", 2000),
            new Transaction("14/01/2012", -500));

    StatementPrinter statementPrinter = new StatementPrinter(console);
    statementPrinter.print(transactions);

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).print("date | amount | balance");
    inOrder.verify(console).print("14/01/2012 | -500 | 2500");
    inOrder.verify(console).print("13/01/2012 | 2000 | 3000");
    inOrder.verify(console).print("10/01/2012 | 1000 | 1000");
  }
}
