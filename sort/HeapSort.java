import java.lang.Math;
import java.util.Arrays;

class HeapSort {
	
	public static void main(String[] args) {
		// for test functions: 
		//   first param: print all before/after arrays?
		//   second param: smallest size array to test
		//   third param: largest size (inclusive) array to test
		testHeapify(false, 1, 1);
		testHeapsort(false, 1, 1);
	}

	//HEAPSORT
	public static <E extends Comparable<? super E>> void heapsort(E[] arr) {
		if (arr.length < 2) return;
		heapify(arr);

		int end = arr.length - 1;
		while (end >= 0) {
			swap(arr, 0, end--);
			bubbleDown(arr, 0, end);
		}
	}

	//HEAPIFY
	public static <E extends Comparable<? super E>> void heapify(E[] arr) {
		int end = arr.length - 1;
		int start = parent(end);

		while (start >= 0) {
			bubbleDown(arr, start, end);
			start -= 1;
		}
	}


	//Returns true if an array is a max heap
	public static <E extends Comparable<? super E>> boolean isMaxHeap(E[] arr) {
		int root = 0;
		while (leftChild(root) <= arr.length - 1) {
			if (arr[root].compareTo(arr[leftChild(root)]) < 0)
				return false;
			if (rightChild(root) <= arr.length - 1 && arr[root].compareTo(arr[rightChild(root)]) < 0)
				return false;
			root++;
		}
		return true;
	}


	 ////////////////////////////
	///////PRIVATE METHODS///////
	////////////////////////////

	private static <E extends Comparable<? super E>> void bubbleDown(E[] arr, int start, int end) {
		int root = start;

		while (leftChild(root) <= end) {
			int child = maxChild(arr, root, end);

			if (child != -1 && arr[root].compareTo(arr[child]) < 0) {
				E t = arr[child];
				arr[child] = arr[root];
				arr[root] = t;

				root = child;
			}
			else 
				return;
		}//---> end-while;
	}

	private static <E extends Comparable<? super E>> void swap(E[] arr, int i, int j) {
		E t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	private static int parent(int i) {
		return i <= 0 ? -1 : (i - 1) / 2;
	}

	private static int leftChild(int i) {
		return 2 * i + 1;
	}
	private static int rightChild(int i) {
		return 2 * i + 2;
	}

	private static <E extends Comparable<? super E>> int maxChild(E[] arr, int i, int end) {
		E leftChildVal = arr[leftChild(i)];
		E rightChildVal = rightChild(i) <= end ? arr[rightChild(i)] : null;
		if (rightChildVal == null) return leftChild(i);
		return leftChildVal.compareTo(rightChildVal) >= 0 ? leftChild(i) : rightChild(i);
	}

	private static void testHeapify(boolean printArrays, int startSize, int maxSize) {
		System.out.println("Starting Heapify tests.");
		int range = 1500;
		for (int size = startSize; size <= maxSize; size++) {
			Integer[] arr = SortTester.getRandomIntegerArray(size, range);
			if (printArrays) printArray(arr);
			heapify(arr);
			if (printArrays) printArray(arr);
			if (!isMaxHeap(arr)) {
				System.out.println("Failed. Array of size "+size+" is not a heap.");
				break;
			}
		}
		System.out.println("Finished Heapify tests.");
	}

	private static void testHeapsort(boolean printArrays, int startSize, int maxSize) {
		System.out.println("Starting Heapsort tests.");
		int range = 1500;
		for (int size = startSize; size <= maxSize; size++) {
			Integer[] arr = SortTester.getRandomIntegerArray(size, range);
			if (printArrays) printArray(arr);
			heapsort(arr);
			if (printArrays) printArray(arr);
			if (!SortTester.checkSorted(arr)) {
				System.out.println("Failed. Array of size "+size+" is not sorted.");
				break;
			}
		}
		System.out.println("Finished Heapsort tests.");
	}

	private static <E extends Comparable<? super E>> void printArray(E[] arr) {
		System.out.println(Arrays.toString(arr));
	}


	public static <E extends Comparable<? super E>> void printIsMaxHeap(E[] arr) {
		if (isMaxHeap(arr)) {
			System.out.println("Array is a max heap.");
		}
		else 
			System.out.println("Array is not a max heap.");
	}
}