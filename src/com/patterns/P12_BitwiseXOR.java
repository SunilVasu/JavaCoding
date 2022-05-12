package com.patterns;

public class P12_BitwiseXOR {

	public static void main(String[] args) {
		System.out.println("##Pattern 12 : Bitwise XOR");

		System.out.println("\n#1: Missing Number");
		System.out.println("missingNum = " + missingNumber(new int[] { 1, 2, 3, 5 }));
		System.out.println("missingNum = " + missingNumber2(new int[] { 1, 2, 3, 5 }));

		System.out.println("\n#2: Single Number");
		System.out.println("singleNum = " + singleNumber(new int[] { 1, 3, 2, 4, 4, 3, 1 }));
		int[] res = singleNumber2(new int[] { 1, 3, 2, 4, 4, 3, 1, 5 });
		System.out.println("singleNum2 = " + res[0] + " & " + res[1]);

		System.out.println("\n#3: Bitwise Complement");
		System.out.println("bitwiseComplement = " + bitwiseComplement(5));

	}

	// #1: Missing number: RT=O(n); Space=O(1)
	// arr of (n-1) integers from range [1,n], find the missing num
	public static int missingNumber(int[] arr) {
		int n = arr.length + 1;
		int sum = n * (n + 1) / 2;

		for (int num : arr) {
			sum -= num;
		}
		return sum;
	}

	// #1.1 Using XOR: RT=O(n); Space=O(1)
	// num^0 = num
	// num^num = 0
	// 0^0=0
	// 1^0=1 -> 1 only if switch
	// 1^1=0
	// -5 = 101 -> 011
	// ~5 = 101 -> 010
	public static int missingNumber2(int[] arr) {

		int n = arr.length + 1;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum ^= i;
		}
		for (int num : arr) {
			sum ^= num;
		}
		return sum;
	}

	// #2: Single Number: find number which is single occurrence
	// Alternate: using HashMap - add num, remove if map it contains;
	// only number remaining is Map is the answer
	// https://leetcode.com/problems/single-number/
	public static int singleNumber(int[] arr) {
		int sum = 0;
		for (int n : arr) {
			sum ^= n;
		}
		return sum;
	}

	// #2.2: Single Number - III: find 2 single numbers
	// https://leetcode.com/problems/single-number-iii/
	// https://leetcode.com/problems/single-number-ii/
	public static int[] singleNumber2(int[] arr) {
		int sum = 0;
		for (int n : arr) {
			sum ^= n;
		}
		System.out.println("\nAfter 1st pass sum=" + Integer.toBinaryString(sum));
		sum = sum & -sum;
		System.out.println("Last set bit sum=" + Integer.toBinaryString(sum));
		int[] res = { 0, 0 };
		for (int n : arr) {
			if ((sum & n) == 0) {
				res[0] ^= n;
			} else {
				res[1] ^= n;
			}
		}
		return res;
	}

	// #3: Complement of base 10 number
	// https://leetcode.com/problems/complement-of-base-10-integer/
	public static int bitwiseComplement(int n) {
		if (n == 0)
			return 1;
		int x = 0;
		// find longest 11..11 >= n
		while (x < n) {
			x = 2 * x + 1;
		}
		return x ^ n;
	}

}
