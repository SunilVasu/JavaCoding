package com.patterns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class P01_SlidingWindow {

	public static void main(String[] args) {
		System.out.println("##Pattern 01 : Sliding Window");

		System.out.println("\n#1 Max Avg sub-array");
		System.out.println("maxAvgsub-array (12.75) = " + maxAvgSubArray(new int[] { 1, 12, -5, -6, 50, 3 }, 4));
		maxAvgSubArray2(new int[] { 1, 12, -5, -6, 50, 3 }, 4);
		maxAvgSubArray2(new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 }, 5); // [2.2,
																		// 2.8,
																		// 2.4,
																		// 3.6,
																		// 2.8]

		System.out.println("\n#2 Largest Subarray of length k: find largest subarray of length k");
		System.out.println("\nmaxSubarrayOfSizeK (9) = " + largestSubarrLenK(new int[] { 2, 1, 5, 1, 3, 2 }, 3));
		System.out.println("\nmaxSubarrayOfSizeK (7) = " + largestSubarrLenK(new int[] { 2, 3, 4, 1, 5 }, 2));

		System.out.println("\n#3 Min size Subarray sum: find min size subarray >= target");
		System.out.println("minSubarraySum (2) = " + minSizeSubarraySum(new int[] { 2, 1, 5, 2, 3, 2 }, 7));
		System.out.println("minSubarraySum (1) = " + minSizeSubarraySum(new int[] { 2, 1, 5, 2, 8 }, 7));
		System.out.println("minSubarraySum (3) = " + minSizeSubarraySum(new int[] { 3, 4, 1, 1, 6 }, 8));

		System.out.println("\n#4 Longest substr with at most k distinct characters");
		System.out.println("longestSubstr (4) = " + longestSubstr("araaci", 2));
		System.out.println("longestSubstr (2) = " + longestSubstr("araaci", 1));
		System.out.println("longestSubstr (5) = " + longestSubstr("cbbebi", 3));

		System.out.println("\n#5 Fruits into Baskets");
		System.out.println("fruitsInBaskets (3) = " + fruitsInBaskets(new char[] { 'A', 'B', 'C', 'A', 'C' }));
		System.out.println("fruitsInBaskets (5) = " + fruitsInBaskets(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));

		System.out.println("\n#6 Longest Substring with at most 2 distinct character");
		System.out.println("longestSubstrWith2Char (3) = " + longestSubstrWith2Char("eceba"));
		System.out.println("longestSubstrWith2Char (5) = " + longestSubstrWith2Char("ccaabbb"));

		System.out.println("\n#7 Longest Substring without repeating characters");
		System.out.println("nonRepeatSubstr (3) = " + nonRepeatSubstr("aabccbb"));
		System.out.println("nonRepeatSubstr (2) = " + nonRepeatSubstr("abbbb"));

		System.out.println("\n#8 Longest Substring with same letters after 'k' replacements");
		System.out.println("longestRepeatingCharReplacement (5) = " + longestRepeatingCharReplacement("aabccbb", 2));
		System.out.println("longestRepeatingCharReplacement (4) = " + longestRepeatingCharReplacement("abbcb", 1));
		System.out.println("longestRepeatingCharReplacement (3) = " + longestRepeatingCharReplacement("abccde", 1));
		System.out.println("longestRepeatingChar_Array (5) = " + longestRepeatingCharReplacement_arr("aabccbb", 2));
		System.out.println("longestRepeatingChar_Array (4) = " + longestRepeatingCharReplacement_arr("abbcb", 1));

		System.out.println("\n#9 Longest Substring with ones after 'k' replacements");
		System.out.println("subarr(6) = " + lenOfLongestSubarr(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
		System.out.println("subarr(9) = " + lenOfLongestSubarr(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3));
		System.out.println("subarr_2(6) = " + lenOfLongestSubarr_2(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
		System.out.println("subarr_2(8) = " + lenOfLongestSubarr_2(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 }, 3));

		System.out.println("\n#10 Permutation in String & String Anagrams");
		checkInclusion();
		System.out.println("Permutation in String (T)" + stringPermutation("oidbcaf", "abc"));
		System.out.println("Permutation in String (T)" + stringPermutation("bcdxabcdy", "bcdxabcdy"));
		System.out.println("Permutation in String (F)" + stringPermutation("odicf", "dc"));

		System.out.println("\n#11 String Anagrams");
		stringAnagrams();
		System.out.println("Anagram in String [1,2]=" + stringAnagrams("ppqp", "pq"));
		System.out.println("Anagram in String [2,3,4]=" + stringAnagrams("abbcabc", "abc"));

		System.out.println("\n#12: Smallest Window containing Substring");
		System.out.println("minimumWindowSubstr (abdec)=" + minimumWindowSubstr("aabdec", "abc"));
		System.out.println("minimumWindowSubstr (bca)=" + minimumWindowSubstr("abdbca", "abc"));
		System.out.println("minimumWindowSubstr ()=" + minimumWindowSubstr("adcad", "abc"));

		System.out.println("\n#13: Word Concatenation");
		findWordConcatenation();
		System.out.println("wordBreak = " + wordBreak("catdog", new LinkedList<>(Arrays.asList("cat", "dog"))));

	}

	// #1 Max Avg sub-array: RT=O(N) space=O(1)
	// Given nums array, find sub-array of size=k that has max avg val.
	// return the max output value;
	// https://leetcode.com/problems/maximum-average-subarray-i/
	public static double maxAvgSubArray(int[] nums, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < k; i++)
			sum += nums[i];
		max = sum;
		for (int i = k; i < nums.length; i++) {
			sum += nums[i] - nums[i - k];
			max = Math.max(max, sum);
		}
		return (double) max / (double) k;
	}

	// #1.2 Max Avg sub-array: RT=O(N) space=O(1) [same as above]
	// Given nums, find the avg of all contiguous sub-array of size ‘K’ in it
	public static void maxAvgSubArray2(int[] nums, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int start = 0;
		// List<Integer> res = new LinkedList<>();
		double[] res = new double[nums.length - k + 1];
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (i >= k - 1) {
				res[start] = (double) sum / (double) k;
				sum -= nums[start];
				start++;
			}
		}
		System.out.println("res=" + Arrays.toString(res));
	}

	// #2 Largest sub-array length k: RT=O(N) space=O(1)
	// https://leetcode.com/problems/largest-subarray-length-k/
	public static int largestSubarrLenK(int[] nums, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0, start = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			System.out.print(sum + " ");
			if (i >= k - 1) {
				max = Math.max(max, sum);
				sum -= nums[start];
				start++;
			}
		}
		return max;
	}

	// #3 Minimum size sub-array sum: RT=O(N) space=O(1)
	// Find the len of smallest continuous sub-array whose sum >= target
	// https://leetcode.com/problems/minimum-size-subarray-sum/
	public static int minSizeSubarraySum(int[] arr, int target) {
		int start = 0, sum = 0;
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			while (sum >= target) {
				minLen = Math.min(minLen, i - start + 1);
				sum -= arr[start];
				start++;
			}
		}
		return minLen; // return minLen==Integer.MAX_VALUE ? 0 : minLen;
	}

	// #4 Longest substr with ATMOST 'K' dist char: RT=O(N); space=O(K)-HashMap
	// Given str, find the len of longest substring with AT-MOST k distinct char
	// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
	public static int longestSubstr(String s, int k) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), 0);
			}
			map.put(s.charAt(i), map.get(s.charAt(i)) + 1);

			while (map.size() > k) {
				map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
				if (map.get(s.charAt(start)) == 0)
					map.remove(s.charAt(start));
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		System.out.println("longestSubstrK=" + longestSubstrK(s, k));
		return maxLen;
	}

	public static int longestSubstrK(String s, int k) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

			while (map.size() > k) {
				map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
				if (map.get(s.charAt(start)) == 0)
					map.remove(s.charAt(start));
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// #5 Fruit into Basket: RT=O(N); space=O(K)-HashMap
	// Same as #4, here k==2
	// Given a char[] of fruits, and 2 baskets. Put max fruit in each basket,
	// each basket should have only 1 type of fruit
	public static int fruitsInBaskets(char[] fruits) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < fruits.length; i++) {
			if (!map.containsKey(fruits[i])) {
				map.put(fruits[i], 0);
			}
			map.put(fruits[i], map.get(fruits[i]) + 1);

			while (map.size() > 2) {
				map.put(fruits[start], map.get(fruits[start]) - 1);
				if (map.get(fruits[start]) == 0)
					map.remove(fruits[start]);
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		System.out.println("fruitsInBaskets=" + fruitBasket(fruits));
		return maxLen;
	}

	public static int fruitBasket(char[] fruits) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < fruits.length; i++) {
			map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);

			while (map.size() > 2) {
				map.put(fruits[start], map.get(fruits[start]) - 1);
				if (map.get(fruits[start]) == 0)
					map.remove(fruits[start]);
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// #6 Longest Substr with at most 2 distinct chars: RT=O(N);space=O(26)=O(1)
	// Given a str, find the len of longest substr with at most 2 distinct chars
	// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/X
	public static int longestSubstrWith2Char(String s) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i)))
				map.put(s.charAt(i), 0);
			map.put(s.charAt(i), map.get(s.charAt(i)) + 1);

			while (map.size() > 2) {
				map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
				if (map.get(s.charAt(start)) == 0)
					map.remove(s.charAt(start));
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}

		return maxLen;
	}

	// #7 Longest substr without repeating characters: RT=O(N); space=O(26)=O(1)
	// Given str, find the len of the longest substr, with no repeating chars
	// Logic: [i,j] --> [i, j', j] => [j'+1, j]
	// https://leetcode.com/problems/longest-substring-without-repeating-characters/
	public static int nonRepeatSubstr(String s) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				start = Math.max(start, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// #8 Longest substr with same letters after replacement:RT=O(N); space=O(1)
	// replace no more than 'k' letters, find len of longest substr having same
	// letters after replacement
	// Same as sliding window, but can replace repeating characters
	// https://leetcode.com/problems/longest-repeating-character-replacement/
	public static int longestRepeatingCharReplacement(String s, int k) {
		int start = 0;
		int maxLen = Integer.MIN_VALUE;
		int maxRepeatCount = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

			maxRepeatCount = Math.max(maxRepeatCount, map.get(s.charAt(i)));

			if (i - start + 1 - maxRepeatCount > k) {
				map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// using constant array: RT=O(N); space=O(1)
	public static int longestRepeatingCharReplacement_arr(String s, int k) {
		int[] count = new int[26];
		int start = 0, maxLen = 0, maxCount = 0;
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;

			maxCount = Math.max(maxCount, count[s.charAt(i) - 'a']);

			if (i - start + 1 - maxCount > k) {
				count[s.charAt(start) - 'a']--;
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// #9 max consecutive 1s:
	// Longest Subarr with 1s after k replacement
	// https://leetcode.com/problems/max-consecutive-ones-iii/
	public static int lenOfLongestSubarr(int[] arr, int k) {
		int start = 0, maxLen = 0, oneCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				oneCount++;
			if (i - start + 1 - oneCount > k) {
				if (arr[start] == 1)
					oneCount--;
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	public static int lenOfLongestSubarr_2(int[] arr, int k) {
		int start = 0, zeroCount = 0, maxLen = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0)
				zeroCount++;
			if (zeroCount > k) {
				if (arr[start] == 0)
					zeroCount--;
				start++;
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}

	// #10 Permutation in String & String Anagrams
	// https://leetcode.com/problems/find-all-anagrams-in-a-string/
	// return true if s2 contains a permutation of s1
	// ADD all character & then REMOVE; check s1 in s2 [USING ARRAY]
	public static void checkInclusion() {
		String s1 = "abc", s2 = "aaacb"; // CHECK: s1 in s2 [USING ARRAY]
		int[] count = new int[26];
		int l1 = s1.length(), l2 = s2.length();
		for (int i = 0; i < l1; i++) {
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}
		if (allZeros(count)) {
			System.out.println("checkInclusion:TRUE");
			return;
		}
		for (int i = l1; i < l2; i++) {
			count[s2.charAt(i) - 'a']--;
			count[s2.charAt(i - l1) - 'a']++;
		}
		if (allZeros(count)) {
			System.out.println("checkInclusion_2:TRUE");
		}
	}

	// find if s contains a permutation of pat: RT=O(S+T); Space=O(S+T)
	// ADD everything into map & then REMOVE; check s1 in s2 [USING MAP]
	public static boolean stringPermutation(String s, String pat) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (Character c : pat.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int start = 0, match = map.size();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
				if (map.get(s.charAt(i)) == 0) {
					match--;
				}
			}
			if (match == 0) {
				return true;
			}
			if (i - start + 1 >= pat.length()) {
				if (map.containsKey(s.charAt(start))) {
					if (map.get(s.charAt(start)) == 0) {
						match++;
					}
					map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
				}
				start++;
			}
		}
		return false;
	}

	// #11 String Anagrams (same as permutation) [USING ARRAY]
	// https://leetcode.com/problems/find-all-anagrams-in-a-string/
	// return an array of all the start indices of s1 anagrams in s2
	public static void stringAnagrams() {
		List<Integer> res = new LinkedList<>();
		// s1.length() < s2.length()
		String s1 = "pq", s2 = "ppqp";
		int[] count = new int[26];
		int l1 = s1.length(), l2 = s2.length();
		for (int i = 0; i < l1; i++) {
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}
		if (allZeros(count)) {
			res.add(0);
		}
		for (int i = l1; i < l2; i++) {
			count[s2.charAt(i) - 'a']--;
			count[s2.charAt(i - l1) - 'a']++;
			if (allZeros(count)) {
				res.add(i - l1 + 1);
			}
		}
		System.out.println("StringAnagrams => " + res);
	}

	public static boolean allZeros(int[] count) {
		for (int n : count)
			if (n != 0)
				return false;
		return true;
	}

	// check if s has anagrams of pat [USING MAP]: RT=O(S+T); Space=O(S+T)
	public static List<Integer> stringAnagrams(String s, String pat) {
		List<Integer> res = new LinkedList<>();
		HashMap<Character, Integer> map = new HashMap<>();
		for (Character c : pat.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int start = 0, match = map.size();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
				if (map.get(s.charAt(i)) == 0) {
					match--;
				}
			}
			if (match == 0) {
				res.add(start);
			}
			if (i - start + 1 >= pat.length()) {
				if (map.containsKey(s.charAt(start))) {
					if (map.get(s.charAt(start)) == 0) {
						match++;
					}
					map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
				}
				start++;
			}
		}
		return res;
	}

	// #12: Smallest Window containing Substring: RT=O(S+T); Space=O(S+T)
	// find the smallest substr, containing all char
	// https://leetcode.com/problems/minimum-window-substring/
	public static String minimumWindowSubstr(String s, String pat) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (char c : pat.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		int start = 0, count = map.size();
		int minStart = 0, minLen = s.length() + 1;
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
				if (map.get(s.charAt(i)) == 0) {
					count--;
				}
				while (count == 0) {
					if ((i - start + 1) < minLen) {
						minStart = start;
						minLen = i - start + 1;
					}
					if (map.containsKey(s.charAt(start))) {
						if (map.get(s.charAt(start)) == 0)
							count++;
						map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
					}
					start++;
				}
			}
		}
		if (minLen > s.length())
			return "";

		return s.substring(minStart, minStart + minLen);
	}

	// #13: Words Concatenation
	// https://leetcode.com/problems/concatenated-words/
	public static void findWordConcatenation() {
		String[] words = new String[] { "cat", "fox", "catfoxfox", "catfox" };
		String s = "catfoxcat";

		Arrays.sort(words, (a, b) -> a.length() - b.length());
		List<String> res = new LinkedList<>();

		HashSet<String> preword = new HashSet<>();
		for (int i = 0; i < words.length; i++) {
			if (wordBreak(words[i], preword)) {
				res.add(words[i]);
			}
			preword.add(words[i]);
		}
		System.out.println("res=" + res);

	}

	public static boolean wordBreak(String s, HashSet<String> preword) {
		if (preword.isEmpty())
			return false;

		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && preword.contains(s.subSequence(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	// WordBreak-I
	public static boolean wordBreak(String s, List<String> dict) {
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && dict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	// SIMILAR LEETCODE
	// https://leetcode.com/problems/maximum-subarray/
	// https://leetcode.com/problems/max-consecutive-ones/
	// https://leetcode.com/problems/maximum-product-subarray/submissions/
	// https://leetcode.com/problems/kth-largest-element-in-an-array/

}
