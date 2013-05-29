package script

import tiny08.{Simulator, Machine, PreProcessor, Parser}
import tiny08.statement.Statement

object SimulatorScript {
  def main(args: Array[String]) {
    if(args.length < 1)
      throw new IllegalArgumentException("Need the assembly file name")

    val asmFilename = args(0)
    val preProcessedAsm = PreProcessor.preProcess(asmFilename)
    val parsedStatements = Parser.parse(preProcessedAsm)
    val machine = new Machine()
    val simulator = new Simulator(parsedStatements, machine)
    simulator.run()
  }
}
