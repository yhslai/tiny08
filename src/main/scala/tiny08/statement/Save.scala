package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.HexDecConversion._

class Save(val address: Int, mem: Int, rx: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    //TODO
  }

  override def toString = {
    f"""${s"[Save [${mem.toHexStr}] R$rx]"}%-25s at $address"""
  }
}

object Save extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Save] = {
    val pattern = """Save \[((?:0x)?(?:[\da-fA-F]+))\], R(\d+)""".r
    code match {
      case pattern(mem, rx) => Some(new Save(addr, mem.tryToInt, rx.toInt, filename, lineNum))
      case _ => None
    }
  }
}