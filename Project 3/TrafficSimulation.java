import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TrafficSimulation extends JPanel{
	
	final int LANE_HEIGHT = 120;
	final int ROAD_WIDTH = 250;
	private static int carcount = 0;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	ArrayList<Road> roads = new ArrayList<Road>();
    
	
	
	public TrafficSimulation(){
		super();
	}
	
	public int getCarCount() {
		return this.carcount;
	}
	
	public static void resetCarCount() {carcount = 0;}
	
	public void addCar(Vehicle veh) {
		cars.add(veh);
	}
	
	public void addRoad(Road r) {
		//Path path = new Path(1000,420 /*getHeight()*/);
		//Intersection intersect = new Intersection(400, 420);
		//Road intersection = new Road(r);
		//r.setX(roads.get(roads.size())+1000);
		roads.add(r);
		//roads.add(path);
		//roads.add(intersect);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(/*getGraphics()*/g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for(int a = LANE_HEIGHT; a < 480; a+=LANE_HEIGHT) {
			for(int b = 0; b < this.getWidth(); b+=45 ) {
				g.fillRect(b, a, 30, 5);}
		}
		/*for (int a= ROAD_WIDTH; a<1000; a+= ROAD_WIDTH) {
			for(int b = 0; b< this.getWidth(); b+= 70) {
			g.fillRect(b, a, 30, getHeight());
			//roads.get(a).getTrafficLight().paintComponent(getGraphics());
			}
			}*/
	
		for(int a = 0; a < cars.size(); a++) {
			cars.get(a).paintMe(g);
		}
		for(int a = 0; a < roads.size(); a++) {
			roads.get(a).paintMe(g);
		}
	}

	public void step() {
		for(int a = 0;a < cars.size();a++) {
			Vehicle veh = cars.get(a);
			if(collision(veh.getX() + veh.getSpeed(), veh.getWidth(), veh ) == false) {
				veh.setX(veh.getX() + veh.getSpeed());
				if(veh.getX() > ROAD_WIDTH) {
					veh.setX(0);
					if(collision(0 + veh.getY(), veh.getWidth(), veh ) == false) {
						veh.setX(0);
						carcount++;
					}
				}
			}
		}
		
	}
	
	public boolean collision(int x, int y, Vehicle v) {
		for(int a = 0;a < cars.size(); a++) {
			Vehicle u =cars.get(a);
			if(y == u.getY()) {
				if(u.equals(v) == false) {
					if(x <u.getX() + u.getWidth() && x + v.getWidth() > u.getX()) {
						return true; 
					}
				}
			}
		}
		return false;
		
	}
	
}
