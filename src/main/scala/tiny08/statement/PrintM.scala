package tiny08.statement

import tiny08.Machine
import tiny08.util.HexDecConversion._

class PrintM(val address: Int, mem: Int, val filename: String, val lineNum: Int)
  extends DebugCommand {

  val debugger = true

  def execute(machine: Machine) {
    //TODO
  }

  override def toString = {
    f"${s"%printm [${mem.toHexStr}]"}%-25s at $address"
  }
}

object PrintM extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[PrintM] = {
    val pattern = """%printm \[((?:0x)?(?:[\da-fA-F]+))\]""".r
    code match {
      case pattern(mem) => Some(new PrintM(addr, mem.tryToInt, filename, lineNum))
      case _ => None
    }
  }
}
