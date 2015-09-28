package me.panavtec.katas.numerals;

import java.util.ArrayList;
import java.util.List;

public class RomanNumberConverter {

  private final List<RomanSymbol> symbols = new ArrayList<>();

  public RomanNumberConverter() {
    symbols.add(new RomanSymbol("I", 1));
    symbols.add(new RomanSymbol("V", 5));
    symbols.add(new RomanSymbol("X", 10));
    symbols.add(new RomanSymbol("L", 50));
    symbols.add(new RomanSymbol("C", 100));
    symbols.add(new RomanSymbol("D", 500));
    symbols.add(new RomanSymbol("M", 1000));
  }

  public String convert(int decimalNumber) {
    StringBuilder resultSymbol = new StringBuilder();
    while (decimalNumber > 0) {
      RomanSymbol exactValue = isExactValue(decimalNumber);
      if (exactValue != null) {
        decimalNumber -= exactValue.getValue();
        resultSymbol.append(exactValue.getSymbol());
      } else {
        RomanSymbol upper = upperSymbolToNumber(decimalNumber);
        if (decimalNumber > upper.getValue()) {
          decimalNumber -= upper.getValue();
          resultSymbol.append(upper.getSymbol());
        } else {
          RomanSymbol prevSubstract = previousSubstractSymbol(upper);
          int substraction = upper.getValue() - prevSubstract.getValue();
          if (decimalNumber >= substraction) {
            decimalNumber -= substraction;
            resultSymbol.append(prevSubstract.getSymbol()).append(upper.getSymbol());
          } else {
            if (upper.getValue() > decimalNumber) {
              RomanSymbol realPrevious = symbols.get(symbols.indexOf(upper) - 1);
              if (realPrevious.getValue() <= decimalNumber) {
                decimalNumber -= realPrevious.getValue();
                resultSymbol.append(realPrevious.getSymbol());
              } else {
                decimalNumber -= prevSubstract.getValue();
                resultSymbol.append(prevSubstract.getSymbol());
              }
            } else {
              decimalNumber -= upper.getValue();
              resultSymbol.append(upper.getSymbol());
            }
          }
        }
      }
    }

    return resultSymbol.toString();
  }

  private RomanSymbol previousSubstractSymbol(RomanSymbol upper) {
    int index = symbols.indexOf(upper) - (upper.isTypeFive() ? 1 : 2);
    return index >= 0 ? symbols.get(index) : new RomanSymbol("", 0);
  }

  private RomanSymbol upperSymbolToNumber(int decimalNumber) {
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