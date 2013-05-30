package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.exception.NoSuchLabel
import tiny08.util.BinDecConversion._

class JmpC(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, labelTable: Simulator#LabelTable) {
    if(machine.carryFlag) machine.programCounter = labelMem(labelTable)
    else machine.programCounter += 2
  }

  def toMachineCode(labelTable: Simulator#LabelTable) = {
    val str =
      "10000000" +
      "00000001" +
      labelMem(labelTable).toBinStr(16)

    str32ToByte4(str)
  }

  private def labelMem(labelTable: Simulator#LabelTable) = {
    if(labelTable.isDefinedAt(label)) labelTable(label)
    else throw new NoSuchLabel(label)
  }

  override def toString = {
    f"${s"[JmpC $label]"}%-25s at $address"
  }
}

object JmpC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[JmpC] = {
    val pattern = """JmpC ([^:]+)""".r
    code match {
      case pattern(label) => Some(new JmpC(addr, label, filename, lineNum))
      case _ => None
    }
  }
}
