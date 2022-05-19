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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	Graph g;
	boolean directions, meridianmap;
	String i1, i2;
	Color brush = Color.BLUE;
	final double scale = 0.9;
	
	public Canvas(Graph g, boolean directions, boolean meridianmap, String i1, String i2){
		this.g = g;
		this.directions = directions;
		this.meridianmap = meridianmap;
		this.i1 = i1;
		this.i2 = i2;
	}
	
	public void paintMap(Graphics2D g2){
		g2.setColor(Color.WHITE);
		for(Vertex v : g.vertexList.values()){
			for(Edge e : v.adjacentEdges){
				double x1 = (e.from.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
				double y1 = (e.from.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );

				double x2 = (e.to.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
				double y2 = (e.to.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );
				
				g2.draw(new Line2D.Double(x1+getHeight()/3-20, y1-getWidth()/8, x2+getHeight()/3-20, y2-getWidth()/8));
			}
		}
	}
	
	public void paintDirections(Graphics2D g2){
		g2.setColor(brush);
		g2.setStroke(new BasicStroke(2));
		
		Edge[] edges = g.shortestPathEdges.toArray(new Edge[g.shortestPathEdges.size()]);
		
		for(Edge e : edges){
			double x1 = (e.from.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
			double y1 = (e.from.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );
			
			double x2 = (e.to.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
			double y2 = (e.to.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );
			
			g2.draw(new Line2D.Double(x1+getHeight()/3-20, y1-getWidth()/8, x2+getHeight()/3-20, y2-getWidth()/8));
		}
		
	}
	
	public void paintMeridianMap(Graphics2D g2){
		g2.setColor(brush);
		
		Edge[] edges = g.meridianPathEdges.toArray(new Edge[g.meridianPathEdges.size()]);
		
		for(Edge e : edges){
			double x1 = (e.from.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
			double y1 = (e.from.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );
			
			double x2 = (e.to.latitude - g.minX) * ( (getHeight()*scale) / (g.maxX - g.minX) );
			double y2 = (e.to.longitude - g.minY) * ( (getWidth()*scale) / (g.maxY - g.minY) );
			
			g2.draw(new Line2D.Double(x1+getHeight()/3-20, y1-getWidth()/8, x2+getHeight()/3-20, y2-getWidth()/8));
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,getHeight()*2, getWidth()*2);
		
        g2.rotate(Math.toRadians(270), getWidth() / 2, getHeight() / 2);
		
		paintMap(g2);
		
		if(directions)
			paintDirections(g2);
		else if(meridianmap)
			paintMeridianMap(g2);
	}
}
