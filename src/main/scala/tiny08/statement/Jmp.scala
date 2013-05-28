package tiny08.statement

class Jmp(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Jmp $label]\tat $address"
  }
}

object Jmp extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Jmp] = {
    val pattern = """Jmp ([^:]+)""".r
    code match {
      case pattern(label) => Some(new Jmp(addr, label, filename, lineNum))
      case _ => None
    }
  }
}
