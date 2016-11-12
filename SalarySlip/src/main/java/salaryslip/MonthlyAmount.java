package salaryslip;

import java.util.Locale;

class MonthlyAmount {
  private final double amount;

  MonthlyAmount(double amount) {
    this.amount = amount;
  }

  String asFormattedAmount() {
    return String.format(Locale.UK, "£%.2f", amount);
  }
}
