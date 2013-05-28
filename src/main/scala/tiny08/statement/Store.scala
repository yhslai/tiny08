package tiny08.statement

class Store(val address: Int, mem: Int, rx: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def run() {

  }

  override def toString = {
    s"[Store [$mem] R$rx]\tat $address"
  }
}

object Store extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Store] = {
    val pattern = """Store \[(\d+)\], R(\d+)""".r
    code match {
      case pattern(mem, rx) => Some(new Store(addr, mem.toInt, rx.toInt, filename, lineNum))
      case _ => None
    }
  }
}
