package tiny08.statement

class SetC(val address: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[SetC]\tat $address"
  }
}

object SetC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[SetC] = {
    val pattern = """Clrc""".r
    code match {
      case pattern() => Some(new SetC(addr, filename, lineNum))
      case _ => None
    }
  }
}
