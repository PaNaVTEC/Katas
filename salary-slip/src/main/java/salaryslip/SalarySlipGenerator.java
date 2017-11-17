package salaryslip;

import static salaryslip.SalarySlipBuilder.aSalarySlip;

class SalarySlipGenerator {

  private final NationalInsuranceCalculation nationalInsuranceCalculation;
  private final GrossSalaryCalculation grossSalaryCalculation;
  private final TaxFreeAllowanceCalculation taxFreeAllowanceCalculation;
  private final TaxableIncomeCalculation taxableIncomeCalculation;
  private final TaxablePayableCalculation taxablePayableCalculation;

  SalarySlipGenerator(NationalInsuranceCalculation nationalInsuranceCalculation,
                      GrossSalaryCalculation grossSalaryCalculation,
                      TaxFreeAllowanceCalculation taxFreeAllowanceCalculation,
                      TaxableIncomeCalculation taxableIncomeCalculation,
                      TaxablePayableCalculation taxablePayableCalculation) {
    this.nationalInsuranceCalculation = nationalInsuranceCalculation;
    this.grossSalaryCalculation = grossSalaryCalculation;
    this.taxFreeAllowanceCalculation = taxFreeAllowanceCalculation;
    this.taxableIncomeCalculation = taxableIncomeCalculation;
    this.taxablePayableCalculation = taxablePayableCalculation;
  }

  SalarySlip generateFor(Employee employee) {
    AnnualAmount annualSalary = new AnnualAmount(employee.getAnnualSalary());
    MonthlyAmount nationalInsuranceContribution = nationalInsuranceCalculation.execute(annualSalary);
    MonthlyAmount grossSalary = grossSalaryCalculation.execute(annualSalary);
    MonthlyAmount taxFreeAllowance = taxFreeAllowanceCalculation.execute(annualSalary);
    MonthlyAmount taxableIncome = taxableIncomeCalculation.execute(annualSalary);
    MonthlyAmount taxablePayable = taxablePayableCalculation.execute(annualSalary);

    return aSalarySlip()
            .withEmployeeID(employee.getEmployeeID())
            .withName(employee.getName())
            .withGrossMonthlySalary(grossSalary.asFormattedAmount())
            .withNationalInsuranceMonthlyContribution(nationalInsuranceContribution.asFormattedAmount())
            .withTaxFreeMonthlyAllowance(taxFreeAllowance.asFormattedAmount())
            .withTaxableMonthlyIncome(taxableIncome.asFormattedAmount())
            .withTaxablePayable(taxablePayable.asFormattedAmount())
            .build();
  }
}
