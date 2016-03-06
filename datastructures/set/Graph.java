import java.util.HashSet;
import java.util.ArrayList;

public class Graph {
	HashSet<Vertex> V;
	ArrayList<Edge> E;

	public Graph() {
		V = new HashSet<Vertex>();
		E = new ArrayList<Edge>();
	}

	public void addEdge(Vertex u, Vertex v, int weight) {
		if (!V.contains(u)) addVertex(u);
		if (!V.contains(v)) addVertex(v);
		E.add(new Edge(u, v, weight));
	}

	public void addVertex(Vertex u) {
		V.add(u);
	}

	public void addVertex(char data) {
		Vertex newV = new Vertex(data);
		V.add(newV);
	}
}


class Vertex {
	SetNode owner;
	char data;
	HashSet<Edge> edges;
	public Vertex(char data) {
		this.data = data;
		edges = new HashSet<Edge>(3);
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public Vertex dest(Edge e) {
		if (e == null) return null;
		if (e.v1 == this) return e.v2;
		if (e.v2 == this) return e.v1;
		return null;
	}
}

class Edge implements Comparable<Edge> {
	Vertex v1;
	Vertex v2;
	int weight;
	public Edge(Vertex v1, Vertex v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}

	public boolean equals(Edge o) {
		return o != null && (v1 == o.v1 || v1 == o.v2) 
						 && (v2 == o.v1 || v2 == o.v2) 
						 && weight == o.weight;
	}
}