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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IO {
	private double maxX, maxY;
	private double minX, minY;
	private Graph g;
	
	public IO(){
		g = new Graph();
		maxX = Double.NEGATIVE_INFINITY;
		maxY = Double.NEGATIVE_INFINITY;
		minY = Double.POSITIVE_INFINITY;
		minX = Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Wrapper method for graph creation
	 * @param filename
	 * @return
	 */
	public Graph getGraph(String filename){
		// Create the graph
		createGraphFromFile(filename);
		
		// Set the max and min values for the graph
		g.maxX = maxX;
		g.maxY = maxY;
		g.minX = minX;
		g.minY = minY;
		
		// Return the graph
		return g;
	}
	
	/**
	 * Function to create the Graph from a file
	 * @param filename
	 */
	private void createGraphFromFile(String filename){
		try {
			Scanner sc = new Scanner(new File(filename));
			
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] lineSplit = line.split("\t");
				String type = lineSplit[0];
				
				if(type.equals("i")){
					String id = lineSplit[1];
					double latitude = Double.parseDouble(lineSplit[2]);
					double longitude = Double.parseDouble(lineSplit[3]);
					
					if(latitude > maxX)
						maxX = latitude;
					
					if(longitude > maxY)
						maxY = longitude;
					
					if(latitude < minX)
						minX = latitude;
					
					if(longitude < minY)
						minY = longitude;
					
					Vertex v = new Vertex(id, latitude, longitude);
					
					g.addVertex(v);
				}
				else{
					String id = lineSplit[1];
					Vertex v = g.vertexList.get(lineSplit[2]);
					Vertex w = g.vertexList.get(lineSplit[3]);
					
					Edge e = new Edge(id, v, w);
					
					g.addEdge(e);
				}
			}
			
			sc.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
