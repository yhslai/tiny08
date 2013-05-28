package tiny08.statement

class Break(val address: Int, val filename: String, val lineNum: Int)
  extends Statement {

  val debugger = true

  def run() {

  }

  override def toString = {
    s"%break%\tat $address"
  }
}

object Break extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Break] = {
    val pattern = """%break""".r
    code match {
      case pattern() => Some(new Break(addr, filename, lineNum))
      case _ => None
    }
  }
}
