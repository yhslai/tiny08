package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.HexDecConversion._
import tiny08.util.BinDecConversion._

class LoadI(val address: Int, rx: Int, value: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    machine.setRegister(rx, value)
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "00010000" +
      rx.toBinStr(4) + "0000" +
      value.toBinStr(16)

    str32ToByte4(str)
  }

  override def toString = {
    f"${s"[LoadI R$rx #$value]"}%-25s at $address"
  }
}

object LoadI extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[LoadI] = {
    val pattern = """LoadI R(\d+), #((?:0x)?(?:[\da-fA-F]+))""".r
    code match {
      case pattern(rx, value) => Some(new LoadI(addr, rx.toInt, value.tryToInt, filename, lineNum))
      case _ => None
    }
  }
}
