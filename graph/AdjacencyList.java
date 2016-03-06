import java.util.ArrayList;

public class AdjacencyList {
	ArrayList<Vertex> vertices;
	ArrayList<Edge> edges;
	private int size;

	public AdjacencyList() {
		vertices = new ArrayList<>(10);
		edges = new ArrayList<>(9);
		size = 0;
	}

	public int size() { return size; }

	public void addEdge(Vertex a, Vertex b, int weight) {
		UndirectedEdge e = new UndirectedEdge(weight);
		e.source = a;
		e.dest = b;

		a.addEdge(e);
		b.addEdge(e);
		edges.add(e);
	}

	public void addVertex(Vertex a) {
		vertices.add(a);
		size++;
	}
}



class Vertex {
	ArrayList<UndirectedEdge> edges;
	char data;

	public Vertex(char c) {
		edges = new ArrayList<>(0);
		data = c;
	}
	public void addEdge(UndirectedEdge e) {
		edges.add(e);
	}
}

class Edge {
	int weight;
	public Edge(int w) {
		weight = w;
	}
}

class UndirectedEdge extends Edge {
	Vertex source, dest;
	public UndirectedEdge(int w) {
		super(w);
	}
	public Vertex getDest(Vertex s) throws IllegalArgumentException{
		if (s == source || s == dest) {
			if (s == source)
				return dest;
			return source;
		}
		throw new IllegalArgumentException("Vertex does not belong to this edge");
	}
}

class DirectedEdge extends Edge {
	Vertex source, dest;
	public DirectedEdge(int w) { 
		super(w);
	}
}