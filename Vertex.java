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

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex>{
	final String id;
	double latitude, longitude;
	LinkedList<Edge> adjacentEdges;
	double distance = Double.POSITIVE_INFINITY;
	Vertex previous = null;
	boolean visited = false;
	
	public Vertex(String id, double latitude, double longitude){
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		
		adjacentEdges = new LinkedList<Edge>();
	}
	
	public Edge[] getAdjacentEdges(){
		return adjacentEdges.toArray(new Edge[adjacentEdges.size()]);
	}
	
	public String toString(){
		return id;
	}

	@Override
	public int compareTo(Vertex o) {
		return Double.compare(distance, o.distance);
	}
}
