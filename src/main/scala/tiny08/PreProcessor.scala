package tiny08

object PreProcessor {

  def preProcess(asm: String): String = {
    removeBlankLines(
      removeRedundantSpaces(
        removeComments(asm)
      )
    )
  }

  private def removeBlankLines(s: String) = {
    """(?m)^\s*\n""".r.replaceAllIn(s, "").trim
  }

  private def removeComments(s: String) = {
    """(?m)//.*$""".r.replaceAllIn(s, "")
  }

  private def removeRedundantSpaces(s: String) = {
    val removedLeadingSpace = """(?m)^[^\S\n]+""".r.replaceAllIn(s, "")
    """(?m)[^\S\n]+""".r.replaceAllIn(removedLeadingSpace, " ")
  }

}
