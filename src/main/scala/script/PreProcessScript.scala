package script

import scala.io.Source
import tiny08.{Asm, PreProcessor}

object PreProcessScript {
  def main(args: Array[String]) {
    if(args.length < 1)
      throw new IllegalArgumentException("Need the assembly file name")

    val asmFilename = args(0)
    val preProcessedAsm = PreProcessor.preProcess(asmFilename)
    printAsm(preProcessedAsm)
  }

  private def printAsm(asms: Seq[Asm]) {
    println(asms.map(_.code).mkString("\n"))
  }
}
