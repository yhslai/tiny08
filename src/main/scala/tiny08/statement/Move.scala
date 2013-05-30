package tiny08.statement

import tiny08.{Simulator, Machine}

class Move(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val y = machine.getRegister(ry)
    machine.setRegister(rx, y)
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[Move R$rx R$ry]"}%-25s at $address"
  }
}

object Move extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Move] = {
    val pattern = """Move R(\d+), R(\d+)""".r
    code match {
      case pattern(rx, ry) => Some(new Move(addr, rx.toInt, ry.toInt, filename, lineNum))
      case _ => None
    }
  }
}
