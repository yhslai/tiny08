package tiny08.statement

class And(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[And R$rx R$ry]\tat $address"
  }
}

object And extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[And] = {
    val pattern = """And R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new And(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
