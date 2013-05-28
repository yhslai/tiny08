package tiny08.statement

class Sub(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Sub R$rx R$ry]\tat $address"
  }
}

object Sub extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Sub] = {
    val pattern = """Sub R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Sub(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
