package com.ctci.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class S07_RecursionDP {

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
		System.out.println("\n#8.5 Recursive Multiply");
		recursiveMultiply();

		// 8.6 Towers of Hanoi
		System.out.println("\n#8.6 Towers of Hanoi");
		towersOfHanoi();

		// 8.9 Recursive Multiply
		System.out.println("\n#8.9 Parens");
		parens();

		// 8.10 Paint Fill
		System.out.println("\n#8.10 Paint Fill");
		fill();

		// 8.11 coins
		System.out.println("\n#8.11 Coins");
		coins();
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

		System.out.println("\n#8.7 Permutation && #8.8 Permutation with Dup");
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

	// i/p list
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
	public static void recursiveMultiply() {
		int a = 5, b = 10;
		int bigger = a > b ? a : b;
		int smaller = a > b ? b : a;
		int ans = minProductHelper(bigger, smaller);
		System.out.println("smaller=" + smaller + "  bigger=" + bigger + " => Product=" + ans);
		smaller = 4;
		System.out.println("smaller=" + smaller + "  bigger=" + bigger + " => Product=" + ans);
	}

	public static int minProductHelper(int smaller, int bigger) {
		if (smaller == 0)
			return 0;
		if (smaller == 1)
			return bigger;

		int s = smaller >> 1;
		int halfProd = minProductHelper(s, bigger);
		if (smaller % 2 == 0)
			return halfProd + halfProd;
		else
			return halfProd + halfProd + bigger;
	}

	// 8.6 Towers of Hanoi
	public static void towersOfHanoi() {
		int n = 3;
		Tower[] towers = new Tower[n];
		for (int i = 0; i < 3; i++) {
			towers[i] = new Tower(i);
		}
		for (int i = n; i > 0; i--) {
			towers[0].add(i);
		}
		System.out.println("Source Tower [0] " + towers[0].disks);
		towers[0].moveDisks(n, towers[2], towers[1]);
		for (int i = 0; i < 3; i++) {
			System.out.println("Tower " + towers[i].index + " : " + towers[i].disks);
		}
	}

	// 8.9 Parens
	public static void parens() {
		int n = 3;
		List<String> list = new LinkedList<>();
		backtrack(list, "", 0, 0, n);
		System.out.println("Parens : " + list);
	}

	public static void backtrack(List<String> list, String str, int open, int close, int max) {
		if (str.length() == 2 * max) {
			list.add(str);
		} else {
			if (open < max)
				backtrack(list, str + "(", open + 1, close, max);
			if (close < open)
				backtrack(list, str + ")", open, close + 1, max);
		}
	}

	// 8.10 Paint fill
	public static void fill() {
		int[][] pic = new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 } };
		System.out.println("paintFill: " + paintFill(pic, 1, 1, 2));
		System.out.println("New Pic:");
		for (int i = 0; i < pic.length; i++) {
			for (int j = 0; j < pic[0].length; j++) {
				System.out.print(pic[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("paintFill: " + paintFill(pic, 1, 1, 2));
	}

	public static boolean paintFill(int[][] pic, int r, int c, int val) {
		if (pic[r][c] == val)
			return false;
		return paintFill(pic, r, c, pic[r][c], val);
	}

	public static boolean paintFill(int[][] pic, int r, int c, int currVal, int newVal) {
		if (r < 0 || r >= pic.length || c < 0 || c >= pic[0].length)
			return false;
		if (pic[r][c] == currVal) {
			pic[r][c] = newVal;
			paintFill(pic, r + 1, c, currVal, newVal);
			paintFill(pic, r - 1, c, currVal, newVal);
			paintFill(pic, r, c + 1, currVal, newVal);
			paintFill(pic, r, c - 1, currVal, newVal);
		}
		return true;
	}

	// 8.11 Coins - Page 361
	public static void coins() {
		System.out.println("Min number of coins to make up amt = " + coin_minCoins(new int[] { 1, 5, 6, 8 }, 11));
		// coin_maxComb();
		System.out.println("Max combination of coins to make up amt = " + coin_maxComb(new int[] { 1, 2, 5 }, 5));
	}

	// Min number of coins to make up amount
	public static int coin_minCoins(int[] coins, int amt) {
		int[] dp = new int[amt + 1];
		for (int sum = 1; sum <= amt; sum++) {
			int min = -1;
			for (int coin : coins) {
				if (sum >= coin && dp[sum - coin] != -1) {
					int temp = dp[sum - coin] + 1;
					min = min == -1 ? temp : (temp < min ? temp : min);
				}
			}
			dp[sum] = min;
		}
		return dp[amt];
	}

	// Max number of combination to make up amount
	public static int coin_maxComb(int[] coins, int amt) {
		int[] dp = new int[amt + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int sum = coin; sum <= amt; sum++) {
				dp[sum] += dp[sum - coin];

			}
		}
		return dp[amt];
	}
}

class Tower {
	Stack<Integer> disks;
	int index;

	public Tower(int i) {
		disks = new Stack<Integer>();
		index = i;
	}

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("ERROR in placing disk " + d);
		} else {
			disks.push(d);
		}
	}

	public void moveTopTo(Tower t) {
		int top = disks.pop();
		t.add(top);
	}

	public void moveDisks(int n, Tower destination, Tower buffer) {
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}
}
