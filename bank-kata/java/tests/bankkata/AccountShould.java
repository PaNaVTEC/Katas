package bankkata;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class AccountShould {

  public static final List<Transaction> TRANSACTIONS =
      Collections.singletonList(new Transaction("22/10/15", 123));
  @Mock TransactionRepository repository;
  @Mock Clock clock;
  @Mock StatementPrinter statementPrinter;

  @Test public void store_deposit_transaction() {
    Account account = new Account(repository, clock, statementPrinter);
    account.deposit(500);

    Transaction transaction = new Transaction(clock.today(), 500);
    verify(repository).store(transaction);
  }

  @Test public void store_withdraw_transaction() {
    Account account = new Account(repository, clock, statementPrinter);
    account.withdrawal(500);

    Transaction transaction = new Transaction(clock.today(), -500);
    verify(repository).store(transaction);
  }

  @Test public void print_statement_with_all_transactions() throws Exception {
    when(repository.getAllTransactions()).thenReturn(TRANSACTIONS);
    Account account = new Account(repository, clock, statementPrinter);

    account.printStatement();

    verify(statementPrinter).print(TRANSACTIONS);
  }
}
