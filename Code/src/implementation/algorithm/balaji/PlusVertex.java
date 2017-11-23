package implementation.algorithm.balaji;

import java.util.List;

/**
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

	int top;

	int inDegree;

	PlusVertex parent;

	int fin;

	public PlusVertex(Graph.Vertex v) {
		this.vertex = v;
		this.adj = v.adj;
		this.revAdj = v.revAdj;

	}

}
