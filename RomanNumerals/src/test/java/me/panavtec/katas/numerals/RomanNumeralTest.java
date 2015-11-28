package me.panavtec.katas.numerals;

import org.junit.Assert;
import org.junit.Test;

public class RomanNumeralTest {
  @Test public void convert1_shouldGiveI() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "I", converter.convert(1));
  }

  @Test public void convert2_shouldGiveII() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "II", converter.convert(2));
  }

  @Test public void convert3_shouldGiveIII() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "III", converter.convert(3));
  }

  @Test public void convert4_shouldGiveIV() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "IV", converter.convert(4));
  }

  @Test public void convert5_shouldGiveV() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "V", converter.convert(5));
  }

  @Test public void convert6_shouldGiveVI() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "VI", converter.convert(6));
  }

  @Test public void convert8_shouldGiveVIII() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "VIII", converter.convert(8));
  }

  @Test public void convert9_shouldGiveIX() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "IX", converter.convert(9));
  }

  @Test public void convert40_shouldGiveXL() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "XL", converter.convert(40));
  }

  @Test public void convert10_shouldGiveX() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "X", converter.convert(10));
  }

  @Test public void convert11_shouldGiveXI() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "XI", converter.convert(11));
  }

  @Test public void convert90_shouldGiveXC() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "XC", converter.convert(90));
  }

  @Test public void convert1000_shouldGiveM() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "M", converter.convert(1000));
  }

  @Test public void convert95_shouldGiveXCV() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "XCV", converter.convert(95));
  }

  @Test public void convert3000_shouldGiveMMM() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "MMM", converter.convert(3000));
  }

  @Test public void convert3200_shouldGiveMMMCC() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "MMMCC", converter.convert(3200));
  }

  @Test public void convert2008_shouldGiveMCMXC() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "MMVIII", converter.convert(2008));
  }

  @Test public void convert1990_shouldGiveMCMXC() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "MCMXC", converter.convert(1990));
  }

  @Test public void convert1999_shouldGiveMCMXCIX() {
    RomanNumberConverter converter = new RomanNumberConverter();
    Assert.assertEquals("Unexpected number", "MCMXCIX", converter.convert(1999));
  }

}
