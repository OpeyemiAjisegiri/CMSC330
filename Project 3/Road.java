import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Road /*extends JComponent*/{
	//final int LANE_HEIGHT = /*120*/240;
	//final int ROAD_WIDTH = 800;
	private int x;
	private int y;
	/*private*/final int width = 40;
	private int height;
	
	private TrafficLight light = new TrafficLight();
	
	public Road(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	
	public void paintMe(Graphics g) {	
		g.setColor(Color.WHITE);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		//g.drawLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public void setX(int newX) {this.x = newX;}
	public void setY(int newY) {this.y = newY;}
	//public void setWidth(int newW) {this.width = newW;}
	public void setHeight(int newH) {this.height = newH;}

	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public TrafficLight getTrafficLight() {return this.light;}


	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
