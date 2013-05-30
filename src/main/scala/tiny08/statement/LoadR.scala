package tiny08.statement

import tiny08.{Simulator, Machine}

class LoadR(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val y = machine.getRegister(ry)
    val value = machine.getMemory(y)
    machine.setRegister(rx, value)
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[LoadR R$rx [R$ry]]"}%-25s at $address"
  }
}

object LoadR extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[LoadR] = {
    val pattern = """LoadR R(\d+), \[R(\d+)\]""".r
    code match {
      case pattern(rx, ry) => Some(new LoadR(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
