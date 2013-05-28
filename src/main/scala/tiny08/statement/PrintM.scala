package tiny08.statement

class PrintM(val address: Int, mem: Int, val filename: String, val lineNum: Int)
  extends Statement {

  val debugger = true

  def run() {

  }

  override def toString = {
    s"%printm [mem]%\tat $address"
  }
}

object PrintM extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[PrintM] = {
    val pattern = """%printm [(\d+)]""".r
    code match {
      case pattern(mem) => Some(new PrintM(addr, mem.toInt, filename, lineNum))
      case _ => None
    }
  }
}
