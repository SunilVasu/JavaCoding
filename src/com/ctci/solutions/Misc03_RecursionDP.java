package com.ctci.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Misc03_RecursionDP {

	public static void main(String[] args) {
		System.out.println("#MISC Recursion & DP");
		// #1 Recusion and DP
		recursion_DP();

		// #2 Island
		System.out.println("\n#2 Island");
		int[][] grid = new int[][] { { 1, 1, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 1 } };
		System.out.println("numOfIsland(2) = " + numOfIsland(grid));

		System.out.println("\n#2.1 Island - distinct count");
		grid = new int[][] { { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 } };
		System.out.println("Distinct numOfIsland(2) = " + numDistinctIsland(grid));

		System.out.println("\n#2.2 Island - max area island");
		grid = new int[][] { { 1, 1, 0, 1 }, { 1, 1, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 } };
		System.out.println("maxAreaIsland (5)= " + maxAreaIsland(grid));

		System.out.println("\n#2.3 Island - closed island count");
		grid = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 0, 1, 0, 0, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 } };
		System.out.println("Closed Island numOfIsland(2) = " + countClosedIslands(grid));

		System.out.println("\n#2.4 Check if word exist in grid");
		char[][] grid1 = { { 'c', 'c', 'x', 't', 'i', 'b' }, { 'c', 'c', 'a', 't', 'n', 'i' },
				{ 'a', 'c', 'n', 'n', 't', 't' }, { 't', 'c', 's', 'i', 'p', 't' }, { 'a', 'o', 'o', 'o', 'a', 'a' },
				{ 'o', 'a', 'a', 'a', 'o', 'o' }, { 'k', 'a', 'i', 'c', 'k', 'i' } };
		String word1 = "catnip";
		String word2 = "cccc";
		String word3 = "s";
		String word4 = "bit";
		String word5 = "aoi";
		String word6 = "ki";
		String word7 = "aaa";
		String word8 = "ooo";
		System.out.println("checkMatch_1 (T)= " + checkMatch(grid1, word1));
		System.out.println("checkMatch_2 (rightDown) (T)= " + checkMatch2(grid1, word1));
		System.out.println("checkMatch_3 (rightDown-track) (T)= " + checkMatch3(grid1, word1));
	}

	// 1 recursion & DP
	public static void recursion_DP() {
		System.out.println("\n#1 Recursion & DP");
		// Fibonacci : 0, 1, 1, 2, 3, 5, 8, 13
		// recursion - Runtime: O(2^n) : number of nodes in tree
		System.out.println("Recursion : " + fibin(7));

		int n = 7;
		int memo[] = new int[n + 1];
		System.out.println("Top-Down DP (Memoization) : " + fibin(n, memo));

		System.out.println("Bottom-Up DP : " + fibinDP(n));
		System.out.println("Bottom-Up DP (without array) : " + fibinDP2(n));
	}

	// recursion - Runtime: O(2^n) : number of nodes in tree
	// For example, a recursive function of input N that is called N times will
	// have a runtime of O(N). On the other hand, a recursive function of input
	// N that calls itself twice per function may have a runtime of O(2^N).
	public static int fibin(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibin(n - 1) + fibin(n - 2);
	}

	// Top-Down DP (Memoization)
	public static int fibin(int n, int[] memo) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (memo[n] == 0) {
			memo[n] = fibin(n - 1, memo) + fibin(n - 2, memo);
		}
		return memo[n];
	}

	// Bottom-Up DP: RT O(n)
	public static int fibinDP(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public static int fibinDP2(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		int a = 0;
		int b = 1;
		for (int i = 2; i <= n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return b;// has c value
	}

	// #2 Number of island
	// https://leetcode.com/problems/number-of-islands/
	// https://leetcode.com/problems/max-area-of-island/
	// https://leetcode.com/problems/island-perimeter/
	// https://leetcode.com/problems/number-of-closed-islands/
	public static int numOfIsland(int[][] grid) {
		int count = 0;
		if (grid == null || grid.length == 0)
			return count;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					count++;
					dfs(grid, i, j);
				}
			}
		}
		return count;
	}

	public static void dfs(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
			return;
		}
		grid[x][y] = 0;
		dfs(grid, x + 1, y);
		dfs(grid, x - 1, y);
		dfs(grid, x, y + 1);
		dfs(grid, x, y - 1);
	}

	// #2.1 count distinct islands
	public static int numDistinctIsland(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					StringBuilder sb = new StringBuilder();
					dfs(grid, i, j, sb, "o"); // origin
					set.add(sb.toString());
				}
			}
		}
		System.out.println("islands =" + set);
		return set.size();
	}

	public static void dfs(int[][] grid, int x, int y, StringBuilder sb, String dir) {

		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
			return;
		}
		grid[x][y] = 0;

		sb.append(dir);
		dfs(grid, x - 1, y, sb, "u");
		dfs(grid, x + 1, y, sb, "d");
		dfs(grid, x, y + 1, sb, "r");
		dfs(grid, x, y - 1, sb, "l");
		sb.append("b"); // back or exit
	}

	// #2.2 Max area
	public static int maxAreaIsland(int[][] grid) {
		int max = 0;
		if (grid == null || grid.length == 0)
			return max;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					int area = dfs(grid, i, j, 0);
					max = Math.max(max, area);
				}
			}
		}
		return max;
	}

	public static int dfs(int[][] grid, int x, int y, int area) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
			return area;

		grid[x][y] = 0;
		area++;
		area = dfs(grid, x + 1, y, area);
		area = dfs(grid, x - 1, y, area);
		area = dfs(grid, x, y + 1, area);
		area = dfs(grid, x, y - 1, area);
		return area;
	}

	// #2.3 count closed islands(1), i.e surrounded by water(0)
	public static int countClosedIslands(int[][] grid) {
		int res = 0;
		if (grid == null || grid.length == 0)
			return res;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					if (dfs_closed(grid, i, j)) {
						res++;
					}
				}
			}
		}
		return res;
	}

	public static boolean dfs_closed(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
			return false;
		if (grid[x][y] == 0)
			return true;
		grid[x][y] = 0;

		boolean res = dfs_closed(grid, x + 1, y);
		res &= dfs_closed(grid, x - 1, y);
		res &= dfs_closed(grid, x, y + 1);
		res &= dfs_closed(grid, x, y - 1);
		return res;
	}

	// Word Search
	// https://leetcode.com/problems/word-search/
	public static boolean checkMatch(char[][] grid, String str) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == str.charAt(0)) {
					if (dfs_wordSearch(grid, i, j, str, 0)) {
						return true;
					}

				}
			}
		}
		return false;
	}

	// WordSearch - only right & down movement
	public static boolean checkMatch2(char[][] grid, String str) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == str.charAt(0)) {
					if (dfs_wordSearch2(grid, i, j, str, 0)) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public static boolean checkMatch3(char[][] grid, String str) {
		List<List<Integer>> list = new LinkedList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == str.charAt(0)) {
					List<List<Integer>> temp = new LinkedList<>();
					if (dfs_wordSearch3(grid, i, j, str, 0, list, temp)) {
						System.out.println("#List=" + list + "\n#Temp=" + temp);
						return true;
					}

				}
			}
		}
		return false;
	}

	public static boolean dfs_wordSearch(char[][] grid, int x, int y, String str, int index) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
			return false;
		if (index == str.length())
			return true;
		if (grid[x][y] != str.charAt(index))
			return false;

		// System.out.println(grid[x][y] + " " + index);
		grid[x][y] = '#';

		boolean res = dfs_wordSearch(grid, x + 1, y, str, index + 1);
		res |= dfs_wordSearch(grid, x, y - 1, str, index + 1);
		res |= dfs_wordSearch(grid, x - 1, y, str, index + 1);
		res |= dfs_wordSearch(grid, x, y + 1, str, index + 1);

		grid[x][y] = str.charAt(index);

		return res;
	}

	public static boolean dfs_wordSearch2(char[][] grid, int x, int y, String str, int index) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
			return false;
		if (index == str.length())
			return true;
		if (grid[x][y] != str.charAt(index))
			return false;

		// System.out.println(grid[x][y] + " " + index + " (" + x + "," + y +
		// ")");
		boolean res = dfs_wordSearch2(grid, x + 1, y, str, index + 1);
		res |= dfs_wordSearch2(grid, x, y + 1, str, index + 1);

		return res;
	}

	public static boolean dfs_wordSearch3(char[][] grid, int x, int y, String str, int index, List<List<Integer>> list,
			List<List<Integer>> temp) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
			return false;
		if (index == str.length() && temp.size() == index) {
			if (list.size() == 0) {
				list.addAll(temp);
				// System.out.println("list = " + list);
			}
			temp = new LinkedList<>();
			return true;
		}
		if (list.size() == str.length())
			return true;

		if (grid[x][y] != str.charAt(index)) {
			return false;
		}

		temp.add(Arrays.asList(x, y));
		// System.out.println(grid[x][y] + " " + index + " (" + x + "," + y +
		// ")");
		System.out.println("->" + temp + "##" + list);
		boolean res = dfs_wordSearch3(grid, x + 1, y, str, index + 1, list, temp);
		res |= dfs_wordSearch3(grid, x, y + 1, str, index + 1, list, temp);

		return res;
	}
	/*
	 * find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3,
	 * 3), (3, 4) ] find_word_location(grid1, word2) => [(0, 1), (1, 1), (2, 1),
	 * (3, 1)] OR [(0, 0), (1, 0), (1, 1), (2, 1)] OR [(0, 0), (0, 1), (1, 1),
	 * (2, 1)] OR [(1, 0), (1, 1), (2, 1), (3, 1)] find_word_location(grid1,
	 * word3) => [(3, 2)] find_word_location(grid1, word4) => [(0, 5), (1, 5),
	 * (2, 5)] find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
	 * find_word_location(grid1, word6) => [(6, 4), (6, 5)]
	 * find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
	 * find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
	 * find_word_location(grid2, word9) => [(0, 0)]
	 * 
	 */
}
