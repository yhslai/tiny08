package tiny08.statement

import tiny08.{Simulator, Machine}

class Exit(val address: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  def execute(machine: Machine, labelTable: Simulator#LabelTable) {
    machine.halt()
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "00000000" +
      "00000000" +
      "00000000" +
      "00000000"

    str32ToByte4(str)
  }

  override def toString = {
    f"${s"Exit"}%-25s at $address"
  }
}

object Exit extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Exit] = {
    val pattern = """Exit""".r
    code match {
      case pattern() => Some(new Exit(addr, filename, lineNum))
      case _ => None
    }
  }
}