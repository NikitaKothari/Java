package lp7.cs6301.g32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs6301.g00.Graph;
import cs6301.g00.GraphAlgorithm;

public class BFS extends GraphAlgorithm<BFS.BFSVertex> {
	public static final int INFINITY = Integer.MAX_VALUE;

	// Class to store information about a vertex in this algorithm
	static class BFSVertex {
		boolean seen;
		Graph.Vertex parent;
		int distance; // distance of vertex from source

		BFSVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			distance = INFINITY;
		}
	}

	public BFS(Graph g) {
		super(g);
		node = new BFSVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new BFSVertex(u);
		}
	}

	void init() {
		for (Graph.Vertex u : g) {
			BFSVertex bu = getVertex(u);
			bu.seen = false;
			bu.parent = null;
			bu.distance = INFINITY;
		}
	}

	void bfs(Graph.Vertex source, Graph.Vertex destinaton) {
		init();
		Queue<Graph.Vertex> q = new LinkedList<>();
		while (!q.isEmpty()) {
			Graph.Vertex u = q.remove();
			for (Graph.Edge e : u) {
				Graph.Vertex v = e.otherEnd(u);
				if (!seen(v)) {
					visit(u, v);
					q.add(v);
				}
				if (v.equals(destinaton))
					break;
			}
		}
	}

	public List<Graph.Vertex> getPath(Graph.Vertex Destination) {
		List<Graph.Vertex> path = new ArrayList<>();
		while (true) {
			path.add(Destination);
			BFSVertex bVertex = getVertex(Destination);
			if (bVertex.parent == null)
				break;
			Destination = bVertex.parent;
		}
		return path;
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
		BFSVertex bv = getVertex(v);
		bv.seen = true;
		bv.parent = u;
		bv.distance = distance(u) + 1;
	}
}