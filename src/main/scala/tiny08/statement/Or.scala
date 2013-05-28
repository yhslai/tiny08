package tiny08.statement

class Or(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Or R$rx R$ry]\tat $address"
  }
}

object Or extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Or] = {
    val pattern = """Or R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Or(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
