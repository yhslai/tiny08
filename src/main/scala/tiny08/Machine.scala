package tiny08

import tiny08.exception._

class Machine {
  val UpperBound = 65535
  val LowerBound = -65536
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

  def getRegister(idx: Int): Int = {
    if(registers.isDefinedAt(idx)) registers(idx)
    else throw new NoSuchRegisterError(idx)
  }

  def setRegister(r: Int, value: Int) {
    if(value > UpperBound || value < LowerBound) throw new RegisterOverflowError(r, value)
    else registers = registers.updated(r, value)
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



