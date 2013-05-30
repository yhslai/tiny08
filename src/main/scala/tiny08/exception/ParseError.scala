package tiny08.exception

class ParseError(str: String, expected: String) extends Throwable {
  override def toString = {
    s"""Can't parse \"$str\", $expected expected"""
  }
}
