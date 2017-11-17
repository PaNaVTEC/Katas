package me.panavtec.katas.potter;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PotterTest {

  PotterCalculator potterCalculator = new PotterCalculator();

  @Test public void oneBookCosts8Eur() {
    assertPrice(8d, potterCalculator.calculatePriceForBooks(1));
  }

  @Test public void twoBookHave5percentDiscount() {
    assertPrice(15.2d, potterCalculator.calculatePriceForBooks(2));
  }

  @Test public void threeBooksHave10percentDiscount() {
    assertPrice(21.6d, potterCalculator.calculatePriceForBooks(3));
  }

  @Test public void fourBooksHave20percentDiscount() {
    assertPrice(25.6d, potterCalculator.calculatePriceForBooks(4));
  }

  @Test public void fiveBooksHave25percentDiscount() {
    assertPrice(30d, potterCalculator.calculatePriceForBooks(5));
  }

  @Test public void calculateAllPossibleSubsets() {
    // 5 - 1
    // 4 - 2
    // 3 - 3
    // 2 - 2 - 2
    List<int[]> subsets = potterCalculator.calculateSubsets(6);
    List<int[]> expectedSubsets = new ArrayList<>();
    expectedSubsets.add(new int[]{5, 1});
    expectedSubsets.add(new int[]{4, 2});
    expectedSubsets.add(new int[]{3, 3});
    expectedSubsets.add(new int[]{2, 2, 2});
    for (int i = 0 ; i < subsets.size() ; i++) {
      Assert.assertArrayEquals(subsets.get(i), expectedSubsets.get(i));
    }
  }

  private void assertPrice(double expected, double actual) {
    Assert.assertEquals("Not expected price", expected, actual, 0d);
  }
}
