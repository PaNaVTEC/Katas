package me.panavtec.katas.numerals;

public class ResultNumeral {
  private StringBuilder composedSymbol = new StringBuilder();
  private int restant;

  public ResultNumeral(int initialRest) {
    this.restant = initialRest;
  }

  public void appendSymbol(RomanNumeral romanSymbol) {
    composedSymbol.append(romanSymbol.getSymbol());
    restant -= romanSymbol.getValue();
  }

  public void appendSymbol(int rest, char... symbol) {
    composedSymbol.append(symbol);
    restant -= rest;
  }

  public int getRestant() {
    return restant;
  }

  public String getComposedSymbol() {
    return composedSymbol.toString();
  }
}
