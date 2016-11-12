package salaryslip;

public final class EmployeeBuilder {
  private EmployeeID employeeID;
  private String name;
  private double annualSalary;

  private EmployeeBuilder() {
  }

  public static EmployeeBuilder anEmployee() {
    return new EmployeeBuilder();
  }

  public EmployeeBuilder withEmployeeID(EmployeeID employeeID) {
    this.employeeID = employeeID;
    return this;
  }

  public EmployeeBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public EmployeeBuilder withAnnualSalary(double annualSalary) {
    this.annualSalary = annualSalary;
    return this;
  }

  public Employee build() {
    Employee employee = new Employee(employeeID, name, annualSalary);
    return employee;
  }
}
