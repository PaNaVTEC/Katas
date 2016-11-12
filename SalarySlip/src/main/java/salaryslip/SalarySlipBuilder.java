package salaryslip;

final class SalarySlipBuilder {
  private EmployeeID employeeID;
  private String name;
  private String grossMonthlySalary;
  private String nationalInsuranceMonthlyContribution;
  private String taxFreeMonthlyAllowance;
  private String taxableMonthlyIncome;
  private String taxablePayable;

  private SalarySlipBuilder() {
  }

  public static SalarySlipBuilder aSalarySlip() {
    return new SalarySlipBuilder();
  }

  public SalarySlipBuilder withEmployeeID(EmployeeID employeeID) {
    this.employeeID = employeeID;
    return this;
  }

  public SalarySlipBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public SalarySlipBuilder withGrossMonthlySalary(String grossMonthlySalary) {
    this.grossMonthlySalary = grossMonthlySalary;
    return this;
  }

  public SalarySlipBuilder withNationalInsuranceMonthlyContribution(String nationalInsuranceMonthlyContribution) {
    this.nationalInsuranceMonthlyContribution = nationalInsuranceMonthlyContribution;
    return this;
  }

  public SalarySlipBuilder withTaxFreeMonthlyAllowance(String taxFreeMonthlyAllowance) {
    this.taxFreeMonthlyAllowance = taxFreeMonthlyAllowance;
    return this;
  }

  public SalarySlipBuilder withTaxableMonthlyIncome(String taxableMonthlyIncome) {
    this.taxableMonthlyIncome = taxableMonthlyIncome;
    return this;
  }

  public SalarySlipBuilder withTaxablePayable(String taxablePayable) {
    this.taxablePayable = taxablePayable;
    return this;
  }

  public SalarySlip build() {
    return new SalarySlip(employeeID, name, grossMonthlySalary, nationalInsuranceMonthlyContribution, taxFreeMonthlyAllowance, taxableMonthlyIncome, taxablePayable);
  }
}
