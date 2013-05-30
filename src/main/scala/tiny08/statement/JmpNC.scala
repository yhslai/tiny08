package tiny08.statement

import tiny08.{Machine, Simulator}
import tiny08.exception.NoSuchLabel

class JmpNC(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, labelTable: Simulator#LabelTable) {
    if(labelTable.isDefinedAt(label)) {
      val labelMem = labelTable(label)
      if(!machine.carryFlag) machine.programCounter = labelMem
      else machine.programCounter += 2
    }
    else throw new NoSuchLabel(label)
  }

  override def toString = {
    f"${s"[JmpNC $label]"}%-25s at $address"
  }
}

object JmpNC extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[JmpNC] = {
    val pattern = """JmpNC ([^:]+)""".r
    code match {
      case pattern(label) => Some(new JmpNC(addr, label, filename, lineNum))
      case _ => None
    }
  }
}
