package tiny08.util

import tiny08.exception.ParseError

object HexDecConversion {
  implicit class HexOrDecString(str: String) {
    def tryToInt = {
      val hexPattern = """^0x([a-fA-F\d]+)$""".r
      val decPattern = """^(\d+)$""".r
      str match {
        case hexPattern(hex) => Integer.parseInt(hex, 16)
        case decPattern(dec) => dec.toInt
        case _ => throw new ParseError(str, "hexadecimal or decimal number")
      }
    }
  }

  implicit class HexInt(i: Int) {
    def toHexStr = {
      s"0x${i.toHexString.toUpperCase}"
    }
  }
}

