package com.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class P10_Subsets {

	public static void main(String[] args) {
		System.out.println("##Pattern 10 : Subsets");

		System.out.println("#1 Subset");
		subset(new int[] { 1, 2, 3 });
		System.out.println("\n#Combination Sum");
		combinationSum(5);
		System.out.println("\n#Palindrome Partition");
		palindromPartition();

		System.out.println("\n#2 Permutation");
		permutation(new int[] { 1, 2, 3 });

		System.out.println("\n#3 String Permutation");
		permutation_str("abc");

		System.out.println("\n#4 Generate Parenthese");
		generateParenthese();

		System.out.println("\n#5 Generate Abbreviations");
		generateAbbreviations();

		System.out.println("\n#6 Different ways to Compute");
		diffWaysToCompute();

		// #7 Unique Binary Search Trees I
		// https://leetcode.com/problems/unique-binary-search-trees/

		// #8 Unique Binary Search Trees II
		// https://leetcode.com/problems/unique-binary-search-trees-ii/

		System.out.println("\n# Calculator");
		calculator();
	}

	// #1 Subset: RT = O(N * 2^N) = space
	public static void subset(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int n : nums)
			set.add(n);
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), set);
		System.out.println("subset - using set :: " + list);

		nums = new int[] { 1, 2, 3 };
		list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), nums, 0);
		System.out.println("subset - using arr :: " + list);

		nums = new int[] { 1, 2, 2 };
		list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), nums, 0);
		System.out.println("subset - with i/p dup  :: " + list);
	}

	// subset - using set
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, Set<Integer> set) {
		list.add(new ArrayList<>(temp));
		for (int n : set) {
			if (temp.contains(n))
				return;
			temp.add(n);
			backtrack(list, temp, set);
			temp.remove(temp.size() - 1);
		}
	}

	// subset - using arr
	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
		list.add(new ArrayList<>(temp));
		for (int i = start; i < nums.length; i++) {
			temp.add(nums[i]);
			backtrack(list, temp, nums, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// subset - with i/p dup
	public static void backtrack_dup(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
		list.add(new ArrayList<>(temp));
		for (int i = start; i < nums.length; i++) {
			if (temp.contains(nums[i]))
				return;
			temp.add(nums[i]);
			backtrack_dup(list, temp, nums, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// #2 Permutation: RT = O(N * N!) = space
	public static void permutation(int[] nums) {
		List<List<Integer>> list = new LinkedList<>();
		backtrack(list, new ArrayList<>(), nums);
		System.out.println("permutation  :: " + list);

		nums = new int[] { 1, 2, 2 };
		list = new LinkedList<>();
		backtrack_dup(list, new ArrayList<>(), nums, new boolean[nums.length]);
		System.out.println("permutation - with dup :: " + list);
	}

	public static void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums) {
		if (temp.size() == nums.length)
			list.add(new ArrayList<>(temp));
		for (int i = 0; i < nums.length; i++) {
			if (temp.contains(nums[i]))
				continue;
			temp.add(nums[i]);
			backtrack(list, temp, nums);
			temp.remove(temp.size() - 1);
		}
	}

	public static void backtrack_dup(List<List<Integer>> list, List<Integer> temp, int[] nums, boolean[] used) {
		if (temp.size() == nums.length) {
			list.add(new ArrayList<>(temp));
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				continue;
			}
			used[i] = true;
			temp.add(nums[i]);
			backtrack_dup(list, temp, nums, used);
			temp.remove(temp.size() - 1);
			used[i] = false;
		}
	}

	// Combination Sum - backtracking
	public static void combinationSum(int target) {

		int[] nums = new int[] { 5, 2, 3, 1 };
		Arrays.sort(nums);
		List<List<Integer>> list = new LinkedList<>();
		backtrack(list, new ArrayList<>(), nums, target, 0);
		System.out.println("target=" + target + "  list=" + list);

		nums = new int[] { 5, 2, 3, 4, 1 };
		Arrays.sort(nums);
		list = new LinkedList<>();
		backtrack_withDup(list, new ArrayList<>(), nums, target, 0);
		System.out.println("target=" + target + "  list=" + list);

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

	// palindrom partition
	public static void palindromPartition() {
		String s = "aab";
		List<List<String>> list = new LinkedList<>();
		backtrack(list, new LinkedList<>(), s, 0);
		System.out.println("list=" + list);
	}

	public static void backtrack(List<List<String>> list, List<String> temp, String s, int start) {
		if (start == s.length()) {
			list.add(new ArrayList<>(temp));
		} else {
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

	// #3 string permutation: RT = O(N * N!)
	public static void permutation_str(String str) {
		System.out.print("string permutation :: ");
		permutation(str, 0, str.length());

		System.out.println();
		System.out.print("string permutation :: ");
		permutation2(str, "");
		System.out.println();
	}

	public static void permutation(String str, int l, int r) {
		if (l == r)
			System.out.print(str + " ");
		for (int i = l; i < r; i++) {
			str = swap(str, l, i);
			permutation(str, l + 1, r);
			str = swap(str, l, i);
		}
	}

	public static String swap(String str, int i, int j) {
		char[] ch = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return String.valueOf(ch);
	}

	public static void permutation2(String str, String temp) {
		if (str.length() == 0) {
			System.out.print(temp + " ");
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String left = str.substring(0, i);
			String right = str.substring(i + 1);
			String res = left + right;
			permutation2(res, temp + ch);
		}
	}

	// #4 Generate Parenthese: RT = O(4^n/ sqrt(n)) = space
	public static void generateParenthese() {
		int n = 2;
		List<String> list = new LinkedList<>();
		backtrack(list, "", 0, 0, n);
		System.out.println("Generate Parenthese n=" + n + "  list=" + list);

		n = 3;
		list = new LinkedList<>();
		backtrack(list, "", 0, 0, n);
		System.out.println("Generate Parenthese n=" + n + "  list=" + list);
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

	// #5 Generalized Abbreviations
	// Given word = "word", return the following list (order does not matter):
	// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
	// "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	public static void generateAbbreviations() {
		String word = "word";
		List<String> list = new LinkedList<>();
		backtrack(list, word, 0, "", 0);
		System.out.println("list=" + list);
	}

	public static void backtrack(List<String> list, String str, int pos, String curr, int count) {
		if (pos == str.length()) {
			if (count > 0)
				curr += count;
			list.add(curr);

		} else {
			backtrack(list, str, pos + 1, curr, count + 1);
			backtrack(list, str, pos + 1, curr + (count > 0 ? count : "") + str.charAt(pos), 0);
		}
	}

	// #6 Different ways to add parentheses
	public static void diffWaysToCompute() {
		String input = "2-1-1";
		List<Integer> res = diffWaysToCompute(input);
		System.out.println("input=" + input + "  res=" + res);

		String input2 = "2-1-10";
		res = diffWaysToCompute(input2);
		System.out.println("input=" + input2 + "  res=" + res);

	}

	public static List<Integer> diffWaysToCompute(String str) {
		System.out.println(str);
		List<Integer> res = new LinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '-' || ch == '+' || ch == '*') {
				String a = str.substring(0, i);
				String b = str.substring(i + 1);
				List<Integer> al = diffWaysToCompute(a);
				List<Integer> bl = diffWaysToCompute(b);
				for (int x : al) {
					for (int y : bl) {
						if (ch == '-')
							res.add(x - y);
						if (ch == '+')
							res.add(x + y);
						if (ch == '*')
							res.add(x * y);
					}
				}
			}
		}
		// input string doesn't have any operation in it
		if (res.size() == 0) {
			res.add(Integer.valueOf(str));
		}
		System.out.println("res:" + res);
		return res;
	}

	// CALCULATORS
	// https://leetcode.com/problems/basic-calculator-ii/
	// https://leetcode.com/problems/basic-calculator/
	public static void calculator() {
		String input = "3+5  /2"; // 5
		calculator_1(input);
		input = "3+2*2";// 7
		calculator_1(input);

		input = " 2-1 + 2 "; // 3
		calculator_2(input);
		input = "(1+(4+5+2)-3)+(6+8)"; // 23
		calculator_2(input);

	}

	// Calculator_1:
	// "3+2*2"=7; " 3/2 "=1; " 3+5 / 2 "=2
	public static void calculator_1(String input) {
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		char sign = '+';
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (Character.isDigit(ch)) {
				num = num * 10 + (ch - '0');
			}
			if (!Character.isDigit(ch) && ch != ' ' || i == input.length() - 1) {
				if (sign == '*')
					stack.push(stack.pop() * num);
				if (sign == '/')
					stack.push(stack.pop() / num);
				if (sign == '+')
					stack.push(num);
				if (sign == '-')
					stack.push(-num);
				num = 0;
				sign = ch;
			}
		}
		int res = 0;
		for (int n : stack)
			res += n;
		System.out.println("calculator_1 :: res=" + res);
	}

	// Calculator_2:
	// " 2-1 + 2 " = 3; "(1+(4+5+2)-3)+(6+8)" = 23
	public static void calculator_2(String input) {
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		int num = 0;
		int sign = 1;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (Character.isDigit(ch)) {
				num = num * 10 + (ch - '0');
			} else if (ch == '+') {
				res += num * sign;
				num = 0;
				sign = 1;
			} else if (ch == '-') {
				res += num * sign;
				num = 0;
				sign = -1;
			} else if (ch == '(') {
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1;
			} else if (ch == ')') {
				res += num * sign;
				res *= stack.pop();
				res += stack.pop();
				num = 0;
			}
		}
		if (num != 0)
			res += num * sign;
		System.out.println("calculator_2 :: res=" + res);
	}

}
