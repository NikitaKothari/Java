package ctci.practice;

import java.util.LinkedList;

import templete.custom.Graph;
import templete.custom.State;

public class GraphSol {
	boolean search(Graph<Integer> g, Graph<Integer> start, Graph<Integer> end) {
		if (start == end)
			return true;

		LinkedList<Graph<Integer>> q = new LinkedList<>();
		for (Graph<Integer> u : g.adjecencyList)
			u.state = State.unvisited;
		start.state = State.visiting;
		q.add(start);
		Graph<Integer> u;

		while (!q.isEmpty()) {
			u = q.remove();
			if (u != null) {
				for (Graph<Integer> v : u.adjecencyList) {
					if (v == end)
						return true;
					else {
						v.state = State.visiting;
						q.add(v);
					}
				}
				u.state = State.visited;
			}
		}
		return false;
	}
}
