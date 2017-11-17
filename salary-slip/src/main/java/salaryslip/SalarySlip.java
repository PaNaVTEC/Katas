package salaryslip;

import java.util.Objects;

class SalarySlip {
  private final EmployeeID employeeID;
  private final String name;
  private final String grossMonthlySalary;
  private final String nationalInsuranceMonthlyContribution;
  private final String taxFreeMonthlyAllowance;
  private final String taxableMonthlyIncome;
  private final String taxablePayable;

  SalarySlip(EmployeeID employeeID,
             String name,
             String grossMonthlySalary,
             String nationalInsuranceMonthlyContribution,
             String taxFreeMonthlyAllowance,
             String taxableMonthlyIncome,
             String taxablePayable) {
    this.employeeID = employeeID;
    this.name = name;
    this.grossMonthlySalary = grossMonthlySalary;
    this.nationalInsuranceMonthlyContribution = nationalInsuranceMonthlyContribution;
    this.taxFreeMonthlyAllowance = taxFreeMonthlyAllowance;
    this.taxableMonthlyIncome = taxableMonthlyIncome;
    this.taxablePayable = taxablePayable;
  }

  @Override public String toString() {
    return "SalarySlip{" +
            "employeeID=" + employeeID +
            ", name='" + name + '\'' +
            ", grossMonthlySalary='" + grossMonthlySalary + '\'' +
            ", nationalInsuranceMonthlyContribution='" + nationalInsuranceMonthlyContribution + '\'' +
            ", taxFreeMonthlyAllowance='" + taxFreeMonthlyAllowance + '\'' +
            ", taxableMonthlyIncome='" + taxableMonthlyIncome + '\'' +
            ", taxablePayable='" + taxablePayable + '\'' +
            '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SalarySlip that = (SalarySlip) o;
    return Objects.equals(employeeID, that.employeeID) &&
            Objects.equals(name, that.name) &&
            Objects.equals(grossMonthlySalary, that.grossMonthlySalary) &&
            Objects.equals(nationalInsuranceMonthlyContribution, that.nationalInsuranceMonthlyContribution) &&
            Objects.equals(taxFreeMonthlyAllowance, that.taxFreeMonthlyAllowance) &&
            Objects.equals(taxableMonthlyIncome, that.taxableMonthlyIncome) &&
            Objects.equals(taxablePayable, that.taxablePayable);
  }

  @Override public int hashCode() {
    return Objects.hash(employeeID, name, grossMonthlySalary, nationalInsuranceMonthlyContribution, taxFreeMonthlyAllowance, taxableMonthlyIncome, taxablePayable);
  }


}
