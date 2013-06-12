package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.exception.RuntimeError

trait Instruction extends Statement {
  def run(machine: Machine, labelTable: Simulator#LabelTable) {
    try {
      execute(machine, labelTable)
    }
    catch {
      case e: RuntimeError => {
        println(s"Runtime error at $filename:${lineNum+1}")
        throw e
      }
    }
  }

  def execute(machine: Machine, labelTable: Simulator#LabelTable)

  def toMachineCode(labelTable: Simulator#LabelTable): Seq[Byte]

  protected def str32ToByte4(str: String): Seq[Byte] = {
    str.grouped(8).map(s => Integer.parseInt(s, 2).toByte).toList
  }
}
