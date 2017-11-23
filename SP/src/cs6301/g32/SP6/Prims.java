package cs6301.g32.SP6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prims extends GraphAlgorithm<Prims.PrimVertex> {

	static class PrimVertex implements Comparator<PrimVertex> {
		int d, index;
		boolean seen;
		Graph.Vertex parent;

		PrimVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
		}

		public int compare(PrimVertex u, PrimVertex v) {
			if (u.d < v.d)
				return -1;
			else if (u.d == v.d)
				return 0;
			else
				return 1;
		}

		public void putIndex(int i) {
			index = i;
		}

		public int getIndex() {
			return index;
		}
	}

	public Prims(Graph g) {
		super(g);
		node = new PrimVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new PrimVertex(v);
		}
	}

	boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	}

	Graph.Vertex getParent(Graph.Vertex u) {
		return getVertex(u).parent;
	}

	int prims1(Graph.Vertex src) {
		// Update parent
		PrimVertex pv = getVertex(src);
		pv.seen = true;
		pv.parent = null;
		int wtmst = 0;
		PriorityQueue<Graph.Edge> queue = new PriorityQueue<>();
		Graph.Vertex parent = src;
		System.out.println(src.toString());
		for (Graph.Edge e : src) {
			queue.add(e);
		}
		while (!queue.isEmpty()) {
			Graph.Edge e = queue.remove();
			Graph.Vertex v = null;

			if (!seen(e.from))
				v = e.from;
			else if (!seen(e.to))
				v = e.to;

			if (v != null) {
				visit(parent, v);
				wtmst += e.weight;
				for (Graph.Edge e2 : v) {
					if (!seen(e2.otherEnd(v)))
						queue.add(e2);
				}
			}
		}
		return wtmst;
	}

	int prims2(Graph.Vertex src) {
		int wtmst = 0;
		return wtmst;
	}

	void visit(Graph.Vertex u, Graph.Vertex v) {
		PrimVertex bv = getVertex(v);
		bv.seen = true;
		bv.parent = u;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readGraph(in);

		Prims p = new Prims(g);
		System.out.println(p.prims1(g.getVertex(4)));
	}

}