package tiny08.statement

import tiny08.{Simulator, Machine}

class JmpC(val address: Int, label: String, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    //TODO
  }

  override def toString = {
    s"[JmpC $label]\tat $address"
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
