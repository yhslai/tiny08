package tiny08.statement

class Print(val address: Int, rx: Int, val filename: String, val lineNum: Int)
  extends Statement {

  val debugger = true

  def run() {

  }

  override def toString = {
    s"%print R$rx%\tat $address"
  }
}

object Print extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Print] = {
    val pattern = """%print R(\d+)""".r
    code match {
      case pattern(rx) => Some(new Print(addr, rx.toInt, filename, lineNum))
      case _ => None
    }
  }
}
