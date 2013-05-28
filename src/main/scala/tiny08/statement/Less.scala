package tiny08.statement

class Less(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Less R$rx R$ry]\tat $address"
  }
}

object Less extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Less] = {
    val pattern = """Less R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Less(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
