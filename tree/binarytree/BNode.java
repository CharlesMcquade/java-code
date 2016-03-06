public class BNode<E extends Comparable<? super E>> implements Comparable<BNode<E>> {
	E data;
	BNode<E> left, right;
	
	public BNode(E d) {
		data = d;
		left = null;
		right = null;
	}

	public int compareTo(BNode<E> other) throws NullPointerException {
		if (other == null) throw new NullPointerException();
		return data.compareTo(other.data);
	}
}