package tiny08.statement

import tiny08.{Simulator, Machine}

class SetC(val address: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    machine.setCarryFlag()
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "10010001" +
      "00000000" +
      "00000000" +
      "00000000"

    str32ToByte4(str)
  }

  override def toString = {
    f"${s"[SetC]"}%-25s at $address"
  }
}

object SetC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[SetC] = {
    val pattern = """Clrc""".r
    code match {
      case pattern() => Some(new SetC(addr, filename, lineNum))
      case _ => None
    }
  }
}
