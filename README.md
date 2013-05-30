Tiny08
================

An minimized assembly language we made.


Try It Out
--------------

For now, you need [sbt](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html), [Scala](http://www.scala-lang.org/) and Java Runtime(I will publish a packaged jar soon).

Example:

    sbt run src/test/resources/big_add.tiny08


Spec
---------------

### Instructions

```
  <<Assembly>>		         <<Machine Code>>				        <<Description>>
LoadI Rx, #imm		00010000 xxxx0000 VVVVVVVV VVVVVVVV		// Load an immediate value into Rx
Load  Rx, [0xMEM]	00010100 xxxx0000 MMMMMMMM MMMMMMMM		// Load a value from memory address MEM into Rx
LoadR Rx, [Ry]    	00010101 xxxxyyyy 00000000 00000000		// Load a value from memory address Ry into Rx
Save  [0xMEM], Rx	00011000 xxxx0000 MMMMMMMM MMMMMMMM		// Save a value from Rx into memory address MEM
SaveR [Ry], Rx     	00011001 xxxxyyyy 00000000 00000000		// Save a value from Rx into memory address Ry
Swap  Rx, Ry		11010000 xxxxyyyy 00000000 00000000		// Swap the values in Rx and Ry
Move  Rx, Ry		11010001 xxxxyyyy 00000000 00000000		// Move the value in Ry into Rx
Add   Rx, Ry		01000000 xxxxyyyy 00000000 00000000		// Save the result of (Rx + Ry) into Rx
Sub   Rx, Ry		01010000 xxxxyyyy 00000000 00000000		// Save the result of (Rx - Ry) into Rx
And   Rx, Ry		01100000 xxxxyyyy 00000000 00000000		// Save the result of (Rx & Ry) into Rx
Or    Rx, Ry		01110000 xxxxyyyy 00000000 00000000		// Save the result of (Rx | Ry) into Rx
Mul   Rx, Ry		11010000 xxxxyyyy 00000000 00000000		// Save the result of (Rx * Ry) into Rx
Div   Rx, Ry		11100000 xxxxyyyy 00000000 00000000		// Save the result of (Rx / Ry) into Rx
Mod   Rx, Ry       	11110000 xxxxyyyy 00000000 00000000		// Save the result of (Rx % Ry) into Rx
Jmp   label			10000000 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc
JmpC  label			10000001 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is set
JmpNC label			10000010 00000000 LLLLLLLL LLLLLLLL		// Jump to label loc if Carry Flag is not set
ClrC				10010000 00000000 00000000 00000000		// Unset Carry Flag
SetC				10010001 00000000 00000000 00000000		// Set Carry Flag
Less  Rx, Ry		11000000 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx < Ry), clear it otherwise
Equal Rx, Ry		11000001 xxxxyyyy 00000000 00000000		// Set Carry Flag if (Rx == Ry), clear it otherwise
Test  Rx, #B		11110000 xxxxBBBB 00000000 00000000		// If the Bth bit of Rx is 1, set Carry Flag, clear it otherwise
Exit  				00000000 00000000 00000000 00000000		// Exit the program

```

### Debug Commands(only for the simulator)


```
%print Rx			// Print the content of Rx to STDOUT
%printm [0xMEM]		// Print the content of memory address MEM
%dump				// Dump all the contents of R0-R15
%break				// Breakpoint (Unimplementeds)
```

### Other Syntax


```
:label				// Indicate a label to be used in Jmp/JmpC/JmpNC
#include "file"		// Include the instructions of another assembly file
```

Machine Spec
---------------

* As described [above](#instructions), all instructions are 32bit.
* All data or memory addresses are 16bit, or 2byte. It's the minimum unit to access memory.
* Consequently, every instruction occupy two memory address.
* Numeric data is signed(-32768 ~ 32767), addresses are unsigned(0 ~ 65535, or 0x0000 ~ 0xFFFF).


Tools
---------------

### Sublime Text syntax

You can find the Sublime Text 2 syntax definition of Tiny08 [here](https://github.com/raincole/sublime-tiny08).
