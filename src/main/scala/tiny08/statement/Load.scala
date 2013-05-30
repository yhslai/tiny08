package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.HexDecConversion._
import tiny08.util.BinDecConversion._

class Load(val address: Int, rx: Int, mem: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    val value = machine.getMemory(mem)
    machine.setRegister(rx, value)
    machine.programCounter += 2
  }

  def toMachineCode(dummy: Simulator#LabelTable) = {
    val str =
      "00010100" +
      rx.toBinStr(4) + "0000" +
      mem.toBinStr(16)

    str32ToByte4(str)
  }

  override def toString = {
    val ins = s"[Load R$rx [${mem.toHexStr}]]"
    f"$ins%-25s at $address"
  }
}

object Load extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Load] = {
    val pattern = """Load R(\d+), \[((?:0x)?(?:[\da-fA-F]+))\]""".r
    code match {
      case pattern(rx, mem) => Some(new Load(addr, rx.toInt, mem.tryToInt, filename, lineNum))
      case _ => None
    }
  }
}
