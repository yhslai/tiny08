package tiny08

import tiny08.statement._

object Parser {
  val factories = List(
    Label,
    Load, LoadI, Store,
    Add, Sub, Mul, Div, And, Or,
    Swap, Move,
    Jmp, JmpC, JmpNC,
    Less, Equal, Test,
    ClrC, SetC,
    Print, PrintM, Dump, Break
  )

  def parse(asms: Seq[Asm]): Seq[Statement] = {
    var addr = 0
    asms.map(asm => {
      val result = parseCode(asm.code, addr, asm.filename, asm.lineNum)
      result match {
        case ins: Instruction => addr += 2
        case _ =>
      }
      result
    })
  }

  private def parseCode(code: String, addr: Int, filename: String, lineNum: Int): Statement = {
    val iter = factories.iterator
    val statementIter = iter.map(factory => factory(code, addr, filename, lineNum))
    val statement = statementIter.flatten.find(_ => true)

    statement match {
      case Some(s) => s
      case None => throw new ParseError(code, filename, lineNum)
    }

  }
}

class ParseError(code: String, filename: String, lineNum: Int) extends Throwable {
  override def toString: String = {
   "Can't parse this line of assembly:\n" +
    s"$filename:$lineNum: " + '"' + s"$code" + '"'
  }
}