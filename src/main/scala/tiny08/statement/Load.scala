package tiny08.statement

import tiny08.{Simulator, Machine}

class Load(val address: Int, rx: Int, mem: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    //TODO
  }

  override def toString = {
    s"[Load R$rx [$mem]]\tat $address"
  }
}

object Load extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Load] = {
    val pattern = """Load R(\d+), \[(\d+)\]""".r
    code match {
      case pattern(rx, mem) => Some(new Load(addr, rx.toInt, mem.toInt, filename, lineNum))
      case _ => None
    }
  }
}
