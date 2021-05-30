package com.ctci;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SolA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		String[] part0 = sc.nextLine().split(" ");
		String[] part1 = sc.nextLine().split(" ");
		String[] part2 = sc.nextLine().split(" ");

		String[][] inputSky = new String[3][n];
		int row = 0;
		for (int j = 0; j < n; j++) {
			inputSky[row][j] = part0[j];
		}
		row += 1;
		for (int j = 0; j < n; j++) {
			inputSky[row][j] = part1[j];
		}
		row += 1;
		for (int j = 0; j < n; j++) {
			inputSky[row][j] = part2[j];
		}
		Map<String, String[][]> starsAndGalaxyPatterns = populateStarsAndGalaxyPatterns();

		StringBuilder galaxyAndStarsFound = new StringBuilder();
		for (int indx = 0; indx <= n - 3; indx++) {
			String[][] subInputSky = getSubInputSky(indx, inputSky);
			for (Map.Entry<String, String[][]> entry : starsAndGalaxyPatterns.entrySet()) {
				boolean starsAndGalaxyExists = starsAndGalaxyPatternsExists(subInputSky, entry.getValue());
				if (starsAndGalaxyExists) {
					galaxyAndStarsFound.append(entry.getKey());
					if (!subInputSky[0][0].equals("#")) {
						indx += 2;
					}
					break;
				}
			}

		}
		System.out.println(galaxyAndStarsFound.toString());
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
		/*
		 * if (inputSky.length == starAndGalaxyPattern.length &&
		 * inputSky[0].length == starAndGalaxyPattern[0].length &&
		 * !inputSky[0][0].equals(starAndGalaxyPattern[0][0])) { return false; }
		 * if (inputSky.length < starAndGalaxyPattern.length) { return false; }
		 * if (inputSky[0].length < starAndGalaxyPattern[0].length) { return
		 * false; }
		 */
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

}
