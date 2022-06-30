package com.patterns.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P04_MergeIntervals {

	public static void main(String[] args) {
		System.out.println("### Pattern 04: Merge Intervals");
		test();

		System.out.println("\n\n#1 Merge Interval");
		int[][] mat = mergeInterval(new int[][] { { 1, 3 }, { 4, 7 }, { 5, 9 } });
		for (int[] n : mat) {
			System.out.print("[" + n[0] + "," + n[1] + "]");
		}

		System.out.println("\n\n#2 Insert Interval");
		mat = insertInterval(new int[][] { { 1, 2 }, { 6, 9 } }, new int[] { 4, 8 });
		for (int[] n : mat) {
			System.out.print("[" + n[0] + "," + n[1] + "]");
		}
	}

	public static void test() {
		System.out.println("\n#0 TEST");
		List<int[]> test = new LinkedList(Arrays.asList(1, 2, 3, 4, 5));

		Integer[] n = test.toArray(new Integer[5]);
		for (int elem : n) {
			System.out.print(elem + " - ");
		}

		List<int[]> test2d = new ArrayList<>(Arrays.asList(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } }));
		System.out.println("");
		int[][] mat = test2d.toArray(new int[test2d.size()][]);
		for (int[] elem : mat) {
			System.out.print("[" + elem[0] + "," + elem[1] + "]");
		}
	}

	public static int[][] mergeInterval(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		LinkedList<int[]> merge = new LinkedList<>();
		for (int[] interval : intervals) {
			if (merge.isEmpty() || merge.getLast()[1] < interval[0]) {
				merge.add(interval);
			} else {
				merge.getLast()[1] = Math.max(merge.getLast()[1], interval[1]);
			}
		}
		return merge.toArray(new int[merge.size()][]);

	}

	public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
		List<int[]> result = new ArrayList<>();
		for (int[] interval : intervals) {
			if (newInterval == null || interval[1] < newInterval[0]) {
				result.add(interval);
			} else if (interval[0] > newInterval[1]) {
				result.add(newInterval);
				result.add(interval);
				newInterval = null;
			} else {
				int lo = Math.min(interval[0], newInterval[0]);
				int hi = Math.max(interval[1], newInterval[1]);
				newInterval = new int[] { lo, hi };
			}
		}
		if (newInterval != null) {
			result.add(newInterval);
		}
		return result.toArray(new int[result.size()][]);
	}

}
