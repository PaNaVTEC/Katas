package bankkata;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {

  public String today() {
    return new SimpleDateFormat("dd/MM/yyyy").format(todayDate());
  }

  protected Date todayDate() {
    return new Date();
  }
}
