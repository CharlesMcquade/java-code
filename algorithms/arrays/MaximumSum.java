public class MaximumSum {
	public static int maximumSum2D(int[][] arr) {
		int maxSumSoFar = Integer.MIN_VALUE;
		int largestNegative = Integer.MIN_VALUE;

		int numCols = arr[0].length;
		int numRows = arr.length;

		for (int left = 0; left < numCols; left++) {
			int[] temp = new int[numRows];
			for (int right = left; right < numCols; right++) {
				for (int i = 0; i < numRows; i++) {
					temp[i] += arr[i][right];
				}
				maxSumSoFar = Math.max(maxSumSoFar, kadane(temp));
			}
		}
		return maxSumSoFar;
	}

	public static int kadane(int[] arr) {
		int currSum = 0;
		int maxSumSoFar = 0;
		boolean allNegative = true;
		int maxNegative = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			currSum += arr[i];
			if (currSum > maxSumSoFar) {
				maxSumSoFar = currSum;
				allNegative = false;
			}
			else if (currSum < 0)
				currSum = 0;
			if (arr[i] < 0 && arr[i] > maxNegative)
					maxNegative = arr[i];
		}
		return allNegative ? maxNegative : maxSumSoFar;
	}

	public static void main(String[] args) {
		int[][] test01 = {{1,2,-1,-4,-20},
						  {-8, -3, 4, 2, 1},
						  {3, 8, 10, 1, 3},
						  {-4, -1, 1, 7, -6}
						 };
		/* Missed return statement
		   Misnamed variables
		   REMEMBER CORRECT CASE	
		*/
		System.out.println(maximumSum2D(test01));
	}
}