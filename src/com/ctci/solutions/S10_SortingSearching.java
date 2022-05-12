package com.ctci.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class S10_SortingSearching {

	public static void main(String[] args) {
		System.out.println("###Sorting & Searching###");
		// #1 Sorted Merge
		System.out.println("\n#1 Sorted Merge");
		int[] a = new int[] { 1, 3, 5, 0, 0, 0 };
		int[] b = new int[] { 2, 4, 6 };
		sortedMerge(a, b, 3, 3);
		// #2 Group Anagrams
		System.out.println("\n#2 Group Anagrams");
		groupAnagram(new String[] { "ate", "bat", "eat", "nat", "tan", "tea" });
		// #3 Search rotated array
		System.out.println("\n#3.0 Binary Search");
		System.out.println("binarySearch (4) : " + binarySearch(new int[] { 1, 2, 3, 4, 5 }, 5));
		System.out.println("binarySearch (-1) : " + binarySearch(new int[] { 1, 2, 3, 4, 5 }, 9));

		System.out.println("\n#3 Search rotated array");
		searchRotate();
		// #4 Sorted Search, No size
		// given an DS Listy without size;
		// we can search for index - 2, 4, 8, 16.. so RT=O(logN)
		// once index is found, do binarySearch; if arr[mid]==-1; treat as high;
		// i.e we reached end

		// #5 Sparse Search
		System.out.println("\n#3 Sparse Search");
		String[] strs = new String[] { "app", "ball", "bat", "cat", "dog" };
		System.out.println("SimpleBinarySearch (1)=" + simpleBinarySearch(strs, "ball"));
		strs = new String[] { "app", "", "", "ball", "", "", "", "bat", "cat", "dog" };
		System.out.println("BinarySearch (3)=" + binarySearch(strs, "ball", 0, strs.length - 1));
		System.out.println("BinarySearch (0)=" + binarySearch(strs, "app", 0, strs.length - 1));
		System.out.println("BinarySearch (9)=" + binarySearch(strs, "dog", 0, strs.length - 1));
		System.out.println("BinarySearch (8)=" + binarySearch2(strs, "cat"));
		System.out.println("BinarySearch (9)=" + binarySearch2(strs, "dog"));
		System.out.println("BinarySearch (-1)=" + binarySearch2(strs, "dogy"));

		// #6 Sort Big Files: sort a file of 20GB file(having 1 str per line)
		// Soln: Don't bring all data to memory; Divide file into chunks of xMB,
		// x -> available memory; each chunk is sorted & saved back to file sys.
		// once all chunk are sorted, we merge then 1-by-1.
		// Finally we have a sorted file: This is External Sort

		// #7 Missing Int:

		// #8 Find Duplicates of array with elements 1 to N
		System.out.println("\n#8 Find Duplicates: input 1 to N");
		cyclicSort(new int[] { 5, 4, 3, 2, 1 });
		System.out.println("duplicate in array (only 1) (4)=> " + findDuplicate(new int[] { 1, 4, 4, 3, 2 }));
		System.out.println("duplicate in array (only 1) (3)=> " + findDuplicate(new int[] { 2, 1, 3, 3, 5, 4 }));
		System.out.println("duplicate in array (only 1) (4)=> " + findDuplicate(new int[] { 2, 4, 1, 4, 4 }));
		System.out.print("duplicates (4,5,6)=> ");
		findDuplicates(new int[] { 6, 3, 4, 4, 5, 5, 6 });

		// #9 Search sorted matrix
		System.out.println("\n#9 Search sorted Matrix");
		System.out.println("search=" + searchSortedMat(new int[][] { { 1, 4 }, { 2, 5 } }, 5));

		// #11 Peaks & Valleys
		System.out.println("\n#11 Generate Peaks & Valleys");
		peaksValleys(new int[] { 5, 3, 1, 2, 4 });
		peaksValleys2(new int[] { 5, 3, 1, 2, 4 });

	}

	// #1 Sorted Merge: RT=O(N)
	// A has enough space for B
	public static void sortedMerge(int[] a, int[] b, int lastA, int lastB) {
		int indexA = lastA - 1;
		int indexB = lastB - 1;
		int indexMerged = lastA + lastB - 1;
		while (indexB >= 0) {
			if (indexA >= 0 && a[indexA] > b[indexB]) {
				a[indexMerged] = a[indexA];
				indexA--;
			} else {
				a[indexMerged] = b[indexB];
				indexB--;
			}
			indexMerged--;
		}
		printArr(a, "mergeArray");
	}

	// #2 Group Anagrams:
	// Write a method to sort an array of strs, so that all anagrams are next to
	// each others
	public static void groupAnagram(String[] strs) {
		Map<String, LinkedList<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] ch = s.toCharArray();
			Arrays.sort(ch);
			String key = String.valueOf(ch);
			if (!map.containsKey(key)) {
				map.put(key, new LinkedList<>());
			}
			map.get(key).add(s);
		}
		int index = 0;
		for (Map.Entry<String, LinkedList<String>> entry : map.entrySet()) {
			List<String> list = entry.getValue();
			for (String s : list) {
				strs[index] = s;
				index++;
			}
		}
		printArr(strs, "groupAnagram");
	}

	// #3.0 Binary Search
	public static int binarySearch(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start <= end) {
			// int mid = start+(end-start)/2;
			int mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	// #3 Search rotated array
	public static void searchRotate() {
		int[] arr = new int[] { 7, 6, 5, 1, 2, 3, 4 };
		int target = 6;
		System.out.println("searchRotated [target=" + target + "]=" + searchRotated(arr, target, 0, arr.length - 1));
		System.out.println("searchRotated(sorted) [target=" + target + "]=" + searchRotated(arr, target));

		// Search in Rotated Sorted Array
		arr = new int[] { 2, 5, 6, 0, 0, 1, 2 };
		target = 6;
		System.out.println("searchRotated(sorted) [target=" + target + "]=" + searchRotated(arr, target));
		System.out.println("searchRotated(sorted-with dups) [target=" + target + "]=" + searchRotateDups(arr, target));
		arr = new int[] { 2, 5, 6, 0, 0, 1, 2 };
		target = 0;
		System.out.println("searchRotated(sorted) [target=" + target + "]=" + searchRotated(arr, target));
		System.out.println("searchRotated(sorted-with dups) [target=" + target + "]=" + searchRotateDups(arr, target));
		arr = new int[] { 1, 0, 1, 1, 1, 1 };
		target = 0;
		System.out.println("searchRotated(sorted-with dups) [target=" + target + "]=" + searchRotateDups(arr, target));

	}

	// https://leetcode.com/problems/search-in-rotated-sorted-array/
	public static int searchRotated(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == target) {
				return mid;
			}
			if (arr[start] <= arr[mid]) {
				if (target < arr[mid] && target >= arr[start]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {
				if (target > arr[mid] && target <= arr[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
	}

	// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
	public static int searchRotateDups(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == target) {
				return mid;
			}
			if (arr[start] < arr[mid]) {
				if (target < arr[mid] && target >= arr[start]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else if (arr[start] > arr[mid]) {
				if (target > arr[mid] && target <= arr[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				start++;
			}
		}
		return -1;
	}

	public static int searchRotated(int[] arr, int target, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (arr[mid] == target)
			return mid;
		if (arr[start] < arr[mid]) {// left is normally ordered
			if (target >= arr[start] && target < arr[mid]) {
				return searchRotated(arr, target, start, mid - 1);// search left
			} else {
				return searchRotated(arr, target, mid + 1, end);// search right
			}
		} else if (arr[start] > arr[mid]) {// right is normally ordered
			if (target > arr[mid] && target <= arr[end]) {
				return searchRotated(arr, target, mid + 1, end);// search right
			} else {
				return searchRotated(arr, target, start, mid - 1);// search left
			}
		} else if (arr[start] == arr[mid]) {// left & right repeating
			if (arr[mid] != arr[end]) {// if right is diff, search right
				return searchRotated(arr, target, mid + 1, end);
			} else {// search left
				int result = searchRotated(arr, target, start, mid - 1);
				if (result == -1) {// search right, if left = -1
					return searchRotated(arr, target, mid + 1, end);
				} else {
					return result;
				}
			}
		}

		return -1;
	}

	// #5 Sparse Search; RT=O(logN); Worst O(N)
	// Simple binary search for string array
	public static int simpleBinarySearch(String[] str, String s) {
		int start = 0, end = str.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (str[mid].compareTo(s) == 0) {
				return mid;
			}
			if (str[mid].compareTo(s) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	// modified binarySearch to handle str[] with empty string
	// We fix the arr[mid] comparison; if mid="", move to nearest non-empty str
	public static int binarySearch(String[] strs, String s, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (strs[mid].isEmpty()) {
			int left = mid - 1;
			int right = mid + 1;
			while (true) {
				if (left < start && right > end) {
					return -1;
				}
				if (left >= start && !strs[left].isEmpty()) {
					mid = left;
					break;
				}
				if (right <= end && !strs[right].isEmpty()) {
					mid = right;
					break;
				}
				right++;
				left--;
			}
		}
		if (s.compareTo(strs[mid]) == 0) {
			return mid;
		} else if (strs[mid].compareTo(s) < 0) {
			return binarySearch(strs, s, mid + 1, end);
		} else
			return binarySearch(strs, s, start, mid - 1);

	}

	public static int binarySearch2(String[] strs, String s) {
		int start = 0, end = strs.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (strs[mid].isEmpty()) {
				int left = mid - 1;
				int right = mid + 1;
				while (true) {
					if (left < start && right > end) {
						return -1;
					}
					if (left >= start && !strs[left].isEmpty()) {
						mid = left;
						break;
					}
					if (right <= end && !strs[right].isEmpty()) {
						mid = right;
						break;
					}
					left--;
					right++;
				}
			}
			if (strs[mid].compareTo(s) == 0) {
				return mid;
			}
			if (strs[mid].compareTo(s) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}

		}
		return -1;
	}

	// #8 Find Duplicates of array with elements 1 to N
	public static void cyclicSort(int[] nums) {
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
		System.out.print("Cyclic Sort:: ");
		for (int n : nums) {
			System.out.print(n + " ");
		}
		System.out.println();

	}

	public static int findDuplicate(int[] nums) {
		int i = 0;
		while (i < nums.length) {

			int j = nums[i] - 1;
			if (nums[i] == i + 1) {
				i++;
			}
			if (nums[i] != nums[j]) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			} else {
				return nums[i];
			}

		}
		return -1;

	}

	public static void findDuplicates(int[] nums) {
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
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				System.out.print(nums[i] + " ");
			}
		}
		System.out.println();
	}

	// #9 Sorted Matrix Search
	public static boolean searchSortedMat(int[][] mat, int target) {
		int row = 0, col = mat[0].length - 1;
		while (row <= col) {
			if (mat[row][col] == target) {
				return true;
			} else if (mat[row][col] < target) {
				row++;
			} else {
				col--;
			}
		}
		return false;
	}

	// #11 Peaks & Valleys: RT=O(nlogn)
	// logic_1: sort -> swap pair
	public static void peaksValleys(int[] arr) {
		Arrays.sort(arr);
		for (int i = 1; i < arr.length; i += 2) {
			int temp = arr[i];
			arr[i] = arr[i - 1];
			arr[i - 1] = temp;
		}
		printArr(arr, "Peaks & Valleys");
	}

	// logic_2:find big among 3, swap with i
	public static void peaksValleys2(int[] arr) {
		for (int i = 1; i < arr.length; i += 2) {
			int bigIndex = maxIndex(arr, i - 1, i, i + 1);
			System.out.println(bigIndex + " " + i);
			if (i != bigIndex) {
				int temp = arr[i];
				arr[i] = arr[bigIndex];
				arr[bigIndex] = temp;
			}
		}
		printArr(arr, "Peaks & Valleys2");
	}

	public static int maxIndex(int[] arr, int a, int b, int c) {
		int aVal = a >= 0 && a < arr.length ? arr[a] : Integer.MIN_VALUE;
		int bVal = b >= 0 && b < arr.length ? arr[b] : Integer.MIN_VALUE;
		int cVal = c >= 0 && c < arr.length ? arr[c] : Integer.MIN_VALUE;

		int maxVal = Math.max(Math.max(aVal, bVal), cVal);
		if (aVal == maxVal)
			return a;
		else if (bVal == maxVal)
			return b;
		else
			return c;
	}

	// META
	public static void printArr(int[] arr, String msg) {
		System.out.print(msg + " :");
		for (int n : arr)
			System.out.print(n + " ");
		System.out.println();
	}

	public static void printArr(String[] arr, String msg) {
		System.out.print(msg + " :");
		for (String s : arr)
			System.out.print(s + " ");
		System.out.println();
	}

}
