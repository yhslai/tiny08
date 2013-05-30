package tiny08

import tiny08.statement.Statement

object Assembler {
  def assemble(statements: Seq[Statement]): Seq[Byte] = {
    val sim = new Simulator(statements, new Machine)
    val instructions = sim.instructions.values.toList
    val labelTable = sim.labelTable

    instructions.flatMap(ins => ins.toMachineCode(labelTable))
  }
}
