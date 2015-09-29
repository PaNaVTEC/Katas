package me.panavtec.katas.numerals;

import java.util.ArrayList;
import java.util.List;

public class RomanNumberConverter {

  private static final RomanSymbol ROMAN_ZERO = new RomanSymbol(' ', 0);
  private static final List<RomanSymbol> symbols = new ArrayList<>();

  public RomanNumberConverter() {
    symbols.add(new RomanSymbol('I', 1));
    symbols.add(new RomanSymbol('V', 5));
    symbols.add(new RomanSymbol('X', 10));
    symbols.add(new RomanSymbol('L', 50));
    symbols.add(new RomanSymbol('C', 100));
    symbols.add(new RomanSymbol('D', 500));
    symbols.add(new RomanSymbol('M', 1000));
  }

  public String convert(int providedArabic) {
    ResultNumber resultNumber = new ResultNumber(providedArabic);
    while (resultNumber.getRestant() > 0) {
      RomanSymbol exactValue = isExactValue(resultNumber.getRestant());
      if (exactValue != null) {
        resultNumber.appendSymbol(exactValue);
      } else {
        RomanSymbol upper = romanGreaterThan(resultNumber.getRestant());
        if (resultNumber.getRestant() > upper.getValue()) {
          resultNumber.appendSymbol(upper);
        } else {
          RomanSymbol prevSubstract = previousSubstractable(upper);
          int substraction = upper.getValue() - prevSubstract.getValue();
          if (resultNumber.getRestant() >= substraction) {
            resultNumber.appendSymbol(substraction, prevSubstract.getSymbol(), upper.getSymbol());
          } else if (upper.getValue() > resultNumber.getRestant()) {
            RomanSymbol previosSymbol = symbols.get(symbols.indexOf(upper) - 1);
            if (previosSymbol.getValue() <= resultNumber.getRestant()) {
              resultNumber.appendSymbol(previosSymbol);
            } else {
              resultNumber.appendSymbol(prevSubstract);
            }
          } else {
            resultNumber.appendSymbol(upper);
          }
        }
      }
    }

    return resultNumber.getComposedSymbol();
  }

  private RomanSymbol previousSubstractable(RomanSymbol upper) {
    int index = symbols.indexOf(upper) - (upper.isTypeFive() ? 1 : 2);
    return index >= 0 ? symbols.get(index) : ROMAN_ZERO;
  }

  private RomanSymbol romanGreaterThan(int decimalNumber) {
    for (RomanSymbol romanSymbol : symbols) {
      if (romanSymbol.getValue() > decimalNumber) {
        return romanSymbol;
      }
    }
    return symbols.get(symbols.size() - 1);
  }

  private RomanSymbol isExactValue(int decimalNumber) {
    for (RomanSymbol romanSymbol : symbols) {
      if (romanSymbol.getValue() == decimalNumber) {
        return romanSymbol;
      }
    }
    return null;
  }
}
