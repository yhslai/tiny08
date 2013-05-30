package tiny08.statement

import tiny08.{Simulator, Machine}

class ClrC(val address: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    machine.clearCarryFlag()
    machine.programCounter += 2
  }

  override def toString = {
    f"${s"[ClrC]"}%-25s at $address"
  }
}

object ClrC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[ClrC] = {
    val pattern = """Clrc""".r
    code match {
      case pattern() => Some(new ClrC(addr, filename, lineNum))
      case _ => None
    }
  }
}
