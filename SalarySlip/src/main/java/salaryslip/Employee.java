package salaryslip;

class Employee {
  private final EmployeeID employeeID;
  private final String name;
  private final double annualSalary;

  Employee(EmployeeID employeeID, String name, double annualSalary) {
    this.employeeID = employeeID;
    this.name = name;
    this.annualSalary = annualSalary;
  }

  EmployeeID getEmployeeID() {
    return employeeID;
  }

  String getName() {
    return name;
  }

  double getAnnualSalary() {
    return annualSalary;
  }
}
