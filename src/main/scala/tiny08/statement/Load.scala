package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.util.HexDecConversion._

class Load(val address: Int, rx: Int, mem: Int, val filename: String, val lineNum: Int)
  extends Instruction {

  val debugger = false

  def execute(machine: Machine, dummy: Simulator#LabelTable) {
    //TODO
  }

  override def toString = {
    val ins = s"[Load R$rx [${mem.toHexStr}]]"
    f"$ins%-25s at $address"
  }
}

object Load extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Load] = {
    val pattern = """Load R(\d+), \[((?:0x)?(?:[\da-fA-F]+))\]""".r
    code match {
      case pattern(rx, mem) => Some(new Load(addr, rx.toInt, mem.tryToInt, filename, lineNum))
      case _ => None
    }
  }
}
