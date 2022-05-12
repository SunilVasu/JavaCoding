package com.ctci.solutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Misc08_Frequent {

	public static void main(String[] args) {

		navigate2D_Recur();
		navigate2D_BFS();
		BFSTraversal();
		DFSTraversal();
	}

	// #1 Navigate 2D Mat (0,0)->(n,n): Recursion; RT=O(4^nm); Space=O(n*m)
	private static void navigate2D_Recur() {
		System.out.println("\n#1 Nav 2D Recursion: Check if path exist (0,0) to (n,n)");
		int[][] arr = new int[][] { { 0, 0, 0, -1, 0 }, { -1, 0, 0, -1, -1 }, { 0, 0, 0, -1, 0 }, { -1, 0, 0, 0, 0 },
				{ 0, 0, -1, 0, 0 } };
		boolean[][] visited = new boolean[arr.length][arr[0].length];

		boolean checkPath = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) {
					if (nav2D(arr, i, j, visited)) {
						checkPath = true;
						break;
					}
				}
			}
		}
		System.out.println("Path Exist = " + checkPath);
	}

	public static boolean nav2D(int[][] arr, int i, int j, boolean[][] visited) {
		if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == -1 || visited[i][j])
			return false;
		if (i == arr.length - 1 && j == arr[0].length - 1)
			return true;
		visited[i][j] = true;
		return nav2D(arr, i + 1, j, visited) || nav2D(arr, i - 1, j, visited) || nav2D(arr, i, j + 1, visited)
				|| nav2D(arr, i, j - 1, visited);
	}

	// #2 Navigate 2D Mat (0,0)->(n,n): BFS; RT=O(n*m); Space=O(n*m)
	public static void navigate2D_BFS() {
		System.out.println("\n#2 Nav 2D BFS: Check if path exist (0,0) to (n,n)");
		int[][] arr = new int[][] { { 0, 0, 0, -1, 0 }, { -1, 0, 0, -1, -1 }, { 0, 0, 0, -1, 0 }, { -1, 0, 0, 0, 0 },
				{ 0, 0, -1, 0, 0 } };
		boolean[][] visited = new boolean[arr.length][arr[0].length];
		boolean checkPath = false;

		Queue<BFSElem> q = new LinkedList<>();
		q.add(new BFSElem(0, 0));

		while (!q.isEmpty()) {
			BFSElem x = q.peek();
			q.remove();
			int i = x.i;
			int j = x.j;
			// System.out.println("i=" + i + " j=" + j);
			if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == -1 || visited[i][j])
				continue;

			if (i == arr.length - 1 && j == arr[0].length - 1) {
				checkPath = true;
				break;
			}

			visited[i][j] = true;

			q.add(new BFSElem(i + 1, j));
			q.add(new BFSElem(i - 1, j));
			q.add(new BFSElem(i, j + 1));
			q.add(new BFSElem(i, j - 1));
		}
		System.out.println("Path Exist = " + checkPath);
	}

	// #3 Traverse 2D Mat: BFS; RT=O(n*m); Space=O(n*m)
	public static void BFSTraversal() {
		System.out.println("\n#3 Traverse 2D Mat: BFS using Queue");
		int[][] arr = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		boolean[][] visited = new boolean[arr.length][arr[0].length];

		Queue<BFSElem> q = new LinkedList<>();
		q.add(new BFSElem(0, 0));

		while (!q.isEmpty()) {
			BFSElem x = q.remove();
			int i = x.i;
			int j = x.j;

			if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || visited[i][j])
				continue;

			System.out.print(arr[i][j] + " ");
			visited[i][j] = true;

			q.add(new BFSElem(i - 1, j));
			q.add(new BFSElem(i, j + 1));
			q.add(new BFSElem(i + 1, j));
			q.add(new BFSElem(i, j - 1));
		}
	}

	// #4 DFS 2D Mat
	public static void DFSTraversal() {
		System.out.println("\n\n#4 Traverse 2D Mat: DFS using Stack");
		int[][] arr = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		boolean[][] visited = new boolean[arr.length][arr[0].length];

		Stack<Elem> s = new Stack<>();
		s.push(new Elem(0, 0));

		while (!s.isEmpty()) {
			Elem x = s.pop();
			int i = x.i;
			int j = x.j;

			if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || visited[i][j])
				continue;

			System.out.print(arr[i][j] + " ");
			visited[i][j] = true;

			s.push(new Elem(i - 1, j));
			s.push(new Elem(i, j + 1));
			s.push(new Elem(i + 1, j));
			s.push(new Elem(i, j - 1));

		}
	}

}

class BFSElem {
	int i, j;

	BFSElem(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

class Elem {
	int i, j;

	Elem(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
