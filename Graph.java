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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	int vertexCount, edgeCount;
	HashMap<String, Vertex> vertexList;
	double maxX, maxY;
	double minX, minY;
	LinkedList<Vertex> shortestPathVertices;
	LinkedList<Edge> shortestPathEdges;
	LinkedList<Edge> meridianPathEdges;
	
	public Graph(){
		vertexList = new HashMap<String, Vertex>();
		shortestPathVertices = new LinkedList<Vertex>();
	}
	
	public void addVertex(Vertex v){
		vertexList.put(v.id, v);
		vertexCount++;
	}
	
	public void addEdge(Edge e){
		Vertex v = e.from;
		Vertex w = e.to;
		
		Edge ePrime = new Edge(e.id, e.to, e.from);
		
		vertexList.get(v.id).adjacentEdges.add(e);
		vertexList.get(w.id).adjacentEdges.add(ePrime);
		
		edgeCount++;
	}
	
	public void getShortestPath(String from, String to){
		Vertex vfrom = vertexList.get(from);
		Vertex vto = vertexList.get(to);
		
		Dijkstra d = new Dijkstra(this);
		
		shortestPathVertices = d.getShortestPathVertices(vfrom, vto);
		shortestPathEdges = d.getShortestPathEdges(vfrom, vto);
		double traveled = d.getTraveled();
		
		Vertex[] pathV = shortestPathVertices.toArray(new Vertex[shortestPathVertices.size()]);
		Edge[] pathE = shortestPathEdges.toArray(new Edge[shortestPathEdges.size()]);

		if(pathV.length == 1)
			System.out.printf("The intersections %s and %s are not connected.", from, to);
		else{
			System.out.printf("The directions to get from %s to %s are:%n", from, to);
			
			for(int i = 0; i < pathE.length; i++){
				System.out.printf("Take %s to get from %s to %s.%n", pathE[i], pathV[i], pathV[i+1]);
			}
			System.out.println("Total miles traveled: " + (traveled ) + " miles.");

			System.out.printf("In summary you want the following intersections: %n%s%n", Arrays.toString(pathV));
		}
	}
	
	public void getMeridianMap(){
		Prim p = new Prim(this);
		
		Vertex start = vertexList.get(vertexList.keySet().toArray(new String[vertexList.keySet().size()])[0]);
		
		meridianPathEdges = p.getMinimumWeightTree(start);
		
		Edge[] meridianPath = meridianPathEdges.toArray(new Edge[meridianPathEdges.size()]);
		
		System.out.printf("The roads that need to be covered to see all intersections are: %n%s%n", Arrays.toString(meridianPath));
	}
}
