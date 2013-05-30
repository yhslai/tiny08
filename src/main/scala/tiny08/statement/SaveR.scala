package tiny08.statement

import tiny08.{Simulator, Machine}

class SaveR(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val y = machine.getRegister(ry)
    val value = machine.getRegister(rx)
    machine.setMemory(y, value)
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[SaveR [R$ry] [$rx]]"}%-25s at $address"
  }
}

object SaveR extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[SaveR] = {
    val pattern = """SaveR \[R(\d+)\], R(\d+)""".r
    code match {
      case pattern(ry, rx) => Some(new SaveR(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
