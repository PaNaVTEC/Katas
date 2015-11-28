package me.panavtec.katas.potter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotterCalculator {

  private static final int PRICE_PER_BOOK = 8;

  private static final Map<Integer, Double> discounts = new HashMap<>();

  public PotterCalculator() {
    discounts.put(2, 0.95d);
    discounts.put(3, 0.90d);
    discounts.put(4, 0.80d);
    discounts.put(5, 0.75d);
  }

  public double calculatePriceForBooks(int numberOfBooks) {
    if (numberOfBooks == 1) {
      return PRICE_PER_BOOK;
    }
    return PRICE_PER_BOOK * numberOfBooks * discounts.get(numberOfBooks);
  }

  public List<int[]> calculateSubsets(int numberOfBooks) {
    for (int j = numberOfBooks - 1; j >= numberOfBooks; j--) {
      numberOfBooks - j
    }
  }
}
