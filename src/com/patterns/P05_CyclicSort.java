package com.patterns;

import java.util.LinkedList;
import java.util.List;

public class P05_CyclicSort {

	public static void main(String[] args) {
		System.out.println("##Pattern 05 : Cyclic Sort");

		System.out.println("\n#1: Cyclic Sort");
		int[] nums = new int[] { 1, 4, 3, 5, 2 };
		cyclicSort(nums);

		System.out.println("\n#2: Find Missing number");
		nums = new int[] { 0, 1, 5, 3, 2 };
		System.out.println("missingNumber = " + missingNum(nums));

		System.out.println("\n#3: Find All Missing number");
		nums = new int[] { 1, 1, 5, 3, 2 };
		System.out.println("All missingNumber = " + missingNumAll(nums));

		System.out.println("\n#4: Find Duplicate number");
		nums = new int[] { 1, 1, 5, 3, 2 };
		System.out.println("Duplicate = " + findDuplicate(nums));
		System.out.println("Duplicate2 = " + findDuplicate2(nums));
		nums = new int[] { 1, 4, 4, 3, 2 };
		System.out.println("Duplicate = " + findDuplicate(nums));
		System.out.println("Duplicate2 = " + findDuplicate2(nums));

		System.out.println("\n#5: Find All Duplicate number");
		nums = new int[] { 3, 4, 4, 5, 5 };
		System.out.println("Duplicate [4,5]= " + findDuplicateAll(nums));

		System.out.println("\n#6: Find Corrupt Pair");
		nums = new int[] { 3, 1, 2, 5, 2 };
		System.out.println("corrupt Pair [2,4]= " + corruptPair(nums));
		nums = new int[] { 3, 1, 2, 3, 6, 4 };
		System.out.println("corrupt Pair [3,5]= " + corruptPair(nums));

		System.out.println("\n#7: Find the smallest Missing Positive Number");
		nums = new int[] { 1 };
		System.out.println("smallest Missing Positive Number [2]= " + smallestMissingPosNum(nums));
		nums = new int[] { 1, 2, 0 };
		System.out.println("smallest Missing Positive Number [3]= " + smallestMissingPosNum(nums));
		nums = new int[] { 3, 4, -1, 1 };
		System.out.println("smallest Missing Positive Number [2]= " + smallestMissingPosNum(nums));
		nums = new int[] { 7, 8, 9, 11, 12 };
		System.out.println("smallest Missing Positive Number [1]= " + smallestMissingPosNum(nums));

		System.out.println("\n#8: Find the first K Missing Positive Numbers");
		nums = new int[] { 7, 12, 8, 9, 11, };
		firstKMissingPosNum(nums, 3);
		nums = new int[] { 1, 9, 2, 3, 5, 7 };
		firstKMissingPosNum(nums, 3);

		// ###LEETCODE###
		// #9: Find the Kth Missing Positive Number from sorted +ve arr
		// https://leetcode.com/problems/kth-missing-positive-number/
		System.out.println("\n#9: Find the Kth Missing Positive Number");
		System.out.println("findKthPositive = " + findKthPositive(new int[] { 2, 3, 4, 7, 11 }, 5));
		System.out.println("findKthPositive = " + findKthPositive(new int[] { 1, 2, 3, 4 }, 2));

	}

	// #1: Cyclic Sort [1,n]: RT=O(N); Space=O(1)
	// input - arr contains 1 to n nums
	public static int[] cyclicSort(int[] nums) {
		printArr(nums, "before");
		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		printArr(nums, "after ");
		return nums;
	}

	// #2 Find the missing numbers: RT=O(N); Space=O(1)
	// Given [0, n], return the only num in the range that is missing in array.
	// Sort & find missing
	public static int missingNum(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			int j = nums[i];
			if (nums[i] < nums.length && nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		printArr(nums, "after ");
		for (i = 0; i < nums.length; i++) {
			if (i != nums[i])
				return i;
		}
		return -1;
	}

	// #3 Find all missing numbers [1,n]: RT=O(N); Space=O(1)
	// sort & find missing
	public static List<Integer> missingNumAll(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		printArr(nums, "after ");
		List<Integer> res = new LinkedList<>();
		for (i = 0; i < nums.length; i++) {
			if (i + 1 != nums[i]) {
				res.add(i + 1);
			}
		}
		return res;

	}

	// #4 Find Duplicate Number:RT=O(N); Space=O(1)****
	// logic: sort+find duplicate
	public static int findDuplicate(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] == i + 1) {
				i++;
			} else if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				return nums[i];
			}

		}
		return -1;
	}

	// #4.2 Find Duplicate Number:RT=O(N); Space=O(1)
	public static int findDuplicate2(int[] nums) {
		int slow = nums[0], fast = nums[nums[0]];
		while (fast != slow) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0;
		while (fast != slow) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return fast;
	}

	// #5 Find All Duplicate Number:RT=O(N); Space=O(1)
	public static List<Integer> findDuplicateAll(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		printArr(nums, "after ");
		List<Integer> res = new LinkedList<>();
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				res.add(nums[i]);
			}
		}

		return res;
	}

	// #6 Find corrupt pair: RT=O(N); Space=O(1)
	public static List<Integer> corruptPair(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		List<Integer> pair = new LinkedList<>();
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				pair.add(nums[i]);
				pair.add(i + 1);
			}
		}
		return pair;
	}

	// #7 Find the smallest Missing Positive Number
	// https://leetcode.com/problems/first-missing-positive/
	public static int smallestMissingPosNum(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) {
				i++;
			} else if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return i + 1;
	}

	// #8 Find the First K Missing Positive Numbers
	public static void firstKMissingPosNum(int[] nums, int k) {

		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) {
				i++;
			} else if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				i++;
			}
		}
		printArr(nums, "after");
		System.out.print("first " + k + " missing numbers = ");
		int count = 0;
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				if (count < k) {
					System.out.print((i + 1) + " ");
					count++;
				}
			}
		}
		while (count < k) {
			System.out.print((i + 1) + " ");
			count++;
		}

		System.out.println();
	}

	// ###LEETCODE###
	// Find the Kth Missing Positive Number from sorted +ve arr
	public static int findKthPositive(int[] nums, int k) {
		int l = 0, r = nums.length;
		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] - (mid + 1) < k) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		System.out.println("l=" + l);
		return l + k;
	}

	// META
	public static void printArr(int[] arr, String msg) {
		System.out.print(msg + " : ");
		for (int n : arr) {
			System.out.print(n + " ");
		}
		System.out.println();
	}

}
