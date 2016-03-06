 public class DisjointSet {
	SetNode head;
	SetNode tail;

	public DisjointSet(Vertex v) {
		SetNode newSet = new SetNode(this, v);
		this.head = newSet;
		tail = head;
		v.owner = newSet;
	}
	public DisjointSet(SetNode s) {
		s.set = this;
		head = s;
		tail = head;
	}

	public static DisjointSet makeSet(Vertex v) { return new DisjointSet(v);}
	public static DisjointSet makeSet(SetNode s) { return new DisjointSet(s);}
	//public static SetNode findSet(SetNode s) { return s.set.head; }
	public static DisjointSet findSet(Vertex v) { return v.owner.set; }

	public static DisjointSet union(DisjointSet a, DisjointSet b) {
		if (a == null || a.head == null) return b;
		if (b == null) return a;
		SetNode curr = b.head;
		while (curr != null) {
			curr.set = a;
			curr = curr.next;
		}
		a.tail.next = b.head;
		a.tail = b.tail;
		
		b.head = null;
		b.tail = null;
		return a;
	}


}

class SetNode<T> {
	DisjointSet set;
	SetNode next;
	Vertex v;
	public SetNode(DisjointSet set, Vertex v) {
		this.set = set;
		this.v = v;
		next = null;
	}

	public SetNode() {}
}