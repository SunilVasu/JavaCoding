package com.ctci.solutions;

import java.util.LinkedList;
import java.util.List;

public class Misc06_Algo {
	public static void main(String[] args) {
		System.out.println("#Misc Algorithms");
		// #1 Majority element
		majorityElem();
		majorityElem_2();

		// #2 Knapsack 01
		knapsack01();

		// #3 KMP
		System.out.println("#3 KMP: Pattern Searching");
		String str = "ababababcab";
		String pattern = "abcab";
		kmp(str, pattern);
		kmp("ababababcabac", "ab");
		kmp("ababababcabac", "abac");
		kmp("abbbabac", "abac");
		kmp("abaabac", "abac");

		// #3.2 Manacher's Algo: Longest Palindronmic String
		System.out.println("#3.1 Longest Palindromic String: O(N^2)");
		System.out.println("longestPalindromicSubstring=" + longestPalindromicSubstring("acbababc"));
		System.out.println("longestPalindromicSubstring2=" + longestPalindromicSubstring2("acbababc"));
		System.out.println("#3.2 Manacher's Algo: O(N)");
		System.out.println("manachersAlgo(5)=" + manachersAlgo("acacacb"));
		System.out.println("manachersAlgo(5)=" + manachersAlgo("abba"));

		// #4 Dijkstra
		shortestPath();
		shortestPath_priorityQueue();
	}

