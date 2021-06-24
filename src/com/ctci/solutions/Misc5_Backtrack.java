package com.ctci.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Misc5_Backtrack {

	public static void main(String[] args) {
		System.out.println("#MISC Backtracking");
		backtracking();
	}

	// 8.4 Power Set: {a1,a2} -> (), (a1), (a2), (a1,a2)
	// 8.7 Permutations: {a1, a2} -> (a1,a2), (a2,a1)
	public static void backtracking() {
		// Power Set: return all subset of a set
		System.out.println("\n#1 PowerSet");
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

		System.out.println("\n#2 Permutation");
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

		System.out.println("\n#3 Combination Sum");
		list = new ArrayList<>();
		nums = new int[] { 2, 3, 7 };
		int target = 7;
		// Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, target, 0);
		System.out.println("Combination Sum (with reuse, i/p distinct): " + list);

		list = new ArrayList<>();
		nums = new int[] { 2, 3, 7, 2 };
		target = 7;
		Arrays.sort(nums);
		backtrack_withDup(list, new ArrayList<>(), nums, target, 0);
		System.out.println("Combination Sum (without reuse, i/p dup): " + list);

		System.out.println("\n#4 Palindrome Partition");
		List<List<String>> list2 = new ArrayList<>();
		String str = "aab";
		backtrack(list2, new ArrayList<String>(), str, 0);
		System.out.println("Palindrome Partition: " + list2);
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

	// Combination Sum - i/p distinct
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int remain, int start) {
		if (remain < 0)
			return;
		if (remain == 0)
			list.add(new ArrayList<>(temp));
		else {
			for (int i = start; i < nums.length; i++) {
				temp.add(nums[i]);
				backtrack(list, temp, nums, remain - nums[i], i);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// Combination Sum - i/p duplicates; return unique
	public static void backtrack_withDup(List<List<Integer>> list, List<Integer> temp, int[] nums, int remain,
			int start) {
		if (remain < 0)
			return;
		if (remain == 0)
			list.add(new ArrayList<>(temp));
		else {
			for (int i = start; i < nums.length; i++) {
				if (i > start && nums[i] == nums[i - 1])
					continue;
				temp.add(nums[i]);
				backtrack(list, temp, nums, remain - nums[i], i + 1);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// Palindrom Partition
	public static void backtrack(List<List<String>> list, List<String> temp, String s, int start) {
		if (start == s.length())
			list.add(new ArrayList<>(temp));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome(s, start, i)) {
					temp.add(s.substring(start, i + 1));
					backtrack(list, temp, s, i + 1);
					temp.remove(temp.size() - 1);
				}
			}
		}
	}

	public static boolean isPalindrome(String s, int start, int end) {
		while (start < end) {
			if (s.charAt(start++) != s.charAt(end--))
				return false;
		}
		return true;
	}

}
