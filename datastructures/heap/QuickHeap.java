import java.util.Arrays;
import java.util.ArrayList;

// A quick min heap implementation
class QuickHeap {
	Integer[] heap;
	int sz;

	public QuickHeap(int capacity) {
		this.heap = new Integer[capacity];
		this.sz = 0;
	}


	public boolean add(int val) {
		if (sz < heap.length) {
			heap[sz] = new Integer(val);
			bubbleUp(sz++);
			return true;
		}
		else return false;
	}

	public Integer extract() {
		if (sz == 0) return null;
		Integer val = heap[0];
		sz--;
		if (sz > 0) {
			heap[0] = heap[sz];
			heap[sz] = null;
			bubbleDown(0);
		}
		return val;
	}

	private void bubbleUp(int i) {
		int c = i;
		int p = parent(i);
		//min heap
		while (p != -1 && heap[p].compareTo(heap[c]) > 0) {
			swap(c, p);
			c = p;
			p = parent(c);
		}
	}

	private void bubbleDown(int i) {
		int p = i;
		int c = minChild(i);
		while (c != -1 && heap[p].compareTo(heap[c]) > 0) {
			swap(c, p);
			p = c;
			c = minChild(p);
		}
	}

	private int minChild(int i) {
		int leftChild = leftChild(i);
		int rightChild = rightChild(i);
		if (leftChild == -1 && rightChild == -1)
			return -1;
		if (rightChild == -1)
			return leftChild;
		return heap[leftChild].compareTo(heap[rightChild]) < 0 ? leftChild : rightChild;
	}

	private int parent(int i) {
		if (i == 0) return -1;
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		int child = (2 * i) + 1;
		return child < this.sz ? child : -1;
	}

	private int rightChild(int i) {
		int child = (2 * i) + 2;
		return child < this.sz ? child : -1;
	}

	private void swap(int i, int j) {
		Integer t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	public String toString() {
		return Arrays.toString(heap);
	}

	public static void main(String[] args) {
		int[] vals = {4,3,2,6,8,1,2,4,2,6,8,3};
		QuickHeap h = new QuickHeap(vals.length);
		for (int i : vals)
			h.add(i);
		ArrayList<Integer> extraction = new ArrayList<>();
		for (int i : vals)
			extraction.add(h.extract());
		System.out.println(extraction.toString());

	}
}