package tiny08.statement

import tiny08.Machine
import java.io.File

class Print(val address: Int, rx: Int, val filename: String, val lineNum: Int)
  extends DebugCommand {

  val debugger = true

  def execute(machine: Machine) {
    val value = machine.getRegister(rx)
    val filenameOnly = new File(filename).getName
    println(s"$filenameOnly:$lineNum: R$rx = $value")
  }

  override def toString = {
    s"%print R$rx%\tat $address"
  }
}

object Print extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Print] = {
    val pattern = """%print R(\d+)""".r
    code match {
      case pattern(rx) => Some(new Print(addr, rx.toInt, filename, lineNum))
      case _ => None
    }
  }
}
