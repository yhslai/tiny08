package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.BinDecConversion._

class Test(val address: Int, rx: Int, bit: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)

    if((x & (1 << bit)) != 0) machine.setCarryFlag()
    else machine.clearCarryFlag()
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "11110000" +
      rx.toBinStr(4) + bit.toBinStr(4) +
      "00000000" +
      "00000000"

    str32ToByte4(str)
  }

  override def toString = {
    f"${s"[Test R$rx #$bit]"}%-25s at $address"
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
