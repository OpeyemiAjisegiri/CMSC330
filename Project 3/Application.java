import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Application extends JFrame implements ActionListener, Runnable{
	boolean running = false;
	int carcount = 0; long startTime = 0;
	TrafficSimulation traffic = new TrafficSimulation();
	JButton start = new JButton("Start"), stop = new JButton("Stop"),
			pause = new JButton("pause"), resume = new JButton("Resume"),
			addCar = new JButton("Add Car"), addIntersection = new JButton("Add Intersection");
	Container south = new Container(), left = new Container(), right = new Container();
	JLabel time = new JLabel("Time: "), throughPut = new JLabel("ThoroughPut: ");
	
	public Application() {
		super("Traffic Simulation");
		setLayout(new BorderLayout());
		add(traffic, BorderLayout.CENTER);
		/** LEFT CONTAINER	**/
		left.setLayout(new GridLayout(2,1));
		left.add(addIntersection);
		addIntersection.addActionListener(this);
		left.add(addCar);
		addCar.addActionListener(this);
		add(left, BorderLayout.WEST);
		/**  RIGHT CONTAINER**/
		right.setLayout(new GridLayout(4,1));
		right.add(start);
		start.addActionListener(this);
		right.add(pause);
		pause.addActionListener(this);
		right.add(resume);
		resume.addActionListener(this);
		right.add(stop);
		stop.addActionListener(this);
		add(right, BorderLayout.EAST);
		/**  SOUTH CONNTAINER  **/
		south.setLayout(new GridLayout(1,4));
		south.add(time);
		south.add(throughPut);
		add(south,BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
		
		
		/*Sedan test = new Sedan(90,150);
		traffic.addCar(test);
		//add(traffic);
		
		Sport test2 = new Sport(50,300);
		traffic.addCar(test2);
		//add(traffic);
		//repaint();
		repaint();*/
	}
	
	public static void main(String[] arg) {
		 // SwingUtilities.invokeLater(new Runnable() {} 
			  new Application();
		//}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(start)) {
			if(running == false) {
				running = true;
				TrafficSimulation.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if(e.getSource().equals(stop)) {
			running = false;
		}
		if(e.getSource().equals(addCar)) {
			Sedan sedan = new Sedan(0,20);
			traffic.addCar(sedan);
			for(int x = 0; x< 100  /*traffic.traffic_WIDTH*/ ; x+=40){
				for(int y = 40; y < 480 /*600*/ ; y+=120) {
					sedan.setX(x);
					sedan.setY(y);
					if(traffic.collision(x, y, sedan) == false) {
						repaint();
						return;
					}
				}
			}
		}
		if(e.getSource().equals(addIntersection)) {
			Road intersection = new Road(/*1000*/0,20/*getHeight()*/);
			traffic.addRoad(intersection);
			intersection.getTrafficLight().paintComponent(getGraphics());
			//traffic.add(intersection.getTrafficLight().paintComponent(getGraphics()));
		}
		if(e.getSource().equals(pause)) {
			Thread t = new Thread(this);
			//implement wait and notify methods.
			if(running == true) {
				running = false;
				//Thread t = new Thread(this);
				t.suspend();
			}
		 if(e.getSource().equals(resume) && running == false) {
				//if(running == false) {
					running = true;
					//Thread t = new Thread(this);
					//t.start();
					t.resume();
					//resume();
				//}
				//implement wait and notify methods.
			}
		}
	}

	@Override
	public void run() {
		while(running == true) {
			traffic.step();
			carcount =traffic.getCarCount();
			double tpCalc = carcount/ (System.currentTimeMillis() - startTime);
			throughPut.setText("ThroughPut: "+tpCalc);
			time.setText("Simulation Time: "+(System.currentTimeMillis() - startTime)+" seconds");
			repaint();
			try {
				Thread.sleep(100);
			}catch(InterruptedException exc) { 
		        System.out.println(exc); 
			}
		}
	}

}
