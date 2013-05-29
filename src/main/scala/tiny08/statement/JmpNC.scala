package tiny08.statement

import tiny08.{Machine, Simulator}

class JmpNC(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    //TODO
  }

  override def toString = {
    s"[JmpNC $label]\tat $address"
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
