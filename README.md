# Path Finding in Maps

## General Information

I was asked to implement a miniature version of Google Maps that works for the University of Rochester campus, Monroe county and New York State. All of this is implemented using the graph data structure. The program reads in a text file specified per command, creates a graph based on the input and then has to be able to find the shortest path between two points (using Dijkstra's algorithm), and create a minimum weight spanning tree (using Prim's algorithm, which I based on this [online repository](http://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html)). Then the user can also specify if the map should be drawn. Of course, the minimum weight spanning tree and the shortest path has to be then also highlighted on the map.

## How To Get Going

The command to run this program is:

```shell
java Main TEXT_FILE.txt (-show) (-directions INTERSECTION INTERSECTION) (-meridianmap)
```

* With the `(-show)` command a map will be painted, animated, and shown in an external window.
* With the `(-directions INTERSECTION INTERSECTION)` command the shortest path between the two intersections will be printed with directions, and if `(-show)` then it will also be painted on the map.
* With the `(-meridianmap)` command the minimum weight tree will be printed as well as if `(-show)`, it will be painted and animated on the map.

## Implementation Details

## Runtime Analysis

* **Plotting of the map:** I am looping through all of the vertices and then all the adjacent edges, so in the worst case it takes $O(V \cdot E)$, but on average it is $O(E \cdot \log V)$.
* **Finding shortest path:** Since I am using a priority queue in my implementation and I am looping through all of the vertices and then all their adjacent edges, it takes $O(E \cdot \log V)$.
* **Generating minimum weight spanning tree:** Since I am using a priority queue and looping through all the vertices, the runtime is $O(E \cdot \log V)$.

### Overcome struggles

* I started this project with an adjacency array graph structure, and I realized quickly that that is unfeasible using a data set greater than or equal to Monroe county. Hence I switched to an Adjacency List, I did that with a map of intersection ids to intersections, where each intersection then has a list of all edges adjacent to it.
* The next issue I faced was implementing Dijkstra's, since my first implementation worked, but it took 3 minutes to find the path for Monroe county, I then switched to a PriorityQueue implementation of it, which allowed me to speed up the algorithm to about 4 seconds for Monroe County and 40 for New York state.
* The last issue I faced was drawing the map, since we were given all the information in longitude and latitude, I needed to figure out how to scale it and how to calculate the distance in miles, after a lot of research, and some math error I figured it out.

## File Information

* `Main.java` -- The class which handles all of the user interactions and then calls different classes to do what the user wants.
* `Vertex.java` -- The class that represents a vertex (Intersection).
* `Edge.java` -- The class that represents an edge (Road).
* `IO.java` -- The class that handles all of the file interactions of this project.
* `Dijkstra.java` -- The class that represents the Dijkstra algorithm and all the methods associated with the shortest path.
* `Prim.java` -- The class that represents the Prim algorithm and all the methods associated with the minimum weight tree.
* `Canvas.java` -- The class that is the JPanel and where all of the painting is done.
* `OUTPUT.DirectionsUR.txt` -- Copy & Paste of console output for the `directions` command for `ur.txt`.
* `OUTPUT.MeridianMapUR.txt` -- Copy & Paste of console output for the `meridianmap` command for `ur.txt`.
* `OUTPUT.DirectionsMonroe.txt` -- Copy & Paste of console output for the `directions` command for `monroe.txt`.
* `OUTPUT.MeridianMapMonroe.txt` -- Copy & Paste of console output for the `meridianmap` command for `monroe.txt`.
* `OUTPUT.DirectionsNYS.txt` -- Copy & Paste of console output for the `directions` command for `nys.txt`.
* `OUTPUT.MeridianMapNYS.txt` -- Copy & Paste of console output for the `meridianmap` command for `nys.txt`.
* `OUTPUT.Case5.txt` -- Copy & Paste of console output for 4 lines intersecting in one point.
