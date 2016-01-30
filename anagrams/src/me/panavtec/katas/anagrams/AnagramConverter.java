package me.panavtec.katas.anagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnagramConverter {

  private static final String SPACE = " ";

  public String convert(String word) {
    List<String> combinations = combinationsOf(word, "");
    Collections.sort(combinations);
    return stringFromCombinations(combinations);
  }

  private List<String> combinationsOf(String word, String prefix) {
    List<String> combinations = new ArrayList<>();
    if (word.length() == 2) {
      combinations.add(prefix + word);
      combinations.add(prefix + switchLastLetters(word));
    } else {
      char[] letters = word.toCharArray();
      for (int i = 0; i < word.length(); i++) {
        String currentLetter = prefix + letters[i];
        String wordWithoutLetterIndex = removeCharacterAtPosition(i, word);
        combinations.addAll(combinationsOf(wordWithoutLetterIndex, currentLetter));
      }
    }
    return combinations;
  }

  private String removeCharacterAtPosition(int i, String word) {
    return word.length() > 1 ? (word.substring(0, i) + word.substring(i + 1, word.length())) : word;
  }

  private String switchLastLetters(String word) {
    char lastLetter = word.charAt(word.length() - 1);
    char preLastLetter = word.charAt(word.length() - 2);
    return new String(new char[] { lastLetter, preLastLetter });
  }

  private String stringFromCombinations(List<String> combinations) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String combination : combinations) {
      stringBuilder.append(combination).append(SPACE);
    }
    return stringBuilder.toString().trim();
  }
}
