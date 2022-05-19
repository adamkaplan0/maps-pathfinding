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

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {
	Graph g;
	double traveled;
	
	public Dijkstra(Graph g){
		this.g = g;
		traveled = 0;
	}
	
	private void doDijkstra(Vertex from, Vertex to){
		Vertex source = g.vertexList.get(from.id);
		
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		
		q.addAll(g.vertexList.values());
		q.remove(source);
		
		source.distance = 0;
		
		q.add(source);
		
		while(!q.isEmpty()){
			
			// Get the minimum Vertex
			Vertex v = g.vertexList.get(q.poll().id);
			
			for(Edge e : v.adjacentEdges){
				Vertex w = g.vertexList.get(e.to.id);
				
				if(q.contains(w)){
					if(v.distance + e.weight < g.vertexList.get(w.id).distance){
						q.remove(g.vertexList.get(w.id));
						
						g.vertexList.get(w.id).distance = v.distance + e.weight;
						g.vertexList.get(w.id).previous = v;
						
						q.add(g.vertexList.get(w.id));
					}
				}
				
				
			}
		}
	}
	
	public LinkedList<Vertex> getShortestPathVertices(Vertex from, Vertex to){
		doDijkstra(from, to);
		
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		
		for(Vertex v = to; v != null; v = v.previous){
			path.add(v);
			traveled += v.distance;
		}
			
		
		Collections.reverse(path);
		
		return path;
	}
	
	public LinkedList<Edge> getShortestPathEdges(Vertex from, Vertex to){
		LinkedList<Vertex> vertices = getShortestPathVertices(from, to);
		LinkedList<Edge> edges = new LinkedList<Edge>();
		
		Vertex[] verticesArray = vertices.toArray(new Vertex[vertices.size()]);
		
		for(int i = 0; i < verticesArray.length-1; i++){
			
			Edge adjacentEdge = null;
			for(Edge e : verticesArray[i].adjacentEdges){
				if(e.to.equals(verticesArray[i+1])){
					adjacentEdge = e;
					break;
				}
			}
			edges.add(adjacentEdge);
		}
		return edges;
		
	}
	
	public double getTraveled(){
		return traveled * 3;
	}
}
