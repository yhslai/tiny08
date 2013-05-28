package tiny08.statement

trait Instruction extends Statement {
  val debugger: Boolean
  def run()
}
