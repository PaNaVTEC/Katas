object romansNumeral {

  val numerals = List(
    (1, "I"),
    (4, "IV"),
    (5, "V"),
    (9, "IX"),
    (10, "X"),
    (50, "L"),
    (90, "XC"),
    (100, "C"),
    (500, "D"),
    (900, "CM"),
    (1000, "M")
  )

  def from(dec: Int): String = {
    def append(n: (Int, String), acc: (Int, String)): (Int, String) =
      if (acc._1 >= n._1) append(n, (acc._1 - n._1, acc._2 + n._2)) else acc
    numerals.foldRight((dec, ""))(append(_, _))._2
  }

}
