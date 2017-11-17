package salaryslip;

class TaxFreeAllowanceCalculation {
  MonthlyAmount execute(AnnualAmount annualSalary) {
    if (annualSalary.getAmount() > TaxableIncomeCalculation.TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD) {
      return new MonthlyAmount(TaxableIncomeCalculation.TAX_FREE_ANNUAL_ALLOWANCE_THRESHOLD / 12);
    } else {
      return new MonthlyAmount(annualSalary.getAmount() / 12);
    }
  }
}
