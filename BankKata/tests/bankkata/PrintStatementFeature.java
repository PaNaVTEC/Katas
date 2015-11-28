package bankkata;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 Given a client makes a deposit of 1000 on 10-01-2012
 And a deposit of 2000 on 13-01-2012
 And a withdrawal of 500 on 14-01-2012
 When she prints her bank statement
 Then she would see
 date       || credit  || debit  || balance
 14/01/2012 ||         || 500.00 || 2500.00
 13/01/2012 || 2000.00 ||        || 3000.00
 10/01/2012 || 1000.00 ||        || 1000.00
 */
public class PrintStatementFeature {
  @Test public void print_statement_containing_all_transactions() {
    TransactionRepository repository = new TransactionRepository();
    Clock clock = Mockito.mock(Clock.class);
    when(clock.today()).thenReturn("10/01/2012", "13/01/2012", "14/01/2012");
    Console console = mock(Console.class);
    StatementPrinter statementPrinter = new StatementPrinter(console);
    Account account = new Account(repository, clock, statementPrinter);
    account.deposit(1000);
    account.deposit(2000);
    account.withdrawal(500);
    account.printStatement();

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).print("date | amount | balance");
    inOrder.verify(console).print("14/01/2012 | -500 | 2500");
    inOrder.verify(console).print("13/01/2012 | 2000 | 3000");
    inOrder.verify(console).print("10/01/2012 | 1000 | 1000");
  }
}
