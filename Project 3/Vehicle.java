import java.awt.Graphics;

public class Vehicle {
	private int x;
	private int y;
	private int width = 0;
	private int height = 0;
	private int speed = 0;
	

	public Vehicle(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	public void paintMe(Graphics g) {}
	
	public void setX(int newX) {this.x = newX;}
	public void setY(int newY) {this.y = newY;}
	public void setWidth(int newW) {this.width = newW;}
	public void setHeight(int newH) {this.height = newH;}
	public void setSpeed(int newSpeed) {this.speed = newSpeed;}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int getSpeed() {return this.speed;}

	

}
