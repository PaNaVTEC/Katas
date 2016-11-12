package salaryslip;

class NationalInsuranceCalculation {

  MonthlyAmount execute(AnnualAmount annualSalary) {
    double nationalInsuranceMonthlyContribution = 0.0d;
    if (annualSalary.getAmount() > TaxableIncomeCalculation.NATIONAL_INSURANCE_ANNUAL_THRESHOLD) {
      double excess = annualSalary.getAmount() - TaxableIncomeCalculation.NATIONAL_INSURANCE_ANNUAL_THRESHOLD;
      double nationalInsuranceAnnualContribution = excess * 12 / 100;
      nationalInsuranceMonthlyContribution = nationalInsuranceAnnualContribution / 12;
    }

    return new MonthlyAmount(nationalInsuranceMonthlyContribution);
  }
}
