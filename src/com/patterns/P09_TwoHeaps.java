package com.patterns;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class P09_TwoHeaps {

	public static void main(String[] args) {
		System.out.println("##Pattern 09 : Two Heaps");

		// #1 Find Median from Data Stream
		// https://leetcode.com/problems/find-median-from-data-stream/

		// #2 Sliding Window Median: RT=O(n)
		// https://leetcode.com/problems/sliding-window-median/
		System.out.println("\n#2 Siliding Window Median");
		slidingWindowMedian(new int[] { 1, 2, 3, 4, 5 }, 2);
		System.out.println();
		slidingWindowMedian(new int[] { 1, 2, 3, 4, 5 }, 3);

		// #3 Maximize Capital
		// https://leetcode.com/problems/ipo/

		// #4 Next Interval
		// https://leetcode.com/problems/find-right-interval/
	}

	// #2 Sliding Window Median
	// https://leetcode.com/problems/sliding-window-median/
	public static void slidingWindowMedian(int[] nums, int k) {
		double[] res = new double[nums.length - k + 1];
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
		PriorityQueue<Integer> right = new PriorityQueue<>(); // minHeap
		for (int i = 0; i < nums.length; i++) {
			if (left.size() <= right.size()) {
				right.add(nums[i]);
				left.add(right.remove());
			} else {
				left.add(nums[i]);
				right.add(left.remove());
			}
			if (left.size() + right.size() == k) {
				double median;
				if (left.size() == right.size()) {
					median = (double) (left.peek() + right.peek()) / 2;
				} else {
					median = left.peek();
				}
				int start = i - k + 1;
				res[start] = median;
				if (!left.remove(nums[start])) {
					right.remove(nums[start]);
				}
			}
		}

		System.out.print("res:: ");
		for (double n : res) {
			System.out.print(n + " ");
		}
		// TreeMap<Integer, Integer> map = new TreeMap<>();
		TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
		map.put(2, 22);
		map.put(1, 11);
		map.put(5, 55);
		System.out.println("->" + map);
		System.out.println("=" + map.ceilingKey(1));

	}

}
