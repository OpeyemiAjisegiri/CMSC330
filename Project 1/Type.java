/***
 * Author: Opeyemi Ajisegiri
 * Course: Advance Programming Languages [CMSC 330]
 * Assignment: Project 1
 * File: Type.java
 * Purpose: Declares an enum type to determine the type of token to be parsed  **/

/*public*/ enum Type {
	Button,		//JButton
	COLON,		//Ends Layouts
	COMMA,		//Separates dimension's numerical value
	End, 		//Ends  Widgets and or Windows GUI
	EOF,		//End of file
	Flow,		//Flow Layout
	Grid,		//Grid Layout
	Group,		//Groups Radio Buttons
	Label, 		//JLabel
	Layout,		//Layout token
	LPAREN,		//Left Parenthesis
	NUMBER,		//Numeric values
	Panel,		//GUI Sub-Division
	PERIOD,		//Ends input
	Radio,		//Radio Buttons token
	RPAREN,		//Right Parenthesis
	SEMICOLON,	//Ends Lines
	STRING,		//Titles
	Textfield,	//JTextField
	UNKNOWN,	//Unknown Instruction
	Widget,		//Widgets token
	Window		//Windows token
}