	// #1 Majority elem: Linear Time RT O(n) (Boyer-Moore Majority Vote Algo)
	// The majority element is the element that appears more than n/2 times.
	public static void majorityElem() {
		System.out.println("\n#1 Majority Element Vote");
		int[] nums = new int[] { 1, 2, 3, 1, 2, 2, 2 };
		int major = -1, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (count == 0) {
				major = nums[i];
				count++;
			} else if (major == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
		System.out.println("Majority Elem: " + major);
	}

	public static void majorityElem_2() {
		System.out.println("\n#1.1 Majority Element Vote - 2 elems");
		int[] nums = new int[] { 2, 3, 2, 2, 1, 6, 6, 6 };
		int major1 = 0, count1 = 0, major2 = 0, count2 = 0;
		for (int n : nums) {
			if (count1 == 0) {
				major1 = n;
				count1++;
			} else if (count2 == 0) {
				major2 = n;
				count2++;
			} else if (major1 == n) {
				count1++;
			} else if (major2 == n) {
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		System.out.println("nums.length: " + nums.length + " & n/3: " + nums.length / 3);
		// Validate
		count1 = 0;
		count2 = 0;
		for (int n : nums) {
			if (major1 == n) {
				count1++;
			} else if (major2 == n) {
				count2++;
			}
		}
		System.out.println("Major_1:" + major1 + " count:" + count1);
		System.out.println("Major_2:" + major2 + " count:" + count2);
		List<Integer> res = new LinkedList<>();
		if (count1 > nums.length / 3)
			res.add(major1);
		if (count2 > nums.length / 3)
			res.add(major2);
		System.out.println("Majority Voting: " + res);
	}

	// #2 Knapsack 01: RT=O(N*W): N=num of nodes; W=capacity
	// maximize value keeping weight <= W
	public static void knapsack01() {
		System.out.println("\n#2 Knapsack 01");

		int[] val = new int[] { 1, 4, 5, 7 };
		int[] wt = new int[] { 1, 3, 4, 5 };
		int W = 7;
		int[][] dp = knapsack01(val, wt, W);

		System.out.println("Knapsack 01 Value = " + dp[val.length][W]);
		printMatrix(dp, "Knapsack 01 Matrix::");

		val = new int[] { 1, 5, 7, 9 };
		wt = new int[] { 2, 4, 1, 3 };
		W = 5;
		dp = knapsack01(val, wt, W);
		System.out.println("Knapsack 01 Max Value = " + dp[val.length][W]);
		printMatrix(dp, "Knapsack 01 Matrix::");
	}

	public static int[][] knapsack01(int[] val, int[] wt, int W) {
		int[][] dp = new int[val.length + 1][W + 1];

		for (int i = 0; i <= val.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (j < wt[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					// System.out.print("i=" + i + " j=" + j + " wt[i-1]=" +
					// wt[i - 1] + " ");
					// System.out.println("max=(" + (val[i - 1] + dp[i - 1][j -
					// wt[i - 1]]) + ", " + dp[i - 1][j] + ")");
					dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
				}
			}
		}
		return dp;
	}

	// #3 KMP: Pattern searching
	// RT: O(m+n) Space: O(n)
	// ***lps - longest proper prefix which is also suffix
	public static void kmp(String str, String pattern) {
		System.out.println("str=" + str + "   pattern=" + pattern);
		char[] txt = str.toCharArray();
		char[] pat = pattern.toCharArray();
		int[] lps = createTempArr(pat);
		printArr(lps, "lps: ");

		int i = 0, j = 0;
		while (i < txt.length && j < pat.length) {
			// System.out.println(txt[i] + " " + pat[j]);
			if (txt[i] == pat[j]) {
				// System.out.println("match");
				i++;
				j++;
			} else {
				if (j != 0) {
					j = lps[j - 1];
					// System.out.println("j=" + j);
				} else {
					i++;
				}
			}
			if (j == pat.length) {
				System.out.println("Found match at = " + (i - j));
				j = lps[j - 1];
			}
		}

		if (j == pat.length)
			System.out.println("Match Found\n");
		else
			System.out.println("No Match Found\n");

	}

	public static int[] createTempArr(char[] pat) {
		int[] lps = new int[pat.length];
		int index = 0;
		int i = 1;
		while (i < pat.length) {
			if (pat[index] == pat[i]) {
				lps[i] = index + 1;
				index++;
				i++;
			} else {
				if (index != 0)
					index = lps[index - 1];
				else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	// #3.1 Longest Palindromic Substring: RT=O(N^2); Space=O(1)
	// https://leetcode.com/problems/longest-palindromic-substring/
	public static String longestPalindromicSubstring(String str) {

		int low, high;
		int maxLen = 1;
		int start = 0;
		for (int i = 1; i < str.length(); i++) {
			// even palindrome
			low = i - 1;
			high = i;
			while (low >= 0 && high < str.length() && str.charAt(low) == str.charAt(high)) {
				low--;
				high++;
			}
			low++;
			high--;
			if (str.charAt(low) == str.charAt(high) && high - low + 1 > maxLen) {
				start = low;
				maxLen = high - low + 1;
			}

			// odd palindrome
			low = i - 1;
			high = i + 1;
			while (low >= 0 && high < str.length() && str.charAt(low) == str.charAt(high)) {
				low--;
				high++;
			}
			low++;
			high--;
			if (str.charAt(low) == str.charAt(high) && high - low + 1 > maxLen) {
				start = low;
				maxLen = high - low + 1;
			}
		}
		return str.substring(start, start + maxLen);
	}

	static int start = 0, end = 0;

	public static String longestPalindromicSubstring2(String str) {

		for (int i = 0; i < str.length(); i++) {
			extend(str, i, i);
			extend(str, i, i + 1);
		}
		return str.substring(start, end + 1);
	}

	public static void extend(String str, int low, int high) {
		while (low >= 0 && high < str.length() && str.charAt(low) == str.charAt(high)) {
			low--;
			high++;
		}
		low++;
		high--;
		if (end - start + 1 < high - low + 1) {
			start = low;
			end = high;
		}
	}

	// #3.2 Manacher's Algo: Longest Palindromic Substring
	// RT=O(N), space=O(N)
	public static int manachersAlgo(String str) {

		String s = getModifiedString(str);
		System.out.println("getModifiedString = " + s);
		int len = (2 * str.length()) + 1;

		int[] P = new int[len];
		int c = 0;// center of longest Palindromic substring
		int r = 0;// right boundary of longest palindromic substring
		int maxLen = 0;

		for (int i = 0; i < len; i++) {
			// mirror index
			int mirror = (2 * c) - i;
			// see if mirror of i expands beyond left boundary
			if (r > i) {
				P[i] = Math.min(r - i, P[mirror]);
			}
			// expand at i
			int a = i + (P[i] + 1);
			int b = i - (P[i] + 1);
			while (a < len && b >= 0 && s.charAt(a) == s.charAt(b)) {
				P[i]++;
				a++;
				b--;
			}
			// palindrome expands beyond right boundary
			if (i + P[i] > r) {
				c = i;
				r = i + P[i];

				if (P[i] > maxLen) {
					maxLen = P[i];
				}
			}
			// printArr(P, "P");
		}
		return maxLen;
	}

	public static String getModifiedString(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			sb.append("#");
			sb.append(str.charAt(i));
		}
		sb.append("#");
		return sb.toString();
	}

	// #4 Dijkstra Shortest path Algo (Adjacency Matrix):RT=O(V^2)
	// With min-priority queue, the time complexity = O (V + E*logV)
	// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
	// https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
	public static void shortestPath() {
		System.out.println("\n#4 Dijkstra Shortest path Algo");
		int[][] graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		dijkstra(graph, 0);
	}

	public static void dijkstra(int[][] graph, int src) {
		int nodes = graph.length;
		int[] dist = new int[nodes];
		boolean[] sptSet = new boolean[nodes];
		// init
		for (int i = 0; i < nodes; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		// distance of source vertex from itself is 0
		dist[src] = 0;

		for (int count = 0; count < nodes - 1; count++) {
			// pick min distance vertex from set of vertices not yet processed
			int u = minDistance(dist, sptSet);
			System.out.println("u=" + u);
			// mark picked vertex as processed
			sptSet[u] = true;
			// update dist val of adjacent vertices of picked vertex
			for (int v = 0; v < nodes; v++) {
				if (sptSet[v] == false && dist[u] != Integer.MAX_VALUE && graph[u][v] != 0
						&& dist[u] + graph[u][v] < dist[v]) {
					dist[v] = dist[u] + graph[u][v];
					System.out.println("dist[v]=" + dist[v]);
				}
			}
		}
		// Display result
		System.out.println("Shortest Path::");
		for (int i = 0; i < nodes; i++) {
			System.out.println(i + "  " + dist[i]);
		}

	}

	public static int minDistance(int[] dist, boolean[] sptSet) {
		int minVal = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int v = 0; v < dist.length; v++) {
			if (sptSet[v] == false && dist[v] <= minVal) {
				minVal = dist[v];
				minIndex = v;
			}
		}
		return minIndex;
	}

	// #4.1 Dijkstra Shortest path Algo (Priority Queue):
	// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
	public static void shortestPath_priorityQueue() {

	}

	// META
	public static void printMatrix(int[][] arr, String msg) {
		System.out.println(msg);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "   ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void printArr(int[] arr, String msg) {
		System.out.print(msg + " : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print("-");
			}
		}
		System.out.println("");
	}
}
