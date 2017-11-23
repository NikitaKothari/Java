package lp3.cs6301.g32;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cs6301.g00.Graph;

/**
 * @author prady Ver 1.0: 10/14/17
 */

public class ZeroEdgeIterator implements Iterator<Graph.Edge> {

	private List<DMST.DMSTEdge> edges;
	private int start;
	private int end;
	private int current;

	public ZeroEdgeIterator(List<DMST.DMSTEdge> edges) {
		this.edges = new ArrayList<>();
		this.edges.addAll(edges);
		start = 0;
		end = this.edges.size() - 1;
		current = start - 1;
	}

	@Override
	public boolean hasNext() {
		if (current == end)
			return false;
		int temp = current;
		DMST.DMSTEdge e = null;
		do {
			e = edges.get(++temp);
		} while (e.isDisabled && current < end);
		current = temp - 1;
		return !e.isDisabled;
	}

	@Override
	public Graph.Edge next() {
		return edges.get(++current).edge;
	}

	@Override
	public void remove() {

	}
}
