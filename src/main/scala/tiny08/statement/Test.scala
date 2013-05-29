package tiny08.statement

import tiny08.{Simulator, Machine}

class Test(val address: Int, rx: Int, bit: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)

    if((x & (1 << bit)) != 0) machine.setCarryFlag()
    else machine.clearCarryFlag()
    machine.programCounter += 2
  }

  override def toString = {
    s"[Test R$rx #$bit]\tat $address"
  }
}

object Test extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Test] = {
    val pattern = """Test R(\d+), #(\d+)""".r
    code match {
      case pattern(rx, bit) => Some(new Test(addr, rx.toInt, bit.toInt, filename, lineNum))
      case _ => None
    }
  }
}
