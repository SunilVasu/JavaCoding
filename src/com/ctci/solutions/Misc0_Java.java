package com.ctci.solutions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Misc0_Java {

	public static void main(String[] args) {
		// #1 Compare
		compare();
	}

	// #1 Compare
	// Arrays.sort() : sort array
	// Collections.sort() : sort list
	public static void compare() {
		// Collections.sort
		Interval[] nums = new Interval[] { new Interval(5, 10), new Interval(1, 3) };
		Arrays.sort(nums, (a, b) -> a.start - b.start);
		for (Interval n : nums)
			System.out.print(n.get_interval() + " ");

		List<Integer> res = new LinkedList<>();
		res.add(5);
		res.add(4);
		res.add(2);
		res.add(1);
		res.add(3);
		Collections.sort(res, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
		System.out.println("\nSorted List:" + res);

		// 1D array sort: Arrays.sort() ascending order by default
		int[] arr = new int[] { 1, 3, 5, 7, 2, 9, 6, 4 };
		Arrays.sort(arr);
		System.out.print("Sorted 1D array: ");
		for (int n : arr) {
			System.out.print(n + " ");
		}
		System.out.println();

		// for primitive arr(int[]),there is no direct way to sort in desc order
		// int[] arr -> need manual rearrange after Arrays.sort() ascending
		Integer[] array = new Integer[] { 1, 5, 3, 2, 4 };
		Arrays.sort(array, Collections.reverseOrder());
		System.out.print("Sorted 2D array: ");
		for (int n : array) {
			System.out.print(n + " ");
		}

		// 2D array sort
		int[][] arr2 = new int[][] { { 1, 10 }, { 8, 9 }, { 4, 7 } };
		Arrays.sort(arr2, (a, b) -> a[0] - b[0]);
		System.out.print("Sorted 2D array: ");
		for (int[] n : arr2) {
			System.out.print("[" + n[0] + "," + n[1] + "] ");
		}
		System.out.println();

	}

}

class Interval {
	int start;
	int end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public String get_interval() {
		return "[" + this.start + "," + this.end + "]";
	}
}
