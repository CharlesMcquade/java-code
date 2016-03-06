import java.util.Arrays;

class InsertionSort {
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(arr[i - 1]) < 0) {
				int j = i;
				while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
					swap(arr, j, j - 1);
					j--;
				}	
			}
		}
	}

	private static <T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
		T t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void main(String[] args) {
		String[] vals = {"billibob", "alligator", "dylan", "epsilon", "cook"};
		System.out.println(Arrays.toString(vals));
		insertionSort(vals);
		System.out.println(Arrays.toString(vals));
	}
}