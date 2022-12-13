import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sedan extends Vehicle{
	Image image;
    //private Graphics g;
	public Sedan(int newX, int newY) {
		super(newX,newY);
		this.setWidth(80);
		setHeight(60);
		setSpeed(7);
		try {
			image = ImageIO.read(new File("src/car.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			//paintMe(g);
		}
	}
	
	
	public void paintMe(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		//g.drawImage(image, getY(), getWidth(), null);
	}
}
