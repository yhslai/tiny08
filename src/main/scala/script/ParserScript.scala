package script

import tiny08.{Asm, PreProcessor, Parser}
import tiny08.statement.Statement

object ParserScript {
  def main(args: Array[String]) {
    if(args.length < 1)
      throw new IllegalArgumentException("Need the assembly file name")

    val asmFilename = args(0)
    val preProcessedAsm = PreProcessor.preProcess(asmFilename)
    val parsedStatements = Parser.parse(preProcessedAsm)
    printStatements(parsedStatements)
  }

  private def printStatements(statements: Seq[Statement]) {
    println(statements.mkString("\n"))
  }
}
