package com.patterns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class P02_TwoPointers {

	public static void main(String[] args) {
		System.out.println("##Pattern 2 : Two Pointers");

		// #1 Two Sum
		System.out.println("#1 Two Sum");
		twoSum(new int[] { 1, 3, 4, 6, 7, 8 }, 7);
		twoSum_Map(new int[] { 1, 3, 4, 6, 7, 8 }, 7);

		// #2 Dedupe
		System.out.println("\n#2 Dedupe array");
		dedupe(new int[] { 1, 2, 2, 3 });
		System.out.println("#2.2 Remove given elem from arr (in-place)");
		removeElem_inplace(new int[] { 1, 2, 2, 3 }, 2);

		// #3 Square a sorted array
		System.out.println("\n#3 Square a sorted array");
		squareSortedArr(new int[] { -4, -1, 0, 2, 3 });

		// #4 Three Sum
		System.out.println("\n#4 Three Sum");
		threeSum(new int[] { -2, 0, 2, 2, 0, 1, 1, -1, -1 });

		// #5 Three Sum closed to target
		System.out.println("\n#5 Three Sum Closest");
		threeSumClosestTarget(new int[] { -1, 2, 1, -4 }, 5);

		// #6 Subarray with product less than target
		System.out.println("\n#6 Subarray with product less than target");
		findProductSubarr(new int[] { 2, 5, 3, 10 }, 30);

		// #7 Three Sum closed to target
		System.out.println("\n#7 Sort Colors (0s, 1s, 2s) in-place");
		sortColors(new int[] { 0, 0, 1, 1, 2, 2, 1, 2, 0 });

		// #8 Four Sum
		System.out.println("\n#8 Four Sum");
		fourSum(new int[] { -4, -1, 0, 2, 3, -1, 4, 0, });

		// #9 Compare string contains backspace(#)
		System.out.println("\n#9 Compare string contains backspace(#)");
		compareString("ab##abc", "aa#bb#cc#");

		// #10 Shortest Subarray to be Removed to Make Array Sorted
		System.out.println("\n#9 Mininum Window Sort size");
		findLengthOfShortestSubarray(new int[] { 1, 2, 3, 3, 4, 5 });
		findLengthOfShortestSubarray(new int[] { 1, 2, 3, 9, 9, 9, 3, 4, 5 });
		findLengthOfShortestSubarray(new int[] { 1, 2, 3, 10, 4, 2, 3, 5 }); // 3

		// SIMILAR LEETCODE
		System.out.println("\n###SIMILAR LEETCODE");

		// #1 mergekSortedLists
		System.out.println("#1: mergeKsortedLists");
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);

		ListNode l3 = new ListNode(2);
		l3.next = new ListNode(6);

		ListNode[] lists = new ListNode[] { l1, l2, l3 };
		ListNode n = mergeKsortedLists(lists);
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
		System.out.println();

		// #2 Remove Duplicate in unsorted list
		System.out.println("\n#2: Remove Duplicate in unsorted list");
		l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		l2.next.next.next = new ListNode(1);
		print(l2, "input");
		ListNode head = removeDuplicates(l2);
		print(head, "ouput");

		// #3 Reverse Nodes in k-Group
		System.out.println("\n#3 Reverse Nodes in k-Group");
		l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next = new ListNode(3);
		l2.next.next.next = new ListNode(4);
		l2.next.next.next.next = new ListNode(5);
		print(l2, "reverse  i/p ");
		head = reverseKGroup(l2, 2);
		print(head, "reverseKGroup");

		// #3 Swap Nodes in Pairs
		System.out.println("\n#4 Swap Nodes in Pairs");
		l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next = new ListNode(3);
		l2.next.next.next = new ListNode(4);
		l2.next.next.next.next = new ListNode(5);
		print(l2, "swap  i/p ");
		head = swapPair(l2);
		print(head, "reverseKGroup");

		System.out.println("\n#5 Odd Even grouping");
		l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next = new ListNode(3);
		l2.next.next.next = new ListNode(4);
		l2.next.next.next.next = new ListNode(5);
		print(l2, "i/p ");
		oddEvenList(l2);
	}

	// #1 Two Sum: RT=O(N) & space=O(1)
	// https://leetcode.com/problems/two-sum/
	public static void twoSum(int[] arr, int target) {
		int p = 0, q = arr.length - 1;
		Arrays.sort(arr);
		while (p < q) {
			int sum = arr[p] + arr[q];
			if (sum == target) {
				System.out.println("Sum Found: p=" + p + " & q=" + q);
				return;
			} else if (sum > target) {
				q--;
			} else {
				p++;
			}
		}
		System.out.println("Sum Not Found.");
	}

	public static void twoSum_Map(int[] arr, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(target - arr[i])) {
				System.out.println("Sum Found (Map): p=" + map.get(target - arr[i]) + " & q=" + i);
			}
			map.put(arr[i], i);
		}
	}

	// #2 Remove Duplicates: RT=O(N) & space=O(1): Input = 1, 2, 2, 3
	// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
	public static void dedupe(int[] arr) {
		Arrays.sort(arr);
		// int i = 1; // pointer for iterating
		int dedupeIndx = 1; // Dedupe index
		for (int i = 1; i < arr.length; i++) {
			if (arr[dedupeIndx - 1] != arr[i]) {
				arr[dedupeIndx] = arr[i];
				dedupeIndx++;
			}
		}
		for (int j = 0; j < dedupeIndx; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println("\ndedupeIndx length=" + dedupeIndx);
	}

	// #2.2 Remove given 'key' from array
	// https://leetcode.com/problems/remove-element/
	public static void removeElem_inplace(int[] arr, int key) {
		Arrays.sort(arr);
		int nextElem = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != key) {
				arr[nextElem++] = arr[i];
			}
		}
		for (int j = 0; j < nextElem; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println("\nEnd pointer=" + (nextElem - 1));
	}

	// #3 Square a sorted array: RT=O(N) & space=O(N)
	// https://leetcode.com/problems/squares-of-a-sorted-array/
	public static void squareSortedArr(int[] arr) {
		int p = 0, q = arr.length - 1;
		int[] sq = new int[arr.length];
		int high = arr.length - 1;
		while (p <= q) {
			int p_sq = arr[p] * arr[p];
			int q_sq = arr[q] * arr[q];

			if (p_sq > q_sq) {
				sq[high] = p_sq;
				p++;
			} else {
				sq[high] = q_sq;
				q--;
			}
			high--;
		}
		for (int n : sq)
			System.out.print(n + " ");
		System.out.println();
	}

	// #4 Three Sum: RT=O(NLogN + N^2) = O(N^2) & space=O(N)
	// checks added to avoid duplicate triplet
	// https://leetcode.com/problems/3sum/
	public static void threeSum(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int p1 = i + 1, p2 = nums.length - 1;
			while (p1 < p2) {
				int sum = nums[i] + nums[p1] + nums[p2];
				if (sum == 0) {
					res.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
					while (p1 < p2 && nums[p1] == nums[p1 + 1])
						p1++;
					while (p1 < p2 && nums[p2] == nums[p2 - 1])
						p2--;
					p1++;
					p2--;
				} else if (sum < 0) {
					p1++;
				} else {
					p2--;
				}
			}
		}
		System.out.println("res=" + res);
	}

	// #5 find ThreeSum close to target: RT=O(NLogN + N^2)=O(N^2) & space=O(N)
	// https://leetcode.com/problems/3sum-closest/
	public static void threeSumClosestTarget(int[] nums, int target) {
		List<Integer> res = new LinkedList();
		Arrays.sort(nums);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int p1 = i + 1, p2 = nums.length - 1;
			while (p1 < p2) {
				int sum = nums[i] + nums[p1] + nums[p2];
				if (sum < target) {
					p1++;
				} else {
					p2--;
				}
				if (Math.abs(target - sum) < Math.abs(target - result))
					result = sum;
			}
		}
		System.out.println("result = " + result);
	}

	// #6 Subarray with cont. product less than target: RT=O(N^3) & space=O(N)
	// https://leetcode.com/problems/subarray-product-less-than-k/
	// { 2, 5, 3, 10 }, 30 -> {2}, {5}, {3}, {10}, {2, 5}, {5, 3}
	public static void findProductSubarr(int[] nums, int target) {
		List<List<Integer>> res = new LinkedList<>();
		int start = 0, count = 0;
		int prod = 1;
		for (int end = 0; end < nums.length; end++) {
			prod *= nums[end];
			// shrink window
			while (prod >= target && start <= end) {
				prod /= nums[start];
				start++;
			}
			List<Integer> temp = new LinkedList<>();
			for (int i = start; i <= end; i++) {
				temp.add(nums[i]);
				res.add(temp);
			}

			// Say now we have {1,2,3} and add {4} into it. Apparently, the new
			// subarray introduced here are: {1,2,3,4}, {2,3,4}, {3,4}, {4}
			// which is the number of elements in the new list.
			count += end - start + 1; // correct
		}
		System.out.println("res=" + res); // this is incomplete
		System.out.println("count=" + count); // correct

		twoSumSmaller(new int[] { 2, 4, 5, 6 }, 15); // 3
		twoSumSmaller(new int[] { -2, 0, 1, 3 }, 2); // 2
	}

	// #6.2 3sum smaller than target
	// call 2SumSmaller inside for(i=0..)
	// https://leetcode.com/problems/3sum-smaller/
	public static void twoSumSmaller(int[] nums, int k) {
		System.out.print("=> 2SumSmaller :: ");
		int low = 0, high = nums.length - 1;
		Arrays.sort(nums);
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int p1 = i + 1, p2 = nums.length - 1;
			while (p1 <= p2) {
				int sum = nums[i] + nums[p1] + nums[p2];
				System.out.print(sum + "-");
				if (sum < k) {
					count += p2 - p1;
					p1++;
				} else {
					p2--;
				}
			}
		}
		System.out.println("\n2SumSmaller = " + count);
	}

	// #7 Sort Colors(Dutch National Flag): RT=O(N) space=O(1)
	// Given arr with 0s, 1s, 2s(sort in-place)
	// Solution: move 0s before low, 2s after high, 1s do nothing
	// hence in the end, all 1s will be b/w [0s, 2s]
	// https://leetcode.com/problems/sort-colors/
	public static void sortColors(int[] nums) {
		int low = 0, high = nums.length - 1;
		int start = 0;
		while (start <= high) {
			if (nums[start] == 0) {
				int temp = nums[low];
				nums[low] = nums[start];
				nums[start] = temp;
				start++;
				low++;
			} else if (nums[start] == 1) {
				start++;
			} else {
				int temp = nums[high];
				nums[high] = nums[start];
				nums[start] = temp;
				high--;
			}
		}
		for (int j = 0; j < nums.length; j++) {
			System.out.print(nums[j] + " ");
		}
		System.out.println();
	}

	// #8 Four Sum: RT=O(N^3) space=O(N)
	// https://leetcode.com/problems/4sum/
	public static void fourSum(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < nums.length; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1])
					continue;
				int p1 = j + 1, p2 = nums.length - 1;
				while (p1 < p2) {
					int sum = nums[i] + nums[j] + nums[p1] + nums[p2];
					if (sum == 0) {
						res.add(Arrays.asList(nums[i], nums[j], nums[p1], nums[p2]));
						p1++;
						p2--;
						while (p1 < p2 && nums[p1] == nums[p1 + 1])
							p1++;
						while (p1 < p2 && nums[p2] == nums[p2 - 1])
							p2--;
					}
					if (sum < 0) {
						p1++;
					} else {
						p2--;
					}
				}
			}
		}
		System.out.println("FourSum =" + res);
	}

	// #9 Compare String contains backspaces:RT=O(N) space=O(1)
	// https://leetcode.com/problems/backspace-string-compare/
	public static void compareString(String s1, String s2) {
		System.out.println("compare String, strs matches : " + getString(s1).equals(getString(s2)));
		System.out.println("compare String[BruteForce - stack], strs matches : " + compareStr(s1, s2));
	}

	public static String getString(String str) {
		String res = "";
		int count = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			if (c == '#') {
				count++;
			} else {
				if (count > 0) {
					count--;
				} else {
					res += c;
				}
			}
		}
		return res;
	}

	public static boolean compareStr(String s1, String s2) {
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();

		for (char c : s1.toCharArray()) {
			if (c == '#' && !stack1.isEmpty()) {
				stack1.pop();
			} else {
				stack1.push(c);
			}
		}

		for (char c : s2.toCharArray()) {
			if (c == '#' && !stack2.isEmpty()) {
				stack2.pop();
			} else {
				stack2.push(c);
			}
		}

		if (stack1.size() != stack2.size())
			return false;

		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			if (stack1.pop() != stack2.pop())
				return false;
		}
		if (stack1.size() == stack2.size())
			return true;
		return false;
	}

	// #10 Mininum Window Sort
	// Shortest Subarray to be Removed to Make Array Sorted
	// https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
	public static void findLengthOfShortestSubarray(int[] nums) {
		int p1 = 0;
		while (p1 < nums.length - 1 && nums[p1] <= nums[p1 + 1])
			p1++;
		if (p1 == nums.length - 1) {
			System.out.println("Sum = 0");
			return;
		}
		int p2 = nums.length - 1;
		while (p2 > p1 && nums[p2] >= nums[p2 - 1])
			p2--;

		int result = Math.min(nums.length - 1 - p1, p2);

		int i = 0;
		int j = p2;
		while (i <= p1 && j < nums.length) {
			if (nums[i] <= nums[j]) {
				result = Math.min(result, j - i - 1);
				i++;
			} else {
				j++;
			}
		}
		System.out.println("Mininum Window Sort size = " + result);
	}

	// SIMILAR LEETCODE
	// #1 merge k sorted list: RT=O(klogN) k-lists
	// https://leetcode.com/problems/merge-k-sorted-lists/
	public static ListNode mergeKsortedLists(ListNode[] lists) {
		return partition(lists, 0, lists.length - 1);
	}

	public static ListNode partition(ListNode[] lists, int start, int end) {
		if (start == end) {
			return lists[start];
		}
		if (start < end) {
			int mid = (start + end) / 2;
			ListNode l1 = partition(lists, start, mid);
			ListNode l2 = partition(lists, mid + 1, end);
			return merge(l1, l2);
		}
		return null;
	}

	public static ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = merge(l1.next, l2);
			return l1;
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
	}

	// #2 Remove Duplicates[inclusive] From an Unsorted Linked List
	public static ListNode removeDuplicates(ListNode head) {

		Map<Integer, Integer> map = new HashMap<>();
		ListNode curr = head;
		while (curr != null) {
			map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
			curr = curr.next;

		}
		System.out.println("map=" + map);

		ListNode dummy = new ListNode();
		dummy.next = head;

		curr = dummy;
		while (curr.next != null) {
			if (map.get(curr.next.val) > 1) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}

		return dummy.next;
	}

	// #3 Reverse Nodes in k-group
	// https://leetcode.com/problems/reverse-nodes-in-k-group/
	// https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11440/Non-recursive-Java-solution-and-idea
	public static ListNode reverseKGroup(ListNode head, int k) {
		// print(reverseKGroup_(head, k), "reverse::");
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode start = dummy;
		int i = 0;
		while (head != null) {
			i++;
			if (i % k == 0) {
				start = reverse(start, head.next);
				head = start.next;
			} else {
				head = head.next;
			}
		}
		return dummy.next;
	}

	// input with k=2
	// O->1->2->3->4->5->6
	// s........e
	// After:: start = reverse(start, head.next)
	// O->2->1->3->4->5->6
	// ......s..e
	public static ListNode reverse(ListNode start, ListNode end) {
		ListNode head = start.next;
		ListNode first = start.next;
		ListNode prev = start;

		while (head != end) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		start.next = prev;// prev=*newHead
		first.next = end; // end =*head
		System.out.println(start.val + "-" + prev.val);
		System.out.println(first.val + "--" + head.val);
		return first;
	}

	// #4 Swap Nodes in Pairs
	// https://leetcode.com/problems/swap-nodes-in-pairs/
	public static ListNode swapPair(ListNode head) {
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode curr = dummy;
		while (curr.next != null && curr.next.next != null) {
			ListNode first = curr.next;
			ListNode second = curr.next.next;
			first.next = second.next;
			curr.next = second;
			curr.next.next = first;

			curr = curr.next.next;
		}
		return dummy.next;
	}

	// #5: 2 pointer pair swap
	// https://leetcode.com/problems/odd-even-linked-list/
	public static void oddEvenList(ListNode head) {
		ListNode odd = head, even = head.next, evenHead = even;
		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		print(head, "OddEvenList grouping");
	}

	// #META
	public static void print(ListNode h, String msg) {
		System.out.print(msg + " : ");
		while (h != null) {
			System.out.print(h.val + "-");
			h = h.next;
		}
		System.out.println();
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}

	ListNode() {

	}
}