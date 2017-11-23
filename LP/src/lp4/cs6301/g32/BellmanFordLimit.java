package lp4.cs6301.g32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs6301.g00.Graph;
import cs6301.g00.GraphAlgorithm;

public class BellmanFordLimit extends GraphAlgorithm<BellmanFordLimit.BellmanFordLimitVertex> {

	public BellmanFordLimit(Graph g) {
		super(g);
		node = new BellmanFordLimitVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new BellmanFordLimitVertex(v.getName());
		}
	}

	public static class BellmanFordLimitVertex extends Graph.Vertex {
		boolean seen;
		List<Graph.Vertex> parents;
		List<Long> dist;
		long distance;
		List<Integer> hops;
		int count;

		public BellmanFordLimitVertex(int n) {
			super(n);
			this.seen = false;
			this.parents = new ArrayList<>();
			this.distance = Long.MAX_VALUE;
			this.hops = new ArrayList<>();
			this.dist = new ArrayList<>();
			this.count = 0;
		}
	}

	public void bellmanFord(Graph.Vertex source, Graph.Vertex destination, int limit) throws Exception {
		LinkedList<Graph.Vertex> queue = new LinkedList<>();
		queue.add(source);

		BellmanFordLimitVertex ds = getVertex(source);
		ds.seen = true;
		ds.distance = 0;

		while (!queue.isEmpty()) {
			Graph.Vertex u = queue.remove();
			getVertex(u).seen = false;
			BellmanFordLimitVertex du = getVertex(u);
			if (du.count >= g.size())
				throw new Exception("Negative cycle found");

			for (Graph.Edge e : u) {
				BellmanFordLimitVertex dv = getVertex(e.otherEnd(u));
				if (!dv.seen) {
					queue.add(e.otherEnd(u));
					dv.seen = true;
				}
				int hops = du.hops.size() == 0 ? 0 : du.hops.get(du.hops.size() - 1);
				if (dv.distance > (du.distance + e.getWeight()) && du.distance != Long.MAX_VALUE
						&& limit >= (hops + 1)) {
					dv.distance = du.distance + e.getWeight();
					dv.hops.add(hops + 1);
					dv.parents.add(u);
					dv.dist.add((long) e.getWeight());
				}
			}
		}

		long ans = 0;
		int hopsNeeded = limit;
		BellmanFordLimitVertex dDest = getVertex(destination);
		BellmanFordLimitVertex dSource = getVertex(source);
		while (dDest.getName() != dSource.getName()) {
			for (int i = dDest.hops.size() - 1; i >= 0; i--) {
				if (hopsNeeded >= dDest.hops.get(i)) {
					hopsNeeded--;
					ans += dDest.dist.get(i);
					dDest = getVertex(dDest.parents.get(i));
					break;
				}
			}
		}

		System.out.println(ans);
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		Graph.Vertex s = g.getVertex(in.nextInt());
		Graph.Vertex d = g.getVertex(in.nextInt());
		int limit = in.nextInt();
		BellmanFordLimit bellmanFordLimit = new BellmanFordLimit(g);
		bellmanFordLimit.bellmanFord(s, d, limit);
	}

}
