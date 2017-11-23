package lp2.cs6301.g32;

import java.util.List;

/**
 * class to include vertex properties in addition to Graph.Vertex
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 * @version 1.0: 2017/09/14
 */
public class PlusVertex {
	Graph.Vertex vertex;

	List<Graph.Edge> adj, revAdj; // adjacency list;

	// if the vertex is visited this flag will be true
	boolean seen = false;
	// the time at which this node was discovered
	int dis;
	// the component number to which this vertex belongs
	int cno;
	// counter to keep track of number of vertices in Algo1 for Topologicalorder
	int top;
	// the number of edges coming in to this vertex
	int inDegree;
	// finish time of this vertex during DFS traversal
	int fin;

	public PlusVertex(Graph.Vertex v) {
		this.vertex = v;
		this.adj = v.adj;
		this.revAdj = v.revAdj;

	}

}
