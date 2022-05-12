package com.patterns;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class P04_MergeIntervals {

	public static void main(String[] args) {
		System.out.println("##Pattern 4 : Merge Intervals");
		// Given intervals a & b - we will have 6 combinations of merges

		// #1 Merge Interval: merge overlapping intervals
		System.out.println("#1 Merge Intervals");
		Interval[] i1 = new Interval[] { new Interval(5, 10), new Interval(1, 3) };
		Interval[] i2 = new Interval[] { new Interval(1, 3), new Interval(2, 6), new Interval(8, 10),
				new Interval(15, 18) };
		mergeInterval(i1);
		mergeInterval(i2);

		// #2 Insert Interval
		System.out.println("\n#2 Insert Interval");
		Interval[] intervals = new Interval[] { new Interval(1, 3), new Interval(6, 9) };
		Interval newInterval = new Interval(2, 5);
		insertInterval(intervals, newInterval);

		// #3 Interval List Intersection
		System.out.println("\n#3 Interval List Intersection");
		i1 = new Interval[] { new Interval(1, 5), new Interval(7, 9) };
		i2 = new Interval[] { new Interval(4, 6) };
		intervalIntersection(i1, i2);
		i1 = new Interval[] { new Interval(1, 7) };
		i2 = new Interval[] { new Interval(3, 10) };
		intervalIntersection(i1, i2);
		i1 = new Interval[] { new Interval(0, 2), new Interval(5, 10), new Interval(13, 23), new Interval(24, 25) };
		i2 = new Interval[] { new Interval(1, 5), new Interval(8, 12), new Interval(15, 24), new Interval(25, 26) };
		intervalIntersection(i1, i2);

		// #4 Meeting Rooms
		System.out.println("\n#4 Meeting Rooms");
		int[][] appts = new int[][] { { 5, 7 }, { 1, 3 }, { 8, 9 } };
		System.out.println("can attend (TRUE) = " + meetingRooms(appts));
		appts = new int[][] { { 4, 7 }, { 1, 5 } };
		System.out.println("can attend (FASLE) = " + meetingRooms(appts));

		// #5 Meeting Rooms 2:
		System.out.println("\n#5 Meeting Rooms - 2");
		appts = new int[][] { { 5, 7 }, { 1, 6 }, { 8, 9 } };
		System.out.println("Rooms (2) = " + meetingRooms2(appts));
		appts = new int[][] { { 4, 8 }, { 1, 10 }, { 6, 9 } };
		System.out.println("Rooms (3) = " + meetingRooms2(appts));
		appts = new int[][] { { 2, 15 }, { 36, 45 }, { 9, 29 }, { 16, 23 }, { 4, 9 } };
		System.out.println("Rooms (2) = " + meetingRooms2(appts));

		// #6 Maximum CPU Load

		// #7 Employee Free Time:
		List<List<Interval>> list = new LinkedList<>();
		list.add(Arrays.asList(new Interval(1, 2), new Interval(5, 6)));
		list.add(Arrays.asList(new Interval(1, 3)));
		list.add(Arrays.asList(new Interval(4, 10)));
		System.out.print("employeeFreeTime [3,4]: ");
		for (Interval n : employeeFreeTime(list)) {
			System.out.print("[" + n.start + "," + n.end + "]");
		}
		System.out.println();
		list = new LinkedList<>();
		list.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
		list.add(Arrays.asList(new Interval(2, 4)));
		list.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
		System.out.print("employeeFreeTime [5,6],[7,9]: ");
		for (Interval n : employeeFreeTime(list)) {
			System.out.print("[" + n.start + "," + n.end + "]");
		}
		System.out.println();
	}

	// #1 Merge Interval: RT=O(N*logN) Space=O(N)
	// Merge the overlapping intervals
	// LOGIC:: a.end < b.start => merge.add(interval)
	// else a.end = max(a.end, b.end)
	public static void mergeInterval(Interval[] intervals) {
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		LinkedList<Interval> merge = new LinkedList<>();
		for (Interval interval : intervals) {
			if (merge.isEmpty() || merge.getLast().end < interval.start) {
				merge.add(interval);
			} else {
				merge.getLast().end = Math.max(merge.getLast().end, interval.end);
			}
		}
		for (Interval n : merge)
			System.out.print(n.get_interval() + " ");
		System.out.println();
	}

	// #2 Insert Interval: RT=O(N) Space=O(N)
	// insert a given interval at correct pos and merge all necessary intervals
	// LOGIC:: 1) if curr interval.end < newInterval.start : add newInterval
	// 2) if curr interval.start > newInterval.end : add newInterval & curr
	// 3) if intersection: merge interval and make it newInterval
	// MERGE start=MIN(a.start, b.start) & end=MAX(a.end, b.end)
	// [1,3][6,9] & [2,5] => [1,5][6,9]
	public static void insertInterval(Interval[] intervals, Interval newInterval) {
		List<Interval> result = new LinkedList<Interval>();
		for (Interval interval : intervals) {
			if (newInterval == null || interval.end < newInterval.start) {
				result.add(newInterval);
			} else if (interval.start > newInterval.end) {
				result.add(newInterval);
				result.add(interval);
				newInterval = null;
			} else {
				int lo = Math.min(interval.start, newInterval.start);
				int hi = Math.max(interval.end, newInterval.end);
				newInterval = new Interval(lo, hi);
			}

		}
		if (newInterval != null) {
			result.add(newInterval);
		}
		for (Interval n : result)
			System.out.print(n.get_interval() + " ");
		System.out.println();
	}

	// #3 Interval List Intersection: RT=O(N+M) Space=O(1)
	// Given 2 list of intervals, find the intersection of these 2 lists
	// Each list of intervals is pairwise disjoint and in sorted order.
	// LOGIC:: start = MAX(a.start, b.start) & end = MIN(a.end, b.end)
	public static void intervalIntersection(Interval[] intervals1, Interval[] intervals2) {
		Arrays.sort(intervals1, (a, b) -> a.start - b.start);
		Arrays.sort(intervals2, (a, b) -> a.start - b.start);

		LinkedList<Interval> merge = new LinkedList<>();
		int i = 0, j = 0;
		while (i < intervals1.length && j < intervals2.length) {
			int lo = Math.max(intervals1[i].start, intervals2[j].start);
			int hi = Math.min(intervals1[i].end, intervals2[j].end);

			if (lo <= hi) {
				merge.add(new Interval(lo, hi));
			}

			if (intervals1[i].end < intervals2[j].end)
				i++;
			else
				j++;
		}
		for (Interval n : merge) {
			System.out.print(n.get_interval() + " ");
		}
		System.out.println();
	}

	// #4 Meeting Rooms: RT=O(N*logN)-sort; Space=O(N)
	// Given an interval of appts, find if a person can attend all
	// Soln: [1].end > [2].start : overlap existing hence return false
	public static boolean meetingRooms(int[][] appts) {
		Arrays.sort(appts, (a, b) -> a[0] - b[0]);
		for (int i = 0; i < appts.length; i++) {
			for (int j = 0; j < appts[0].length; j++) {
				System.out.print(appts[i][j] + " ");
			}
			System.out.print("| ");
		}

		for (int i = 0; i < appts.length - 1; i++) {
			if (appts[i][1] > appts[i + 1][0])
				return false;
		}
		return true;
	}

	// #5 Meeting Rooms 2: RT=O(N*logN)-sort; Space=O(N)
	// Given arr of meeting time, find the min number of room required
	// Soln: sort meeting by start-time; add 1st meeting end-time in heap
	// if n.start >= heap.peek() => heap.poll() & heap.add(n.end)
	// Required number of rooms = Heap size at the end
	public static int meetingRooms2(int[][] appts) {
		Arrays.sort(appts, (a, b) -> a[0] - b[0]);
		for (int i = 0; i < appts.length; i++) {
			for (int j = 0; j < appts[0].length; j++) {
				System.out.print(appts[i][j] + " ");
			}
			System.out.print("| ");
		}

		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int room = 0;
		for (int[] n : appts) {
			// System.out.println("heap =>" + heap);
			if (heap.isEmpty()) {
				heap.add(n[1]);
			} else {
				if (n[0] >= heap.peek()) {
					heap.poll();
				} else {
					room++;
				}
				heap.add(n[1]);
			}
		}
		return heap.size();
	}

	// #6 Maximum CPU Load

	// #7 Employee Free Time: RT=O(N*logN)-sort; Space=O(N)
	// given schedule of employees representing working time for each employee.
	// find finite intervals of common, +ve free time for all employees sorted
	public static List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
		PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> a.start - b.start);

		for (List<Interval> schedule : schedules)
			for (Interval i : schedule)
				queue.add(i);

		List<Interval> result = new LinkedList<>();
		int max = -1; // end
		while (!queue.isEmpty()) {
			Interval top = queue.poll();
			if (max != -1 && top.start > max) {
				result.add(new Interval(max, top.start));
			}
			max = Math.max(max, top.end);
		}
		return result;
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
