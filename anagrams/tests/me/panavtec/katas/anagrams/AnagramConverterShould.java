package me.panavtec.katas.anagrams;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class AnagramConverterShould {
  @Test public void give_2_combinations_for_2_letters_word() {
    AnagramConverter anagramConverter = new AnagramConverter();

    String result = anagramConverter.convert("ab");

    Assert.assertThat(result, is("ab ba"));
  }

  @Test public void give_6_combinations_for_3_letters_word() {
    AnagramConverter anagramConverter = new AnagramConverter();

    String result = anagramConverter.convert("abc");

    Assert.assertThat(result, is("abc acb bac bca cab cba"));
  }

  @Test public void give_24_combinations_for_4_letters_word() {
    AnagramConverter anagramConverter = new AnagramConverter();

    String result = anagramConverter.convert("bori");

    Assert.assertThat(result,
        is("bior biro boir bori brio broi ibor ibro iobr iorb irbo irob obir obri oibr oirb orbi orib rbio rboi ribo riob robi roib"));
  }
}
