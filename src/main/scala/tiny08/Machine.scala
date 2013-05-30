package tiny08

import tiny08.exception._

class Machine {
  private var _programCounter = 0
  private var registers = Vector.fill(16)(0)
  private var memory = Vector.fill(65536)(0)
  private var _carryFlag = false
  private var _isStopped = false
  private var _isHalted = false

  def programCounter = _programCounter

  def programCounter_=(pc: Int) {
    _programCounter = pc
  }

  def getRegister(r: Int): Int = {
    if(registers.isDefinedAt(r)) registers(r)
    else throw new NoSuchRegisterError(r)
  }

  def setRegister(r: Int, value: Int) {
    if(registers.isDefinedAt(r)) {
      if(is16bit(value)) registers = registers.updated(r, value)
      else throw new RegisterOverflowError(r, value)
    }
    else throw new NoSuchRegisterError(r)
  }

  def getMemory(mem: Int): Int = {
    if(memory.isDefinedAt(mem)) memory(mem)
    else throw new NoSuchMemoryError(mem)
  }

  def setMemory(mem: Int, value: Int) {
    if(registers.isDefinedAt(mem)) {
      if(is16bit(value)) memory = memory.updated(mem, value)
      else throw new MemoryOverflowError(mem, value)
    }
    else throw new NoSuchMemoryError(mem)
  }

  private def is16bit(value: Int): Boolean = {
    value < (1 << 15) && value >= -(1 << 15)
  }

  def carryFlag = _carryFlag

  def setCarryFlag() {
    _carryFlag = true
  }

  def clearCarryFlag() {
    _carryFlag = false
  }

  def isStop = _isStopped

  def break() {
    _isStopped = true
  }

  def resume() {
    _isStopped = false
  }

  def isHalted = _isHalted

  def halt() {
    _isHalted = true
  }
}



