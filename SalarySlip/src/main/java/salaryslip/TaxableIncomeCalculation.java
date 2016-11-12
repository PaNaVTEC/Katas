package salaryslip;

class TaxableIncomeCalculation {
  static final double NATIONAL_INSURANCE_ANNUAL_THRESHOLD = 8060d;
  static final double TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD = 11000d;

  MonthlyAmount execute(AnnualAmount annualSalary) {
    if (annualSalary.getAmount() > TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD) {
      double taxableAnnualIncome = annualSalary.getAmount() - TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD;
      return new MonthlyAmount(taxableAnnualIncome / 12);
    } else {
      return new MonthlyAmount(0);
    }
  }
}
