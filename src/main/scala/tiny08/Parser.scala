package tiny08

import tiny08.statement._
import tiny08.exception.ParseError

object Parser {
  val factories = List(
    Label,
    Load, LoadI, LoadR, Save, SaveR,
    Add, Sub, Mul, Div, Mod, And, Or,
    Swap, Move,
    Jmp, JmpC, JmpNC,
    Less, Equal, Test,
    ClrC, SetC,
    Print, PrintM, Dump, Break,
    Exit
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

    try {
      val statement = statementIter.flatten.find(_ => true)

      statement match {
        case Some(s) => s
        case None => throw new ParseError(code, "instruction")
      }
    }
    catch {
      case e: ParseError => {
        println(s"Parse error at $filename:$lineNum")
        throw e
      }
      case e: Throwable => throw e
    }
  }
}

