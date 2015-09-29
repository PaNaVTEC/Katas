package me.panavtec.katas.numerals;

public class RomanNumberConverter {

  private static final RomanNumeral[] numerals = RomanNumeral.values();

  public String convert(int providedArabic) {
    ResultNumeral resultNumeral = new ResultNumeral(providedArabic);
    while (resultNumeral.getRestant() > 0) {
      RomanNumeral exactValue = hasExactNumeral(resultNumeral.getRestant());
      if (exactValue != null) {
        resultNumeral.appendSymbol(exactValue);
      } else {
        RomanNumeral upper = romanGreaterThan(resultNumeral.getRestant());
        if (resultNumeral.getRestant() > upper.getValue()) {
          resultNumeral.appendSymbol(upper);
        } else {
          RomanNumeral prevSubstract = previousSubstractable(upper);
          int substraction = upper.getValue() - prevSubstract.getValue();
          if (resultNumeral.getRestant() >= substraction) {
            resultNumeral.appendSymbol(substraction, prevSubstract.getSymbol(), upper.getSymbol());
          } else if (upper.getValue() > resultNumeral.getRestant()) {
            RomanNumeral previousSymbol = numerals[RomanNumeral.indexOf(upper) - 1];
            if (previousSymbol.getValue() <= resultNumeral.getRestant()) {
              resultNumeral.appendSymbol(previousSymbol);
            } else {
              resultNumeral.appendSymbol(prevSubstract);
            }
          } else {
            resultNumeral.appendSymbol(upper);
          }
        }
      }
    }

    return resultNumeral.getComposedSymbol();
  }

  private RomanNumeral previousSubstractable(RomanNumeral upper) {
    int index = RomanNumeral.indexOf(upper) - (upper.isTypeFive() ? 1 : 2);
    return index >= 0 ? numerals[index] : RomanNumeral.I;
  }

  private RomanNumeral romanGreaterThan(int decimalNumber) {
    for (RomanNumeral romanSymbol : numerals) {
      if (romanSymbol.getValue() > decimalNumber) {
        return romanSymbol;
      }
    }
    return RomanNumeral.M;
  }

  private RomanNumeral hasExactNumeral(int decimalNumber) {
    for (RomanNumeral romanSymbol : numerals) {
      if (romanSymbol.getValue() == decimalNumber) {
        return romanSymbol;
      }
    }
    return null;
  }

}
