package tiny08.util

import tiny08.exception.ParseError

object BinDecConversion {
  implicit class BinInt(i: Int) {
    def toBinStr(digit: Int) = {
      val format = f"%${digit}s"
      format.format(Integer.toBinaryString(i)).replace(" ", "0")
    }
  }
}

