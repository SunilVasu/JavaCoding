package com.ctci.solutions;

import java.util.LinkedList;
import java.util.List;

public class Misc6_Algo {
	public static void main(String[] args) {
		System.out.println("#Misc Algorithms");
		// #1 Majority element
		majorityElem();
		majorityElem_2();

		// #2 Knapsack 01
		knapsack01();
	}

	// #1 Majority elem: Linear Time RT O(n) (Boyer-Moore Majority Vote Algo)
	// The majority element is the element that appears more than n/2 times.
	public static void majorityElem() {
		System.out.println("\n#1 Majority Element Vote");
		int[] nums = new int[] { 1, 2, 3, 1, 2, 2, 2 };
		int major = -1, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (count == 0) {
				major = nums[i];
				count++;
			} else if (major == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
		System.out.println("Majority Elem: " + major);
	}

	public static void majorityElem_2() {
		System.out.println("\n#1.1 Majority Element Vote - 2 elems");
		int[] nums = new int[] { 2, 3, 2, 2, 1, 6, 6, 6 };
		int major1 = 0, count1 = 0, major2 = 0, count2 = 0;
		for (int n : nums) {
			if (count1 == 0) {
				major1 = n;
				count1++;
			} else if (count2 == 0) {
				major2 = n;
				count2++;
			} else if (major1 == n) {
				count1++;
			} else if (major2 == n) {
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		System.out.println("nums.length: " + nums.length + " & n/3: " + nums.length / 3);
		// Validate
		count1 = 0;
		count2 = 0;
		for (int n : nums) {
			if (major1 == n) {
				count1++;
			} else if (major2 == n) {
				count2++;
			}
		}
		System.out.println("Major_1:" + major1 + " count:" + count1);
		System.out.println("Major_2:" + major2 + " count:" + count2);
		List<Integer> res = new LinkedList<>();
		if (count1 > nums.length / 3)
			res.add(major1);
		if (count2 > nums.length / 3)
			res.add(major2);
		System.out.println("Majority Voting: " + res);
	}

	// #2 Knapsack 01: maximize value keeping weight <= W
	public static void knapsack01() {
		System.out.println("\n#2 Knapsack 01");

		int[] val = new int[] { 1, 4, 5, 7 };
		int[] wt = new int[] { 1, 3, 4, 5 };
		int W = 7;
		int[][] dp = knapsack01(val, wt, W);

		System.out.println("Knapsack 01 Value = " + dp[val.length][W]);
		printMatrix(dp, "Knapsack 01 Matrix::");

		val = new int[] { 1, 5, 7, 9 };
		wt = new int[] { 2, 4, 1, 3 };
		W = 5;
		dp = knapsack01(val, wt, W);
		System.out.println("Knapsack 01 Max Value = " + dp[val.length][W]);
		printMatrix(dp, "Knapsack 01 Matrix::");
	}

	public static int[][] knapsack01(int[] val, int[] wt, int W) {
		int[][] dp = new int[val.length + 1][W + 1];

		for (int i = 0; i <= val.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (j < wt[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					// System.out.print("i=" + i + " j=" + j + " wt[i-1]=" +
					// wt[i - 1] + " ");
					// System.out.println("max=(" + (val[i - 1] + dp[i - 1][j -
					// wt[i - 1]]) + ", " + dp[i - 1][j] + ")");
					dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
				}
			}
		}
		return dp;
	}

	// META
	public static void printMatrix(int[][] arr, String msg) {
		System.out.println(msg);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "   ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
