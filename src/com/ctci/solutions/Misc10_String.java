package com.ctci.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Misc10_String {

	public static void main(String[] args) {
		System.out.println("##MISC 10 : String");

		System.out.println("\n#Remove Duplicates");
		System.out.println("removeDuplicates: " + removeDuplicates("abbaca"));
		System.out.println("removeDuplicates: " + removeDuplicates("azxxzy"));
		System.out.println("\nremoveDuplicates2: " + removeDuplicates2("abcd", 2));
		System.out.println("removeDuplicates2: " + removeDuplicates2("deeedbbcccbdaa", 3));

		System.out.println("\n#L1: Reorganize String");
		System.out.println("reorganizeString=" + reorganizeString("aaacbb"));

		System.out.println("\n#L2:  Find Common Characters");
		System.out.println("commonChars=" + commonChars(new String[] { "bella", "label", "roller" }));
	}

	// #1: Remove Duplicates from String: RT=O(n)=space
	// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
	public static String removeDuplicates(String s) {
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

	// #2: Remove Duplicates from String with k char: RT=O(n)=space
	// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
	public static String removeDuplicates2(String s, int k) {
		Stack<StrNode> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (!stack.isEmpty() && stack.peek().ch == c) {
				stack.peek().count++;
			} else {
				stack.push(new StrNode(c, 1));
			}
			if (stack.peek().count == k) {
				stack.pop();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (StrNode n : stack) {
			for (int i = 0; i < n.count; i++) {
				sb.append(n.ch);
			}
		}
		return sb.toString();
	}

	// LEETCODE
	// #1: Reorganize String
	// https://leetcode.com/problems/reorganize-string/
	// Logic: 1) get char with max occurrence
	// 2) put max char in even position
	// 3) fill the remaining char in open positions
	public static String reorganizeString(String s) {
		char[] count = new char[26];
		int max = 0, letter = 0;
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
			if (max < count[c - 'a']) {
				max = count[c - 'a'];
				letter = c - 'a';
			}
		}
		if (max > (s.length() + 1) / 2) {
			return "";
		}
		char[] res = new char[s.length()];
		int index = 0;
		while (count[letter] > 0) {
			res[index] = (char) (letter + 'a');
			index += 2;
			count[letter]--;
		}
		for (int i = 0; i < count.length; i++) {
			while (count[i] > 0) {
				if (index >= res.length) {
					index = 1;
				}
				res[index] = (char) (i + 'a');
				index += 2;
				count[i]--;
			}
		}
		return String.valueOf(res);
	}

	// #2 Find Common Character
	// https://leetcode.com/problems/find-common-characters/
	// i/p: words = ["bella","label","roller"]; Output: ["e","l","l"]
	// Logic: 1) create dict for 1st word
	// 2) for other words create curr dict
	// 3) update common dict, if curr count is less
	// 4) convert to String using dict
	public static List<String> commonChars(String[] words) {
		int[] dict = new int[26];
		for (char c : words[0].toCharArray()) {
			dict[c - 'a']++;
		}
		for (int i = 1; i < words.length; i++) {
			int[] curr = new int[26];
			for (char c : words[i].toCharArray()) {
				curr[c - 'a']++;
			}
			for (int j = 0; j < 26; j++) {
				if (curr[j] < dict[j]) {
					dict[j] = curr[j];
				}
			}
		}
		List<String> res = new LinkedList<>();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < dict[i]; j++) {
				res.add(String.valueOf((char) (i + 'a')));
			}
		}

		return res;
	}
}

class StrNode {
	char ch;
	int count;

	StrNode(char ch, int count) {
		this.ch = ch;
		this.count = count;
	}
}