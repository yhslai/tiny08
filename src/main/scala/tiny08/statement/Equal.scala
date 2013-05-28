package tiny08.statement

class Equal(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Equal R$rx R$ry]\tat $address"
  }
}

object Equal extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Equal] = {
    val pattern = """Equal R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Equal(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
