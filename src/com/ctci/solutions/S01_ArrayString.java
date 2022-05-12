package com.ctci.solutions;

public class S01_ArrayString {
	// Chp 1: Arrays and Strings
	public static void main(String[] args) {
		// 1.1 - check if a string has unique character
		System.out.println("isUnique = " + isUnique(new String("helo")));
		System.out.println("isUnique = " + isUnique(new String("heloo")));

		// 1.2 - check if a string is a permutation of another string
		System.out.println("isPermutation = " + isPermutation(new String("helo"), new String("hel")));
		System.out.println("isPermutation = " + isPermutation(new String("helo"), new String("help")));

		// 1.3 - URLify - "Mr John Smith ", 13 -> "Mr%20John%20Smith";
		System.out.println("URLify = " + urlify("Hello World  ", 11));

		// 1.4 - Palindrome Permutation
		System.out.println("Palindrome Permutation = " + isPermutationOfPalindrome("Tact Cat"));

		// 1.5 - OneAway
		System.out.println("OneAway = " + isOneAway("epal", "pal"));
		System.out.println("OneAway = " + isOneAway2("epal", "pal"));

		// 1.6 - Compress
		System.out.println("Compress = " + compress("aabbbc"));

		// 1.7 - MATRIX rotation
		rotateMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		// 1.8 - nullify MATRIX
		zeroMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } });

		// 1.9 - isRotated
		System.out.println("isRotated = " + isRotated("water", "terwa"));
	}

	// 1.1 - check if a string has unique character
	public static boolean isUnique(String s) {
		boolean[] char_set = new boolean[128];
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i);
			if (char_set[val]) {
				return false;
			}
			char_set[val] = true;
		}
		return true;
	}

	// 1.2 - check if a string is a permutation of another string
	public static boolean isPermutation(String s, String t) {
		int[] letters_s = new int[128];
		char[] s_arr = s.toCharArray();
		for (char c : s_arr) {
			letters_s[c]++;
		}
		for (int i = 0; i < t.length(); i++) {
			int c = t.charAt(i);
			letters_s[c]--;
			if (letters_s[c] < 0) {
				return false;
			}
		}
		return true;
	}

	// 1.3 - URLify - "Mr John Smith ", 13 -> "Mr%20John%20Smith"; given str
	// with sufficient len and true str len, add '%20'
	public static String urlify(String str, int trueLen) {
		int count = 0;
		for (int i = 0; i < trueLen; i++) {
			if (str.charAt(i) == ' ') {
				count++;
			}
		}
		int index = trueLen + count * 2;
		char[] strArr = str.toCharArray();
		for (int i = trueLen - 1; i >= 0; i--) {
			if (strArr[i] == ' ') {
				strArr[index - 1] = '0';
				strArr[index - 2] = '2';
				strArr[index - 3] = '%';
				index = index - 3;
			} else {
				strArr[index - 1] = strArr[i];
				index--;
			}
		}
		return String.valueOf(strArr);
	}

	// 1.4 - Palindrome Permutation
	// Input : Tact Coa
	// Output : true (permutation of - Taco Cat)
	public static boolean isPermutationOfPalindrome(String phrase) {

		// int[] table = buildCharFreq(phrase);
		// boolean foundOdd = false;
		// for (int count : table) {
		// if (count % 2 == 1) {
		// if (foundOdd) {
		// return false;
		// }
		// foundOdd = true;
		// }
		// }
		// return true;

		// Solution2
		int[] table2 = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		int countOdd = 0;
		for (char c : phrase.toCharArray()) {
			int x = getCharacterNumber(c);
			if (x != -1) {
				table2[x]++;
				if (table2[x] % 2 == 1) {
					countOdd++;
				} else {
					countOdd--;
				}
			}
		}
		return countOdd <= 1;
	}

	public static int[] buildCharFreq(String phrase) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int[] table = new int[z - a + 1];
		for (char c : phrase.toCharArray()) {
			int x = getCharacterNumber(c);
			if (x != -1) {
				table[x]++;
			}
		}
		return table;
	}

	private static int getCharacterNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if (val >= a && val <= z) {
			return val - a;
		}
		return -1;
	}

	// 1.5 One Away
	private static boolean isOneAway(String s1, String s2) {
		if (s1.length() == s2.length()) {
			return oneEditReplace(s1, s2);
		} else if (s1.length() + 1 == s2.length()) {
			return oneEditInsert(s1, s2);
		} else if (s1.length() == s2.length() + 1) {
			return oneEditInsert(s2, s1);
		}
		return false;
	}

	private static boolean isOneAway2(String s1, String s2) {
		// combines code
		return isOneAwayCombined(s1, s2);
	}

	private static boolean oneEditReplace(String s1, String s2) {
		boolean foundDiff = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDiff) {
					return false;
				}
				foundDiff = true;
			}
		}
		return true;
	}

	private static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < s1.length() && index2 < s2.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	private static boolean isOneAwayCombined(String a, String b) {
		// if (a.length() < b.length())
		// return isOneAwayCombined(b, a);

		String s1 = a.length() < b.length() ? a : b;
		String s2 = a.length() < b.length() ? b : a;

		if ((s2.length() - s1.length()) > 1)
			return false;

		int index1 = 0, index2 = 0;
		boolean foundDiff = false;
		while (index1 < s1.length() && index2 < s2.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (foundDiff)
					return false;
				foundDiff = true;

				if (s1.length() == s2.length())
					index1++;

			} else {
				index1++;
			}
			index2++;
		}

		return true;
	}

	// 1.6 Compress
	private static String compress(String str) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			count++;
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				sb.append(str.charAt(i));
				sb.append(count);
				count = 0;
			}
		}
		return sb.toString();
	}

	// 1.7 Rotate matrix
	private static void rotateMatrix(int[][] mat) {
		System.out.println("Rotate Matrix: Input Matrix::");
		displayMat(mat);
		// 90 Rotation
		for (int layer = 0; layer < mat.length / 2; layer++) {
			int first = layer;
			int last = mat.length - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				int top = mat[first][i];
				mat[first][i] = mat[last - offset][first];
				mat[last - offset][first] = mat[last][last - offset];
				mat[last][last - offset] = mat[i][last];
				mat[i][last] = top;
			}
		}
		System.out.println("After 90* manual rotation::");
		displayMat(mat);
		// 90 rotation => Transpose -> Flip
		mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		// Transpose
		for (int i = 0; i < mat.length; i++) {
			for (int j = i; j < mat[0].length; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}
		// Flip
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length / 2; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[i][mat[0].length - 1 - j];
				mat[i][mat[0].length - 1 - j] = temp;
			}
		}
		System.out.println("After 90* rotation: Transpose->Flip ::");
		displayMat(mat);
	}

	private static void displayMat(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 1.8 Zero Matrix
	public static void zeroMatrix(int[][] mat) {
		boolean[] rowNull = new boolean[mat.length];
		boolean[] colNull = new boolean[mat[0].length];
		System.out.println("ZeroMatrix: Input Matrix::");
		displayMat(mat);
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 0) {
					rowNull[i] = true;
					colNull[j] = true;
				}
			}
		}
		for (int i = 0; i < rowNull.length; i++) {
			if (rowNull[i]) {
				nullifyRow(mat, i);
			}
		}
		for (int j = 0; j < colNull.length; j++) {
			if (colNull[j]) {
				nullifyCol(mat, j);
			}
		}
		System.out.println("After Matrix Nullification");
		displayMat(mat);
	}

	public static void nullifyRow(int[][] mat, int r) {
		for (int j = 0; j < mat[0].length; j++) {
			mat[r][j] = 0;
		}
	}

	public static void nullifyCol(int[][] mat, int c) {
		for (int i = 0; i < mat.length; i++) {
			mat[i][c] = 0;
		}
	}

	// 1.9 is Substring
	private static boolean isRotated(String s1, String s2) {
		int len = s1.length();
		if (len == s2.length() && len > 0) {
			String s1s1 = s1 + s1;
			return s1s1.indexOf(s2) != -1;
		}
		return false;
	}

}
