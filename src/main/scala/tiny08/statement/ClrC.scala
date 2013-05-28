package tiny08.statement

class ClrC(val address: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[ClrC]\tat $address"
  }
}

object ClrC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[ClrC] = {
    val pattern = """Clrc""".r
    code match {
      case pattern() => Some(new ClrC(addr, filename, lineNum))
      case _ => None
    }
  }
}
