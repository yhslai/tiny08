package tiny08

import tiny08.statement._

object Parser {
  val factories = List(
    Add,
    LoadI,
    Label,
    Print
  )

  def parse(asms: Seq[Asm]): Seq[Statement] = {
    var addr = 0
    asms.map(asm => {
      val result = parseCode(asm.code, addr, asm.filename, asm.lineNum)
      result match {
        case ins: Instruction => if(!ins.debugger) addr += 1
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