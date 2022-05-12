package com.ctci.solutions;

import java.util.Arrays;

public class S16_Moderate {

	public static void main(String[] args) {
		System.out.println("#S16 : Moderate");

		System.out.println("\n#1 Number Swapper");
		numberSwap(5, 6);

		System.out.println("\n#2 Factorial Trailing Zeros");
		System.out.println("countFactorialZeros (3)=" + countFactorialZeros(19));

		System.out.println("\n#6: Smallest Diff");
		System.out.println("smallestDiff (11-8=3)="
				+ smallestDiff(new int[] { 1, 3, 15, 11, 2 }, new int[] { 23, 127, 235, 19, 8 }));
		System.out.println("maxDiffPair = " + maxDiff(new int[] { 1, 3, 15, 11, 2 }));
	}

	// #1: Number Swapper (swap inplace)
	public static void numberSwap(int a, int b) {
		System.out.println("input :: a=" + a + "\tb=" + b);
		a = a - b;
		b = b + a;
		a = b - a;
		System.out.println("swap(1): a=" + a + "\tb=" + b);

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("swap(2): a=" + a + "\tb=" + b);
	}

	// #5: Factorial Zeros
	// Logic: 10 is make by 2*5 pair. Count this pair. There are more 2 multiple
	// than 5 multiple. Hence just count the 5 multiples.
	public static int countFactorialZeros(int num) {
		int count = 0;
		for (int i = 1; i <= num; i++) {
			count += factorialOf5(i);
		}
		return count;
	}

	public static int factorialOf5(int n) {
		int count = 0;
		while (n % 5 == 0) {
			count++;
			n = n / 5;
		}
		return count;
	}

	// #6: Smallest Diff
	public static int smallestDiff(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE;
		while (i < a.length && j < b.length) {
			if (Math.abs(a[i] - b[j]) < min) {
				min = Math.abs(a[i] - b[j]);
			}
			if (a[i] > b[j]) {
				j++;
			} else {
				i++;
			}
		}
		return min;
	}

	// 6.1 find the max diff pair in an array
	public static int maxDiff(int[] a) {
		int min, diff = -1;
		min = a[0];
		for (int i = 1; i < a.length; i++) {
			diff = Math.max(diff, a[i] - min);
			min = Math.min(min, a[i]);
		}
		return diff;
	}

}
