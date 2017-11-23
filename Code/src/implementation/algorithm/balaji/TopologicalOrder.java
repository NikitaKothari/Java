package implementation.algorithm.balaji;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author vinaya
 */
public class TopologicalOrder {
	Graph g;
	PlusVertex vertex[];

	public TopologicalOrder(Graph g) {
		this.g = g;
		// initializing PlusVertex based on the number of vertices in grpah
		vertex = new PlusVertex[g.size()];
		int i = 0;
		for (Graph.Vertex v : g) {
			vertex[i++] = new PlusVertex(v);
		}
	}

	class DfsObjects {
		int time;
		int cno;
		int topNum;
		boolean DAG;

		public DfsObjects(int time, int cno, int topNum) {
			this.time = time;
			this.cno = cno;
			this.topNum = topNum;
		}
	}

	/**
	 * 
	 * @param v
	 *            : Graph.Vertex whose PlusVertex is needed
	 * @return PlusVertex
	 */
	PlusVertex getPlusVertex(Graph.Vertex v) {
		return vertex[v.name];
	}

	public List<PlusVertex> topologicalOrder1() {
		int topNum = 0;
		Queue<PlusVertex> q = new ArrayDeque<>();
		List<PlusVertex> topList = new ArrayList<>();

		for (Graph.Vertex v : g) {
			PlusVertex u = getPlusVertex(v);
			u.inDegree = u.revAdj.size();
			if (u.inDegree == 0) {
				q.add(u);
			}
		}

		while (!q.isEmpty()) {
			PlusVertex u = q.remove();
			u.top = ++topNum;
			topList.add(u);
			for (Graph.Edge e : u.adj) {
				PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
				v.inDegree--;
				if (v.inDegree == 0) {
					q.add(v);
				}
			}
		}

		if (topNum != g.size()) {
			return null;
		}

		return topList;

	}

	/**
	 * 
	 * @param u
	 *            source to start DFS traversal
	 * @param time:
	 *            time of discovery of its parent
	 * @param cno:
	 *            component number of its parent
	 * @return true if DAG
	 */
	private boolean dfsVisit(PlusVertex u, LinkedList<PlusVertex> decFinList, DfsObjects obj) {
		// mark u as seen
		u.seen = true;
		// save the time of discovery to the vertex's dis attribute
		u.dis = ++obj.time;
		// component number of the vertex(same as its parent)
		u.cno = obj.cno;

		// iterate through all the outgoing edges of u
		for (Graph.Edge e : u.adj) {
			// get the other end of edge e, call it v
			PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
			/**
			 * if other end v, was discovered before u and they belong to same
			 * component e is a back-edge
			 */
			if (v.dis < u.dis && v.cno == u.cno) {
				return false;
			}
			// if no cylce then recursively call the dfs on v
			else if (!v.seen) {
				v.parent = u;

				if (!dfsVisit(v, decFinList, obj))
					return false;
			}
		}
		// add finish time for the node
		u.fin = ++obj.time;
		u.top = obj.topNum--;
		decFinList.addFirst(u);
		return true;
	}

	public LinkedList<PlusVertex> topologicalOrder2() {
		int topNum = 0;
		int time = 0;
		int cno = 0;
		LinkedList<PlusVertex> decFinList = new LinkedList<>();

		// call dfs on every vertex which is not seen before
		for (Graph.Vertex v : g) {
			PlusVertex pv = getPlusVertex(v);
			if (!pv.seen) {
				// component number is increased to mark the next vertex as
				// different component
				cno++;
				DfsObjects obj = new DfsObjects(time, cno, topNum);
				if (!dfsVisit(pv, decFinList, obj))
					return null;
			}
		}

		return decFinList;
	}

	void printTopologicalOrder(List<PlusVertex> topList) {
		for (PlusVertex v : topList) {
			System.out.print((v.vertex.name + 1) + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readDirectedGraph(in);

		TopologicalOrder t = new TopologicalOrder(g);
		List res1 = t.topologicalOrder1();

		System.out.println("Algorithm 1 Output:");
		if (res1 == null)
			System.out.println("Not a DAG");
		else {
			t.printTopologicalOrder(res1);
		}

		LinkedList<PlusVertex> res2 = t.topologicalOrder2();

		System.out.println("Algorithm 2 Output:");
		if (res2 == null || res2.isEmpty())
			System.out.println("Not a DAG");
		else {
			t.printTopologicalOrder(res2);
		}

	}
}
