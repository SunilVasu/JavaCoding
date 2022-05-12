package com.ctci.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Misc04_Longest {

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
		System.out.println("\n#5 LongestIncreasingSubsequence: " + longestIncreasingSubseq(new int[] { 9, -1, 2, 9 }));
		System.out.println("#5 LongestIncreasingSubsequence: " + longestIncreasingSubseq(new int[] { 9, 9, 9 }));
		System.out.println("\n#5 LongestIncreasingSubsequence: " + longestIncreasingSubseq2(new int[] { 9, -1, 2, 9 }));
		System.out.println("#5 LongestIncreasingSubsequence: " + longestIncreasingSubseq2(new int[] { 9, 9, 9 }));

		// #6 Longest valid parenthese
		System.out.println("\n#6 Longest valid parenthese: " + longestValidParen("((())"));
		// #7 Coin Change
		System.out.println("\n#7 Coin Change");
		coinChange_1();
		System.out.println("min coins: " + coin_minCoins(new int[] { 1, 5, 6, 8 }, 11));
		System.out.println("max combination: " + coin_maxComb(new int[] { 1, 5, 6, 8 }, 5));
		// #8 Subset sum
		System.out.println("\n#6 Subset Sum: " + subsetSum(new int[] { 1, 5, 6, 8 }, 11));
		// #9 Matrix Chain Multiplication
		System.out.println("\n#9 Matrix Chain Multiplication");
		matChainMul();

		// #10 Longest Consecutive Sequence
		System.out.println("\n#10 Longest Consecutive Sequence");
		int[] nums = new int[] { 100, 4, 200, 1, 3, 2 };
		System.out.println("longestConsecutive (4) = " + longestConsecutive(nums));
		System.out.println("longestConsecutive_optimized (4) = " + longestConsecutive_optimized(nums));

		// #11 Longest Palindromic Substringe
		System.out.println("\n#11 Longest Palindromic Substring");
		System.out.println("longestPalindromicSubstring = " + longestPalindromicSubstring("babad"));
		System.out.println("longestPalindromicSubstring_2 = " + longestPalindromicSubstring_2("babad"));
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

	// #5.1 LIS tails: binarySearch Runtime=O(nlogn) space=O(n)
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

	// #6 Longest valid parenthese
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

	// #7 min number of coins needed to get the amount
	public static void coinChange_1() {
		int[] coins = new int[] { 1, 5, 6, 8 };
		int amt = 1;
		int[][] dp = new int[coins.length][amt + 1];
		for (int i = 0; i < coins.length; i++) {
			for (int j = 0; j <= amt; j++) {
				if (j == 0 || i == 0)
					dp[i][j] = j;
				else if (j >= coins[i])
					dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i]]);
				else {
					dp[i][j] = dp[i - 1][j];

				}
			}
		}
		System.out.println("min coins (using dp): " + dp[coins.length - 1][amt]);
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

	// # 8 Subset sum
	public static boolean subsetSum(int[] nums, int sum) {
		boolean[][] dp = new boolean[nums.length + 1][sum + 1];
		for (int i = 0; i <= nums.length; i++)
			dp[i][0] = true;
		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j >= nums[i - 1])
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[nums.length][sum];
	}

	// # 9 Matrix Chain Multiplication
	public static void matChainMul() {
		int[] arr = new int[] { 2, 3, 6, 4, 5 };

		int[][] dp = new int[arr.length][arr.length];
		for (int l = 2; l < arr.length; l++) {
			for (int i = 0; i < arr.length - l; i++) {
				int j = i + l;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
				}
			}
		}
		System.out.println("min cost = " + dp[0][arr.length - 1]);
	}

	// #10 Longest Consecutive Seq
	// https://leetcode.com/problems/longest-consecutive-sequence/
	// RT = O(nlogn) Space=O(1)
	public static int longestConsecutive(int[] nums) {
		Arrays.sort(nums);

		int longest = 1;
		int currSeq = 1;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				if (nums[i] == nums[i - 1] + 1) {
					currSeq += 1;
				} else {
					longest = Math.max(longest, currSeq);
					currSeq = 1;
				}
			}
		}
		return Math.max(longest, currSeq);
	}

	// RT=O(N+N)=O(N) Space=O(N) set
	public static int longestConsecutive_optimized(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int n : nums)
			set.add(n);

		int longest = 0;

		for (int num : set) {
			if (!set.contains(num - 1)) {
				int currNum = num;
				int currSeq = 1;

				while (set.contains(currNum + 1)) {
					currNum += 1;
					currSeq += 1;
				}

				longest = Math.max(longest, currSeq);
			}
		}
		return longest;
	}

	// #11 Longest Palindromic Substring
	// https://leetcode.com/problems/longest-palindromic-substring/
	static int start = 0, end = 0;

	public static String longestPalindromicSubstring(String s) {

		for (int i = 0; i < s.length(); i++) {
			extend(s, i, i);
			extend(s, i, i + 1);
		}
		return s.substring(start, end + 1);
	}

	public static void extend(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			if (right - left > end - start) {
				start = left;
				end = right;
			}
			left--;
			right++;
		}

	}

	// LongestPalindromicSubstring - iterativeApproach
	public static String longestPalindromicSubstring_2(String s) {
		int start = 0, end = 0;
		int left = 0, right = 0;
		for (int i = 0; i < s.length(); i++) {
			left = i;
			right = i;
			while (left >= 0 && right < s.length() && s.charAt(right) == s.charAt(left)) {
				if (right - left > end - start) {
					start = left;
					end = right;
				}
				left--;
				right++;
			}
			left = i;
			right = i + 1;
			while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
				if (right - left > end - start) {
					start = left;
					end = right;
				}
				left--;
				right++;
			}
		}
		return s.substring(start, end + 1);
	}
}
