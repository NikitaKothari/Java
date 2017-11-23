package lp4.cs6301.g32;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Vertex;
import cs6301.g00.GraphAlgorithm;

public class RewardCollection extends GraphAlgorithm<RewardCollection.RewardCollectionVertex> {

	int maxRewards;
	static int[] reward;
	static List<Graph.Vertex> rewardedPath;

	public RewardCollection(Graph g) {
		super(g);
		node = new RewardCollectionVertex[g.size()];
		maxRewards = Integer.MIN_VALUE;
		for (Graph.Vertex v : g) {
			node[v.getName()] = new RewardCollectionVertex(v.getName(), g.size());
		}
	}

	public static class RewardCollectionVertex extends Graph.Vertex {
		boolean seen;
		int distance;
		Set<Graph.Vertex> parents;
		boolean alternate;
		int count;
		int[] dist;

		public RewardCollectionVertex(int n, int k) {
			super(n);
			this.seen = false;
			this.distance = Integer.MAX_VALUE;
			this.parents = new HashSet<>();
			this.alternate = false;
			this.count = 0;
			this.dist = new int[k];
			for (int i = 0; i < this.dist.length; i++)
				this.dist[i] = Integer.MAX_VALUE;
		}
	}

	ArrayList<LinkedList<Graph.Vertex>> allshortestpath = new ArrayList<>();

	public void RewardCollection(Graph.Vertex source) throws Exception {

		for (Graph.Vertex v : g) {
			getVertex(v).dist[0] = Integer.MAX_VALUE;
		}

		RewardCollectionVertex ds = getVertex(source);
		ds.seen = true;
		ds.distance = 0;
		ds.dist[0] = 0;

		for (int k = 1; k < g.size(); k++) {
			boolean noChange = false;
			for (Graph.Vertex u : g) {
				System.out.println(u.toString());
				RewardCollectionVertex ru = getVertex(u);
				for (Graph.Edge e : u) {
					RewardCollectionVertex rv = getVertex(e.otherEnd(u));
					if (ru.dist[k] > rv.dist[k - 1] + e.getWeight() && rv.dist[k - 1] != Integer.MAX_VALUE) {
						ru.dist[k] = rv.dist[k - 1] + e.getWeight();
						ru.parents.add(e.otherEnd(u));
						noChange = false;
					}

				}
			}
			if (!noChange) {
				for (Graph.Vertex u : g) {
					RewardCollectionVertex ru = getVertex(u);
					ru.distance = ru.dist[k];
				}
			}

		}

		for (Graph.Vertex u : g) {
			RewardCollectionVertex ru = getVertex(u);
			System.out.println(ru.toString() + "   " + ru.distance);
		}

		/*
		 * if (v != source) { RewardCollectionVertex rv = getVertex(v); if
		 * (rv.alternate) { allshortestpath = new ArrayList<>();
		 * LinkedList<Graph.Vertex> list = new LinkedList<>(); list.add(v);
		 * getAllPaths(v, source, list); for (List<Graph.Vertex> incomingPath :
		 * allshortestpath) { int rewards = getRewards(incomingPath); if
		 * (rewards > maxRewards) { maxRewards = rewards; rewardedPath =
		 * incomingPath; } } } } else { if (0 > maxRewards) maxRewards = 0; } }
		 * return maxRewards;
		 */
	}

	void print(int[] arr) {
		for (int a : arr)
			System.out.print(a + " ");
	}

	void getAllPaths(Graph.Vertex dest, Graph.Vertex source, LinkedList<Vertex> path) {
		if (dest == source) {
			allshortestpath.add((LinkedList<Graph.Vertex>) path.clone());
		} else {
			RewardCollectionVertex dv = getVertex(dest);
			for (Graph.Vertex u : dv.parents) {
				path.addFirst(u);
				int size = path.size();
				getAllPaths(u, source, path);
				path.remove(path.size() - size);
			}
		}
	}

	private int getRewards(List<Graph.Vertex> list) {
		int rewards = 0;
		for (Graph.Vertex v : list) {
			rewards += reward[v.getName()];
		}
		return rewards;
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readGraph(in);
		Graph.Vertex s = g.getVertex(in.nextInt());
		reward = new int[g.size()];
		for (int i = 0; i < g.size(); i++)
			reward[i] = in.nextInt();
		RewardCollection RewardCollection = new RewardCollection(g);
		RewardCollection.RewardCollection(s);
		System.out.println(rewardedPath);
	}

}
