package com.ctci.solutions;

public class Misc3_RecursionDP {

	public static void main(String[] args) {
		System.out.println("#MISC Recursion & DP");
		// #1 Recusion and DP
		recursion_DP();
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

}
