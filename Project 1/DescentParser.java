/***
 * Author: Opeyemi Ajisegiri
 * Course: Advance Programming Languages [CMSC 330]
 * Assignment: Project 1
 * File: DescentParser.java
 * Purpose: Accepts the file selected by the user; reading the input and tokenizing it into 
 * 		a list. The list is then parsed using a look-ahead token [LL(1)] creating a GUI 
 * 		window pane based on the content of the file.   ***/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.io.InputStream;
//import java.io.StreamTokenizer;
import java.util.LinkedList;
//import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.*;
import java.awt.*;

/** Constructor: Accepts the selected file and tokenize the content into a list. **/
public class DescentParser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(new BorderLayout());

	public DescentParser(File file) throws ParseError {
        LinkedList<String> tempList = new LinkedList<String>();
		try { 
  			 BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
  			 String line = null;
  	         while ((line = in.readLine()) != null) {	            
  	            StringTokenizer tokened = new StringTokenizer(line, " .,:;()", true);
   	            while(tokened.hasMoreTokens()) {   
   	            	tempList.add(tokened.nextToken());
   	            }
  	        }
	         in.close();
  		}catch(Exception E) {
  			JOptionPane.showMessageDialog(null, E);
  		}
		parseGUI(tempList);
	}
	
	/*** Removing the space content in the list. ***/
	private void parseSpace(LinkedList<String> list) {
		while(list.peek().matches(" "))
			list.pop();
		return;
	}
	
	/**GUI: parsing the tokens for the GUI grammar rule
	 *  "gui -> Window STRING '(' NUMBER ',' NUMBER ')' layout widgets End '.' **/
	private void parseGUI(LinkedList<String> tokens) throws ParseError {
		String appName = null;
		int numA = 0, numB =0;
		if(tokens.peek().matches("Window")) {
			tokens.pop();
			parseSpace(tokens);
			if(tokens.peek().charAt(0) == '"' && tokens.peek().charAt(tokens.peek().length()-1) == '"') {
				String tstring = tokens.peek();
				appName = tokens.pop().substring(1, tstring.length()-1);
				setTitle(appName);
				parseSpace(tokens);
				if(tokens.peek().matches("\\(")) {
					tokens.pop();
					parseSpace(tokens);
					if(tokens.peek().matches("[0-9]+")) {
						numA = Integer.parseInt(tokens.pop());
						parseSpace(tokens);
						if(tokens.peek().matches("\\,")) {
							tokens.pop();
							parseSpace(tokens);
							if(tokens.peek().matches("[0-9]+")) {
								numB = Integer.parseInt(tokens.pop());
								parseSpace(tokens);
								if(tokens.peek().matches("\\)")) {
									tokens.pop();
									parseSpace(tokens);
									if(tokens.peek().matches("Layout")) {
										setLayout(parseLayout(tokens));
										parseSpace(tokens);
										while(!tokens.peek().matches("End")) {
											add(parseWidgets(tokens, panel));
										}	
										if (tokens.peek().matches("End")) {
											tokens.pop();
											if(tokens.pop().matches("\\.")) {
												//tokens.pop();
											}else {
												throw new ParseError("Expected a token: '.'");}
										}else
											throw new ParseError("Expected keyword 'End'");
									}else
										throw new ParseError("Expected a keyword: 'Layout'");
								}else
									throw new ParseError("Expected a ')'");
							}else
								throw new ParseError("Expected an Integer");
						}else
							throw new ParseError("Expected a ','");
					}else
						throw new ParseError("Expected an Integer");
				}else
					throw new ParseError("Expected a '(' ");
			}else
				throw new ParseError("Expected a string wrapped in quotations");
			setSize(numA, numB);
			setLocation(150, 150);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(panel);
			setVisible(true);
		}
		else
			throw new ParseError("Expected Keyword: 'Window'");
		return;
	}
	
	/**Parsing the tokens for the Layout grammar rule: "Layout layout_type ':'" **/
	private LayoutManager parseLayout(LinkedList<String> tokens) throws ParseError {
		LayoutManager layout = null;
		tokens.pop();
		parseSpace(tokens);
		if(tokens.peek().matches("Flow") || tokens.peek().matches("Grid")) {
			layout = parseLayoutType(tokens);
			parseSpace(tokens);
		}else
			throw new ParseError("Expected a keyword: 'Flow' or 'Grid'");
		if(tokens.peek().matches("\\:"))
			tokens.pop();
		return layout;
	}
	
	
	/**Parsing the tokens for the LayoutType grammar rule: " Flow |
 								Grid '(' NUMBER ',' NUMBER [',' NUMBER ',' NUMBER] ')'" **/
	private LayoutManager parseLayoutType(LinkedList<String> tokens) throws ParseError {
		LayoutManager layout = null;
		if(tokens.peek().matches("Flow")) {
			layout = new FlowLayout();
			tokens.pop();
			parseSpace(tokens);
			if(tokens.peek().matches("\\:")) {
			}else
				throw new ParseError("Expected a ':'");
		}else if(tokens.peek().matches("Grid")) {
			int numA,numB,numC,numD;
			tokens.pop();
			parseSpace(tokens);
			if(tokens.peek().matches("\\(")) {
				tokens.pop();
				parseSpace(tokens);
				if(tokens.peek().matches("[0-9]+")) {
					numA = Integer.parseInt(tokens.pop());
					parseSpace(tokens);
					if(tokens.peek().matches("\\,")) {	
						tokens.pop();
						parseSpace(tokens);
						if(tokens.peek().matches("[0-9]+")) {
							numB = Integer.parseInt(tokens.pop());
							parseSpace(tokens);
							if(tokens.peek().matches("\\)")) {
								tokens.pop();
								parseSpace(tokens);
								if(tokens.peek().matches("\\:")) {
									layout = new GridLayout(numA,numB);
									parseSpace(tokens);
								}else
									throw new ParseError("Expected a token: ':'");
							}else if(tokens.peek().matches("\\,")) {	
								tokens.pop();
								parseSpace(tokens);
								if(tokens.peek().matches("[0-9]+")) {
									numC = Integer.parseInt(tokens.pop());
									parseSpace(tokens);
									if(tokens.pop().matches("\\,")) {	
										parseSpace(tokens);
										if(tokens.peek().matches("[0-9]+")) {
											numD = Integer.parseInt(tokens.pop());
											parseSpace(tokens);
											if(tokens.pop().matches("\\)")) {	
												parseSpace(tokens);
												if(tokens.peek().matches("\\:")) {
													layout = new GridLayout(numA,numB,numC,numD);
													parseSpace(tokens);
												}else
													throw new ParseError("Expected a token: ':'");
											}else
												throw new ParseError("Expected a token: ')'");
										}else
											throw new ParseError("Expected an integer");
									}else
										throw new ParseError("Expected a token: ','");
								}else
									throw new ParseError("Expected an integer");
							}else
								throw new ParseError("Expected a token: ','");
						}else
							throw new ParseError("Expected an integer");
					}else
						throw new ParseError("Expected a token: ','");
				}else
					throw new ParseError("Expected an integer");
			}else
				throw new ParseError("Expected a token: '('");
		}
		return layout;
	}
	
	/**  Parsing the tokens for the Widgets grammar rule **/
	private Component parseWidgets(LinkedList<String> tokens, JPanel panel) throws ParseError {
		/*** Recursive implementation but it uses the first keyword token last. ***/
		/*Component comp = null;
		if(tokens.peek().matches(" "))
			parseSpace(tokens);
		//Base Case
		if(tokens.peek().matches("End")) {
			return comp;
		}else {
			if(tokens.peek().matches(" "))
				parseSpace(tokens);
			comp = parseWidget(tokens);
			//System.out.println(comp.toString());
			panel.add(comp);

			parseWidgets(tokens, panel);
			return comp;
		}*/
		Component comp = null;
			parseSpace(tokens);
			comp = parseWidget(tokens);
		return comp;
	}
	
	/**Parsing the tokens for the Widget grammar rule **/
	private Component parseWidget(LinkedList<String> tokens) throws ParseError {
		Component comp = null; 
		String next = tokens.pop();
		switch(next) {
		case "Button":
			/** rule: Button STRING ';' **/
			//Assuming the popped is a button token
			JButton button = new JButton();
			String buttonLabel;
			parseSpace(tokens);
			if(tokens.peek().charAt(0) == '"' && tokens.peek().charAt(tokens.peek().length()-1) == '"') {
				String tstring = tokens.peek();
				buttonLabel = tokens.pop().substring(1, tstring.length()-1);
				parseSpace(tokens);
				if(tokens.pop().matches("\\;")) {
					button.setText(buttonLabel);
					comp = button;
				}else
					throw new ParseError("Token ';' expected");
			}else
				throw new ParseError("Expected a string");
			break;
		case "Label":
			/** rule: Label STRING ';'  **/
			//Assuming the popped is a Label token
			JLabel label = new JLabel();
			String labelString;
			if(tokens.peek().matches(" "))
				parseSpace(tokens);
			if(tokens.peek().charAt(0) == '"' && tokens.peek().charAt(tokens.peek().length()-1) == '"') {
				String tstring = tokens.peek();
				labelString = tokens.pop().substring(1, tstring.length()-1);
				parseSpace(tokens);
				if(tokens.pop().matches("\\;")) {
					label.setText(labelString);
					comp = label;
				}else
					throw new ParseError("Token ';' expected");
			}else
				throw new ParseError("Expected a string");
			break;
		case "Panel": 
			/** rule: Panel layout widgets End ';' **/
			//Assuming the popped token is a Panel token
			JPanel tempPanel = new JPanel();
			if(tokens.pop().matches("Panel"))
			parseSpace(tokens);
			if(tokens.peek().matches("Layout")) {
				tempPanel.setLayout(parseLayout(tokens));
				if(tokens.peek().matches(" "))
					parseSpace(tokens);
				/** The line below works through recursion but 
				 * uses the first token last **/
				/*  tempPanel.add(parseWidgets(tokens,tempPanel)); */
				while(!tokens.peek().matches("End")) {
					parseSpace(tokens);
					if(tokens.peek().matches("End"))
						break;
					tempPanel.add(parseWidgets(tokens,tempPanel));
				}
				parseSpace(tokens);
				if(tokens.pop().matches("End")) {
					parseSpace(tokens);
					if(tokens.pop().matches(";")) {
						comp = tempPanel;
						panel.add(tempPanel);
					}else
						throw new ParseError("Token ';' expected.");
				}else
					throw new ParseError("Expected keyword 'End'");
			}else
				throw new ParseError("Keyword 'Layout' expected.");
			break;
		case "Group":
			/** rule: Group radio_buttons End ';'  **/
			//Assuming the popped token is a Group token
			JPanel groupPanel = new JPanel();
			ButtonGroup temp = new ButtonGroup();
			if(tokens.pop().matches("Group")) //{
				parseSpace(tokens);
				groupPanel = parseRadioButtons(tokens,groupPanel, temp);
				/** The while loop below works sans recursion [and the parseButtons 
				 * returning JRadioButton] but the recursive implementation reduces
				 * the code base. To use the loop, remove the line above	 ***/
				/*while(!tokens.peek().matches("End")) {
					if(tokens.peek().matches(" "))
						parseSpace(tokens);
					if(tokens.peek().matches("End"))
						break;
					temporary = parseRadioButtons(tokens);
					temp.add((AbstractButton) temporary);
					groupPanel.add(temporary);
				}*/		
				if(tokens.pop().matches("End")) {
					parseSpace(tokens);
					if(tokens.pop().matches(";")) {
						comp = groupPanel;
						panel.add(groupPanel);
					}else
						throw new ParseError("Token ';' expected.");
				}else 
					throw new ParseError("Expected keyword 'End'");
			/*}else
				throw new ParseError("Expected Keyword 'Group'");*/
			break;
		case "Textfield":
			/** rule: Textfield NUMBER ';'  **/
			//Assuming the popped token is a Textfield token
			JTextField text = new JTextField();
			int num;
			parseSpace(tokens);
			 if(tokens.peek().matches("[0-9]+")) {
					num = Integer.parseInt(tokens.pop());
				parseSpace(tokens);
				if(tokens.pop().matches("\\;")) {
					text.setColumns(num);
					comp = text;
				}else
					throw new ParseError("Token ';' expected.");
			}else
				throw new ParseError("Expected an integer.");
			break;
		default: 
			throw new ParseError("Unknown keyword: '"+ tokens.pop()+"'");
		}
		return comp;
	}
	
	/**  Parsing the tokens for the Radio Buttons grammar rule: " radio_button radio_buttons | radio_button" **/
	private /*JRadioButton*/ JPanel parseRadioButtons(LinkedList<String> tokens, JPanel panel, ButtonGroup group) 
																throws ParseError {
		JRadioButton radButton = null;
		parseSpace(tokens);
		//Base Case
		if(tokens.peek().matches("End")) {
			return panel;
		}else {
			parseSpace(tokens);
			radButton = parseRadioButton(tokens);
			group.add(radButton);
			panel.add(radButton);
			parseRadioButtons(tokens,panel,group);
		}
		return panel;
		/*** Iterative implementation**/
		/*if(tokens.peek().matches(" "))
			parseSpace(tokens);
		radButton = parseRadioButton(tokens);
		return radButton;*/
	}
	
	/**  Parsing the tokens for the Radio Button grammar rule: "Radio STRING ';'" **/
	private JRadioButton parseRadioButton(LinkedList<String> tokens) throws ParseError {
		JRadioButton radButton = new JRadioButton();
		String label;
		parseSpace(tokens);
		if(tokens.pop().matches("Radio")) {
				parseSpace(tokens);
			if(tokens.peek().charAt(0) == '"' && tokens.peek().charAt(tokens.peek().length()-1) == '"') {
				String temp = tokens.peek();
				label = tokens.pop().substring(1, temp.length()-1);
				parseSpace(tokens);
				if(tokens.peek().matches("\\;")) {
					tokens.pop();
					radButton.setText(label);
					return radButton;
				}else
					throw new ParseError("Token ';' expected");
			}else
				throw new ParseError("Expected a string");
		}else
			throw new ParseError("Expected 'Radio' keyword");
	}
}
