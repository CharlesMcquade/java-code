import java.util.Arrays;
class QuickMergeSort {
	public static void mergesort(int[] arr) {
		mergesort(arr, 0, arr.length - 1);
	}
	private static void mergesort(int[] arr, int l, int r) {
		if (l >= r) return;
		int m = l + ((r - l)>>1);
		mergesort(arr, l, m);
		mergesort(arr, m + 1, r);
		merge(arr, l, m, m + 1, r);
	}

	private static void merge(int[] arr, int l1, int r1, int l2, int r2) {
		int[] left = getCopy(arr, l1, r1);
		int[] right = getCopy(arr, l2, r2);
		int i = l1;
		while (l1 <= r1 && l2 <= r2) {
			if (arr[l1] < arr[l2])
				arr[i++] = arr[l1++];
			else 
				arr[i++] = arr[l2++];
		}
		while (l1 <= r1) arr[i++] = arr[l1++];
		while (l2 <= r2) arr[i++] = arr[l2++];
	}

	private static int[] getCopy(int[] arr, int l, int r) {
		if (l > r) return new int[0];
		int[] ret = new int[(r - l) + 1];
		int j = 0;
		for (int i = l; i <= r; i++) {
			ret[j++] = arr[i];
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] vals = {3, 2, 4, 5, 7, 8, 1, 2, 3, 8, 2, 3};
		System.out.println(Arrays.toString(vals));
		mergesort(vals);
		System.out.println(Arrays.toString(vals));
	}
}