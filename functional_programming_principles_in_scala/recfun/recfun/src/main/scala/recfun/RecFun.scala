package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    /*println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()
    */
    val testString: String = "()()jus(t an exampl)e"
    println("***Start***")
    println(balance(testString.toList))
    println("***End***")

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else {
        pascal(c-1, r-1) + pascal(c, r-1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceWithWeight(chars: List[Char], weight: Int): Boolean = {
      if (chars.isEmpty & weight == 0) {
        true
      } else if ((chars.isEmpty & weight != 0) | (!chars.isEmpty & weight < 0)) {
        false
      } else if (chars.head == '(') {
        balanceWithWeight(chars.tail, weight + 1)
      } else if (chars.head == ')') {
        balanceWithWeight(chars.tail, weight - 1)
      } else {
        balanceWithWeight(chars.tail, weight)
      }
    }

    balanceWithWeight(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
