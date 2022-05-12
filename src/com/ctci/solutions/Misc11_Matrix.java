package com.ctci.solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Misc11_Matrix {

	public static void main(String[] args) {
		int[][] mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printMat(mat, "Input");
		System.out.println("\n#1: SprialMatrix");
		spiralMat(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });

		System.out.println("\n#2: ZeroMatrix");
		setZeros(new int[][] { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 9 } });

		System.out.println("\n#3.1: SearchMat");
		System.out.println("search=" + searchMat(new int[][] { { 1, 4 }, { 5, 6 } }, 5));

		System.out.println("\n#3.2: SearchMat 2");
		System.out.println("search=" + searchMat2(new int[][] { { 1, 4 }, { 2, 5 } }, 5));

		System.out.println("\n#3.3 Rotate Mat");
		rotateMat(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });

		System.out.println("\n#3.4 Sudoku - Validation");
		char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		System.out.println("SudoValidation (T)=" + sudokuValidator(board));
		board = new char[][] { { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		System.out.println("SudoValidation (F)=" + sudokuValidator(board));

		System.out.println("#3.5 Sudoku - Solve");
		board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		sudokuSolve(board);
		printMat(board, "Solved");
	}

	// #1: Spiral Matrix
	// https://leetcode.com/problems/spiral-matrix/
	// https://leetcode.com/problems/spiral-matrix-ii/
	public static void spiralMat(int[][] mat) {
		List<Integer> res = new LinkedList<>();

		int top = 0, bottom = mat.length - 1;
		int left = 0, right = mat[0].length - 1;

		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++)
				res.add(mat[top][i]);
			top++;
			for (int i = top; i <= bottom; i++)
				res.add(mat[i][right]);
			right--;
			if (top <= bottom) {
				for (int i = right; i >= left; i--)
					res.add(mat[bottom][i]);
			}
			bottom--;
			if (left <= right) {
				for (int i = bottom; i >= top; i--)
					res.add(mat[i][left]);
			}
			left++;
		}
		System.out.println("res=" + res);
	}

	// #2 Set Zeros
	// https://leetcode.com/problems/set-matrix-zeroes/
	public static void setZeros(int[][] mat) {
		boolean fr = false, fc = false;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 0) {
					if (i == 0)
						fr = true;
					if (j == 0)
						fc = true;
					mat[i][0] = 0;
					mat[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				if (mat[i][0] == 0 || mat[0][j] == 0) {
					mat[i][j] = 0;
				}
			}
		}
		if (fr) {
			for (int i = 0; i < mat.length; i++)
				mat[i][0] = 0;
		}
		if (fc) {
			for (int j = 0; j < mat[0].length; j++)
				mat[0][j] = 0;
		}
		printMat(mat, "ZeroMat");
	}

	// 3.1 Search Mat (input - fully sorted in order)
	// row: sorted left->right
	// 1st integer of each row is greater than last integer of prev row
	// https://leetcode.com/problems/search-a-2d-matrix/
	public static boolean searchMat(int[][] mat, int target) {
		int row = mat.length;
		int col = mat[0].length;
		int start = 0, end = row * col - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			int mid_val = mat[mid / col][mid % col];
			if (mid_val == target) {
				return true;
			} else if (mid_val < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
	}

	// 3.2 Search Mat - II
	// row: sorted left->right
	// col: sorted top->bottom
	// https://leetcode.com/problems/search-a-2d-matrix-ii/
	public static boolean searchMat2(int[][] mat, int target) {
		int row = 0, col = mat[0].length - 1;
		while (row <= col) {
			if (mat[row][col] == target) {
				return true;
			} else if (mat[row][col] < target) {
				row++;
			} else {
				col--;
			}
		}

		return false;
	}

	// #3.3 Rotate Mat
	// Transpose -> Flip
	// https://leetcode.com/problems/rotate-image/
	public static void rotateMat(int[][] mat) {
		printMat(mat, "input");
		// Transpose
		for (int i = 0; i < mat.length; i++) {
			for (int j = i; j < mat[0].length; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}

		// Flip
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length / 2; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[i][mat[0].length - 1 - j];
				mat[i][mat[0].length - 1 - j] = temp;
			}
		}

		printMat(mat, "rotate");
	}

	// #3.4 Sudoku - Validation
	// https://leetcode.com/problems/valid-sudoku/
	public static boolean sudokuValidator(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			Set<Character> row = new HashSet<>();
			Set<Character> col = new HashSet<>();
			Set<Character> cube = new HashSet<>();
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != '.' && !row.add(board[i][j]))
					return false;
				if (board[j][i] != '.' && !col.add(board[j][i]))
					return false;
				int rowIndex = 3 * (i / 3);
				int colIndex = 3 * (i % 3);
				if (board[rowIndex + j / 3][colIndex + j % 3] != '.'
						&& !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
					return false;
			}
		}
		return true;
	}

	// #3.5 Sudoku - Solve: RT=O(9^m) m-blank spaces
	// https://leetcode.com/problems/sudoku-solver/
	public static void sudokuSolve(char[][] board) {
		if (board == null)
			return;
		solve(board);
	}

	public static boolean solve(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							if (solve(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] != '.' && board[row][i] == c)
				return false;
			if (board[i][col] != '.' && board[i][col] == c)
				return false;
			int rowIndex = 3 * (row / 3);
			int colIndex = 3 * (col / 3);
			if (board[rowIndex + i / 3][colIndex + i % 3] != '.' && board[rowIndex + i / 3][colIndex + i % 3] == c)
				return false;
		}
		return true;
	}

	// META
	public static void printMat(int[][] mat, String msg) {
		System.out.println(msg + " : ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void printMat(char[][] mat, String msg) {
		System.out.println(msg + " : ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}
	}

}
