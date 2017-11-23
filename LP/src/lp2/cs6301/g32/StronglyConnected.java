package lp2.cs6301.g32;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * class to count the number of strongly connected components in the given graph
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class StronglyConnected {
	Graph g;
	PlusVertex vertex[];

	/**
	 * class to store intermediate values of time and cno between recursions
	 */
	class DfsObjects {
		int time;
		int cno;
		boolean reverseEdges; // if true then during DFS revAdj will be
								// considered

		public DfsObjects(int time, int cno, boolean reverseEdges) {
			this.time = time;
			this.cno = cno;
			this.reverseEdges = reverseEdges;
		}
	}

	public StronglyConnected(Graph g) {
		this.g = g;
		// initializing PlusVertex based on the number of vertices in grpah
		vertex = new PlusVertex[g.size()];
		int i = 0;
		for (Graph.Vertex v : g) {
			vertex[i++] = new PlusVertex(v);
		}
	}

	/**
	 * returns PlusVertex for given Graph.Vertex
	 * 
	 * @param v
	 *            : Graph.Vertex whose PlusVertex is needed
	 * @return PlusVertex
	 */
	PlusVertex getPlusVertex(Graph.Vertex v) {
		return vertex[v.name];
	}

	/**
	 * dfsVisit : recursive function for dfs traversal
	 * 
	 * @param u
	 *            source to start DFS traversal
	 * @param decFinList:
	 *            linked list to add vertices in decreasing order of finish
	 *            times
	 * @param obj:
	 *            DfsObjects containing: dis, cno, reverseEdges between
	 *            recursions
	 * @return true if DAG
	 */
	private void dfsVisit(PlusVertex u, LinkedList<Graph.Vertex> decFinList, DfsObjects obj) {
		// mark u as seen
		u.seen = true;
		// save the time of discovery to the vertex's dis attribute
		u.dis = ++obj.time;
		// component number of the vertex(same as its parent)
		u.cno = obj.cno;

		/**
		 * if reverseEdges is true then incoming edges will be iterated through
		 * else the outgoing edges
		 */
		Iterator<Graph.Edge> it = obj.reverseEdges ? (u.revAdj).iterator() : (u.adj).iterator();
		while (it.hasNext()) {
			// get the other end of edge e, call it v
			PlusVertex v = getPlusVertex(it.next().otherEnd(u.vertex));
			// if v is not seen then call dfsVisit on v
			if (!v.seen) {
				dfsVisit(v, decFinList, obj);
			}
		}
		// add finish time for the node
		u.fin = ++obj.time;
		// add the vertex at the beginning of linkedlist
		decFinList.addFirst(u.vertex);

	}

	/**
	 * function to return list of vertices by decreasing finish times of DFS
	 * 
	 * @param it
	 *            iterator for the set of vertices provided by calling function
	 * @param obj
	 *            DfsObjects containing: dis, cno, reverseEdges between
	 *            recursions
	 * @return linked list containing vertices in decreasing order of finish
	 *         times
	 */
	private List<Graph.Vertex> topologicalOrder(Iterator<Graph.Vertex> it, DfsObjects obj) {

		LinkedList<Graph.Vertex> decFinList = new LinkedList<>();

		// call dfs on every vertex which is not seen before
		while (it.hasNext()) {
			PlusVertex pv = getPlusVertex(it.next());
			if (!pv.seen) {
				// component number is increased to mark the next vertex as
				// different component
				obj.cno++;

				dfsVisit(pv, decFinList, obj);
			}
		}

		return decFinList;
	}

	/**
	 * clears the seen property of PlusVertex to use the graph for next DFS
	 * traversal
	 */
	private void clearVertexProperties() {
		for (int i = 0; i < g.size(); i++) {
			vertex[i].seen = false;
		}
	}

	/**
	 * function to get number of strongly connected components
	 * 
	 * @param g
	 *            input graph
	 * @return number of strongly connected components
	 */
	public int stronglyConnectedComponents() {
		// initializing time and cno for first DFS traversal
		DfsObjects obj = new DfsObjects(0, 0, false);

		// decFinList is the list with vertices ordered in decreasing order of
		// finish times
		List<Graph.Vertex> decFinList = topologicalOrder(g.iterator(), obj);

		// initializing time, cno and reverseEdges as true for second DFS
		// traversal
		obj = new DfsObjects(0, 0, true);
		// clearing seen property of vertices
		clearVertexProperties();

		// second traversal of DFS
		decFinList = topologicalOrder(decFinList.iterator(), obj);

		// the final component count
		return obj.cno;
	}

	// public static void main(String[] args) {
	// Scanner in = new Scanner(System.in);
	// Graph g = Graph.readDirectedGraph(in);
	//
	// StronglyConnected eg = new StronglyConnected(g);
	//
	// System.out.println("Number of
	// components:"+eg.stronglyConnectedComponents(g));
	//
	//
	// }
}
