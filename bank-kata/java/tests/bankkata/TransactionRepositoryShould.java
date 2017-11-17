package bankkata;

import java.util.List;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TransactionRepositoryShould {

  @Test public void store_a_transaction() throws Exception {
    Transaction transaction = new Transaction(null, 0);
    TransactionRepository transactionRepository = new TransactionRepository();

    transactionRepository.store(transaction);

    List<Transaction> allTransactions = transactionRepository.getAllTransactions();
    assertEquals(allTransactions.size(), 1);
    assertEquals(allTransactions.get(0), transaction);
  }
}
