package com.ctci.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Misc05_Backtrack {

	public static void main(String[] args) {
		System.out.println("#MISC Backtracking");
		backtracking();

		// #6 String permutation
		stringPermutate();

		// #7 Longest Palindromic Substring
		System.out.println("\n#7 Longest Palindromic Substring");
		System.out.println("longestPalindromicSubstring (bab) = " + longestPalindromicSubstring("babacc"));
		System.out.println("longestPalindromicSubstring (bab) = " + longestPalindromicSubstring_2("babacc"));
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

		System.out.println("\n#5 Generate Parenthesis");
		List<String> listP = new ArrayList<>();
		int max = 2; // input
		// backtrack(list, String str, int open, int close, int max)
		backtrack(listP, "", 0, 0, max);
		System.out.println("Generate Parenthesis: max=" + max + "  :: " + listP);
		listP = new ArrayList<>();
		max = 3;
		backtrack(listP, "", 0, 0, max);
		System.out.println("Generate Parenthesis: max=" + max + "  :: " + listP);

	}

	// set - i/p distinct; o/p must not contain duplicate set
	// https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
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
				backtrack_withDup(list, temp, nums, remain - nums[i], i + 1);
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

	// Generate Parenthesis
	public static void backtrack(List<String> list, String str, int open, int close, int max) {
		if (str.length() == 2 * max)
			list.add(str);
		else {
			if (open < max)
				backtrack(list, str + "(", open + 1, close, max);
			if (close < open)
				backtrack(list, str + ")", open, close + 1, max);
		}
	}

	// #6 Longest valid parenthese: soln from Misc04_Longest
	// https://leetcode.com/problems/longest-valid-parentheses/
	public static int longestValidParen(String str) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int max = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty())
					stack.push(i);
				else
					max = Math.max(max, i - stack.peek());
			}
		}
		return max;
	}

	// #6 String permutation: RT=O(n*n!); There are n! permutations & it takes
	// O(n) time to print a permutation
	public static void stringPermutate() {
		String str = "abc";
		System.out.println("\n#6.1 String permutation: ");
		permutate(str, 0, str.length() - 1);

		System.out.println("\n#6.2 String permutation - 2: ");
		permutate2(str, "");

		System.out.println("\n#6.3 Check if S2 has a permutation of S1");
		checkPermutation();

	}

	public static void permutate(String str, int l, int r) {
		if (l == r) {
			System.out.print(str + " ");
		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permutate(str, l + 1, r);
				str = swap(str, l, i);
			}
		}
	}

	public static String swap(String str, int i, int j) {
		char[] ch = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return String.valueOf(ch);
	}

	public static void permutate2(String str, String temp) {
		if (str.length() == 0) {
			System.out.print(temp + " ");
		} else {
			for (int i = 0; i < str.length(); i++) {
				String rem = str.substring(0, i) + str.substring(i + 1);
				permutate2(rem, temp + str.charAt(i));
			}
		}
	}

	// https://leetcode.com/problems/permutation-in-string/
	public static void checkPermutation() {
		String s1 = "ab";
		String s2 = "cccabcc";
		int[] count = new int[26];
		int l1 = s1.length(), l2 = s2.length();

		for (int i = 0; i < l1; i++) {
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}
		if (allZeros(count)) {
			System.out.println("True-1");
			return;
		}
		for (int i = l1; i < l2; i++) {
			count[s2.charAt(i) - 'a']--;
			count[s2.charAt(i - l1) - 'a']++;
			if (allZeros(count)) {
				System.out.println("True-2");
				return;
			}
		}
		System.out.println("False");
	}

	public static boolean allZeros(int[] count) {
		for (int n : count)
			if (n != 0)
				return false;
		return true;
	}

	// Longest Palindromic Substring
	// https://leetcode.com/problems/longest-palindromic-substring/description/
	// https://leetcode.com/problems/palindromic-substrings/description/
	static int start = 0, end = 0;

	public static String longestPalindromicSubstring(String s) {
		for (int i = 0; i < s.length(); i++) {
			extend(s, i, i);
			extend(s, i, i + 1);
		}
		return s.substring(start, end + 1);
	}

	public static void extend(String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		// reverse 1 step
		i++;
		j--;
		if (end - start < j - i) {
			start = i;
			end = j;
		}
	}

	// LongestPalindromicSubstring - iterative approach
	public static String longestPalindromicSubstring_2(String s) {
		int start = 0, end = 0;
		int i = 0, j = 0;
		for (int k = 0; k < s.length(); k++) {
			i = k;
			j = k;
			while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
				i--;
				j++;
			}
			i++;
			j--;
			if (end - start < j - i) {
				start = i;
				end = j;
			}

			i = k;
			j = k + 1;
			while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
				i--;
				j++;
			}
			i++;
			j--;
			if (end - start < j - i) {
				start = i;
				end = j;
			}
		}
		return s.substring(start, end + 1);
	}

}
