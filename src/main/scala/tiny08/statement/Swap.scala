package tiny08.statement

class Swap(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Swap R$rx R$ry]\tat $address"
  }
}

object Swap extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Swap] = {
    val pattern = """Swap R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Swap(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}