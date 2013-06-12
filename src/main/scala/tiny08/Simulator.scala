package tiny08

import tiny08.statement._
import tiny08.exception.NoSuchInstruction
import java.util.Scanner
import tiny08.util.HexDecConversion._

class Simulator(statements: Seq[Statement], machine: Machine, verbose: Boolean = false) {
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
        inputExpression()
        machine.resume()
        runInstruction(pc)
      }
      else {
        runInstruction(pc)
      }
    }
  }

  // Only for debugging
  private def inputExpression() {
    val scanner = new Scanner(System.in)

    print("Operand 1: ")
    val op1 = scanner.nextLine()
    setOperator(op1, "0x0000".tryToInt)

    print("Operand 2: ")
    val op2 = scanner.nextLine()
    setOperator(op2, "0x0008".tryToInt)

    print("Operator: ")
    val operator = scanner.nextLine()
    val operatorAddress = "0x0010".tryToInt

    operator match {
      case "+" | "add" => machine.setMemory(operatorAddress, 1)
      case "-" | "sub" => machine.setMemory(operatorAddress, 2)
      case "*" | "sub" => machine.setMemory(operatorAddress, 3)
      case "/" | "sub" => machine.setMemory(operatorAddress, 4)
      case "sqrt"      => machine.setMemory(operatorAddress, 5)
    }
  }

  private def setOperator(op: String, addr: Int) {
    var tmp = op.stripMargin
    val negative = tmp.head == '-'
    if(negative) {
      tmp = tmp.tail
      machine.setMemory(addr+6, 1)
      println(s"Set ${(addr+6).toHexStr} to 1")
    }

    val point = if(tmp.indexOf('.') != -1) tmp.length - tmp.indexOf('.') - 1
                else 0
    tmp = tmp.replaceAll("\\.", "")
    machine.setMemory(addr+7, point)
    println(s"Set ${(addr+7).toHexStr} to $point")

    val divided = tmp.reverse.grouped(2).map(_.reverse.toInt)
    divided.zipWithIndex.foreach { case (num, i) => {
      machine.setMemory(addr+i, num)
      println(s"Set ${(addr+i).toHexStr} to $num")
    }}
  }

  private def runDebugCommands(pc: Int) {
    val cmdsToRun = debugCommands.get(pc)
    cmdsToRun.map(_.foreach(_.run(machine)))
  }

  private def runInstruction(pc: Int) {
    if(instructions.isDefinedAt(pc)) {
      val ins = instructions(pc)
      if(verbose) println(s"Running ${ins.filename}:${ins.lineNum}: $ins")
      ins.run(machine, labelTable)
    }
    else throw new NoSuchInstruction(pc)
  }
}
