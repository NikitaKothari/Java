
package lp2.cs6301.g32;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for finding Euler Tours in a directed graph
 *
 * @author Vinaya, Pradeep, Mohan, Nikita
 * @version 1.0: 2017/09/14
 */
public class Euler extends GraphAlgorithm<Euler.EulerVertex> {
	int VERBOSE;
	Graph g;
	Graph.Vertex start;
	List<Graph.Edge> tour;

	/**
	 * Class to store information about the vertex in Euler's algorithm
	 */
	static class EulerVertex {
		private Graph.Vertex vertex;
		private boolean explored;
		// list to store the subtour starting at that vertex
		private List<Graph.Edge> subtour;
		// this is used to stitch subtours so that no subtour is picked twice
		private boolean visitedSubtour;
		// iterator for iterating the edges of the vertex
		private Iterator<Graph.Edge> it;

		EulerVertex(Graph.Vertex u) {
			vertex = u;
			subtour = new LinkedList<>();
			it = u.iterator();
		}

		boolean isExplored() {
			return explored;
		}

		// boolean hasNext() {
		// return it.hasNext();
		// }

		/**
		 * helper method to get the next edge in a vertex's adj. list
		 */
		Graph.Edge next() {
			if (it.hasNext()) {
				return it.next();
			}
			explored = true;
			return null;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder(vertex + ": ");
			for (Graph.Edge e : subtour) {
				sb.append(e);
			}
			return sb.toString();
		}

	}

	Euler(Graph g, Graph.Vertex start) {
		super(g);
		this.g = g;
		VERBOSE = 1;
		this.start = start;
		tour = new LinkedList<>();
		// initialize parallel array in GraphAlgorithm for storing vertex
		// properties
		node = new EulerVertex[g.size()];
		for (Graph.Vertex u : g) {
			node[u.getName()] = new EulerVertex(u);
		}
	}

	/**
	 * function to find an Euler tour
	 *
	 * @return
	 */
	public List<Graph.Edge> findEulerTour() {
		findTours();
		if (VERBOSE > 9) {
			printTours();
		}
		stitchTours();
		return tour;
	}

	/**
	 * test if the graph is Eulerian. If the graph is not Eulerian, it prints
	 * the message: "Graph is not Eulerian" and one reason why, such as
	 * "inDegree = 5, outDegree = 3 at Vertex 37" or "Graph is not strongly
	 * connected"
	 *
	 * @return : true if graph is Eulerian false otherwise
	 */
	boolean isEulerian() {
		for (Graph.Vertex u : g) {
			if (u.adj.size() != u.revAdj.size()) {
				System.out.println("Graph is not Eulerian");
				System.out.println("Reason: inDegree = " + u.adj.size() + ", outDegree = " + u.revAdj.size()
						+ " at Vertex " + u.getName());
				return false;
			}
		}
		StronglyConnected s = new StronglyConnected(this.g);
		if (s.stronglyConnectedComponents() > 1) {
			System.out.println("Graph is not Eulerian");
			System.out.println("Reason: Graph is not strongly connected");
			return false;
		}
		return true;
	}

	/**
	 * This method finds subtours starting from the start edge and ging through
	 * all the vertices with an unexplored edge
	 */
	void findTours() {
		EulerVertex u = getVertex(start); // node[start.getName()];
		findTour(u, u.subtour);
		// it is enough to loop through all vertices just once because once we
		// find a subtour starting at vertex u
		// we have explored all its outgoing edges
		for (Graph.Vertex v : g) {
			u = getVertex(v);
			if (!u.explored) {
				findTour(u, u.subtour);
			}
		}
	}

	/**
	 * This method finds a subtour starting at u and stores it in tour
	 *
	 * @param u
	 *            : EulerVertex : the vertex to start the tour from
	 * @param tour
	 *            : List : an empty list
	 */
	private void findTour(EulerVertex u, List<Graph.Edge> tour) {
		//
		Graph.Edge edge;
		while ((edge = u.next()) != null) {
			tour.add(edge);
			Graph.Vertex v = edge.otherEnd(u.vertex);
			u = node[v.getName()];
		}
	}

	/**
	 * Print tours found by findTours() using following format: Start vertex of
	 * tour: list of edges with no separators
	 */
	void printTours() {
		EulerVertex ev = getVertex(start);
		System.out.println(ev);
		for (EulerVertex u : node) {
			if (!u.subtour.isEmpty() && u.vertex.getName() != start.getName()) {
				System.out.println(u);
			}
		}
	}

	/**
	 * Creates a tour starting at start vertex by stitching subtours together
	 */
	void stitchTours() {
		explore(start);
	}

	/**
	 * This method appends subtour starting at u to T
	 *
	 * @param u
	 *            : starting vertex of subtour
	 */
	private void explore(Graph.Vertex u) {
		Graph.Vertex tmp = u;
		EulerVertex eu = getVertex(u);
		eu.visitedSubtour = true;
		for (Graph.Edge e : eu.subtour) {
			tour.add(e);
			tmp = e.otherEnd(tmp);
			EulerVertex etmp = getVertex(tmp);
			if (!etmp.visitedSubtour && !etmp.subtour.isEmpty()) {
				explore(tmp);
			}
		}
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}

	/**
	 * This method allows finding Euler Tour on the same graph many times with
	 * different start node
	 *
	 * @param start
	 *            : Start vertex of tour
	 */
	void reinitialize(Graph.Vertex start) {
		this.start = start;
		this.tour.clear();
		for (Graph.Vertex u : g) {
			EulerVertex eu = getVertex(u);
			eu.explored = false;
			eu.subtour.clear();
			eu.visitedSubtour = false;
			eu.it = u.iterator();
		}
	}
}
