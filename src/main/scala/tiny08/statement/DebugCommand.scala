package tiny08.statement

import tiny08.Machine
import tiny08.exception.RuntimeError

trait DebugCommand extends Statement {
  def run(machine: Machine) {
    try {
      execute(machine)
    }
    catch {
      case e: RuntimeError => {
        println(s"Runtime error at $filename:${lineNum+1}")
        throw e
      }
    }
  }

  def execute(machine: Machine)
}
