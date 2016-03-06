import java.util.Random;

public class SortTester {
	public static int[] getRandomIntArray(int sz, int maxVal) {
		int[] arr = new int[sz];
		for (int i = 0; i < arr.length; i++)
			arr[i] = RandomGenerator.getRandomValue(maxVal);
		return arr;
	}

	public static Integer[] getRandomIntegerArray(int sz, int maxVal) {
		Integer[] arr = new Integer[sz];
		for (int i = 0; i < arr.length; i++)
			arr[i] = new Integer(RandomGenerator.getRandomValue(maxVal));
		return arr;
	}

	public static <E extends Comparable<? super E>> boolean checkSorted(E[] arr) {
		if (arr.length < 2) return true;
		E prev = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(prev) < 0)
				return false;
			prev = arr[i];
		}
		return true;
	}

	public static <E extends Comparable<? super E>> String checkSortedString(E[] arr) {
	 	if (checkSorted(arr)) {
	 		return "Array is sorted.";
	 	}
	 	return "Array is not sorted.";
	 }
	 public static <E extends Comparable<? super E>> void printIfSorted(E[] arr) {
	 	System.out.println(checkSortedString(arr));
	 }


}




class RandomGenerator {
	private static final char[] chars = "abcdefghijklmnopqrstuvwxyz-'".toCharArray();
	private static final char[] upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final Random r = new Random();
	public static int getRandomValueInRange(int min, int max) {
		return min + r.nextInt(max - min + 1);
	}
	public static String getRandomWord(int minLength, int maxLength) {
		StringBuilder sb = new StringBuilder();
		int wordLen = minLength + r.nextInt(maxLength - minLength + 1);
		char lastChar = 0;
		for (int i = 0; i < wordLen; i++) {
			char c;
			if (lastChar == 0 || lastChar == ' ' || lastChar == '-')
				c = upperChars[r.nextInt(upperChars.length)];
			else 
				c = chars[r.nextInt(chars.length)];

			sb.append(c);
			lastChar = c;
		}
		return sb.toString();
	}

	public static int getRandomValue(int maxVal) {
		return r.nextInt(maxVal + 1);
	}


}