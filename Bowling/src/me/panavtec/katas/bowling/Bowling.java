package me.panavtec.katas.bowling;

import java.util.Arrays;

public class Bowling {

  private final static char SPARE = '/';
  private final static char STRIKE = 'X';
  private final static char GUTTER = '-';
  private final static int MAX_STRIKE_COUNT = 3;
  private final static int STRIKE_SCORE = 10, SPARE_SCORE = 10;
  private final static char[] rolls = new char[21];

  private int currentIndex = 0;

  public Bowling() {
    Arrays.fill(rolls, GUTTER);
  }

  public void roll(char roll) {
    rolls[currentIndex++] = roll;
  }

  public void roll(char roll, char roll2) {
    roll(roll);
    roll(roll2);
  }

  public int calculateScore() {
    int finalScore = 0;
    for (int index = 0; index < rolls.length - 1; index++) {
      if (isScoringRoll(index)) {
        if (isStrike(index)) {
          finalScore += strike(index);
        } else if (isSpare(index)) {
          finalScore += spare(index);
        } else if (isNumeric(index)) {
          finalScore += numericValue(index);
        }
      }
    }
    return finalScore;
  }

  private int spare(int index) {
    int finalScore = SPARE_SCORE;
    int nextFrame = index + 2;
    if (isStrike(nextFrame)) {
      finalScore += STRIKE_SCORE;
    } else if (isScoringRoll(nextFrame)) {
      finalScore += numericValue(nextFrame);
    }
    return finalScore;
  }

  private int strike(int index) {
    return strike(index, 1);
  }

  private int strike(int index, int strikeCount) {
    int finalScore = STRIKE_SCORE;

    int nextFrame = index + 2;
    if (nextFrame < rolls.length) {
      if (isStrike(nextFrame)) {
        finalScore += strikeCount < MAX_STRIKE_COUNT ? strike(nextFrame, ++strikeCount) : 0;
      } else if (strikeCount < MAX_STRIKE_COUNT) {
        if (strikeCount < 2 && isSpare(nextFrame)) {
          finalScore += 10;
        } else if (isNumeric(nextFrame)) {
          finalScore += numericValue(nextFrame);
        }
      }
    }

    return finalScore;
  }

  private int numericValue(int index) {
    return Character.getNumericValue(rolls[index]);
  }

  private boolean isSpare(int index) {
    return !isLastBall(index) && rolls[index + 1] == SPARE;
  }

  private boolean isStrike(int index) {
    return rolls[index] == STRIKE;
  }

  private boolean isLastBall(int index) {
    return index == rolls.length - 1;
  }

  private boolean isScoringRoll(int index) {
    return rolls[index] != GUTTER && rolls[index] != SPARE;
  }

  private boolean isNumeric(int index) {
    return isScoringRoll(index) && rolls[index] != SPARE && !isStrike(index);
  }
}
