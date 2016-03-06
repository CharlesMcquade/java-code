package code.datastructures.heap;
import java.util.ArrayList;


class MinHeap<E extends Comparable<? super E>> implements BinaryHeap<E> {
	public static final int DEFAULT_SIZE = 10;
	public static final double DEFAULT_LOAD_LOW_THRESHOLD = 0.25;
	public static final int NOT_EXISTS = -1;

	public final double LOAD_LOW_THRESHOLD;
	public final int START_SZ;
	private boolean debugging;
	private StringBuilder debugBuffer;



	private ArrayList<E> heap;
	private int bottomIdx;



	public MinHeap(int defaultStart, double loadLowThreshold, boolean isDebugging) {
		LOAD_LOW_THRESHOLD = loadLowThreshold;
		START_SZ = defaultStart;


		heap = new ArrayList<E>();
		bottomIdx = 0;
		debugging = isDebugging;
		debugBuffer = debugging ? new StringBuilder() : null;
	}

	public MinHeap() {
		this(DEFAULT_SIZE, DEFAULT_LOAD_LOW_THRESHOLD, false);
	}

	public void insert(Comparable val) {
		heap.add((E)val);
		bubbleUp(bottomIdx);
		bottomIdx++;
	}

	public E extract() {
		E root = heap.size() > 0 ? heap.get(0) : null;
		if (root != null && heap.size() > 1) {
			heap.set(0, heap.remove(bottomIdx - 1));
			if (bottomIdx > 0) {
				bottomIdx--;
				bubbleDown(0);
			}
		}
		return root;
	}

	private void bubbleUp(int i) {
		int pIndex = getParent(i);
		int currIndex = i;
		while (pIndex != -1 && heap.get(pIndex).compareTo(heap.get(currIndex)) > 0) {
			//swap
			E t = heap.get(pIndex);
			heap.set(pIndex, heap.get(currIndex));
			heap.set(currIndex, t);

			//update current
			currIndex = pIndex;
			pIndex = getParent(currIndex);
		}
	}

	private void bubbleDown(int i) {
		int currIndex = i;
		int childIndex = getSmallestChild(currIndex);
		while (childIndex != -1 && currIndex != -1 && heap.get(currIndex).compareTo(heap.get(childIndex)) > 0) {
			//swap
			E t = heap.get(childIndex);
			heap.set(childIndex, heap.get(currIndex));
			heap.set(currIndex, t);

			//update current
			currIndex = childIndex;
			childIndex = getSmallestChild(currIndex);
		}
	}


	private int getSmallestChild(int i ) {
		int left = getLeftChild(i);
		int right = getRightChild(i);
		if (left != NOT_EXISTS && right != NOT_EXISTS) 
			return heap.get(left).compareTo(heap.get(right)) > 0 ? right : left; 
		if (left == NOT_EXISTS)
			return right == NOT_EXISTS ? NOT_EXISTS : right;
		return right == NOT_EXISTS ? left : NOT_EXISTS;
	}

	private int getParent(int i) {
		return i == 0 ? NOT_EXISTS : (i - 1) / 2;
	}

	private int getLeftChild(int i) {
		int j = 2*i + 1;
		return j < bottomIdx ? j : NOT_EXISTS;
	}

	private int getRightChild(int i) {
		int j = 2*i + 2;
		return j < bottomIdx ? j : NOT_EXISTS;
	}


	@Override
	public String toString() {
		return heap.toString();
	}

	public ArrayList<E> getOrderedValues() {
		ArrayList<E> retList = new ArrayList<>();
		int sz = heap.size();
		for (int i = 0; i < sz; i++) {
			retList.add(extract());
		}
		return retList;
	}

	public static void main(String[] args) {
		MinHeap<String> heap = new MinHeap<>();
		String[] vals = {"cat", "dog", "pig", "alligator", "poop", "fish", "helicopter"};
		for(String i : vals)
			heap.insert(i);
		System.out.println(heap.toString());
		ArrayList<String> orderedValues = heap.getOrderedValues();
		System.out.println(orderedValues.toString());

	}

}