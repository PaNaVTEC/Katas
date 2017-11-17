public class Move {
  private final int diskNumber;
  private final int destPile;

  public Move(int diskNumber, int destPile) {
    this.diskNumber = diskNumber;
    this.destPile = destPile;
  }

  public int getDiskNumber() {
    return diskNumber;
  }

  public int getDestPile() {
    return destPile;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Move move = (Move) o;

    if (diskNumber != move.diskNumber) return false;
    return destPile == move.destPile;
  }

  @Override public int hashCode() {
    int result = diskNumber;
    result = 31 * result + destPile;
    return result;
  }

  @Override public String toString() {
    return "Move{" +
        "diskNumber=" + diskNumber +
        ", destPile=" + destPile +
        '}';
  }
}
