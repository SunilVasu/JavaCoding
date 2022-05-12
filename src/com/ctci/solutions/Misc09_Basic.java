package com.ctci.solutions;

import java.util.HashMap;

public class Misc09_Basic {

	public static void main(String[] args) {
		// #1 Sort
		sort();
		// #2 Search
		search();

		// #3 Int To Roman
		System.out.println("\n#3 Int to Roman");
		System.out.println("intToRoman = " + intToRoman(9));

		// #4 Roman to Int
		System.out.println("\n#4 Roman to Int");
		System.out.println("RomanToInt = " + romanToInt("IX"));
	}

	// #1 Sort
	public static void sort() {
		System.out.println("#1 SORT ::");
		bubbleSort();
		selectionSort();
		insertionSort();
		mergeSort();
		quickSort();
	}

	public static void bubbleSort() {
		System.out.println("Bubble Sort::");
		int[] arr = new int[] { 5, 4, 3, 2, 1 };
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			printArr(arr);
		}

	}

	public static void selectionSort() {
		System.out.println("Selection Sort::");
		int[] arr = new int[] { 5, 4, 3, 2, 1 };
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[min])
					min = j;

			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
			printArr(arr);
		}
	}

	public static void insertionSort() {
		System.out.println("Insertion Sort::");
		int[] arr = new int[] { 5, 4, 3, 2, 1 };
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
			printArr(arr);
		}
	}

	// MergeSort
	// Soln: 1) Split arr => left & right
	// 2) merge left & right into arr

	// List:https://leetcode.com/problems/merge-k-sorted-lists/
	// List:https://leetcode.com/problems/sort-list/
	public static void mergeSort() {
		System.out.println("Merge Sort::");
		int[] arr = new int[] { 5, 4, 3, 2, 1 };
		printArr(arr, "input");
		mergeSort(arr, arr.length);
		printArr(arr);
	}

	public static void mergeSort(int[] arr, int len) {
		if (len < 2)
			return;
		int mid = len / 2;
		int[] left = new int[mid];
		int[] right = new int[len - mid];
		int k = 0;
		for (int i = 0; i < len; i++) {
			if (i < mid) {
				left[i] = arr[i];
			} else {
				right[k++] = arr[i];
			}
		}
		printArr(left, "left");
		printArr(right, "right");
		mergeSort(left, left.length);
		mergeSort(right, right.length);

		merge(left, right, arr);
	}

	public static void merge(int[] left, int[] right, int[] arr) {
		int i = 0, l = 0, r = 0;
		while (l < left.length && r < right.length) {
			if (left[l] < right[r]) {
				arr[i++] = left[l++];
			} else {
				arr[i++] = right[r++];
			}
		}
		while (l < left.length) {
			arr[i++] = left[l++];
		}
		while (r < right.length) {
			arr[i++] = right[r++];
		}

	}

	// QuickSort: RT= O(nlogn)
	public static void quickSort() {
		System.out.println("\nQuick Sort::");
		int[] nums = new int[] { 1, 5, 3, 2, 4 };
		printArr(nums, "input");
		quickSort(nums, 0, nums.length - 1);
		printArr(nums, "ouput");

		nums = new int[] { 1, 4, 5, 2, 3 };
		printArr(nums, "\ninput");
		quickSort(nums, 0, nums.length - 1);
		printArr(nums, "ouput");
	}

	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int pi = partition(arr, left, right);

			quickSort(arr, left, pi - 1);
			quickSort(arr, pi + 1, right);
		}
	}

	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[right];

		int i = left;
		System.out.println("pi: " + pivot);
		for (int j = left; j < right; j++) {
			if (arr[j] < pivot) {
				System.out.println("swaping: " + arr[i] + "  " + arr[j]);

				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, right);
		printArr(arr);
		return i;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// #2 Binary Search: RT=O(logn); Space=O(1)
	public static void search() {
		System.out.println("\n#2 BINARY SEARCH ::");
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		int elem = 5;
		binarySearch(arr, elem);
		System.out.println("Binary Search Recur, elem pos = " + binarySearch(arr, 0, arr.length - 1, elem));

		// check if given arr is sorted - ascending or desc
		boolean isAscending = arr[0] < arr[arr.length - 1];
		System.out.println("isAscending = " + isAscending);

		arr = new int[] { 5 };
		binarySearch(arr, elem);

	}

	public static void binarySearch(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			// System.out.println("start=" + start + " end=" + end);
			// int mid = (start + end) / 2;
			int mid = start + (end - start) / 2; // safest way to find mid
			if (arr[mid] == target) {
				System.out.println("Binary Search(Iterative): Elem found = " + mid);
				return;
			} else if (arr[mid] < target)
				start = mid + 1;
			else
				end = mid - 1;
		}
		System.out.println("Binary Search(Iterative): Elem not found");
	}

	public static int binarySearch(int[] arr, int l, int r, int target) {
		if (l <= r) {
			// System.out.println("l=" + l + " r=" + r);
			int mid = (l + r) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target)
				return binarySearch(arr, mid + 1, r, target);
			else
				return binarySearch(arr, l, mid - 1, target);
		}
		return -1;
	}

	// #3 Int to Roman
	// https://leetcode.com/problems/integer-to-roman/
	public static String intToRoman(int num) {
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}

		return sb.toString();
	}

	// #4 Roman to Int
	// https://leetcode.com/problems/roman-to-integer/
	public static int romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int result = map.get(s.charAt(s.length() - 1));
		for (int i = s.length() - 2; i >= 0; i--) {
			// left > right => add
			// left < right => subtract
			if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
				result += map.get(s.charAt(i));
			} else {
				result -= map.get(s.charAt(i));
			}
		}

		return result;
	}

	// META
	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void printArr(int[] arr, String msg) {
		System.out.print(msg + " : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
