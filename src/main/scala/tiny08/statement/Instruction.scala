package tiny08.statement

import tiny08.{Simulator, Machine}
import tiny08.exception.RuntimeError

trait Instruction extends Statement {
  def run(machine: Machine, labelTable: Simulator#LabelTable) {
    println(s"Running $filename:$lineNum: $this")
    try {
      execute(machine, labelTable)
    }
    catch {
      case e: RuntimeError => {
        println(s"Runtime error at $filename:$lineNum")
        throw e
      }
    }
  }

  def execute(machine: Machine, labelTable: Simulator#LabelTable)
}
