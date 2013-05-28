package tiny08

object PreProcessor {

  def preProcess(asm: String): String = {
    removeBlankLines(
      removeComments(asm)
    )
  }

  private def removeBlankLines(s: String) = {
    """(?m)^\s*\n""".r.replaceAllIn(s, "").trim
  }

  private def removeComments(s: String) = {
    """(?m)//.*$""".r.replaceAllIn(s, "")
  }

}
