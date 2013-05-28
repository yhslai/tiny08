package script

import scala.io.Source
import tiny08.PreProcessor

object PreProcessScript {
  def main(args: Array[String]) {
    if(args.length < 1)
      throw new IllegalArgumentException("Need the assembly file name")
    
    val asmFilename = args(0)
    val sourceCode = Source.fromFile(asmFilename).mkString
    val preProcessedCode = PreProcessor.preProcess(sourceCode)
    println(preProcessedCode)
  }
}
