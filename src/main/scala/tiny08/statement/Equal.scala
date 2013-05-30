package tiny08.statement

import tiny08.{Machine, Simulator}

class Equal(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)
    val y = machine.getRegister(ry)
    if(x == y) machine.setCarryFlag()
    else machine.clearCarryFlag()
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[Equal R$rx R$ry]"}%-25s at $address"
  }
}

object Equal extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Equal] = {
    val pattern = """Equal R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Equal(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
