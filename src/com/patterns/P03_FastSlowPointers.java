package com.patterns;

public class P03_FastSlowPointers {

	public static void main(String[] args) {
		System.out.println("##Pattern 03 : Fast & Slow Pointer");

		// #1 Check if cycle exist in LinkedList: RT=O(N) n-nodes; space=O(1)
		System.out.println("\n#1: hasCycle");
		ListNode listNode = initList_Cyclic();
		System.out.println("hasCycle (T) = " + hasCycle(listNode));
		listNode = initList_Odd();
		System.out.println("hasCycle (F) = " + hasCycle(listNode));

		// #2 Start of LinkedList Cycle
		System.out.println("\n#2: Start of LinkedList Cycle");
		listNode = initList_Cyclic();
		System.out.println("startOfListCycle=" + startOfListCycle(listNode));

		// #3: Happy Number
		System.out.println("\n#3: HappyNumber");
		System.out.println("happyNumber (T)=" + happyNumber(19));
		System.out.println("happyNumber (T)=" + happyNumber(23));
		System.out.println("happyNumber (F)=" + happyNumber(12));

		// #4: Middle of List
		System.out.println("\n#4: Middle of List");
		listNode = initList_Odd();
		System.out.println("middleOfList=" + middleOfList(listNode));
		listNode = initList_Odd();
		deleteMid(listNode);

		// #5: Palindrome LinkedList
		System.out.println("\n#5: Palindrome LinkedList");
		listNode = initList_Palindrome();
		System.out.println("isPalindrome(T)=" + palindromeList(listNode));
		listNode = initList_Odd();
		System.out.println("isPalindrome(F)=" + palindromeList(listNode));

		// #6: Rearrange a LinkedList
		System.out.println("\n#6: Rearrange a LinkedList");
		listNode = initList_Odd();
		printList(listNode, "Before reorder List");
		reorderList(listNode);
		printList(listNode, "reorder List");

		// #7: Cycle in a Circular Array
		System.out.println("\n#7: Cycle in a Circular Array");
		System.out.println("circularArrayLoop(T)=" + circularArrayLoop(new int[] { 2, -1, 1, 2, 2 }));
		System.out.println("circularArrayLoop(F)=" + circularArrayLoop(new int[] { -1, 2 }));

		// LEETCODE
		// #1 Rotate List
		// https://leetcode.com/problems/rotate-list/
		System.out.println("\n#1: Rotate List");
		ListNode n = new ListNode(1);
		n.next = new ListNode(2);
		n.next.next = new ListNode(3);
		n.next.next.next = new ListNode(4);
		n.next.next.next.next = new ListNode(5);
		n = rotateRight(n, 2);
		printList(n, "rotate");

		// #2 Sort List
		// https://leetcode.com/problems/sort-list/
		System.out.println("\n#2 Sort List");
		printList(n, "sort: before");
		n = sortList(n);
		printList(n, "sort: after");

		// #3 Merge K sorted lists
		System.out.println("\n#3 Merge K sorted lists");
		ListNode n1 = new ListNode(1);
		n1.next = new ListNode(5);

		ListNode n2 = new ListNode(2);
		n2.next = new ListNode(4);

		ListNode n3 = new ListNode(3);
		n = mergeKLists(new ListNode[] { n1, n2, n3 });
		printList(n, "mergeKLists");

	}

