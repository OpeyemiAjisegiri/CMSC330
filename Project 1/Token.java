/***
 * Author: Opeyemi Ajisegiri
 * Course: Advance Programming Languages [CMSC 330]
 * Assignment: Project 1
 * File: Token.java
 * Purpose: This class file creates an object of token and uses the enum 
 * 			Type to determine the type of token to be parsed.  ***/

final class Token {

	private Type type;
	private String token;
	private int number;
	
	/** Constructors **/
	Token(){
		this.type = Type.UNKNOWN;
		this.token = "";
		this.number = 0;
	}
	
	Token(Type type){
		this.setType(type);
	}
	
	Token(String Token){
		this.token = Token;
	}
	
	Token(Type type, String Token){
		this.setType(type);
		this.setToken(Token);
		//this.setNumber(NaN);
	}
	
	Token(Type type, int Number){
		this.setType(type);
		this.setNumber(Number);
	}
	
	Token(Type Type, String Token, int Number){
		/*this.type = Type;
		this.token = Token;
		this.number = Number;*/
		
		this.setType(Type);
		this.setToken(Token);
		this.setNumber(Number);
		
	}
	
	/**  Setters   **/
	private void setType(Type type) { this.type = type; }
	
	private void setToken(String token) { this.token = token; }
	
	private void setNumber(int number) {  this.number = number; }
	
	/**  Getters   **/
	protected Type getType() { return this.type; }
	
	protected String getToken() {  return this.token; }
	
	protected int getNumber() {  return this.number; }
}
