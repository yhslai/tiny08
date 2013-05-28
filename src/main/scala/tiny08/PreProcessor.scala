package tiny08

case class Asm(code: String, filename: String, lineNum: Int)

object PreProcessor {

  def preProcess(asm: String, filename: String): Seq[Asm] = {
    val asms = asm.split("\n").zipWithIndex.map{ case (code, i) => Asm(code, filename, i) }
    removeBlankLines(
      removeRedundantSpaces(
        removeComments(asms)
      )
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

}
