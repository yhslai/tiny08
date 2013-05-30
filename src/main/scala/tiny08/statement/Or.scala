package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.BinDecConversion._

class Or(val address: Int, rx: Int, ry: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)
    val y = machine.getRegister(ry)
    machine.setRegister(rx, x | y)
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "01110000" +
      rx.toBinStr(4) + ry.toBinStr(4) +
      "00000000" +
      "00000000"

    str32ToByte4(str)
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
