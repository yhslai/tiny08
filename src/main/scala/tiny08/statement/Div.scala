package tiny08.statement

class Div(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Div R$rx R$ry]\tat $address"
  }
}

object Div extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Div] = {
    val pattern = """Div R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Div(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
