package lp3.cs6301.g32;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.Graph;

/**
 * This class is used to find a MST in a directed graph using Tarjan's algorithm
 * 
 * @author Ver 1.0: 10/13/17
 */

public class DMST extends GraphAlgorithm<DMST.DMSTVertex> {
	/**
	 *
	 * @param g
	 *            : A directed graph
	 */
	public DMST(Graph g) {
		super(g);
		node = new DMSTVertex[g.size()];
		// O(V)
		for (Graph.Vertex v : g) {
			node[v.getName()] = new DMSTVertex(v.getName());
		}
		// O(VE)
		for (Graph.Vertex v : g) {
			for (Graph.Edge e : v.adj) {
				DMSTEdge de = new DMSTEdge(e);
				getVertex(v).dmstAdj.add(de);
				getVertex(e.otherEnd(v)).revAdj.add(de);
				// node[v.getName()].minWeight =
			}
		}

	}

	static class DMSTVertex extends Graph.Vertex {
		boolean isSuper;

		List<DMSTEdge> dmstAdj;
		List<DMSTEdge> dmstRev;
		int minWeight;

		public DMSTVertex(int n) {
			super(n);
			// components = new ArrayList<>();
			dmstAdj = new LinkedList<>();
			dmstRev = new LinkedList<>();
			// components.add(this);
			minWeight = Integer.MAX_VALUE;
		}

		@Override
		public Iterator<Graph.Edge> iterator() {
			return new ZeroEdgeIterator(dmstAdj);
		}
	}

	static class DMSTEdge extends Graph.Edge {

		int dmstWeight;
		boolean isDisabled;
		Graph.Edge edge;

		public DMSTEdge(Graph.Edge e) {
			super(e);
			this.edge = e;
			dmstWeight = e.getWeight();
		}
	}

	private void findMinWeight(DMSTVertex v) {
		// traverse all incoming edges
		int min = v.minWeight;
		for (DMSTEdge de : v.dmstRev) {
			if (min > de.dmstWeight) {
				min = de.dmstWeight;
			}
		}
		v.minWeight = min;
	}

	public void findDMST(Graph.Vertex r, List<Graph.Edge> dmst) {
		// TODO check all nodes are reachble from root
		for (Graph.Vertex v : g) {
			if (v.getName() != r.getName()) {
				findMinWeight(getVertex(v));
				deductWeight(getVertex(v));
			}
		}
		List<Graph.Edge> temp = new ArrayList<>();
		// checkZeroWeightMST(r, temp);
	}

	private void deductWeight(DMSTVertex vertex) {
		for (DMSTEdge e : vertex.dmstRev) {
			e.dmstWeight -= vertex.minWeight;
			if (e.dmstWeight != 0)
				e.isDisabled = true;
		}
	}

	private boolean checkZeroWeightMST(Graph.Vertex r) {
		DFS dfs = new DFS(g);
		dfs.dfsVisit(dfs.getVertex(r));
		for (DFS.DFSVertex dv : dfs.node) {
			if (!dv.seen)
				return false;
		}
		return true;
	}

}
