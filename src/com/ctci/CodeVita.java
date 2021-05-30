package com.ctci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CodeVita {

	public static void main(String[] args) {
		System.out.println("HelloWorld!");
		solution_A(12,
				new String[][] { { "*", ".", "*", "#", ".", "*", "*", "*", "#", ".", "*", "." },
						{ "*", ".", "*", "#", ".", ".", "*", ".", "#", "*", "*", "*" },
						{ "*", "*", "*", "#", ".", "*", "*", "*", "#", "*", ".", "*" } });
		solution_A(18,
				new String[][] {
						{ "*", ".", "*", "#", "*", "*", "*", "#", "*", "*", "*", "#", "*", "*", "*", ".", "*", "." },
						{ "*", ".", "*", "#", "*", ".", "*", "#", ".", "*", ".", "#", "*", "*", "*", "*", "*", "*" },
						{ "*", "*", "*", "#", "*", "*", "*", "#", "*", "*", "*", "#", "*", "*", "*", "*", ".", "*" } });
		// solution_F(new int[] { 4, 2, 100 }, new float[] { 20, 10, 30, 40 },
		// new float[] { 5, 10, 30, 20 });
	}

	public static String solution_A(int n, String[][] inputSky) {

		System.out.println(inputSky[0]);

		Map<String, String[][]> starsAndGalaxyPatterns = populateStarsAndGalaxyPatterns();

		StringBuilder galaxyAndStars = new StringBuilder();
		for (int indx = 0; indx <= n - 3; indx++) {
			String[][] subInputSky = getSubInputSky(indx, inputSky);
			for (Map.Entry<String, String[][]> entry : starsAndGalaxyPatterns.entrySet()) {
				boolean starsAndGalaxyExists = starsAndGalaxyPatternsExists(subInputSky, entry.getValue());
				if (starsAndGalaxyExists) {
					galaxyAndStars.append(entry.getKey());
					break;
				}
			}

		}
		System.out.println(galaxyAndStars);
		return galaxyAndStars.toString();
	}

	private static Map<String, String[][]> populateStarsAndGalaxyPatterns() {
		Map<String, String[][]> constellation = new HashMap<>();
		constellation.put("#", new String[][] { { "#" }, { "#" }, { "#" } });
		constellation.put("A", new String[][] { { ".", "*", "." }, { "*", "*", "*" }, { "*", ".", "*" } });
		constellation.put("E", new String[][] { { "*", "*", "*" }, { "*", "*", "*" }, { "*", "*", "*" } });
		constellation.put("I", new String[][] { { "*", "*", "*" }, { ".", "*", "." }, { "*", "*", "*" } });
		constellation.put("O", new String[][] { { "*", "*", "*" }, { "*", ".", "*" }, { "*", "*", "*" } });
		constellation.put("U", new String[][] { { "*", ".", "*" }, { "*", ".", "*" }, { "*", "*", "*" } });
		return constellation;
	}

	public static String[][] getSubInputSky(int col, String[][] input) {
		String[][] subInputSky = new String[3][3];
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				subInputSky[r][c] = input[r][c + col];
			}
		}
		return subInputSky;
	}

	public static boolean starsAndGalaxyPatternsExists(String[][] inputSky, String[][] starAndGalaxyPattern) {
		// Adding checks for special cases/corner cases
		if (inputSky.length == starAndGalaxyPattern.length && inputSky[0].length == starAndGalaxyPattern[0].length
				&& inputSky[0][0] != starAndGalaxyPattern[0][0]) {
			return false;
		}
		if (inputSky == null || starAndGalaxyPattern == null || inputSky.length == 0 || inputSky[0].length == 0
				|| starAndGalaxyPattern.length == 0 || starAndGalaxyPattern[0].length == 0) {
			throw new IllegalArgumentException();
		}
		if (inputSky.length < starAndGalaxyPattern.length) {
			return false;
		}
		if (inputSky[0].length < starAndGalaxyPattern[0].length) {
			return false;
		}
		// Checking for Galaxy
		if (starAndGalaxyPattern[0][0].equals("#") && inputSky[0][0].equals("#")) {
			boolean isGalaxy = true;
			for (int i = 0; i < 3; i++) {
				if (!inputSky[i][0].equals("#")) {
					isGalaxy = false;
				}
			}
			if (isGalaxy) {
				return true;
			}
		} else {
			// Checking for Stars
			if (inputSky[0][0].equals(starAndGalaxyPattern[0][0])) {
				if (isConstellation(inputSky, starAndGalaxyPattern))
					return true;
			}
		}

		return false;
	}

	private static boolean isConstellation(String[][] inputSky, String[][] starPattern) {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (!inputSky[r][c].equals(starPattern[r][c])) {
					return false;
				}
			}
		}
		return true;
	}

	public static void solution2() {

	}

	public static void solution_F(int[] l1, float[] l2, float[] l3) {
		int N = l1[0]; // num of stock
		int K = l1[1]; // max qty of a stock you can buy
		int A = l1[2]; // capital amt you have

		Map mapStock = new HashMap<>();
		int i = 0;
		for (float s : l2) {
			mapStock.put(i, s);
			i++;
		}

		Map mapPercentage = new HashMap<>();
		i = 0;
		for (float s : l3) {
			mapPercentage.put(i, s);
			i++;
		}
		System.out.println(l3[N - 1]);
		Arrays.sort(l3); // sorted Percentage
		System.out.println(l3[N - 1]);

		float[] sortedStocks = new float[N - 1];

		int qtyCount = 1;
		int stockIndex = 0;
		float profit = 0;
		while (A > 0) {
			while (qtyCount < K) {
				int subAmt = qtyCount;
			}
			if (0 == 0) {
				qtyCount = 0;
				stockIndex++;
			}
		}
	}

}
