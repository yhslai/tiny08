package tiny08

import tiny08.statement._
import tiny08.exception.NoSuchInstruction

class Simulator(statements: Seq[Statement], machine: Machine) {
  type LabelTable = Map[String, Int]
  type Instructions = Map[Int, Instruction]
  val instructions = makeInstructions(statements)
  val labelTable = makeLabelTable(statements)
  val debugCommands = makeDebugCommands(statements)

  private def makeInstructions(statements: Seq[Statement]): Instructions = {
    val instructions = statements.flatMap{ case ins: Instruction => Some(ins); case _ => None }
    instructions.map(ins => (ins.address, ins)).toMap
  }

  private def makeLabelTable(statements: Seq[Statement]): LabelTable = {
    val labels = statements.flatMap{ case lbl: Label => Some(lbl); case _ => None }
    labels.map(lbl => (lbl.name, lbl.address)).toMap
  }

  private def makeDebugCommands(statements: Seq[Statement]): Map[Int, Seq[DebugCommand]] = {
    val debugCmds = statements.flatMap{
      case debugCmd: DebugCommand => Some(debugCmd)
      case _ => None
    }
    debugCmds.groupBy(_.address)
  }

  def run() {
    while(!machine.isHalted) {
      val pc = machine.programCounter
      runDebugCommands(pc)
      if(machine.isStop) {
        //TODO
      }
      else {
        runInstruction(pc)
      }
    }
  }

  private def runDebugCommands(pc: Int) {
    val cmdsToRun = debugCommands.get(pc)
    cmdsToRun.map(_.foreach(_.run(machine)))
  }

  private def runInstruction(pc: Int) {
    if(instructions.isDefinedAt(pc)) {
      val ins = instructions(pc)
      ins.run(machine, labelTable)
    }
    else throw new NoSuchInstruction(pc)
  }
}
