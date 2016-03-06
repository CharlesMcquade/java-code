import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.lang.Math;


class GraphTests {
	public static void main(String[] args) {
		adjacencyListTest();
	}



	public static void adjacencyListTest() {
		AdjacencyList g = new AdjacencyList();
		Vertex a = new Vertex('A');
		Vertex b = new Vertex('B');
		Vertex c = new Vertex('C');
		Vertex d = new Vertex('D');
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		g.addEdge(a, b, 7);
		g.addEdge(b, c, 3);
		g.addEdge(b, d, 2);
		g.addEdge(a, d, 1);

		System.out.println(dijkstra(a, c, g));
	}


	public static int dijkstra(Vertex s, Vertex d, AdjacencyList g) {
		HashSet<Vertex> visited = new HashSet<>();
		LinkedList<Vertex> vertices = new LinkedList();
		HashMap<Vertex, Integer> minDistances = new HashMap<>();
		
		minDistances.put(s, 0);
		vertices.add(s);
		
		while (vertices.size() > 0) {
			Vertex curr = vertices.pop();
			System.out.println("Visited Vertex "+curr.data);
			Vertex closestVertex = null;
			int closestEdgeWeight = Integer.MAX_VALUE;
			for (UndirectedEdge e : curr.edges) {
				Vertex dest = e.getDest(curr);
				if (!visited.contains(dest)) {
					int weight = e.weight;
					if (weight < closestEdgeWeight) {
						closestEdgeWeight = weight;
						closestVertex = dest;
					}
					else {
						vertices.add(dest);
					}
					if (minDistances.containsKey(dest)) {
						int smallestSoFar = minDistances.get(dest);
						minDistances.put(dest, Math.min(smallestSoFar, weight + minDistances.get(curr)));
					}
					else 
						minDistances.put(dest, weight + minDistances.get(curr));
				}
			}
			if (closestVertex != null) vertices.push(closestVertex);
			visited.add(curr);		
		}
		return minDistances.get(d);
	}




	//s = source, g = graph
	public static void bfsTest(Vertex s, AdjacencyList g) {
		HashSet<Vertex> visited = new HashSet<>();
		LinkedList<Vertex> vertices = new LinkedList<>();
		vertices.add(s);
		visited.add(s);


		while (vertices.size() > 0) {
			Vertex curr = vertices.pop();
			System.out.println("Visited Vertex "+curr.data);
			for (UndirectedEdge e : curr.edges) {
				Vertex dest = e.getDest(curr);
				if (!visited.contains(dest)) {
					visited.add(dest);
					vertices.add(dest);
				}
			}
		}// --> end-while

		System.out.println("BFS done.");
	}


	public static void dfsTest(Vertex s, AdjacencyList g) {
		HashSet<Vertex> visited = new HashSet<>();
		LinkedList<Vertex> vertices = new LinkedList<>();
		vertices.push(s); //top of stack
		visited.add(s); //visited

		while (vertices.size() > 0) {
			Vertex curr = vertices.pop();
			System.out.println("Visited Vertex "+curr.data);
			for (UndirectedEdge e : curr.edges) {
				Vertex dest = e.getDest(curr);
				if (!visited.contains(dest)) {
					visited.add(dest); //mark visited
					vertices.push(dest); //add to top of stack
				}
			}
		}// --> end-while

		System.out.println("DFS done.");
	}
}