package bankkata;

import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountShould {

  public static final List<Transaction> TRANSACTIONS =
      Collections.singletonList(new Transaction("22/10/15", 123));
  @Mock TransactionRepository repository;
  @Mock Clock clock;
  @Mock StatementPrinter statementPrinter;

  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
  }

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
