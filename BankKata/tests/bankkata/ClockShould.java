package bankkata;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClockShould {

  @Test public void return_today_representation() throws Exception {
    Clock clock = new TestableClock();

    String today = clock.today();

    assertEquals("26/10/2015", today);
  }

  public static class TestableClock extends Clock {
    @Override protected Date todayDate() {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DATE, 26);
      calendar.set(Calendar.MONTH, 9);
      calendar.set(Calendar.YEAR, 2015);
      return calendar.getTime();
    }
  }
}