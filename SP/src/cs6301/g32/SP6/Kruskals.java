package cs6301.g32.SP6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import cs6301.g32.SP6.Graph.Edge;
import cs6301.g32.SP6.Graph.Vertex;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */
public class Kruskals extends GraphAlgorithm<Kruskals.KrukalVertex> {

	public Kruskals(Graph g) {
		super(g);
		node = new KrukalVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new KrukalVertex(v);
		}
	}

	class KrukalVertex {
		boolean seen;
		Graph.Vertex rep;
		int rank;

		public KrukalVertex(Vertex v) {
			seen = false;
			rep = v;
			rank = 0;
		}
	}

	/**
	 * Find representative of graph vertex
	 * 
	 * @param u
	 *            : Graph vertex
	 * @return Returns graph vertex who is u's representative
	 */
	Graph.Vertex find(Graph.Vertex u) {
		KrukalVertex ku = getVertex(u);
		Graph.Vertex ru;
		if (ku.rep.equals(u))
			ru = u;
		else
			ru = find(ku.rep);
		ku.rep = ru;
		return ru;
	}

	/**
	 * Add edge to the set
	 * 
	 * @param u
	 *            : Graph vertex
	 * @param v
	 *            : Graph vertex
	 */
	void union(Graph.Vertex u, Graph.Vertex v) {
		KrukalVertex ku = getVertex(u);
		KrukalVertex kv = getVertex(v);

		if (ku.rank > kv.rank)
			kv.rep = u;
		else if (ku.rank < kv.rank)
			ku.rep = v;
		else {
			kv.rank++;
			kv.rep = u;
		}
	}

	public class WeightComparator implements Comparator<Graph.Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			if (o1.weight > o2.weight)
				return 1;
			else if (o1.weight == o2.weight && o1.from == o2.from && o1.to == o2.to)
				return 0;
			return -1;
		}
	}

	ArrayList<Graph.Edge> kruskal() {
		WeightComparator weightComparator = new WeightComparator();
		Set<Graph.Edge> set = new TreeSet<>(weightComparator);
		int wtmst = 0;
		for (Graph.Vertex v : g) {
			for (Graph.Edge e : v) {
				set.add(e);
			}
		}
		ArrayList<Graph.Edge> selectedEdges = new ArrayList<>();
		for (Graph.Edge e : set) {
			Graph.Vertex ru = find(e.from);
			Graph.Vertex rv = find(e.to);
			if (!ru.equals(rv)) {
				selectedEdges.add(e);
				wtmst += e.weight;
				union(e.from, e.to);
			}
		}
		System.out.println("Weight of Minimum spanning Tree: " + wtmst);
		return selectedEdges;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Graph credentials ");
		Graph g = Graph.readGraph(in);
		Kruskals kruskals = new Kruskals(g);
		System.out.println("Selected Vertices: ");
		System.out.println(kruskals.kruskal());
	}
	/**
	 * Enter Graph credentials 9 15 1 2 4 2 3 8 3 4 7 4 5 9 5 6 10 6 7 2 6 3 4 6
	 * 4 14 7 8 1 7 9 6 8 1 8 8 9 7 8 2 11 9 3 2 8 2 11 Selected Vertices:
	 * Weight of Minimum spanning Tree: 37 [(7,8), (6,7), (9,3), (6,3), (1,2),
	 * (3,4), (8,1), (4,5)]
	 */

}
