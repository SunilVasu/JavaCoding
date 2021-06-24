package com.ctci.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S7_RecursionDP {

	public static void main(String[] args) {
		System.out.println("###Recursion and Dynamic Programming###");

		// 8.1 Triple Steps
		System.out.println("#8.1 Triple Steps");
		tripleSteps();

		// 8.2 Robot in a Grid

		// 8.3 Magic Index
		System.out.println("\n#8.3 Magic Index");
		magicIndex();

		// 8.4 Power Set: return all subset of a set
		System.out.println("\n#8.4 PowerSet");
		powerSet();

		// 8.5 Recursive Multiply

	}

	// 8.1 Triple Steps
	public static void tripleSteps() {
		int n = 4;
		System.out.println("Triple Step (bottom-up): " + dp(n));
		System.out.println("Triple Step (top-down): " + dp(n, new int[n + 1]));
		System.out.println("Triple Step (recursion): " + recur(n));
	}

	// DP bottom-up
	public static int dp(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		return dp[n];
	}

	// DP top-down
	public static int dp(int n, int[] memo) {
		if (n < 0)
			return 0;
		if (n == 0)
			return 1;
		memo[n] = dp(n - 1, memo) + dp(n - 2, memo) + dp(n - 3, memo);
		return memo[n];
	}

	// Recursion
	public static int recur(int n) {
		if (n < 0)
			return 0;
		if (n == 0)
			return 1;
		return recur(n - 1) + recur(n - 2) + recur(n - 3);
	}

	// 8.2 Robot in a Grid

	// 8.3 Magic Index: array with index
	public static void magicIndex() {

		int[] arr = new int[] { -1, 2, 3, 3, 4, 6, 8, 9 };

		System.out.println("Magic Index (recur): " + magicIndex(arr, 0, arr.length));

		// duplicate
		arr = new int[] { -5, -2, 1, 2, 2, 5, 7 };
		System.out.println("Magic Index (recur): " + magicIndex(arr, 0, arr.length));
		arr = new int[] { -10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13 };
		System.out.println("Magic Index (recur): " + magicDuplicate(arr, 0, arr.length));
	}

	// recur
	public static int magicIndex(int[] arr, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == mid) {
			return mid;
		} else if (arr[mid] > mid) {
			return magicIndex(arr, start, mid - 1);
		} else {
			return magicIndex(arr, mid + 1, end);
		}
	}

	// with duplicates
	public static int magicDuplicate(int[] arr, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;

		if (arr[mid] == mid) {
			return mid;
		}
		int leftIndex = Math.min(mid - 1, arr[mid]);
		int left = magicDuplicate(arr, start, leftIndex);
		if (left >= 0)
			return left;

		int rightIndex = Math.max(mid + 1, arr[mid]);
		int right = magicDuplicate(arr, rightIndex, end);

		return right;
	}

	// 8.4 Power Set: {a1,a2} -> (), (a1), (a2), (a1,a2)
	// 8.7 Permutations: {a1, a2} -> (a1,a2), (a2,a1)
	public static void powerSet() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), set);
		System.out.println("Input: " + set + "\nSubSet (input set): " + list);

		list = new ArrayList<>();
		int[] nums = new int[] { 1, 2, 3 };
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		System.out.println("SubSet: " + list);

		list = new ArrayList<>();
		nums = new int[] { 1, 2, 2 };
		Arrays.sort(nums);
		backtrack_withDup(list, new ArrayList<>(), nums, 0);
		System.out.println("SubSet (with dup): " + list);

		System.out.println("\n#8.4 Permutation");
		list = new ArrayList<>();
		nums = new int[] { 1, 2, 3 };
		// Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums);
		System.out.println("Permutation: " + list);

		list = new ArrayList<>();
		nums = new int[] { 1, 2, 2 };
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
		System.out.println("Permutation(with dup): " + list);
	}

	// set - i/p distinct; o/p must not contain duplicate set
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, Set<Integer> set) {
		list.add(new ArrayList<>(temp));
		for (int i : set) {
			if (temp.contains(i))
				return;
			temp.add(i);
			backtrack(list, temp, set);
			temp.remove(temp.size() - 1);
		}
	}

	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
		list.add(new ArrayList<>(temp));
		for (int i = start; i < nums.length; i++) {
			temp.add(nums[i]);
			backtrack(list, temp, nums, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// set - i/p contains duplicate; o/p must not contain duplicate set
	public static void backtrack_withDup(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
		list.add(new ArrayList<>(temp));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1])
				continue;
			temp.add(nums[i]);
			backtrack_withDup(list, temp, nums, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// permutation - i/p distinct
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums) {
		if (temp.size() == nums.length) {
			list.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (temp.contains(nums[i]))
					continue;
				temp.add(nums[i]);
				backtrack(list, temp, nums);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// permutation - i/p contains duplicate; return unique
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, boolean[] used) {
		if (temp.size() == nums.length) {
			list.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
					continue;
				used[i] = true;
				temp.add(nums[i]);
				backtrack(list, temp, nums, used);
				used[i] = false;
				temp.remove(temp.size() - 1);
			}
		}
	}

	// 8.5 Recursive Multiply
	// 8.6 Towers of Hanoi

}
