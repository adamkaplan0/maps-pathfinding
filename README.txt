=== CSC 172 Project 04 ===
Author: Adam Kaplan (akaplan6)
Submitted on: April 23, 2016
Lab Section: TR 4:50PM - 6:05PM
TA: Charlie Kelman

I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own.


== General Description ==

This project was one of the most difficult. We were asked to implement a miniature version of Google Maps that works for the UR campus, Monroe county and New York State. All of this is implemented using the Graph data structure. The program reads in a text file specified per command, creates a graph based on the input and then has to be able to find the shortest path between two points (using Dijkstra's algorithm), and create a minimum weight spanning tree (using Prim's algorithm, which I based on this online repository: http://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html). Then the user can also specify if the map should be drawn. Of course, the minimum weight spanning tree and the shortest path has to be then also highlighted on the map. 

== Overcome struggles ==

I started this project with an adjacency array graph structure, and I realized quickly that that is unfeasible using a data set greater than or equal to Monroe county. Hence I switched to an Adjacency List, I did that with a map of intersection ids to intersections, where each intersection then has a list of all edges adjacent to it.
The next issue I faced was implementing Dijkstra's, since my first implementation worked, but it took 3 minutes to find the path for Monroe county, I then switched to a PriorityQueue implementation of it, which allowed me to speed up the algorithm to about 4 seconds for Monroe County and 40 for New York state.
The last issue I faced was drawing the map, since we were given all the information in longitude and latitude, I needed to figure out how to scale it and how to calculate the distance in miles, after a lot of research, and some math error I figured it out.

== Runtime Analysis ==

Plotting of the map: I am looping through all of the vertices and then all the adjacent edges, so I suspect that it would take worst case around O(V * E), but I suspect usually it is around O(E*LogV).
Finding shortest Path: Since I am using a priority queue in my implementation and I am looping through all of the vertices and then all their adjacent edges I think it is O(E*LogV)
Generating minimum weight spanning tree: Since I am using a priority queue and looping through all the vertices, the runtime is O(E*LogV)

== Additional Information ==

The command to run this program is:

java Main TEXT_FILE.txt (-show) (-directions INTERSECTION INTERSECTION) (-meridianmap)

With the (-show) command a map will be painted and shown in an external window.
With the (-directions INTERSECTION INTERSECTION) command the shortest path between the two intersections will be printed with directions, and if (-show) then it will also be painted on the map.
With the (-meridianmap) command the minimum weight tree will be printed as well as if (-show), it will be painted on the map.

== Files ==

Main.java	- 	The class which handles all of the user interactions and then calls different classes to do what the user inputed
Vertex.java	-	The class that represents a vertex (Intersection)
Edge.java	-	The class that represents an edge (Road)
IO.java	-	The class that handles all of the file interactions of this project
Dijkstra.java	-	The class that represents the Dijkstra algorithm and all the methods associated with the shortest path
Prim.java	-	The class that represents the Prim algorithm and all the methods associated with the minimum weight tree
Canvas.java	-	The class that is the JPanel and where all of the painting is done
OUTPUT.DirectionsUR.txt	-	Copy & Paste of console output for the directions command for ur.txt
OUTPUT.MeridianMapUR.txt	-	Copy & Paste of console output for the meridianmap command for ur.txt
OUTPUT.DirectionsMonroe.txt	-	Copy & Paste of console output for the directions command for monroe.txt
OUTPUT.MeridianMapMonroe.txt	-	Copy & Paste of console output for the meridianmap command for monroe.txt
OUTPUT.DirectionsNYS.txt	-	Copy & Paste of console output for the directions command for nys.txt
OUTPUT.MeridianMapNYS.txt	-	Copy & Paste of console output for the meridianmap command for nys.txt
OUTPUT.Case5.txt	-	Copy & Paste of console output for 4 lines intersecting in one point

== Extra Credit ==

Path and meridian map drawings are animated