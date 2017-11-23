package lp7.cs6301.g32;

import java.util.HashMap;
import java.util.Scanner;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;

public class FlowDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);
		Vertex s = g.getVertex(in.nextInt());
		Vertex t = g.getVertex(in.nextInt());

		HashMap<Edge, Integer> capacity = new HashMap<>();
		for (Graph.Vertex v : g) {
			if (v.equals(t))
				continue;
			for (Graph.Edge e : v) {
				capacity.put(e, e.getWeight());
			}
		}
		Flow f = new Flow(g, s, t, capacity);
		System.out.println(f.dinitzMaxFlow());

	}

}