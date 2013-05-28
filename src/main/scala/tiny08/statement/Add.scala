package tiny08.statement

class Add(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Add R$rx R$ry]\tat $address"
  }
}

object Add extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Add] = {
    val pattern = """Add R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Add(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
