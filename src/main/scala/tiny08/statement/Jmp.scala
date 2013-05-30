package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.exception.NoSuchLabel
import tiny08.util.BinDecConversion._

class Jmp(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, labelTable: Simulator#LabelTable) {
    machine.programCounter = labelMem(labelTable)
  }

  def toMachineCode(labelTable: Simulator#LabelTable) = {
    val str =
      "10000000" +
      "00000000" +
      labelMem(labelTable).toBinStr(16)

    str32ToByte4(str)
  }

  private def labelMem(labelTable: Simulator#LabelTable) = {
    if(labelTable.isDefinedAt(label)) labelTable(label)
    else throw new NoSuchLabel(label)
  }

  override def toString = {
    f"${s"[Jmp $label]"}%-25s at $address"
  }
}

object Jmp extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Jmp] = {
    val pattern = """Jmp ([^:]+)""".r
    code match {
      case pattern(label) => Some(new Jmp(addr, label, filename, lineNum))
      case _ => None
    }
  }
}
