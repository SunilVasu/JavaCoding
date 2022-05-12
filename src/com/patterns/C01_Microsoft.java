package com.patterns;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class C01_Microsoft {

	public static void main(String[] args) {

		// REFERRENCE
		// https://algo.monster/problems/microsoft_online_assessment_questions
		// https://leetcode.com/discuss/interview-question/398023/Microsoft-Online-Assessment-Questions

		System.out.println("#1 FizzBuzz");
		System.out.println("generateFizzBuzz : " + generateFizzBuzz(15));

		System.out.println("\n#2 RemoveAdjacentDuplicates");
		System.out.println("deduped : " + removeAdjacentDup("abbaca"));

		System.out.println("\n#2.2 RemoveAdjacentDuplicates-II");
		System.out.println("deduped : " + removeAdjacentDup2("abbbaca", 3));
		System.out.println("deduped : " + removeAdjacentDup2("abbaca", 2));

		System.out.println("\n#3 MaxValue");
		System.out.println("maxVal : " + maxVal("-18", 5));
		System.out.println("maxVal : " + maxVal("72", 5));
		System.out.println("maxVal : " + maxVal("72", 1));

		System.out.println("\n#4 Unique substring");
		uniqueSubstring("abba");
		uniqueSubstring("dddd");

		System.out.println("\n#5 Can Pair");
		System.out.println("maxVal : " + canPair(new int[] { 1, 2, 2, 1 }));
		System.out.println("maxVal : " + canPair(new int[] { 1, 2, 2, 1, 3, 5 }));

		// Problems from: LeetCode
		// https://leetcode.com/discuss/interview-question/398023/Microsoft-Online-Assessment-Questions
		System.out.println("\n#6 MinDeletion to make freq unique");
		System.out.println("minDeletion : " + minDeletion("aaabbbcc"));
		System.out.println("minDeletion : " + minDeletion("abbccc"));

		System.out.println("\n#7 Min swaps to make Palindrome");
		System.out.println("minSwapToMakePalindrome : " + minSwapToMakePalindrome("mamad"));

		System.out.println("\n#8 Min Steps to Make Piles Equal Height");
		System.out.println("minSteps=" + minSteps(new Integer[] { 5, 2, 1 }));
		System.out.println("minSteps=" + minSteps(new Integer[] { 5, 1, 5 }));

		System.out.println("\n#9 Largest K such that both K and -K exist in array");
		System.out.println("largest k = " + largestK(new int[] { 2, 4, 5, -3, 3, 5 }));
		System.out.println("largest k_2 = " + largestK_2(new int[] { 2, 4, 5, -3, 3, 5 }));

		System.out.println("largest k = " + largestK(new int[] { 2, 4, 5, -3, -3, 5 }));
		System.out.println("largest k_2 = " + largestK_2(new int[] { 2, 4, 5, -3, -3, 5 }));

		System.out.println("\n#10 Max Length of a Concatenated String with Unique Characters");
		System.out.println("maxLen (4)= " + maxLen(new LinkedList<>(Arrays.asList("un", "iq", "ue"))));
		System.out.println("maxLen (0)= " + maxLen(new LinkedList<>(Arrays.asList("aa", "bb"))));

		System.out.println("\n#11 Unique integers that sum up to Zero");
		int[] arr = zeroArr(5);
		for (int n : arr) {
			System.out.print(n + ", ");
		}
		System.out.println();

		System.out.println("\n#13 Jump Game");
		System.out.println("jumpGame(T)=" + jumpGame(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println("jumpGame(F)=" + jumpGame(new int[] { 3, 2, 1, 0, 4 }));

		System.out.println("\n#14 Reverse Words in a String");
		System.out.println("reverseWords = " + reverseWords("the blue sky"));

		System.out.println("\n#17 Fair Indexs");
		System.out.println("fairIndex = " + fairIndex(new int[] { 4, -1, 0, 3 }, new int[] { -2, 5, 0, 3 }));
	}

	// #1 FizzBuzz
	// https://leetcode.com/problems/fizz-buzz/submissions/
	public static List<String> generateFizzBuzz(int n) {
		List<String> list = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				list.add("FizzBuzz");
			} else if (i % 3 == 0) {
				list.add("Fizz");
			} else if (i % 5 == 0) {
				list.add("Buzz");
			} else {
				list.add(Integer.toString(i));
			}
		}
		return list;
	}

	// #2 Remove all adjacent duplicates from str
	public static String removeAdjacentDup(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (!stack.isEmpty() && stack.peek() == c) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c : stack) {
			sb.append(c);
		}
		return sb.toString();
	}

	// #2.2 Remove all adjacent duplicates in str - II
	public static String removeAdjacentDup2(String s, int k) {
		Stack<Adjacent> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (!stack.isEmpty() && stack.peek().c == c) {
				stack.peek().count++;
			} else {
				stack.push(new Adjacent(c, 1));
			}
			if (stack.peek().count == k) {
				stack.pop();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Adjacent adj : stack) {
			for (int i = 0; i < adj.count; i++) {
				sb.append(adj.c);
			}
		}
		return sb.toString();
	}

	// #3 MaximizeValue after Insertion
	// 74,5 => 754 //put x before smaller digit
	// -13,2 => -123 //put x before bigger digit
	public static String maxVal(String s, int x) {
		StringBuilder sb = new StringBuilder();
		if (s.charAt(0) == '-') {
			sb.append(s.charAt(0));
			for (int i = 1; i < s.length(); i++) {
				if (s.charAt(i) - '0' > x) {
					return sb.append(x).append(s.substring(i)).toString();
				}
				sb.append(s.charAt(i) - '0');
			}
		} else {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) - '0' < x) {
					return sb.append(x).append(s.substring(i)).toString();
				}
				sb.append(s.charAt(i) - '0');
			}
		}
		return sb.append(x).toString();
	}

	// #4 Count all unique substring with non-repeating char
	public static void uniqueSubstring(String str) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			boolean[] freq = new boolean[26];
			String s = "";
			for (int j = i; j < str.length(); j++) {
				int pos = str.charAt(j) - 'a';
				if (freq[pos] == true)
					break;

				freq[pos] = true;

				s += str.charAt(j);
				set.add(s);
			}
		}
		System.out.println("set:" + set);
		System.out.println("subStringsWithNoRepeat:" + subStringsWithNoRepeat(str));
	}

	public static int subStringsWithNoRepeat(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		String temp = "";
		int res = 1;
		for (char c : s.toCharArray()) {
			if (temp.contains(c + "")) {
				res++;
				temp = c + "";
			} else
				temp += c + "";
		}
		return res;
	}

	// #5 Given an array N, return true if it is possible we can pair all the
	// numbers in the array with equal values
	public static boolean canPair(int[] nums) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}
		for (int c : count.keySet()) {
			if (count.get(c) % 2 == 1)
				return false;
		}
		return true;
	}

	// #6 Min Deletions to Make Character Frequencies Unique
	// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
	public static int minDeletion(String s) {
		int minDel = 0;
		int[] count = new int[26];
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
		}
		for (int val : count) {
			while (val != 0 && set.contains(val)) {
				val--;
				minDel++;
			}
			set.add(val);
		}
		return minDel;
	}

	// #7 Min swaps to make Palindrome
	public static int minSwapToMakePalindrome(String str) {
		System.out.println("swap i/p=" + str);
		char[] s = str.toCharArray();
		int count = 0;
		for (int i = 0; i < s.length / 2; i++) {

			int left = i;
			int right = s.length - left - 1;

			while (left < right) {
				if (s[left] == s[right]) {
					System.out.println("left=" + left + "   right=" + right);
					break;
				} else {
					right--;
				}
			}
			if (left == right) {
				return -1;
			} else {
				for (int j = right; j < s.length - left - 1; j++) {
					char c = s[j];
					s[j] = s[j + 1];
					s[j + 1] = c;
					count++;
					System.out.println("swap [" + count + "]=" + String.valueOf(s));
				}
			}
		}
		return count;
	}

	// #8 Min Steps to Make Piles Equal Height
	public static int minSteps(Integer[] arr) {
		int count = 0;
		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[i - 1]) {
				count += i;
			}
		}
		return count;
	}

	// #9 Largest K such that both K and -K exist in array
	public static int largestK(int[] arr) {
		Set<Integer> set = new HashSet<>();
		int res = 0;
		for (int n : arr) {
			if (set.contains(-n)) {
				res = Math.max(res, Math.abs(n));
			}
			set.add(n);
		}
		return res;
	}

	public static int largestK_2(int[] arr) {
		int res = 0;
		Arrays.sort(arr);
		int l = 0, r = arr.length - 1;
		while (l < r) {
			if (arr[l] + arr[r] == 0) {
				res = Math.max(res, Math.max(arr[l], arr[r]));
			}
			if (arr[l] + arr[r] < 0) {
				l++;
			} else {
				r--;
			}
		}
		return res;
	}

	// #10 Max Length of a Concatenated String with Unique Characters
	// https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
	static int max = 0;

	public static int maxLen(List<String> list) {
		max = 0;
		dfs(list, 0, "");
		return max;
	}

	public static void dfs(List<String> list, int start, String str) {
		if (!isUnique(str)) {
			return;
		}
		max = Math.max(max, str.length());
		for (int i = start; i < list.size(); i++) {
			System.out.println("str=" + str);
			dfs(list, i + 1, str + list.get(i));
		}
	}

	public static boolean isUnique(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				return false;
			}
			set.add(c);
		}
		return true;
	}

	// #11 Unique integers that sum up to Zero
	public static int[] zeroArr(int n) {
		int[] arr = new int[n];
		int j = 0;
		for (int i = 1; i <= n / 2; i++) {
			arr[j] = i;
			arr[j + 1] = -i;
			j = j + 2;
		}
		return arr;
	}

	// #12 Partition array into N subsets with balanced sum

	// #13 Jump Game
	// https://leetcode.com/problems/jump-game/
	public static boolean jumpGame(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > max)
				return false;
			max = Math.max(max, nums[i] + i);
		}
		return true;
	}

	// #14 Meeting Rooms II

	// #15 Count Visible Nodes in Binary Tree
	// https://leetcode.com/problems/count-good-nodes-in-binary-tree/

	// #16 Largest Alphabetic Character

	// #17 Reverse word in strign
	// https://leetcode.com/problems/reverse-words-in-a-string/
	public static String reverseWords(String s) {
		char[] ch = s.toCharArray();
		int n = s.length();

		reverse(ch, 0, n - 1);

		reverseWord(ch, n);

		return cleanSpace(ch, n);
	}

	public static void reverse(char[] ch, int i, int j) {
		while (i < j) {
			char c = ch[i];
			ch[i] = ch[j];
			ch[j] = c;
			i++;
			j--;
		}
	}

	public static void reverseWord(char[] ch, int n) {
		int i = 0, j = 0;
		while (i < n) {
			while (i < j || i < n && ch[i] == ' ')
				i++;
			while (j < i || j < n && ch[j] != ' ')
				j++;
			reverse(ch, i, j - 1);
		}
	}

	public static String cleanSpace(char[] ch, int n) {
		int i = 0, j = 0;
		while (j < n) {
			while (j < n && ch[j] == ' ')
				j++;
			while (j < n && ch[j] != ' ')
				ch[i++] = ch[j++];
			while (j < n && ch[j] == ' ')
				j++;
			if (j < n)
				ch[i++] = ' ';
		}
		return String.valueOf(ch).substring(0, i);
	}

	// #18 Fair Index
	// https://algo.monster/problems/fair_indexes
	// find index that will split array, in such a way that the sums of the
	// resulting array elements are equal.
	public static int fairIndex(int[] a, int[] b) {
		int sumA = 0, sumB = 0;
		for (int i = 0; i < a.length; i++) {
			sumA += a[i];
			sumB += b[i];
		}
		int count = 0;
		int tempA = a[0];
		int tempB = b[0];

		for (int i = 1; i < a.length; i++) {
			if (i != 1 && tempA == tempB && 2 * tempA == sumA && 2 * tempB == sumB) {
				count++;
			}
			tempA += a[i];
			tempB += b[i];
		}
		return count;
	}
}

class Adjacent {
	char c;
	int count;

	Adjacent(char c, int count) {
		this.c = c;
		this.count = count;
	}
}
