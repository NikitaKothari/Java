package lp4.cs6301.g32;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Vertex;
import cs6301.g00.GraphAlgorithm;

public class BellmanFord extends GraphAlgorithm<BellmanFord.BellmanFordVertex> {
	public BellmanFord(Graph g) {
		super(g);
		node = new BellmanFordVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new BellmanFordVertex(v.getName());
		}
	}

	public static class BellmanFordVertex extends Graph.Vertex {
		boolean seen;
		int distance;
		Set<Graph.Vertex> parents;
		int count;

		public BellmanFordVertex(int n) {
			super(n);
			this.seen = false;
			this.distance = Integer.MAX_VALUE;
			this.parents = new HashSet<>();
			this.count = 0;
		}
	}

	ArrayList<LinkedList<Graph.Vertex>> allshortestpath = new ArrayList<>();

	public void bellmanFord(Graph.Vertex source, Graph.Vertex destination) throws Exception {
		LinkedList<Graph.Vertex> queue = new LinkedList<>();
		queue.add(source);

		BellmanFordVertex ds = getVertex(source);
		ds.seen = true;
		ds.distance = 0;

		while (!queue.isEmpty()) {
			Graph.Vertex u = queue.remove();
			getVertex(u).seen = false;
			BellmanFordVertex du = getVertex(u);
			du.count = du.count + 1;
			if (du.count >= g.size())
				throw new Exception("Negative cycle found");
			for (Graph.Edge e : u) {
				BellmanFordVertex dv = getVertex(e.otherEnd(u));
				if (dv.distance >= (du.distance + e.getWeight()) && du.distance != Integer.MAX_VALUE) {
					if (dv.distance == (du.distance + e.getWeight())) {
						dv.parents.add(u);
					} else {
						dv.distance = du.distance + e.getWeight();
						dv.parents = new HashSet<>();
						dv.parents.add(u);
					}
					if (!dv.seen) {
						queue.add(e.otherEnd(u));
						dv.seen = true;
					}
				}
			}
		}

		LinkedList<Graph.Vertex> list = new LinkedList<>();
		list.add(destination);
		getAllPaths(destination, source, list);
		System.out.println("allshortestpath distance is: " + allshortestpath.size());
		if (allshortestpath.size() > 1) {
			for (LinkedList<Graph.Vertex> lists : allshortestpath) {
				System.err.println(lists);
			}
		}
	}

	void getAllPaths(Graph.Vertex dest, Graph.Vertex source, LinkedList<Vertex> path) {
		if (dest == source) {
			allshortestpath.add((LinkedList<Graph.Vertex>) path.clone());
		} else {
			BellmanFordVertex dv = getVertex(dest);
			for (Graph.Vertex u : dv.parents) {
				path.addFirst(u);
				int size = path.size();
				getAllPaths(u, source, path);
				path.remove(path.size() - size);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		Graph.Vertex s = g.getVertex(in.nextInt());
		Graph.Vertex d = g.getVertex(in.nextInt());
		BellmanFord bellmanFord = new BellmanFord(g);
		bellmanFord.bellmanFord(s, d);
	}

}
