/***
 * Author: Opeyemi Ajisegiri
 * Course: Advance Programming Languages [CMSC 330]
 * Assignment: Project 1
 * File: Application.java
 * Purpose: The user is given a directory to select a file from; 
 * 		the selected file should contain the language to be parsed.  **/
import java.io.*;
import java.util.*;
import javax.swing.*;
public class Application extends JFileChooser {
	
	private JFileChooser chooser;
	private JButton  open = new JButton();
	
	public Application() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Recursive Descent Parser: Input File Selector");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
	        try {
				DescentParser newP = new DescentParser(file);
			} catch (ParseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	

	public static void main(String[] args) {
		new Application();		
	}

}

/**
 * An object of type ParseError represents a syntax error found in 
 * the input read from file.
 */
/*private static*/ class ParseError extends Exception {
	private static final long serialVersionUID = 1L;
	
	ParseError(String message) {
	      super(message);
	   }
} // end nested class ParseError
