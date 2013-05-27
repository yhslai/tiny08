Tiny08
================

An minimized assembly language we made.

Spec
---------------

```
  <<Assembly>>		         <<Machine Code>>
// Load an immediate value into Rx
LoadI Rx, #imm		00010000 xxxx0000 VVVVVVVV VVVVVVVV
// Load a value from memory address into Rx
Load  Rx, [mem]		00010100 xxxx0000 MMMMMMMM MMMMMMMM
// Save a value from Rx into memory
Store [mem], Rx		00011000 xxxx0000 MMMMMMMM MMMMMMMM
// Swap the values in Rx and Ry
Swap  Rx, Ry		11010000 xxxxyyyy 00000000 00000000
// Move the value in Ry into Rx
Move  Rx, Ry		11010001 xxxxyyyy 00000000 00000000
// Save the result of (Rx + Ry) into Rx
Add   Rx, Ry		01000000 xxxxyyyy 00000000 00000000
// Save the result of (Rx - Ry) into Rx
Sub   Rx, Ry		01010000 xxxxyyyy 00000000 00000000
// Save the result of (Rx & Ry) into Rx
And   Rx, Ry		01100000 xxxxyyyy 00000000 00000000
// Save the result of (Rx | Ry) into Rx
Or    Rx, Ry		01110000 xxxxyyyy 00000000 00000000
// Jump to label loc
Jmp   loc			10000000 00000000 LLLLLLLL LLLLLLLL
// Jump to label loc if Carry Flag is set
JmpC  loc			10000001 00000000 LLLLLLLL LLLLLLLL
// Unset Carry Flag
ClrC				10010000 00000000 00000000 00000000
// Set Carry Flag
SetC				10010001 00000000 00000000 00000000
// Set Carry Flag if (Rx < Ry)
Less  Rx, Ry		11000000 xxxxyyyy 00000000 00000000
// Set Carry Flag if (Rx == Ry)
Equal Rx, Ry		11000001 xxxxyyyy 00000000 00000000

```


Tools
---------------

### Sublime Text syntax

You can find the Sublime Text 2 syntax definition of Tiny08 [here](https://github.com/raincole/sublime-tiny08).