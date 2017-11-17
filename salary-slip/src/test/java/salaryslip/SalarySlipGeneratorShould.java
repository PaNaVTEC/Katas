package salaryslip;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static salaryslip.EmployeeBuilder.anEmployee;
import static salaryslip.SalarySlipBuilder.aSalarySlip;

public class SalarySlipGeneratorShould {

  private SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(
          new NationalInsuranceCalculation(),
          new GrossSalaryCalculation(),
          new TaxFreeAllowanceCalculation(),
          new TaxableIncomeCalculation(),
          new TaxablePayableCalculation()
  );

  @Test
  public void calculate_monthly_gross_salary() {
    Employee employee = anEmployee()
            .withAnnualSalary(5000d)
            .withName("John J Doe")
            .withEmployeeID(new EmployeeID("12345"))
            .build();
    SalarySlip calculatedPaySlip = salarySlipGenerator.generateFor(employee);

    assertThat(calculatedPaySlip, is(
            aSalarySlip()
                    .withEmployeeID(new EmployeeID("12345"))
                    .withName("John J Doe")
                    .withGrossMonthlySalary("£416.67")
                    .withNationalInsuranceMonthlyContribution("£0.00")
                    .withTaxFreeMonthlyAllowance("£416.67")
                    .withTaxableMonthlyIncome("£0.00")
                    .withTaxablePayable("£0.00")
                    .build()
            )
    );
  }

  @Test
  public void calculate_national_insurance_contribution_for_an_annual_salary_above_8060() {
    Employee employee = anEmployee()
            .withAnnualSalary(9060d)
            .withName("John J Doe")
            .withEmployeeID(new EmployeeID("12345"))
            .build();
    SalarySlip calculatedPaySlip = salarySlipGenerator.generateFor(employee);

    assertThat(calculatedPaySlip, is(
            aSalarySlip()
                    .withEmployeeID(new EmployeeID("12345"))
                    .withName("John J Doe")
                    .withGrossMonthlySalary("£755.00")
                    .withNationalInsuranceMonthlyContribution("£10.00")
                    .withTaxFreeMonthlyAllowance("£755.00")
                    .withTaxableMonthlyIncome("£0.00")
                    .withTaxablePayable("£0.00")
                    .build()
            )
    );
  }

  @Test
  public void calculate_tax_free_allowance_for_an_annual_salary_above_11000() {
    Employee employee = anEmployee()
            .withAnnualSalary(12000d)
            .withName("John J Doe")
            .withEmployeeID(new EmployeeID("12345"))
            .build();

    SalarySlip calculatedPaySlip = salarySlipGenerator.generateFor(employee);

    assertThat(calculatedPaySlip, is(
            aSalarySlip()
                    .withEmployeeID(new EmployeeID("12345"))
                    .withName("John J Doe")
                    .withGrossMonthlySalary("£1000.00")
                    .withNationalInsuranceMonthlyContribution("£39.40")
                    .withTaxFreeMonthlyAllowance("£916.67")
                    .withTaxableMonthlyIncome("£83.33")
                    .withTaxablePayable("£16.67")
                    .build()
            )
    );
  }

  @Test
  public void calculate_taxable_income_for_an_annual_salary_above_11000() {
    Employee employee = anEmployee()
            .withAnnualSalary(12000d)
            .withName("John J Doe")
            .withEmployeeID(new EmployeeID("12345"))
            .build();

    SalarySlip calculatedPaySlip = salarySlipGenerator.generateFor(employee);

    assertThat(calculatedPaySlip, is(
            aSalarySlip()
                    .withEmployeeID(new EmployeeID("12345"))
                    .withName("John J Doe")
                    .withGrossMonthlySalary("£1000.00")
                    .withNationalInsuranceMonthlyContribution("£39.40")
                    .withTaxFreeMonthlyAllowance("£916.67")
                    .withTaxableMonthlyIncome("£83.33")
                    .withTaxablePayable("£16.67")
                    .build()
            )
    );
  }

  @Test
  public void calculate_taxable_income_for_an_annual_salary_above_43000() {
    Employee employee = anEmployee()
            .withAnnualSalary(45000d)
            .withName("John J Doe")
            .withEmployeeID(new EmployeeID("12345"))
            .build();

    SalarySlip calculatedPaySlip = salarySlipGenerator.generateFor(employee);

    assertThat(calculatedPaySlip, is(
            aSalarySlip()
                    .withEmployeeID(new EmployeeID("12345"))
                    .withName("John J Doe")
                    .withGrossMonthlySalary("£3750.00")
                    .withNationalInsuranceMonthlyContribution("£369.40")
                    .withTaxFreeMonthlyAllowance("£916.67")
                    .withTaxableMonthlyIncome("£2833.33")
                    .withTaxablePayable("£566.67")
                    .build()
            )
    );
  }
}
