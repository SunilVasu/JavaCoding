package com.ctci.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class Misc01_Array {

	public static void main(String[] args) {
		System.out.println("#MISC Array");
		// #1 BinarySearch
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		binarySearch(arr, 5);
		int ans = binarySearchRecursion(arr, 0, arr.length - 1, 5);
		System.out.println("binarySearchRecursion = " + ans);

		// #2 Comparator Example
		comparatorExample(new int[] { 15, 2 });

		// #3 de-duplicate
		dedup();

		// #4 Find Median of 2 sorted array O(log(n+m))
		System.out.println("\n#4 Median of Sorted Array");
		// 1 2 3 4 5 8 9 10 15 -> Median : 5
		System.out.println("Median of Sorted Array : "
				+ findMedianSortedArray(new int[] { 1, 5, 8, 10 }, new int[] { 2, 3, 4, 9, 15 }));
		// 1 2 3 4 5 6 7 8 -> Median : (4+5)/2 = 4.5
		System.out.println("Median of Sorted Array : "
				+ findMedianSortedArray(new int[] { 1, 3, 5, 7 }, new int[] { 2, 4, 6, 8 }));

		// #5 HashMap
		testMap();

		// #6 Sort Array by Increasing Frequency
		frequencySort();

		// #7 Find the 3rd largest num
		System.out.println("\n#7 Find the 3rd largest num in Array");
		System.out.println("thirdLargest=" + thirdLargest(new int[] { 1, 5, 4, 3, 2 }));

	}

	// #1 Binary Search
	public static void binarySearch(int[] arr, int num) {
		System.out.println("\n#1 Binary Search");
		Arrays.sort(arr);
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num == arr[mid]) {
				System.out.println("BS: Number found in Array at position =  " + mid);
				return;
			}
			if (arr[mid] < num) {
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

	// #2 Comparator Example: Create largest number using all digits from array:
	// [15, 2] -> 215
	public static void comparatorExample(int[] arr) {
		System.out.println("\n#2 Comparator");
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
		printIntArr(array, "Int arr(Desc) - with comparator");

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

		Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		});
		queue.add(5);
		queue.add(1);
		queue.add(9);
		queue.add(2);
		System.out.println("Priority Queue: " + queue + "   peek():" + queue.peek() + "   poll():" + queue.poll()
				+ "   remove():" + queue.remove());

		Queue<String> pQueue = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		pQueue.add("5");
		pQueue.add("1");
		pQueue.add("9");
		pQueue.add("2");
		System.out.println("Priority Queue: " + pQueue + "   peek():" + pQueue.peek());

		String s1 = "a", s2 = "b";
		System.out.println("s2>s1 :" + s2.compareTo(s1));
		System.out.println("s2==s2 :" + s2.compareTo(s2));
		System.out.println("s2<s1 :" + s1.compareTo(s2));

		List<Integer> list = new LinkedList<>();
		list.add(7);
		list.add(5);
		list.add(1);
		list.add(9);
		Collections.sort(list);
		System.out.println("Collections.sort() list(Default): " + list);
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("Collections.sort() list(with reverseorder): " + list);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		});
		System.out.println("Collections.sort() list(with comparator): " + list);

	}

	// #3 Dedup
	public static void dedup() {
		System.out.println("\n#3 Dedup - 1, 1, 1, 2, 3, 3, 3, 3, 4");
		int[] arr = new int[] { 1, 1, 1, 2, 3, 3, 3, 3, 4 };
		// Remove duplicate (1 allowed)
		int i = 0;
		for (int n : arr) {
			if (i < 1 || n > arr[i - 1])
				arr[i++] = n;
		}
		System.out.print("Removed Duplicate (1 allowed) : ");
		for (int j = 0; j < i; j++) {
			System.out.print(arr[j] + " ");
		}
		// Remove duplicate (2 allowed)
		arr = new int[] { 1, 1, 1, 2, 3, 3, 3, 3, 4 };
		i = 0;
		for (int n : arr) {
			if (i < 2 || n > arr[i - 2])
				arr[i++] = n;
		}
		System.out.print("\nRemoved Duplicate (2 allowed) : ");
		for (int j = 0; j < i; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.print("\n");
	}

	// #4 Find Median of 2 sorted array: RT=O(log(min(x,y)))
	// partitionX + partitionY = (x+y+1)/2; Similar to Binary Search
	// Found : maxLeftX <= minRightY && maxLeftY <= minRightX
	// else if maxLeftX > minRigthY : move towards left in X
	// else : move towards right in X
	public static double findMedianSortedArray(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length)
			return findMedianSortedArray(nums2, nums1);

		int x = nums1.length;
		int y = nums2.length;

		int low = 0;
		int high = x;
		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX;
			System.out.println("partitionX: " + partitionX + "    partitionY: " + partitionY);

			int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
			int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];

			int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
			int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];

			System.out.println("maxLeftX: " + maxLeftX + "    minRightX: " + minRightX);
			System.out.println("maxLeftY: " + maxLeftY + "    minRightY: " + minRightY);

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if ((x + y) % 2 == 0) {
					return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (Math.max(maxLeftX, maxLeftY));
				}
			} else if (maxLeftX > minRightY) {
				high = partitionX - 1;
			} else {
				low = partitionX + 1;
			}
		}

		return -1;
	}

	// #5 Map Test
	public static void testMap() {
		System.out.println("\n#5 MapTest");
		System.out.println("groupAnagrams: " + groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "bat" }));

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "AAA");
		map.put(2, "BBB");
		map.put(3, "CCC");
		map.put(4, "DDD");

		System.out.println("Map: " + map);
		System.out.println("Map values:" + map.values());
		System.out.println("Map keys:" + map.keySet());
		System.out.println("Map get:" + map.get(1));
		Object key = new Integer(1);
		System.out.println("Map getOrDefault:" + map.getOrDefault(10, "-1"));
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("entry: " + entry);
		}
	}

	// #5.1 group anagrams
	public static List<List<String>> groupAnagrams(String[] strs) {

		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] ch = s.toCharArray();
			Arrays.sort(ch);
			String key = String.valueOf(ch);
			if (!map.containsKey(key))
				map.put(key, new ArrayList<>());
			map.get(key).add(s);
		}
		return new LinkedList<>(map.values());
	}

	// #6 Sort Array by Increasing Frequency
	public static void frequencySort() {
		System.out.println("\n#6 Sort Array by Increasing Frequency");

		int[] nums = new int[] { 1, 1, 1, 4, 2, 2, 3 };
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list,
				(a, b) -> a.getValue() == b.getValue() ? b.getKey() - a.getKey() : a.getValue() - b.getValue());
		System.out.println("Sorted List (based on freq): " + list);

		int[] res = new int[nums.length];
		int index = 0;
		for (Map.Entry<Integer, Integer> entry : list) {
			int key = entry.getKey();
			int count = entry.getValue();
			while (count > 0) {
				res[index++] = key;
				count--;
			}
		}
		printArr(res, "Arr sorted based on freq");
	}

	// #7 Find the 3rd largest elem from array
	public static int thirdLargest(int[] arr) {
		int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > first) {
				second = first;
				third = second;
				first = arr[i];
			} else if (arr[i] > second) {
				third = second;
				second = arr[i];
			} else if (arr[i] > third) {
				third = arr[i];
			}
		}
		return third;
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

	public static void printArr(int[] arr, String msg) {
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
