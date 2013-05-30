package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.HexDecConversion._
import tiny08.util.BinDecConversion._

class Save(val address: Int, mem: Int, rx: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val x = machine.getRegister(rx)
    machine.setMemory(mem, x)
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "00011000" +
      rx.toBinStr(4) + "0000" +
      mem.toBinStr(16)

    str32ToByte4(str)
  }

  override def toString = {
    val ins = s"[Save [${mem.toHexStr}] R$rx]"
    f"$ins%-25s at $address"
  }
}

object Save extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Save] = {
    val pattern = """Save \[((?:0x)?(?:[\da-fA-F]+))\], R(\d+)""".r
    code match {
      case pattern(mem, rx) => Some(new Save(addr, mem.tryToInt, rx.toInt, filename, lineNum))
      case _ => None
    }
  }
}
