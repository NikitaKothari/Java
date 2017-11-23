package lp4.cs6301.g32;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs6301.g00.Graph;
import cs6301.g00.GraphAlgorithm;

public class TopologicalOrder extends GraphAlgorithm<TopologicalOrder.DFSVertex> {
	public TopologicalOrder(Graph g) {
		super(g);
		node = new DFSVertex[g.size()];
		for (Graph.Vertex v : g) {
			node[v.getName()] = new DFSVertex(v.getName());
		}

		for (Graph.Vertex v : g) {
			DFSVertex dv = getVertex(v);
			dv.inorder = v.revAdj.size();
		}
	}

	public static class DFSVertex extends Graph.Vertex {
		boolean seen;
		Graph.Vertex parent;
		int inorder;

		public DFSVertex(int n) {
			super(n);
			this.seen = false;
			this.parent = null;
			this.inorder = 0;
		}
	}

	ArrayList<ArrayList<DFSVertex>> res = new ArrayList<>();

	public void topologicalOrder(ArrayList<DFSVertex> list) {
		boolean flag = false;
		for (Graph.Vertex v : g) {
			DFSVertex dv = getVertex(v);
			if (dv.inorder == 0 && !dv.seen) {
				for (Graph.Edge e : v) {
					getVertex(e.otherEnd(v)).inorder--;
				}
				dv.seen = true;
				list.add(dv);

				topologicalOrder(list);

				list.remove(list.size() - 1);
				dv.seen = false;
				for (Graph.Edge e : v) {
					getVertex(e.otherEnd(v)).inorder++;
				}
				flag = true;
			}
		}
		if (!flag) {
			if (list != null && list.size() > 0) {
				res.add((ArrayList<DFSVertex>) list.clone());
			}
		}
	}

	public void topologicalOrder() {
		ArrayList<DFSVertex> list = new ArrayList<>();
		res = new ArrayList<>();
		topologicalOrder(list);
		System.out.println("Number of topological orders " + res.size());
		if (res.size() != 0) {
			System.out.println("Topological orders are: ");
			for (List<DFSVertex> d : res) {
				System.out.println(d);
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		TopologicalOrder topologicalOrder = new TopologicalOrder(g);
		topologicalOrder.topologicalOrder();
	}
}
