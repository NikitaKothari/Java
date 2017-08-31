package templete.custom;

public class Graph<E> {

	public E val;
	public State state;
	public Graph<E>[] adjecencyList;

	public Graph(E val) {
		this.val = val;
		adjecencyList = null;
		state = State.unvisited;
	}

	public void depthFirstSearch(Graph<E> graph) {
		if (graph == null)
			return;
		for (Graph<E> n : graph.adjecencyList) {
			if (n.state == State.unvisited) {
				n.state = State.visited;
				depthFirstSearch(n);
			}
		}
	}

	public void breathFirstSearch(Graph<E> graph) {
		GenericQueue<Graph<E>> queue = new GenericQueue<>();
		graph.state = State.visited;
		queue.enqueue(graph);

		while (queue.hasItems()) {
			Graph<E> r = queue.dequeue();
			r.state = State.visited;
			for (Graph<E> n : r.adjecencyList) {
				if (n.state == State.unvisited) {
					n.state = State.visited;
					queue.enqueue(n);
				}
			}
		}

	}

}
