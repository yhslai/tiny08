Tiny08
================

An minimized assembly language we made.

Spec
---------------

```
  <<Assembly>>		         <<Machine Code>>				        <<Description>>
LoadI Rx, #imm		00010000 xxxx0000 VVVVVVVV VVVVVVVV		// Load an immediate value into Rx
Load  Rx, [Ry]		00010100 xxxxyyyy 00000000 00000000		// Load a value from memory address Ry into Rx
Store [Ry], Rx		00011000 xxxxyyyy 00000000 00000000		// Save a value from Rx into memory address Ry
Swap  Rx, Ry		11010000 xxxxyyyy 00000000 00000000		// Swap the values in Rx and Ry
Move  Rx, Ry		11010001 xxxxyyyy 00000000 00000000		// Move the value in Ry into Rx
Add   Rx, Ry		01000000 xxxxyyyy 00000000 00000000		// Save the result of (Rx + Ry) into Rx
Sub   Rx, Ry		01010000 xxxxyyyy 00000000 00000000		// Save the result of (Rx - Ry) into Rx
And   Rx, Ry		01100000 xxxxyyyy 00000000 00000000		// Save the result of (Rx & Ry) into Rx
Or    Rx, Ry		01110000 xxxxyyyy 00000000 00000000		// Save the result of (Rx | Ry) into Rx
Jmp   loc			10000000 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc
JmpC  loc			10000001 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is set
JmpNC loc			10000010 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is not set
ClrC				10010000 00000000 00000000 00000000		// Unset Carry Flag
SetC				10010001 00000000 00000000 00000000		// Set Carry Flag
Less  Rx, Ry		11000000 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx < Ry)
Equal Rx, Ry		11000001 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx == Ry)

```


Tools
---------------

### Sublime Text syntax

You can find the Sublime Text 2 syntax definition of Tiny08 [here](https://github.com/raincole/sublime-tiny08).
