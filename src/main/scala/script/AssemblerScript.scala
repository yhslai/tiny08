package script

import tiny08._
import java.io.FileOutputStream

object AssemblerScript {
  def main(args: Array[String]) {
    if(args.length < 1)
      throw new IllegalArgumentException("Need the assembly file name")
    if(args.length < 2)
      throw new IllegalArgumentException("Need the output file name")

    val asmFilename = args(0)
    val outputFilename = args(1)
    val preProcessedAsm = PreProcessor.preProcess(asmFilename)
    val parsedStatements = Parser.parse(preProcessedAsm)
    val machineCode = Assembler.assemble(parsedStatements)

    val fos = new FileOutputStream(outputFilename)
    fos.write(machineCode.toArray)
    fos.close()

  }
}
