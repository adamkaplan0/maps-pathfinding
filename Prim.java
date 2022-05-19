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
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prim {
	Graph g;
	PriorityQueue<Edge> queue;
	LinkedList<Edge> tree;
	
	public Prim(Graph g){
		this.g = g;
		queue = new PriorityQueue<Edge>();
		tree = new LinkedList<Edge>();
	}
	
	
	// Source: http://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html
		private void addEdgesToPQ(Vertex v){
			v.visited = true;
			
			for(Edge e : v.adjacentEdges){
				if(!e.to.visited)
					queue.add(e);
			}
		}
		// Source: http://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html
		private void prim(Vertex v){
			tree = new LinkedList<Edge>();
			queue = new PriorityQueue<Edge>();
			
			addEdgesToPQ(v);
			
			while(!queue.isEmpty()){
				Edge e = queue.remove();
				
				if(e.from.visited && e.to.visited)
					continue;
				
				tree.add(e);
				
				if(!e.from.visited)
					addEdgesToPQ(e.from);
				if(!e.to.visited)
					addEdgesToPQ(e.to);
			}
		}
		
		public LinkedList<Edge> getMinimumWeightTree(Vertex v){
			prim(g.vertexList.get(v.id));
			
			LinkedList<Edge> meridianPath = new LinkedList<Edge>();
			
			meridianPath.addAll(tree);
			
			return meridianPath;
		}
}
