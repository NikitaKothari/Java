package cs6301.g00;

public class GraphAlgorithm<T> {
	public Graph g;
	// Algorithm uses a parallel array for storing information about vertices
	public T[] node;

	public GraphAlgorithm(Graph g) {
		this.g = g;
	}

	public T getVertex(Graph.Vertex u) {
		return Graph.Vertex.getVertex(node, u);
	}
}
