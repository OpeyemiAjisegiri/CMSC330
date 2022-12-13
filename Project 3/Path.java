import java.awt.Color;
import java.awt.Graphics;

public class Path extends Road{

	public Path(int newX, int newY) {
		super(newX, newY);
		//this.setWidth(80);
		//setHeight(60);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(/*getGraphics()*/g);
		/*g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());*/
		g.setColor(Color.WHITE);
		/*for(int a = LANE_HEIGHT; a < 480; a+=LANE_HEIGHT) {
			//System.out.println(LANE_HEIGHT);
			for(int b = 0; b < this.getWidth(); b+=45 ) {
				g.fillRect(b, a, 30, 5);}
		}*/
	}

}
