package bankkata;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

  private final List<Transaction> transactions = new ArrayList<>();

  public void store(Transaction transaction) {
    transactions.add(transaction);
  }

  public List<Transaction> getAllTransactions() {
    return transactions;
  }
}
