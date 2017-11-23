package lp3.cs6301.g32;

import cs6301.g00.Graph;

public class GraphAlgorithm<T> {
	Graph g;
	// Algorithm uses a parallel array for storing information about vertices
	T[] node;

	public GraphAlgorithm(Graph g) {
		this.g = g;
	}

	T getVertex(Graph.Vertex u) {
		return Graph.Vertex.getVertex(node, u);
	}
}