	// #1 Check if cycle exist in LinkedList: RT=O(N) n-nodes; space=O(1)
	// https://leetcode.com/problems/linked-list-cycle/
	public static boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				break;
		}
		if (fast == null || fast.next == null)
			return false;

		// to get len, start & count till you reach slow
		ListNode n = head;
		int len = 0;
		while (n != null) {
			if (n == slow) {
				break;
			}
			len++;
			n = n.next;
		}
		System.out.println("CycleLen=" + len);
		return true;
	}

	// #2 Start of LinkedList Cycle: RT=O(N) space=O(1)
	// LOGIC:: There is a cycle in linked list if there is some node in the list
	// that can be reached again by continuously following the 'next' pointer
	// https://leetcode.com/problems/linked-list-cycle-ii/
	public static int startOfListCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}
		if (fast == null || fast.next == null)
			return -1;

		// iterate till some node is reached again
		fast = head;
		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast.val;
	}

	// #3: Happy Number: RT=O(logN); space=O(1)
	// https://leetcode.com/problems/happy-number/
	public static boolean happyNumber(int n) {
		int slow = n, fast = n;
		while (true) {
			slow = findSq(slow);
			fast = findSq(findSq(fast));

			if (slow == fast) {
				System.out.println("slow=fast=" + fast);
				break;
			}
		}
		return slow == 1;
	}

	public static int findSq(int num) {
		int sum = 0;
		while (num > 0) {
			int digit = num % 10;
			sum += digit * digit;
			num = num / 10;
		}
		return sum;
	}

	// #4: Middle of List: RT=O(N); space=O(1)
	// https://leetcode.com/problems/middle-of-the-linked-list/
	public static int middleOfList(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = dummy, fast = dummy;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
			slow = slow.next;
		}
		return slow.val;
	}

	// #4.2: Delete mid elem and return head
	// https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
	public static void deleteMid(ListNode head) {
		if (head.next == null)
			return;
		ListNode temp = null;
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			temp = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		temp.next = temp.next.next;
		printList(head, "delete mid elem");
	}

	// #5: Palindrome LinkedList
	// https://leetcode.com/problems/palindrome-linked-list/
	public static boolean palindromeList(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		slow = reverse(slow);
		fast = head;
		while (slow != null) {
			if (slow.val != fast.val) {
				return false;
			}
			fast = fast.next;
			slow = slow.next;
		}
		return true;
	}

	public static ListNode reverse(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}

	// #6: Rearrange a LinkedList: RT=O(N); space=O(1)
	// L1 -> L2 -> L3......Ln-2 -> Ln-1 -> Ln
	// 1) split at mid 2) reverse 2nd half 3) merge 2 list
	// https://leetcode.com/problems/reorder-list/
	public static ListNode reorderList(ListNode head) {
		ListNode head2 = split(head);
		head2 = reverse(head2);
		merge(head, head2);
		return head;
	}

	public static ListNode split(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode head2 = slow.next;
		slow.next = null;
		return head2;
	}

	public static void merge(ListNode head, ListNode head2) {
		ListNode curr1 = head, curr2 = head2;
		while (curr1 != null && curr2 != null) {
			ListNode next1 = curr1.next;
			ListNode next2 = curr2.next;

			curr1.next = curr2;
			curr2.next = next1;

			curr1 = next1;
			curr2 = next2;
		}
	}

	// #7: Cycle in a Circular Array
	public static boolean circularArrayLoop(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			boolean forward = nums[i] >= 0;
			int slow = i, fast = i;

			while (true) {
				slow = findNextIndex(nums, forward, slow);
				fast = findNextIndex(nums, forward, fast);

				if (fast != -1) {
					fast = findNextIndex(nums, forward, fast);
				}

				if (slow == -1 || fast == -1 || slow == fast) {
					break;
				}
			}
			if (slow != -1 && slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static int findNextIndex(int[] nums, boolean forward, int index) {
		boolean direction = nums[index] >= 0;

		if (forward != direction) {
			return -1;
		}
		int nextIndex = (index + nums[index]) % nums.length;
		if (nextIndex < 0) {
			nextIndex += nums.length;
		}
		if (nextIndex == index) {
			nextIndex = -1;
		}
		return nextIndex;
	}

	// LEETCODE
	// #1 Rotate List
	// https://leetcode.com/problems/rotate-list/
	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;
		int size = 0;
		while (fast.next != null) {
			fast = fast.next;
			size++;
		}
		for (int i = size - (k % size); i > 0; i--) {
			slow = slow.next; // before rotate
		}

		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}

	// #2 Sort List
	// 1) Split @ prev of mid 2) sort 2-list 3) merge then together
	// https://leetcode.com/problems/sort-list/
	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		// Step1: split list into 2 parts
		ListNode fast = head, slow = head, prev = null;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		// Step2: sort the 2 list
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);
		// Step3: merge the 2 list
		return mergeList(l1, l2);
	}

	public static ListNode mergeList(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(), p = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;
		}
		if (l1 != null) {
			p.next = l1;
		}
		if (l2 != null) {
			p.next = l2;
		}
		return dummy.next;
	}

	// #3 Merge K sorted lists
	// https://leetcode.com/problems/merge-k-sorted-lists/
	public static ListNode mergeKLists(ListNode[] lists) {
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

			return mergeLists(l1, l2);
		}
		return null;
	}

	public static ListNode mergeLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeLists(l1, l2.next);
			return l2;
		}
	}

	// META
	public static void printList(ListNode head, String msg) {
		System.out.print(msg + " : ");
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static ListNode initList_Odd() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		return listNode;
	}

	public static ListNode initList_Even() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		listNode.next.next.next.next.next = new ListNode(6);
		return listNode;
	}

	public static ListNode initList_Palindrome() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(3);
		listNode.next.next.next.next = new ListNode(2);
		listNode.next.next.next.next.next = new ListNode(1);
		return listNode;
	}

	public static ListNode initList_Cyclic() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = listNode.next;
		return listNode;
	}
}
