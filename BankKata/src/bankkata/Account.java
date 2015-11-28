package bankkata;

public class Account {

  private final TransactionRepository repository;
  private final Clock clock;
  private final StatementPrinter statementPrinter;

  public Account(TransactionRepository repository, Clock clock, StatementPrinter statementPrinter) {
    this.repository = repository;
    this.clock = clock;
    this.statementPrinter = statementPrinter;
  }

  public void deposit(int amount) {
    repository.store(new Transaction(clock.today(), amount));
  }

  public void withdrawal(int amount) {
    repository.store(new Transaction(clock.today(), -amount));
  }

  public void printStatement() {
    statementPrinter.print(repository.getAllTransactions());
  }
}
