/*
 * Name: Adam Kaplan
 * 
 * NetID: akaplan6
 * 
 * Project: #4
 * 
 * Lab Section: TR 4:50PM - 6:05PM (I switched my lab section)
 * 
 * TA: Charlie Kelman
 * 
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own.
 */

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame{
	boolean switching = false;
	
	public Main(Graph g, boolean directions, boolean meridianmap, String i1, String i2){
		setTitle("Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		
		Canvas c = new Canvas(g, directions, meridianmap, i1, i2);

		new Timer(500, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				c.brush = Color.GREEN;

				if(switching){
					c.brush = Color.BLUE;
					switching = false;
				}
				else{
					c.brush = Color.GREEN;
					switching = true;
				}
				repaint();
			}
		}).start();
		
		add(c);
	}

	public static void main(String[] args) {
		IO io = new IO();
		
		String filename = args[0];
		Graph g = io.getGraph(filename);
		boolean showMap = false;
		boolean showDirections = false;
		boolean showMeridianMap = false;
		
		String i1 = "", i2 = "";
		
		for(int i = 1; i < args.length; i++){
			if(args[i].equals("-show"))
				showMap = true;
			if(args[i].equals("-directions")){
				showDirections = true;
				
				i1 = args[++i];
				i2 = args[++i];
			}
			if(args[i].equals("-meridianmap"))
				showMeridianMap  = true;
		}
		
		if(showDirections)
			g.getShortestPath(i1, i2);
		
		if(showMeridianMap)
			g.getMeridianMap();
		
		if(showMap)
			new Main(g, showDirections, showMeridianMap, i1, i2).setVisible(true);
	}

}
