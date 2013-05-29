package tiny08.exception

import tiny08.statement.Statement

trait RuntimeError extends Throwable {
}

class RegisterOverflowError(r: Int, value: Int) extends RuntimeError {
  override def toString = {
    s"Register overflow: R$r #$value"
  }
}

class MemoryOverflowError(mem: Int, value: Int) extends RuntimeError {
  override def toString = {
    s"Memory overflow: [$mem] #$value"
  }
}

class NoSuchRegisterError(r: Int) extends RuntimeError {
  override def toString = {
    s"No such Register: R$r"
  }
}

class NoSuchMemoryError(mem: Int) extends RuntimeError {
  override def toString = {
    s"No such memory address: [$mem]"
  }
}

class NoSuchInstruction(pc: Int) extends RuntimeError {
  override def toString = {
    s"No such instruction: PC = $pc"
  }
}