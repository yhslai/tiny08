package tiny08.statement

trait Statement {
  val address: Int
  val filename: String
  val lineNum: Int
}

trait StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Statement]
}
