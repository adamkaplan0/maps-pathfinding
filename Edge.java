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

public class Edge implements Comparable<Edge>{
	final String id;
	Vertex from, to;
	double weight;
	
	public Edge(String id, Vertex from, Vertex to){
		this.id = id;
		this.from = from;
		this.to = to;
		this.weight = Math.toRadians(Math.sqrt( Math.pow( (to.latitude - from.latitude), 2.0) + Math.pow( (to.longitude - from.longitude), 2.0) )) * 69;
	}
	
	public String toString(){
		return id;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(weight, o.weight);
	}
}
