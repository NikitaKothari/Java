package cs6301.g32.SP3;

import java.util.Scanner;
import java.util.Stack;

import cs6301.g32.SP3.Graph.Vertex;

/**
 *
 * @author Vinaya, Pradeep, Mohan, Nikita
 * @version 1.0: 2017/09/14
 */
public class StronglyConnectedComponent {
	Graph g;

	PlusVertex vertex[];

	StronglyConnectedComponent(Graph g) {
		this.g = g;
		// initializing PlusVertex based on the number of vertices in grpah
		vertex = new PlusVertex[g.size()];
		int i = 0;
		for (Graph.Vertex v : g) {
			vertex[i++] = new PlusVertex(v);
		}
	}

	/**
	 * 
	 * @param v
	 *            : Graph.Vertex whose PlusVertex is needed
	 * @return PlusVertex
	 */
	PlusVertex getPlusVertex(Graph.Vertex v) {
		return vertex[v.name];
	}

	/**
	 * 
	 * @param u
	 *            source to start DFS traversal
	 * @param time:
	 *            time of discovery of its parent
	 * @param cno:
	 *            component number of its parent
	 * @return true if DAG
	 */
	private boolean dfsVisit(PlusVertex u, int time, int cno) {
		// mark u as seen
		u.seen = true;
		// save the time of discovery to the vertex's dis attribute
		u.dis = ++time;
		// component number of the vertex(same as its parent)
		u.cno = cno;

		// iterate through all the outgoing edges of u
		for (Graph.Edge e : u.adj) {
			// get the other end of edge e, call it v
			PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
			/**
			 * if other end v, was discovered before u and they belong to same
			 * component e is a back-edge
			 */
			// if no cylce then recursively call the dfs on v
			if (!v.seen) {
				// if any of the adjacent vertices discover a cycle, return
				// false
				if (!dfsVisit(v, time, cno))
					return false;
			}
		}
		return true;

	}

	/**
	 * helper function for dfs()
	 * 
	 * @return true if DAG
	 */
	boolean dfs() {
		// initializing time and component number
		int time = 0;
		int cno = 0;

		// call dfs on every vertex which is not seen before
		for (Graph.Vertex v : g) {
			PlusVertex dg = getPlusVertex(v);
			if (!dg.seen) {
				// component number is increased to mark the next vertex as
				// different component
				cno++;

				// if any component returned with cycles then return false
				if (!dfsVisit(dg, time, cno))
					return false;
			}
		}

		return true;
	}

	public Graph getReverseGraph(Graph g) {
		Graph gRev = new Graph(g.size());
		gRev.directed = true;
		for (Graph.Vertex v : g) {
			for (Graph.Edge e : v.adj) {
				gRev.addEdge(gRev.getVertex(Integer.parseInt(e.to.toString())),
						gRev.getVertex(Integer.parseInt(e.from.toString())), e.weight);
			}
		}
		return gRev;
	}

	void fillStack(PlusVertex u, Stack<Graph.Vertex> stack) {
		u.seen = true;
		for (Graph.Edge e : u.adj) {
			PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
			if (!v.seen)
				fillStack(v, stack);
		}
		stack.push(u.vertex);
	}

	void dfs(PlusVertex u) {
		u.seen = true;
		System.out.print(u.vertex + " ");
		for (Graph.Edge e : u.adj) {
			PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
			if (!v.seen)
				dfs(v);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		StronglyConnectedComponent stronglyConnectedComponent = new StronglyConnectedComponent(g);

		Stack<Vertex> stack = new Stack<>();
		for (Graph.Vertex v : g) {
			PlusVertex u = stronglyConnectedComponent.getPlusVertex(v);
			if (!u.seen)
				stronglyConnectedComponent.fillStack(u, stack);
		}
		Graph gRev = stronglyConnectedComponent.getReverseGraph(g);
		StronglyConnectedComponent stronglyConnectedComponentRev = new StronglyConnectedComponent(gRev);

		System.out.println("Connected Components are: ");
		while (!stack.isEmpty()) {
			Graph.Vertex v = stack.pop();
			PlusVertex u = stronglyConnectedComponentRev.getPlusVertex(v);
			if (!u.seen) {
				stronglyConnectedComponentRev.dfs(u);
				System.out.println();
			}
		}
	}
	/**
	 * Output:
	 * 
	 * 11 17 1 11 1 2 3 1 2 7 1 3 10 1 4 1 1 4 9 1 5 7 1 5 8 1 5 4 1 6 3 1 7 8 1
	 * 8 2 1 9 11 1 10 6 1 11 3 1 11 6 1 11 4 1 Connected Components are:
	 * 
	 * 5
	 * 
	 * 2 8 7
	 * 
	 * 1 4 11 9
	 * 
	 * 3 6 10
	 */
}
