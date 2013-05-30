package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.exception.NoSuchLabel

class Jmp(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, labelTable: Simulator#LabelTable) {
    if(labelTable.isDefinedAt(label)) {
      val labelMem = labelTable(label)
      machine.programCounter = labelMem
    }
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
