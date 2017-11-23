package lp3.cs6301.g32;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import cs6301.g00.Graph;

/**
 * @author prady Ver 1.0: 10/14/17
 */

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	int time;

	public DFS(Graph g) {
		super(g);
		node = new DFSVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new DFSVertex(v.getName());
		}
	}

	public void initialize(Graph g) {
		node = new DFSVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new DFSVertex(v.getName());
		}
	}

	boolean relax(Graph.Edge e) {
		DFSVertex from = getVertex(e.fromVertex());
		DFSVertex to = getVertex(e.toVertex());
		if (to.d > from.d + e.getWeight()) {
			to.d = from.d + e.getWeight();
			to.parent = e.fromVertex();
			return true;
		}
		return false;
	}

	public static class DFSVertex extends Graph.Vertex {
		boolean seen;
		Graph.Vertex parent;
		int finTime;
		int d;

		public DFSVertex(int n) {
			super(n);
			this.seen = false;
			this.parent = null;
			this.finTime = 0;
			this.d = 0;
		}
	}

	public void dfs() {
		time = 0;
		for (Graph.Vertex v : g) {
			DFSVertex d = getVertex(v);
			if (!d.seen) {
				dfsVisit(d);
			}
		}
	}

	public void dfsVisit(DFSVertex v) {
		time++;
		for (Graph.Edge e : v) {
			DFSVertex dv = getVertex(e.otherEnd(v));
			if (!dv.seen) {
				dfsVisit(dv);
			}
		}
		v.seen = true;
		v.finTime = ++time;
	}

	public void topologicalOrder() {
		Stack<Graph.Vertex> stack = new Stack<>();
		for (Graph.Vertex v : g) {
			topologicalOrder(v, stack);
		}

		while (!stack.isEmpty()) {
			System.out.println(stack.pop().getName());
		}
	}

	private void topologicalOrder(Graph.Vertex v, Stack<Graph.Vertex> stack) {
		DFSVertex dv = getVertex(v);
		if (!dv.seen) {
			dv.seen = true;
			for (Graph.Edge e : v) {
				dv = getVertex(e.otherEnd(v));
				if (!dv.seen) {
					topologicalOrder(e.otherEnd(v), stack);
				}
			}
			stack.add(v);
		}
	}

	public void bfs(Graph.Vertex s) {
		LinkedList<Graph.Vertex> queue = new LinkedList<>();
		DFSVertex ds = getVertex(s);
		ds.seen = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			Graph.Vertex v = queue.remove();
			for (Graph.Edge e : v) {
				DFSVertex dv = getVertex(e.otherEnd(v));
				if (!dv.seen) {
					dv.parent = v;
					dv.d = getVertex(v).d + 1;
					dv.seen = true;
					queue.add(e.otherEnd(v));
				}
			}

		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		DFS dfs = new DFS(g);
		dfs.topologicalOrder();
	}

}
