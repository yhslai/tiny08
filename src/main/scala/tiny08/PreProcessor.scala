package tiny08

object PreProcessor {
  def preProcess(asm: String): String = {
    """(?m)^\s*\n""".r.replaceAllIn(asm, "").trim
  }
}
