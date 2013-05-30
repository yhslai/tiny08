package tiny08.statement

import tiny08.{Simulator, Machine}

class Swap(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val y = machine.getRegister(ry)
    val x = machine.getRegister(rx)
    machine.setRegister(rx, y)
    machine.setRegister(ry, x)
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[Swap R$rx R$ry]"}%-25s at $address"
  }
}

object Swap extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Swap] = {
    val pattern = """Swap R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Swap(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
