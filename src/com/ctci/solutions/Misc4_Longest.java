package com.ctci.solutions;

public class Misc4_Longest {

	public static void main(String[] args) {
		// #1 Longest Common Prefix
		System.out.println("#1 Longest Common Prefix: " + longestCommonPrefix());
		// #2 Longest Common Subsequence
		System.out.println("\n#2 Longest Common Subsequence: " + longestCommonSubseq("acbdef", "adbedf"));
		// #3 Longest Common Substring
		System.out.println("\n#3 Longest Common String: " + longestCommonSubstring("acbdef", "addeff"));
		// #4 Minimum Edit distance
		System.out.println("\n#4 Minimum Edit distance: " + minimumEditDistance("abcdef", "azced"));
		// #4.1 Minimum Delete distance: in one step only one operation
		System.out.println("#4.1 Minimum Delete distance: " + minimumDeleteDistance("pale", "pal"));
		// #4.2 Minimum Delete distance = strs.length - LongestCommonSubseq
		System.out.println("#4.2 Minimum Delete distance: " + minimumDeleteDistance2("pale", "pal"));

		// #5 Longest Increasing Subsequence
		System.out
				.println("\n#5 Longest Increasing Subsequence: " + longestIncreasingSubseq(new int[] { 9, -1, 2, 9 }));
		System.out.println("#5 Longest Increasing Subsequence: " + longestIncreasingSubseq(new int[] { 9, 9, 9 }));
		System.out
				.println("\n#5 Longest Increasing Subsequence: " + longestIncreasingSubseq2(new int[] { 9, -1, 2, 9 }));
		System.out.println("#5 Longest Increasing Subsequence: " + longestIncreasingSubseq2(new int[] { 9, 9, 9 }));
	}

	// #1 Longest Common Prefix: Runtime=O(mn) Space=O(1)
	public static String longestCommonPrefix() {
		String[] strs = new String[] { "hello", "helo", "he" };
		int min = Integer.MAX_VALUE;
		for (String s : strs) {
			min = Math.min(min, s.length());
		}

		for (int i = 0; i < min; i++) {
			for (int j = 0; j < strs.length - 1; j++) {
				String s1 = strs[j];
				String s2 = strs[j + 1];
				if (s1.charAt(i) != s2.charAt(i))
					return s1.substring(0, i);
			}
		}
		return strs[0].substring(0, min);
	}

	// #2 Longest Common Subsequence: Runtime=O(mn) Space=O(mn)
	public static int longestCommonSubseq(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[s1.length()][s2.length()];
	}

	// #3 Longest Common Substring: Runtime=O(mn) Space=O(mn)
	public static int longestCommonSubstring(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return max;
	}

	// #4 Minimum Edit Distance: Runtime=O(mn) Space=O(mn)
	public static int minimumEditDistance(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					dp[i][j] = j;
				else if (j == 0)
					dp[i][j] = i;
				else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	// #4.1 Minimum Delete Distance
	public static int minimumDeleteDistance(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	// #4.2 Minimum Delete distance = strs.length - LongestCommonSubseq
	public static int minimumDeleteDistance2(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return s1.length() - dp[s1.length()][s2.length()] + s2.length() - dp[s1.length()][s2.length()];
	}

	// #5 Longest Increasing Subsequence: dp Runtime=O(n^2) space=O(n)
	public static int longestIncreasingSubseq(int[] nums) {
		int[] dp = new int[nums.length + 1];
		for (int i = 0; i <= nums.length; i++) {
			dp[i] = 1;
		}

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for (int n : dp)
			max = Math.max(max, n);

		return max;
	}

	// LIS tails: binarySearch Runtime=O(nlogn) space=O(n)
	public static int longestIncreasingSubseq2(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;
		for (int n : nums) {
			int start = 0, end = size;
			while (start != end) {
				int mid = (start + end) / 2;
				if (tails[mid] < n)
					start = mid + 1;
				else
					end = mid;
			}
			tails[start] = n;
			if (start == size)
				size++;
		}
		return size;
	}

}
