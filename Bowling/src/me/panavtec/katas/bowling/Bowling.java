package me.panavtec.katas.bowling;

import java.util.Arrays;

public class Bowling {

  private char[] rolls = new char[21];
  private int currentIndex = 0;

  public Bowling() {
    Arrays.fill(rolls, '-');
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
    for (int index = 0; index < rolls.length; index++) {
      if (isScoringRoll(index)) {
        if (isStrike(index)) {
          finalScore += strike(index);
          if (isLastBall(index + 1)) index++;
        } else if (isSpare(index)) {
          finalScore += spare(index);
          index++;
          if (isLastBall(index + 1)) index++;
        } else {
          finalScore += numericRoll(index);
        }
      }
    }
    return finalScore;
  }

  private int spare(int index) {
    int finalScore = 10;
    if (isStrike(index + 2)) {
      finalScore += 10;
    } else if (isScoringRoll(index + 2)) {
      finalScore += numericRoll(index + 2);
    }
    return finalScore;
  }

  private int strike(int index) {
    int finalScore = 10;

    if (index + 2 < rolls.length - 1) {
      if (isSpare(index + 2)) {
        finalScore += 10;
      } else if (isScoringRoll(index + 2) && rolls[index + 2] != '/' && rolls[index + 2] != 'X') {
        finalScore += numericRoll(index + 2);
      }
    }

    if (index + 1 < rolls.length - 1 && isStrike(index + 2)) {
      finalScore += 10;
      if (index + 2 < rolls.length - 1 && isStrike(index + 4) && !isLastBall(index)) {
        finalScore += 10;
      } else if (index + 2 < rolls.length - 1 && rolls[index + 4] != '-') {
        finalScore += numericRoll(index + 4);
      }
    }

    return finalScore;
  }

  private int numericRoll(int index) {
    return Character.getNumericValue(rolls[index]);
  }

  private boolean isSpare(int index) {
    return !isLastBall(index) && rolls[index + 1] == '/';
  }

  private boolean isStrike(int index) {
    return rolls[index] == 'X';
  }

  private boolean isLastBall(int index) {
    return index == rolls.length - 1;
  }

  private boolean isScoringRoll(int index) {
    return rolls[index] != '-' &&  rolls[index] != '/';
  }
}
