package me.panavtec.katas.bowling;

import java.util.Arrays;

public class Bowling {

  private final static char[] rolls = new char[21];

  private int currentIndex = 0;

  public Bowling() {
    Arrays.fill(rolls, BowlingSymbols.GUTTER.symbol());
  }

  public void roll(char roll) {
    rolls[currentIndex++] = roll;
  }

  public void roll(char roll, char roll2) {
    roll(roll);
    roll(roll2);
  }

  public int calculateScore() {
    return new ScoreCalculator(rolls).calculateScore();
  }
}
