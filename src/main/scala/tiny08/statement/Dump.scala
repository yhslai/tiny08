package tiny08.statement

import tiny08.{Simulator, Machine}

class Dump(val address: Int, val filename: String, val lineNum: Int)
  extends DebugCommand {

  val debugger = true

  def execute(machine: Machine) {
    //TODO
  }

  override def toString = {
    s"%dump%\tat $address"
  }
}

object Dump extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Dump] = {
    val pattern = """%dump""".r
    code match {
      case pattern() => Some(new Dump(addr, filename, lineNum))
      case _ => None
    }
  }
}
