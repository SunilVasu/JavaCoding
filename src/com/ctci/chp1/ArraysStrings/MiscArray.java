package com.ctci.chp1.ArraysStrings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MiscArray {

	public static void main(String[] args) {

		// BinarySearch
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		binarySearch(arr, 5);
		int ans = binarySearchRecursion(arr, 0, arr.length - 1, 5);
		System.out.println("binarySearchRecursion = " + ans);

		// Comparator Example
		comparatorExample(new int[] { 15, 2 });
	}

	// Binary Search
	public static void binarySearch(int[] arr, int num) {
		Arrays.sort(arr);
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num == arr[mid]) {
				System.out.println("BS: Number found in Array at position =  " + mid);
				return;
			}
			if (num > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println("BS: Number not found in array");
	}

	public static int binarySearchRecursion(int[] arr, int start, int end, int num) {
		if (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == num) {
				return mid;
			} else if (num > arr[mid]) {
				return binarySearchRecursion(arr, mid + 1, end, num);
			} else {
				return binarySearchRecursion(arr, start, mid - 1, num);
			}
		}
		return -1;
	}

	// Comparator Example: Create largest number using all digits from array:
	// [15, 2] -> 215
	public static void comparatorExample(int[] arr) {
		String[] strArr = new String[arr.length];
		int index = 0;
		for (int num : arr) {
			strArr[index++] = String.valueOf(num);
		}
		Arrays.sort(strArr, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}
		});

		printStringArr(strArr, "comparatorExample(largest)");
		Arrays.sort(strArr, new SmallestNumberComparator());
		printStringArr(strArr, "comparatorExample(smallest)");

		Integer[] array = new Integer[] { 9, 8, 7, 6, 1, 2, 3, 4, 5 };
		Arrays.sort(array);
		printIntArr(array, "Int arr(Default)");
		Arrays.sort(array, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		});
		printIntArr(array, "Int arr(Desc)");

		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}

		});
		set.add(9);
		set.add(1);
		set.add(8);
		set.add(2);
		System.out.println("TreeSet with descending ordering" + set);
		System.out.println("Size of TreeSet = " + set.size());
	}

	// META
	public static void printStringArr(String[] strArr, String msg) {
		System.out.print(msg + " : ");
		for (int i = 0; i < strArr.length; i++) {
			System.out.print(strArr[i]);
			if (i < strArr.length - 1) {
				System.out.print("-");
			}
		}
		System.out.println("");
	}

	public static void printIntArr(Integer[] arr, String msg) {
		System.out.print(msg + " : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print("-");
			}
		}
		System.out.println("");
	}

}

// asc - a.compareTo(b)
// desc - b.compareTo(a)
class SmallestNumberComparator implements Comparator<String> {
	@Override
	public int compare(String a, String b) {
		return (a + b).compareTo(b + a);
	}
}
