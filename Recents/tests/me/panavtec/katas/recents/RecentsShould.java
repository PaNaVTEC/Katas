package me.panavtec.katas.recents;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecentsShould {

  @Test public void empty_when_created() {
    Recents recents = new Recents();

    assertThat(recents.length(), is(0));
  }

  @Test public void enqueue_item_to_first_position_when_added() {
    Recents recents = new Recents();

    recents.put("1st");
    recents.put("2nd");
    recents.put("3rd");

    assertThat(recents.current(), is("3rd"));
    assertThat(recents.current(), is("2nd"));
    assertThat(recents.current(), is("1st"));
  }

  @Test public void access_elements_by_index() {
    Recents recents = new Recents();

    recents.put("1st");
    recents.put("2nd");
    recents.put("3rd");

    assertThat(recents.elementAtIndex(0), is("3rd"));
    assertThat(recents.elementAtIndex(1), is("2nd"));
    assertThat(recents.elementAtIndex(2), is("1st"));
  }

  @Test public void duplicates_moved_to_first() {
    Recents recents = new Recents();

    recents.put("1st");
    recents.put("2nd");
    recents.put("3rd");
    recents.put("1st");

    assertThat(recents.length(), is(3));
    assertThat(recents.elementAtIndex(0), is("1st"));
    assertThat(recents.elementAtIndex(1), is("3rd"));
    assertThat(recents.elementAtIndex(2), is("2nd"));
  }

  @Test(expected = IllegalArgumentException.class) public void throw_exception_when_null_push() {
    Recents recents = new Recents();

    recents.put(null);
  }

  @Test public void discard_old_items_when_out_of_capacity() {
    Recents recents = new Recents(2);

    recents.put("1st");
    recents.put("2nd");
    recents.put("3rd");

    assertThat(recents.length(), is(2));
    assertThat(recents.elementAtIndex(0), is("3rd"));
    assertThat(recents.elementAtIndex(1), is("2nd"));
  }

}
