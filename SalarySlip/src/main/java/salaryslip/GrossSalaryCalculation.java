package salaryslip;

class GrossSalaryCalculation {
  MonthlyAmount execute(AnnualAmount annualSalary) {
    double monthlyGrossSalary = annualSalary.getAmount() / 12;
    return new MonthlyAmount(monthlyGrossSalary);
  }
}
