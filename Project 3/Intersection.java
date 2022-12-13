import java.awt.Color;
import java.awt.Graphics;

public class Intersection extends Road {
	public Intersection(int newX, int newY) {
		super(newX, newY);
		//this.setWidth(80);
		setHeight(60);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(/*getGraphics()*/g);
		/*g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());*/

		
	}
}
