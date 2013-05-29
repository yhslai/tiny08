package tiny08.statement

class Label(val address: Int, val name: String, val filename: String, val lineNum: Int)
  extends Statement{

  override def toString = {
    s":Label '$name':\tat $address"
  }
}

object Label extends StatementFactory {
  def apply(code: String, addr: Int, filename: String, lineNum: Int): Option[Label] = {
    val pattern = """([^:]+):""".r
    code match {
      case pattern(name) => Some(new Label(addr, name, filename, lineNum))
      case _ => None
    }
  }
}
