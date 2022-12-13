import java.awt.Color;
import java.awt.Graphics;

public class Sport extends Vehicle{
	public Sport(int newX, int newY) {
		super(newX,newY);
		setWidth(58);
		setHeight(50);
		setSpeed(10);
	}
	
	
	public void paintMe(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
