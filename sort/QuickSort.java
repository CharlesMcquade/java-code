import java.util.Arrays;

public class QuickSort {
	public static <E extends Comparable<? super E>> void quicksort(E[] arr) {
		quicksort(arr, 0, arr.length - 1);
	}

	public static <E extends Comparable<? super E>> void quicksort(E[] arr, int low, int high) {
		if (low >= high) return;
				
		E pivot = arr[low];

		int lastSwapped = low;
		int firstHigh = low + 1;
		int i = low + 1;

		while (i <= high) {
			if (arr[i].compareTo(pivot) < 0) {
				swap(arr, i, firstHigh);
				lastSwapped = firstHigh;
				firstHigh++;
			}
			i++;
		}

		if (lastSwapped != 0) {
			swap(arr, low, lastSwapped);
		}
		
		quicksort(arr, low, lastSwapped - 1);
		quicksort(arr, lastSwapped + 1, high);


	}
	private static <E extends Comparable<? super E>> void swap(E[] arr, int i, int j) {
		E t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}


	public static void main(String[] args) {
		int numNotSorted = 0;
		for (int i = 0; i < 10; i++) {
			Integer[] vals = SortTester.getRandomIntegerArray(1000000, 1000000);
			quicksort(vals);
			if (!SortTester.checkSorted(vals)) {
				numNotSorted++;
				System.out.print("\r"+numNotSorted+" not sorted.");
			}
		}
		if (numNotSorted == 0)
			System.out.println("\nAll arrays sorted.");
	}
}