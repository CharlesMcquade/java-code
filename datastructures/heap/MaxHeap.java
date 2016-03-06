package code.datastructures.heap;


class MaxHeap<E extends Comparable<? super E>> implements BinaryHeap<E> {
	public static final int DEFAULT_SIZE = 10;
	public static final double DEFAULT_LOAD_LOW_THRESHOLD = 0.25;
	public static final int NOT_EXISTS = -1;

	public final double LOAD_LOW_THRESHOLD;
	public final int START_SZ;
	private boolean debugging;
	private StringBuilder debugBuffer;



	private E[] heap;
	private int size;
	private int bottomIdx;
	
	public MaxHeap(int defaultStart, double loadLowThreshold, boolean isDebugging) {
		LOAD_LOW_THRESHOLD = loadLowThreshold;
		START_SZ = defaultStart;


		heap = (E[])new Comparable[START_SZ];
		size = 0;
		bottomIdx = 0;
		debugging = isDebugging;
		debugBuffer = debugging ? new StringBuilder() : null;
	}


	public MaxHeap() {
		this(DEFAULT_SIZE, DEFAULT_LOAD_LOW_THRESHOLD, false);
	}

	public MaxHeap(boolean debugEnable) {
		this(DEFAULT_SIZE, DEFAULT_LOAD_LOW_THRESHOLD, debugEnable);
	}


	
	public void insert(Comparable val) {
		//resize up logic
		if (bottomIdx == heap.length) {
			if (debugging) {
				debugBuffer.append("In insert...\n");
				debugBuffer.append("\tbottomIdx == heap.length\n");
			}
			resizeHeapUp();
		}
		

		//bottomIdx should now be valid
		heap[bottomIdx] = (E)val;
		bubbleUp(bottomIdx);
		bottomIdx++;
		size++;
	}


	public E extract() {
		E root = heap[0];
		if (root != null) {
			heap[0] = heap[bottomIdx - 1];
			heap[bottomIdx - 1] = null;
			bottomIdx--;
			size--;
			bubbleDown(0);


			//resize down logic
			if (size / (double)heap.length <= LOAD_LOW_THRESHOLD &&
				heap.length > START_SZ) {
				if (debugging) {
					debugBuffer.append("In extract()...\n");
					debugBuffer.append("\tsize / (double)heap.length <= LOAD_LOW_THRESHOLD\n");
				}
				resizeHeapDown();
			}
		}
		return root;
	}



	private void bubbleUp(int i) {
		int pIndex = getParent(i);
		int currIndex = i;
		while (pIndex != -1 && heap[pIndex].compareTo(heap[currIndex]) < 0) {
			//swap
			E t = heap[pIndex];
			heap[pIndex] = heap[currIndex];
			heap[currIndex] = t;

			//update current
			currIndex = pIndex;
			pIndex = getParent(currIndex);
		}
	}

	private void bubbleDown(int i) {
		int currIndex = i;
		int childIndex = getGreatestChild(currIndex);
		while (childIndex != -1 && currIndex != -1 && heap[currIndex].compareTo(heap[childIndex]) < 0) {
			//swap
			E t = heap[childIndex];
			heap[childIndex] = heap[currIndex];
			heap[currIndex] = t;

			//update current
			currIndex = childIndex;
			childIndex = getGreatestChild(currIndex);
		}
	}


	private int getGreatestChild(int i ) {
		int left = getLeftChild(i);
		int right = getRightChild(i);
		if (left != NOT_EXISTS && right != NOT_EXISTS) 
			return heap[left].compareTo(heap[right]) < 0 ? right : left; 
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

	private void resizeHeapUp() {
		if (debugging) {
			debugBuffer.append("In resizeHeapUp()...\n");
			debugBuffer.append("Old size: ");
			debugBuffer.append(heap.length);
			debugBuffer.append(" New size: ");
			debugBuffer.append(heap.length * 2);
			debugBuffer.append('\n');
		}
		E[] newHeap = (E[]) new Comparable[heap.length * 2];
		for (int i = 0; i < size; i++)
			newHeap[i] = heap[i];
		heap = newHeap;
	}

	private void resizeHeapDown() {
		if (debugging) {
			debugBuffer.append("In resizeHeapDown()...\n");
			debugBuffer.append("Old size: ");
			debugBuffer.append(heap.length);
			debugBuffer.append(" New size: ");
			debugBuffer.append(heap.length / 2);
			debugBuffer.append('\n');
		}
		E[] newHeap = (E[]) new Comparable[heap.length / 2];
		for (int i = 0; i < size; i++)
			newHeap[i] = heap[i];
		heap = newHeap;
	}

	

	public String inOrderExtractionString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');

		E e;
		while ((e = extract()) != null) {
			sb.append(e.toString());
			sb.append(", ");
		}

		sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		int i = 0;
		while (heap[i] != null) {
			sb.append(heap[i++].toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	}

	public String getDebugMessages() {
		if (debugging) {
			String messages = debugBuffer.toString();
			debugBuffer.setLength(0); //clear the buffer, all messages up to this point received
			return messages;
		}
		return "Debug mode not enabled.";
	}


	public static void main(String[] args) {
		MaxHeap<String> heap = new MaxHeap<>();
		String[] vals = {"cat", "dog", "pig", "alligator", "poop", "fish", "helicopter"};
		for(String i : vals)
			heap.insert(i);
		System.out.println(heap.toString());
		System.out.println(heap.inOrderExtractionString());
	}



}