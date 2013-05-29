package tiny08.statement

import tiny08.Machine
import java.io.File

class PrintM(val address: Int, mem: Int, val filename: String, val lineNum: Int)
  extends DebugCommand {

  val debugger = true

  def execute(machine: Machine) {
    //TODO
  }

  override def toString = {
    s"%printm [mem]%\tat $address"
  }
}

object PrintM extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[PrintM] = {
    val pattern = """%printm [(\d+)]""".r
    code match {
      case pattern(mem) => Some(new PrintM(addr, mem.toInt, filename, lineNum))
      case _ => None
    }
  }
}
