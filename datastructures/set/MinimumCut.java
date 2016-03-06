import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;

class MinimumCut {
	public static Vertex kruskal(Graph G) {
		DisjointSet A = null;
		for (Vertex v : G.V) {
			DisjointSet.makeSet(v);
		}
		Edge[] sortedEdges = G.E.toArray(new Edge[0]);
		Arrays.sort(sortedEdges);
		for (Edge e : sortedEdges) {
			Vertex u = e.v1;
			Vertex v = e.v2;
			if (DisjointSet.findSet(u) != DisjointSet.findSet(v)) {
				u.addEdge(e);
				v.addEdge(e);
				A = DisjointSet.union(u.owner.set, v.owner.set);
			}
		}
		return A.head.v;
	}


	public static void main(String[] args) {
		Graph G = new Graph();

		Vertex a = new Vertex('a');
		Vertex b = new Vertex('b');
		Vertex c = new Vertex('c');
		Vertex d = new Vertex('d');
		Vertex e = new Vertex('e');
		Vertex f = new Vertex('f');
		Vertex g = new Vertex('g');
		Vertex h = new Vertex('h');
		Vertex i = new Vertex('i');


		G.addEdge(a,b,4);
		G.addEdge(b,c,8);
		G.addEdge(c,d,7);
		G.addEdge(d,e,9);
		G.addEdge(e,f,10);
		G.addEdge(f,g,2);
		G.addEdge(g,h,1);
		G.addEdge(h,a,8);
		G.addEdge(b,h,11);
		G.addEdge(i,h,7);
		G.addEdge(i,g,6);
		G.addEdge(c,i,2);
		G.addEdge(c,f,4);
		G.addEdge(d,f,14);

		Vertex v = kruskal(G);

		dfs(v);
	}

	public static void dfs(Vertex v) {
		LinkedList<Vertex> stack = new LinkedList<Vertex>();
		HashSet<Vertex> marked = new HashSet<Vertex>();
		HashSet<Edge> markedEdges = new HashSet<Edge>();
		stack.push(v);

		while (!stack.isEmpty()) {
			Vertex curr = stack.pop();
			for (Edge e : curr.edges) {
				if (!markedEdges.contains(e)) {
					System.out.println(String.format("%s <--%d--> %s",e.v1.data, e.weight, e.v2.data));
					markedEdges.add(e);
					Vertex dest = curr.dest(e);
					if (!marked.contains(dest)) {
						stack.push(dest);
						marked.add(dest);
					}
				}
			}
		}
	}
}