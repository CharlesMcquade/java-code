import java.lang.Math;
class UniqueChars {
	
	// input: A string str, an int k
	// return the *first* longest found substring with exactly
	// k unique characters. Return the empty string if there are 
	// no substrings of k unique characters.
	// assumption: all characters in the string are 'a'-'z'
	public static String kUniqueChars(String str, int k) {
		if (k == 0) return ""; // return nothing if 0 unique chars
		
		// convert to lower case. 
		// a different implementation would be required to preserve case
		// of returned substring, one that would ensure that it is in 
		// ASCII range of either 'a'-'z' or 'A'-'Z' and subtract
		// 'a' or 'A' respectively to find the index of freq
		str = str.toLowerCase();
		// frequency array to count total chars in sliding "window"
		int[] freq = new int[26];
		

		int leftL = 0; // longest found
		int rightL = 1; // longest found

		int left = 0; // left, inclusive
		int right = 1; // right, exclusive
		
		// length of longest found so far, for simplicity in code
		int longest = 1; 
		// count of total unique characters
		int numUnique = 1;
		freq[str.charAt(0) - 'a']++; // increase frequency of first character
		for (int i = 1; i < str.length(); i++) {
			// increment current right bound (exclusive)
			right++;
			char c = str.charAt(i);
			// frequency of current character is incremented
			freq[c - 'a']++;
			if (freq[c - 'a'] == 1) numUnique++;
			// if the number of unique characters has now increased,
			if (numUnique > k) {
				// must remove characters from left side of window
				// until any one of the characters is no longer in the 
				// window at all
				while (numUnique > k && left < right) {
					char removeChar = str.charAt(left);
					freq[removeChar - 'a']--;
					if (freq[removeChar - 'a'] == 0) numUnique--;
					left++;
				}
			}
			// check if current substring is longer than
			// longest window found so far
			if (right - left > longest) {
				rightL = right;
				leftL = left;
				longest = right - left;
			}
		}

		// If we never reached exactly k unique characters,
		// then there less than k unique characters in the string,
		// return the empty string.
		// Otherwise, return the longest one we found.
		return numUnique == k ? str.substring(leftL, rightL) : "";
	}



	// testing
	public static void main(String[] args) {
		// test methods
		test("bacabbac", 2, "abba");
		test("bacabbac", 0, "");
		test("bacabbac", 3, "bacabbac");
		test("ababababababaccccaccab", 2, "ababababababa");
		test("abcdefghijklmnopqrstuvwxyz", 27, "");
		test("abcdefghijklmnopqrstuvwxyz", 26, "abcdefghijklmnopqrstuvwxyz");
		test("abcdefgabbcafghz", 4, "gabbca");
	}
	// test helper method
	public static void test(String in, int k, String expected) {
		String actual = kUniqueChars(in, k);
		String pass = actual.equals(expected) ? "PASS" : "FAIL";
		String output = String.format("%s ### Input: \"%s\" -- k: %d -- Expected: \"%s\" -- Actual: \"%s\"",
									  pass,
									  in,
									  k,
									  expected,
									  actual);
		System.out.println(output);
	}
}