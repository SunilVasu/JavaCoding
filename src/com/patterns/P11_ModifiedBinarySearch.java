package com.patterns;

public class P11_ModifiedBinarySearch {

	public static void main(String[] args) {
		System.out.println("##Pattern 11 : Modified Binary Search\n");

		int[] nums = new int[] { 1, 3, 5, 6, 7, 8 };
		System.out.println("binarySearch (ascen) pos = " + binarySearch(nums, 5));
		System.out.println("binarySearch-recur (ascen) pos = " + binarySearch(nums, 0, nums.length, 5) + "\n");

		nums = new int[] { 8, 6, 5, 3, 2, 1 };
		System.out.println("binarySearch (desc) pos = " + binarySearch(nums, 5) + "\n");

		nums = new int[] { 5 };
		System.out.println("binarySearch (desc) pos = " + binarySearch(nums, 5));
		System.out.println("binarySearch-recur pos = " + binarySearch(nums, 0, nums.length, 5) + "\n");

	}

	public static int binarySearch(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		boolean isAscending = arr[start] <= arr[end];
		System.out.println("isAscending = " + isAscending);
		while (start <= end) {
			// int mid = (start+end)/2;
			int mid = start + (end - start) / 2;

			if (arr[mid] == target) {
				return mid;
			}
			if (isAscending) {
				if (arr[mid] < target) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				if (arr[mid] < target) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
		}
		return -1;
	}

	public static int binarySearch(int[] arr, int start, int end, int target) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == target)
			return mid;
		if (arr[mid] < target)
			return binarySearch(arr, start + 1, end, target);
		else
			return binarySearch(arr, start, end - 1, target);
	}

}
