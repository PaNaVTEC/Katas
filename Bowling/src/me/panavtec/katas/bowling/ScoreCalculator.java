package me.panavtec.katas.bowling;

public class ScoreCalculator {

  private final static int MAX_STRIKE_COUNT = 3;
  private final static int STRIKE_SCORE = 10, SPARE_SCORE = 10;

  private final char[] rolls;

  public ScoreCalculator(char[] rolls) {
    this.rolls = rolls;
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
    return !isLastBall(index) && rolls[index + 1] == BowlingSymbols.SPARE.symbol();
  }

  private boolean isStrike(int index) {
    return rolls[index] == BowlingSymbols.STRIKE.symbol();
  }

  private boolean isLastBall(int index) {
    return index == rolls.length - 1;
  }

  private boolean isScoringRoll(int index) {
    return rolls[index] != BowlingSymbols.GUTTER.symbol() && rolls[index] != BowlingSymbols.SPARE.symbol();
  }

  private boolean isNumeric(int index) {
    return isScoringRoll(index) && rolls[index] != BowlingSymbols.SPARE.symbol() && !isStrike(index);
  }
}
