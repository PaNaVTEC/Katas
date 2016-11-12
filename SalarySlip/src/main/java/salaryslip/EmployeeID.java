package salaryslip;

import java.util.Objects;

class EmployeeID {
  private final String rawId;

  EmployeeID(String rawId) {
    this.rawId = rawId;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeID that = (EmployeeID) o;
    return Objects.equals(rawId, that.rawId);
  }

  @Override public int hashCode() {
    return Objects.hash(rawId);
  }

  @Override public String toString() {
    return "EmployeeID{" +
            "rawId='" + rawId + '\'' +
            '}';
  }
}
