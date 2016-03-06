package code.algorithms.math 



class Math {
	
	// Returns: absolute value of 
	// greatest common divisor of two integers a & b
	public static int gcd(int a, int b) {
		if (a == 0 || b == 0) return a == 0 ? b : a;
		
		int r = a % b;
		while (r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b >= 0 ? b : ~b + 1;
	}

	public static long largestPrimeFactor(long n) {

	}



	public static void main(String[] args) {

	}
}