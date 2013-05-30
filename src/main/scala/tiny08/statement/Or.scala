package tiny08.statement

import tiny08.{Simulator, Machine}

class Or(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)
    val y = machine.getRegister(ry)
    machine.setRegister(rx, x | y)
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[Or R$rx R$ry]"}%-25s at $address"
  }
}

object Or extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Or] = {
    val pattern = """Or R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Or(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
