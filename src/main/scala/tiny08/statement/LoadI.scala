package tiny08.statement

class LoadI(val address: Int, rx: Int, value: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {
    
  }

  override def toString = {
    s"[LoadI R$rx #$value]\tat $address"
  }
}

object LoadI extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[LoadI] = {
    val pattern = """LoadI R(\d+), #(\d+)""".r
    code match {
      case pattern(rx, value) => Some(new LoadI(addr, rx.toInt, value.toInt, filename, lineNum))
      case _ => None
    }
  }
}
