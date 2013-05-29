Tiny08
================

An minimized assembly language we made.

Spec
---------------

```
  <<Assembly>>		         <<Machine Code>>				        <<Description>>
LoadI Rx, #imm		00010000 xxxx0000 VVVVVVVV VVVVVVVV		// Load an immediate value into Rx
Load  Rx, [mem]		00010100 xxxx0000 MMMMMMMM MMMMMMMM		// Load a value from memory address mem into Rx
Store [mem], Rx		00011000 xxxx0000 MMMMMMMM MMMMMMMM		// Save a value from Rx into memory address mem
Swap  Rx, Ry		11010000 xxxxyyyy 00000000 00000000		// Swap the values in Rx and Ry
Move  Rx, Ry		11010001 xxxxyyyy 00000000 00000000		// Move the value in Ry into Rx
Add   Rx, Ry		01000000 xxxxyyyy 00000000 00000000		// Save the result of (Rx + Ry) into Rx
Sub   Rx, Ry		01010000 xxxxyyyy 00000000 00000000		// Save the result of (Rx - Ry) into Rx
And   Rx, Ry		01100000 xxxxyyyy 00000000 00000000		// Save the result of (Rx & Ry) into Rx
Or    Rx, Ry		01110000 xxxxyyyy 00000000 00000000		// Save the result of (Rx | Ry) into Rx
Mul   Rx, Ry		11010000 xxxxyyyy 00000000 00000000		// Save the result of (Rx * Ry) into Rx
Div   Rx, Ry		11100000 xxxxyyyy 00000000 00000000		// Save the result of (Rx / Ry) into Rx
Jmp   label			10000000 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc
JmpC  label			10000001 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is set
JmpNC label			10000010 0000000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is not set
ClrC				10010000 00000000 00000000 00000000		// Unset Carry Flag
SetC				10010001 00000000 00000000 00000000		// Set Carry Flag
Less  Rx, Ry		11000000 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx < Ry), clear it otherwise
Equal Rx, Ry		11000001 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx == Ry), clear it otherwise
Test  Rx, #B		11110000 xxxxBBBB 00000000 00000000		// If the Bth bit of Rx is 1, set Carry Flag, clear it otherwise

```

Debug Commands(only work on simulator)
-------------------

```
%print Rx			// Print the content of Rx to STDOUT
%printm [mem]		// Print the content of memory address mem
%dump				// Dump all the contents of R0-R15
%break				// Breakpoint
```

Other Syntax
------------------

```
:label				// Indicate a label to be used in Jmp/JmpC/JmpNC
#include "file"		// Include the instructions of another assembly file
```


Tools
---------------

### Sublime Text syntax

You can find the Sublime Text 2 syntax definition of Tiny08 [here](https://github.com/raincole/sublime-tiny08).
