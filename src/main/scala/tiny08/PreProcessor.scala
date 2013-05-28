package tiny08

object PreProcessor {
  def preProcess(asm: String): String = {
    """^\s*\n""".r.replaceAllIn(asm, "")
  }
}
