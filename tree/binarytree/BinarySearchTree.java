class BinarySearchTree<E extends Comparable<? super E>> {
	BNode<E> root;
	int size;
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public boolean add(E val) {
		BNode<E> newNode = new BNode<>(val);
		if (root == null) {
			root = new BNode<E>(val);
			size++;
		}
		else {
			BNode<E> parent = null;
			BNode<E> curr = root;
			int compareVal = 0;
			while (curr != null) {
				parent = curr;
				int compareVal = curr.compareTo(newNode);
				if (compareVal > 0)
					curr = curr.left;
				else if (compareVal < 0)
					curr = curr.right;
				else return false;
			}
			if (compareVal > 0)
				parent.left = newNode;
			else 
				parent.right = newNode;
		}
		return true;
	}

	public E remove(E val) {
		// if (root != null) {
		// 	BNode<E> parent = null;
		// 	BNode<E> curr = root;
		// 	int compareVal = 0;
		// 	while (curr != null) {
		// 		int compareVal = curr.compareTo(newNode);
		// 		if (compareVal == 0)
		// 			break;

		// 		parent = curr;
		// 		if (compareVal > 0)
		// 			curr = curr.left;
		// 		else 
		// 			curr = curr.right;
		// 	}
		// 	if (curr == null) return null;
		// 	compareVal = parent.compareTo(curr);
		// 	if (compareVal > 0) {
		// 		if (isLeaf(curr)) {
		// 			parent.left = null;
		// 		}
		// 		else {
		// 			parent.left = curr.right;
		// 			if (curr.right != null && leftMostGrandchild(curr.right) != null)
		// 				parent.right = 
		// 		}
		// 	}
		// }
	}




	private BNode<E> leftMostGrandchild(BNode<E> n) throws InvalidArgumentException {
		if (n == null) throw new InvalidArgumentException();
		if (isLeaf(n)) return null;
		BNode<E> parent = null;
		BNode<E> curr = n;
		while (curr != null) {
			parent = curr;
			curr = curr.left;
		}
		return parent;
	}



	public boolean isLeaf(BNode<E> n) {
		return n != null && n.left == null && n.right == null;
	}
}