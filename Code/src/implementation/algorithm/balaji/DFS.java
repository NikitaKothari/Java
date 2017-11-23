package implementation.algorithm.balaji;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
	public static final int INFINITY = Integer.MAX_VALUE;

	// Class to store information about a vertex in this algorithm
	static class DFSVertex {
		boolean seen;
		Graph.Vertex parent;
		int distance; // distance of vertex from source
		int cno;
		int top;

		DFSVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			distance = INFINITY;
			cno = 0;
			top = INFINITY;
		}
	}

	Graph.Vertex src;

	public DFS(Graph g, Graph.Vertex src) {
		super(g);
		this.src = src;
		node = new DFSVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new DFSVertex(u);
		}
		// Set source to be at distance 0
		getVertex(src).distance = 0;
	}

	// reinitialize allows running BFS many times, with different sources
	void reinitialize(Graph.Vertex newSource) {
		src = newSource;
		for (Graph.Vertex u : g) {
			DFSVertex bu = getVertex(u);
			bu.seen = false;
			bu.parent = null;
			bu.distance = INFINITY;
		}
		getVertex(src).distance = 0;
	}

	void dfs(Graph g) {
		for (Graph.Vertex v : g) {
			if (!seen(v)) {
				DFS_visit(v);
			}
		}
	}

	void DFS_visit(Graph.Vertex v) {
		for (Graph.Edge e : v) {
			Graph.Vertex u = e.otherEnd(v);
			if (!seen(u)) {
				visit(u, v);
				DFS_visit(u);
			}
		}
	}

	boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	}

	Graph.Vertex getParent(Graph.Vertex u) {
		return getVertex(u).parent;
	}

	int distance(Graph.Vertex u) {
		return getVertex(u).distance;
	}

	// Visit a node v from u
	void visit(Graph.Vertex u, Graph.Vertex v) {
		DFSVertex bv = getVertex(v);
		bv.seen = true;
		bv.parent = u;
		bv.distance = distance(u) + 1;
	}
}