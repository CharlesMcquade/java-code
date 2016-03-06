import java.util.Arrays;

class MergeSort {


	public static <E extends Comparable<? super E>> void mergesort(E[] arr) {
		mergesort(arr, 0, arr.length - 1);
	}

	public static <E extends Comparable<? super E>> void mergesort(E[] arr, int low, int high) {
		if (low >= high) return;

		int mid = (low + high) / 2;
		mergesort(arr, low, mid);
		mergesort(arr, mid + 1, high);

		merge(arr, low, mid, mid + 1, high);
	}

	public static <E extends Comparable<? super E>> void merge(E[] arr, int lowLeft, int highLeft, 
																		int lowRight, int highRight) {
		
		E[] temp = (E[]) new Comparable[highRight - lowLeft + 1];

		int indexLeft = lowLeft;
		int indexRight = lowRight;
		int indexTemp = 0;

		while (indexLeft <= highLeft && indexRight <= highRight) {
			int compareVal = arr[indexLeft].compareTo(arr[indexRight]);
			if (compareVal <= 0)
				temp[indexTemp++] = arr[indexLeft++];
			else 
				temp[indexTemp++] = arr[indexRight++];
		}
		while (indexLeft <= highLeft)
			temp[indexTemp++] = arr[indexLeft++];
		while (indexRight <= highRight)
			temp[indexTemp++] = arr[indexRight++];

		indexTemp = 0;
		for (int i = lowLeft; i <= highRight; i++) {
			arr[i] = temp[indexTemp++];
		}
	}

	public static void main(String[] args) {
		int numNotSorted = 0;
		for (int i = 0; i < 10; i++) {
			Integer[] vals = SortTester.getRandomIntegerArray(1000000, 1000);
			mergesort(vals);
			if (!SortTester.checkSorted(vals)) {
				numNotSorted++;
				System.out.print("\r"+numNotSorted+" not sorted.");
			}
		}
		if (numNotSorted == 0)
			System.out.println("\nAll arrays sorted.");
	}
}