package tiny08.statement

import tiny08.{Simulator, Machine}

class Less(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)
    val y = machine.getRegister(ry)
    if(x < y) machine.setCarryFlag()
    else machine.clearCarryFlag()
    machine.programCounter += 2
  }

  override def toString = {
    s"[Less R$rx R$ry]\tat $address"
  }
}

object Less extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Less] = {
    val pattern = """Less R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Less(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
