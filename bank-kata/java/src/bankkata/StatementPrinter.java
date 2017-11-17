package bankkata;

import java.util.List;

public class StatementPrinter {
  private final Console console;

  public StatementPrinter(Console console) {
    this.console = console;
  }

  public void print(List<Transaction> transactions) {
    printHeader();
    printStatements(transactions);
  }

  private void printHeader() {
    console.print("date | amount | balance");
  }

  private void printStatements(List<Transaction> transactions) {
    int currentBalance = calculateBalance(transactions);
    for (int i = transactions.size() - 1; i >= 0; i--) {
      Transaction transaction = transactions.get(i);
      console.print(String.format("%s | %d | %d", transaction.getDate(), transaction.getAmount(),
          currentBalance));
      currentBalance -= transaction.getAmount();
    }
  }

  private int calculateBalance(List<Transaction> transactions) {
    int amount = 0;
    for (Transaction transaction : transactions) {
      amount += transaction.getAmount();
    }
    return amount;
  }
}
