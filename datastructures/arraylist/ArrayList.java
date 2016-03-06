class ArrayList<E> {
	public static final double DEFAULT_LOAD_LOW_THRESHOLD = 0.25;
	public static final int DEFAULT_START_SZ = 10;


	private final double loadLowThreshold;
	private final int initialCapacity;

	private E[] arr;
	private int size;
	private int startIdx;
	private int nextEmpty;

	public ArrayList(int initialCapacity, double loadLowThreshold) {
		this.initialCapacity = initialCapacity;
		this.loadLowThreshold = loadLowThreshold;

		arr = (E[])new Object[initialCapacity];
		size = 0;
		nextEmpty = 0;
		startIdx = 0;
	}

	public int size() { return size; }

	public ArrayList() {
		this(DEFAULT_START_SZ, DEFAULT_LOAD_LOW_THRESHOLD);
	}

	public E get(int index) {
		checkBounds(index);
		return arr[index];
	}


	//adds to back
	public void add(E val) { addBack(val); }
	public void addBack(E val) {
		if (checkResizeUp(nextEmpty)) resizeUp();

		arr[nextEmpty] = val;
		size++;
		nextEmpty++;
	}


	//remove from back
	public E remove() { return removeBack(); }
	public E removeBack() {
		E val = null;
		if (nextEmpty > 0) {
			val = arr[nextEmpty - 1];
			arr[nextEmpty - 1] = null;
			nextEmpty--;
			size--;
		}
		return val;
	}


	//performs resize if required
	private void resizeUp() {
		E[] newArr = (E[])new Object[arr.length * 2];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	private boolean checkResizeUp(int sz) {
		return sz >= arr.length;
	}

	private void shiftUp(int start) {
		//source array
		E[] currArr = arr;
		//if a resize is needed, copy all the elements
		//up to start into a new array and make that
		//the new internal array
		if (checkResizeUp(nextEmpty + 1)) {
			E[] newArr = (E[])new Object[arr.length * 2];
			for (int i = 0; i <= start; i++)
				newArr[i] = currArr[i];
			arr = newArr;
		}

		//note that destination array could have changed now!
		E prev = arr[start];
		int i = start + 1;
		do {
			E temp = currArr[i];
			arr[i] = prev;
			prev = temp;
			i++;
		} while (i <= nextEmpty);
	}


	public void set(int index, E val) {
		checkBounds(index);
		arr[index] = val;
	}

	public void addAt(int index, E val) {
		checkBounds(index);
		shiftUp(index);
		arr[index] = val;
		nextEmpty++;
		size++;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		int i = startIdx;
		while (i < nextEmpty) {
			sb.append(arr[i++].toString());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(']');

		return sb.toString();
	}

	private void checkBounds(int index) throws IndexOutOfBoundsException {
		if (index >= nextEmpty || index < 0) throw new IndexOutOfBoundsException(index + " ");
	}




	public static void main(String[] args) {
		ArrayList<Integer> arrList = new ArrayList<>();
		int[] vals = {0, 1, 2, 3, 4, 5};
		for (int v : vals) arrList.add(v);
		System.out.println(arrList.toString());
		arrList.addAt(0, 5);
		System.out.println(arrList.toString());
		arrList.addAt(3, 15);
		System.out.println(arrList.toString());
	}
}