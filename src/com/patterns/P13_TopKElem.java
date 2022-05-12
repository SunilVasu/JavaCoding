package com.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class P13_TopKElem {

	public static void main(String[] args) {

		System.out.println("\n#1 Top K Frequent Elements");
		System.out.println("topKFrequent [1,2]=" + topKElem(new int[] { 1, 1, 1, 2, 2, 3 }, 2));
		System.out.println("topKFrequent [1]=" + topKElem(new int[] { 1 }, 1));
	}

	// #1 Top K Freq elems
	// Build a array of list to be buckets with length 1 to sort.
	public static List<Integer> topKElem(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> map = new HashMap<>();

		for (int n : nums)
			map.put(n, map.getOrDefault(n, 0) + 1);

		for (int key : map.keySet()) {
			int freq = map.get(key);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(key);
		}
		System.out.println("\nkey=" + map.keySet() + "  val=" + map.values());
		int i = 0;
		for (List<Integer> n : bucket) {
			System.out.print("(" + i++ + ")" + n + "  ");
		}
		System.out.println();

		List<Integer> res = new LinkedList<>();
		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}

}
