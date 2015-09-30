package me.panavtec.katas.bowling;

import org.junit.Assert;
import org.junit.Test;

public class BowlingTest {

  @Test public void gutterGame() {
    Bowling bowling = new Bowling();
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 0, bowling.calculateScore());
  }

  @Test public void allOnes() {
    Bowling bowling = new Bowling();
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    bowling.roll('1', '1');
    Assert.assertEquals("Unexpected score", 20, bowling.calculateScore());
  }

  @Test public void oneFirstThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    bowling.roll('1', '-');
    Assert.assertEquals("Unexpected score", 10, bowling.calculateScore());
  }

  @Test public void allNineThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    bowling.roll('9', '-');
    Assert.assertEquals("Unexpected score", 90, bowling.calculateScore());
  }

  @Test public void firstStrikeThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 10, bowling.calculateScore());
  }

  @Test public void twoStrikeThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 30, bowling.calculateScore());
  }

  @Test public void turkeyThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 60, bowling.calculateScore());
  }

  @Test public void perfectGame() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', 'X');
    bowling.roll('X');
    Assert.assertEquals("Unexpected score", 300, bowling.calculateScore());
  }

  @Test public void oneSpareThenNull() {
    Bowling bowling = new Bowling();
    bowling.roll('6', '/');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 10, bowling.calculateScore());
  }

  @Test public void allSparesOfFive() {
    Bowling bowling = new Bowling();
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5', '/');
    bowling.roll('5');
    Assert.assertEquals("Unexpected score", 150, bowling.calculateScore());
  }

  @Test public void strikeAndFollowingSpare() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('5', '/');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 30, bowling.calculateScore());
  }

  @Test public void strikeFollowNumericScore() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('5', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 20, bowling.calculateScore());
  }

  @Test public void twoStrikesFollowNumericScore() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('5', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 45, bowling.calculateScore());
  }

  @Test public void spareAndStrike() {
    Bowling bowling = new Bowling();
    bowling.roll('5', '/');
    bowling.roll('X', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 30, bowling.calculateScore());
  }

  @Test public void spareAndNumeric() {
    Bowling bowling = new Bowling();
    bowling.roll('5', '/');
    bowling.roll('5', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 20, bowling.calculateScore());
  }

  @Test public void spareAndTwoNumericsSameRoll() {
    Bowling bowling = new Bowling();
    bowling.roll('5', '/');
    bowling.roll('5', '4');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    bowling.roll('-', '-');
    Assert.assertEquals("Unexpected score", 24, bowling.calculateScore());
  }

  @Test public void acceptanceSampleGame() {
    Bowling bowling = new Bowling();
    bowling.roll('8', '/');
    bowling.roll('5', '4');
    bowling.roll('9', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('5', '/');
    bowling.roll('5', '3');
    bowling.roll('6', '3');
    bowling.roll('9', '/');
    bowling.roll('9', '/');
    bowling.roll('X');
    Assert.assertEquals("Unexpected score", 149, bowling.calculateScore());
  }

  @Test public void acceptanceSampleGame2() {
    Bowling bowling = new Bowling();
    bowling.roll('X', '-');
    bowling.roll('9', '/');
    bowling.roll('5', '/');
    bowling.roll('7', '2');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('X', '-');
    bowling.roll('9', '-');
    bowling.roll('8', '/');
    bowling.roll('9', '/');
    bowling.roll('X');
    Assert.assertEquals("Unexpected score", 187, bowling.calculateScore());
  }
}
