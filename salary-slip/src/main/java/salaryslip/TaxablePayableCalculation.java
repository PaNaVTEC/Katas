package salaryslip;

class TaxablePayableCalculation {

  private static final double TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD = 11000d;

  MonthlyAmount execute(AnnualAmount annualSalary) {
    if (annualSalary.getAmount() > TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD) {
      double taxableAnnualPayable = ((annualSalary.getAmount() - TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD) * 20) / 100;
      return new MonthlyAmount(taxableAnnualPayable / 12);
    } else {
      return new MonthlyAmount(0);
    }
  }
}
