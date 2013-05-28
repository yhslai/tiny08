package tiny08

import scala.io.Source
import java.io.File

case class Asm(code: String, filename: String, lineNum: Int)

object PreProcessor {

  def preProcess(filename: String): Seq[Asm] = {
    val sourceDir = new File(filename).getParent
    val sourceCode = Source.fromFile(filename).mkString
    val asms = sourceCode.split("\n").zipWithIndex.map{ case (code, i) => Asm(code, filename, i) }
    includeOtherFiles(
      removeBlankLines(
        removeRedundantSpaces(
          removeComments(asms)
        )
      ), sourceDir
    )
  }

  private def removeComments(asms: Seq[Asm]) = {
    asms.map(asm =>
      asm.copy(code = """(?m)//.*$""".r.replaceAllIn(asm.code, ""))
    )
  }

  private def removeRedundantSpaces(asms: Seq[Asm]) = {
    asms.map(asm => {
      val removedLeadingSpace = """(?m)^[^\S\n]+""".r.replaceAllIn(asm.code, "")
      asm.copy(code = """(?m)[^\S\n]+""".r.replaceAllIn(removedLeadingSpace, " "))
    })
  }

  private def removeBlankLines(asms: Seq[Asm]) = {
    asms.filterNot(asm => asm.code.matches("""^\s*$""")).toVector
  }

  private def includeOtherFiles(asms: Seq[Asm], sourceDir: String) = {
    val pattern = """#include\s+"([^"]+)"""".r
    asms.flatMap{
      case Asm(pattern(otherFilename), _, _) => preProcess(s"$sourceDir/$otherFilename.tiny08")
      case asm: Asm => List(asm)
    }
  }

}
