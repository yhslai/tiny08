package tiny08.statement

class Mul(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Mul R$rx R$ry]\tat $address"
  }
}

object Mul extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Mul] = {
    val pattern = """Mul R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Mul(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
