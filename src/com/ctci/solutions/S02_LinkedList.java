package com.ctci.solutions;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class S02_LinkedList {
	// Chp 2: LinkedList
	public static void main(String[] args) {
		ListNode head = initListNode();
		System.out.print("Unsorted: ");
		printListNode(head);
		head = initListNodeSorted();
		System.out.print("Sorted:   ");
		printListNode(head);
		// 2.1 Remove Dups
		removeDups(head);
		head = initListNodeSorted();
		removeDups2(head);

		// 2.2 nth to last
		head = initListNodeSorted();
		nthToLast(head, 2);

		// 2.4 Delete node : delete the current node
		head = initListNode();
		deleteNode(head.next.next);
		System.out.print("\n#2.3 Delete Node:\nDelete the current Node (3rd):");
		printListNode(head);

		// 2.4 Partition
		head = initListNode();
		partition(head, 4);
		head = initListNode();
		partition2(head, 4);

		// 2.5 Sum Lists
		System.out.println("\n#2.5 Sum Lists: ");
		System.out.println("Sum (Reverse Order): 1->5 + 2->5 : 51+52=103 : 3->0->1");
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(5);
		ListNode l2 = new ListNode(2);
		l2.next = new ListNode(5);
		ListNode sum = addLists(l1, l2, 0);
		printListNode(sum);
		System.out.println("Sum2 (In Order): 1->5->5 + 2->5 : 155+25=180 : 1->8->0");
		l1.next.next = new ListNode(5);
		ListNode sum2 = addLists2(l1, l2);
		printListNode(sum2, "Sum2");

		// 2.6 Palindrome
		System.out.println("\n#2.6 Palindrome: ");
		head = initListNode();
		isPalindrome(head);
		head = initPalindromeList();
		isPalindrome(head);
		System.out.println("\n#2.6 Palindrome2: ");
		head = initListNode();
		isPalindrome2(head);

		// 2.7 Intersection
		System.out.println("\n#2.7 Intersection: ");
		head = initListNode();
		ListNode node = new ListNode(10);
		node.next = new ListNode(20);
		node.next.next = head.next.next.next;
		printListNode(head);
		printListNode(node);
		intersection(head, node);

		// 2.8 Loop Detection
		System.out.println("\n#2.8 Loop Detection: ");
		ListNode n = new ListNode(1);
		n.next = new ListNode(2);
		n.next.next = new ListNode(3);
		n.next.next.next = new ListNode(4);
		n.next.next.next.next = n.next;
		System.out.println("Loop Detected : " + loopDetection(n));

		// Misc 1: Merge Lists
		l1 = initListNodeSorted();
		l2 = initListNodeSorted();
		ListNode mergeList = mergeTwoLists(l1, l2);
		printListNode(mergeList, "MergeTwoLists");
		l1 = initListNodeSorted();
		l2 = initListNodeSorted();
		ListNode l3 = initListNodeSorted();
		ListNode[] lists = new ListNode[] { l1, l2, l3 };
		mergeList = mergeKLists(lists);
		printListNode(mergeList, "mergeKLists");
	}

	// 2.1 Remove duplicate from unsorted list
	public static void removeDups(ListNode head) {
		ListNode node = head;
		ListNode prev = null;
		Set<Integer> set = new HashSet<>();
		while (node != null) {
			if (set.contains(node.val)) {
				prev.next = node.next;
			} else {
				set.add(node.val);
				prev = node;
			}
			node = node.next;
		}

		System.out.println("#2.1 removeDups::");
		printListNode(head);
	}

	public static void removeDups2(ListNode head) {
		ListNode curr = head;
		while (curr != null) {
			ListNode runner = curr;
			while (runner.next != null) {
				if (runner.next.val == curr.val) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			curr = curr.next;
		}

		System.out.println("removeDups no buffer::");
		printListNode(head);
	}

	// 2.2 Kth element from last
	public static void nthToLast(ListNode head, int n) {
		ListNode fast = head, slow = head;
		for (int i = 0; i <= n; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		System.out.println("\n#2.2 nthToLast::");
		System.out.println("Nth to last element = " + slow.val + " where n=" + n);
	}

	// 2.3 Delete node: Delete the current node
	public static void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	// 2.4 Partition
	public static void partition(ListNode node, int n) {
		ListNode dummySmall = new ListNode(0);
		ListNode dummyLarge = new ListNode(0);
		ListNode small = dummySmall;
		ListNode large = dummyLarge;
		while (node != null) {
			if (node.val < n) {
				small.next = node;
				small = node;
			} else {
				large.next = node;
				large = node;
			}
			node = node.next;
		}
		small.next = dummyLarge.next;
		large.next = null;
		System.out.println("\n#2.4 Partition::");
		printListNode(dummySmall.next);
	}

	public static void partition2(ListNode node, int n) {
		ListNode head = node;
		ListNode tail = node;
		while (node != null) {
			ListNode next = node.next;
			if (node.val < n) {
				node.next = head;
				head = node;
			} else {
				tail.next = node;
				tail = node;
			}
			node = next;
		}
		System.out.println("\n#2.4 Partition2::");
		printListNode(head);
	}

	// 2.5 Sum Lists
	// Sum(Reverse Order): 1->5 + 2->5 :: 51+52=103 :: Sum=> 3->0->1
	public static ListNode addLists(ListNode l1, ListNode l2, int carry) {
		if (l1 == null && l2 == null && carry == 0) {
			return null;
		}
		ListNode result = new ListNode();
		int value = carry;
		if (l1 != null) {
			value += l1.val;
		}
		if (l2 != null) {
			value += l2.val;
		}
		result.val = value % 10;
		if (l1 != null || l2 != null) {
			ListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
			result.next = more;
		}
		return result;
	}

	// Sum2(In Order): 1->5->5 + 2->5 :: 155+25=180 :: Sum=> 1->8->0
	public static ListNode addLists2(ListNode l1, ListNode l2) {
		int len1 = length(l1);
		int len2 = length(l2);

		if (len1 < len2) {
			l1 = padList(l1, len2 - len1);
		} else {
			l2 = padList(l2, len1 - len2);
		}
		printListNode(l1, "List1");
		printListNode(l2, "List2");

		PartialSum sum = addListsHelper(l1, l2);

		if (sum.carry == 0) {
			return sum.sum;
		} else {
			ListNode result = insertBefore(sum.sum, sum.carry);
			return result;
		}
	}

	public static PartialSum addListsHelper(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return new PartialSum();
		}
		PartialSum sum = addListsHelper(l1.next, l2.next);
		int value = sum.carry + l1.val + l2.val;
		ListNode fullResult = insertBefore(sum.sum, value % 10);

		sum.sum = fullResult;
		sum.carry = value / 10;
		return sum;
	}

	public static ListNode insertBefore(ListNode list, int val) {
		ListNode node = new ListNode(val);
		if (list != null) {
			node.next = list;
		}
		return node;
	}

	public static ListNode padList(ListNode head, int padding) {
		for (int i = 0; i < padding; i++) {
			ListNode node = new ListNode(0);
			node.next = head;
			head = node;
		}
		return head;
	}

	public static int length(ListNode node) {
		int count = 0;
		while (node != null) {
			node = node.next;
			count++;
		}
		return count;
	}

	// 2.6 Palindrome
	public static void isPalindrome(ListNode head) {
		System.out.print("Input: ");
		printListNode(head);
		ListNode reverse = reverse(head);
		while (head != null && reverse != null) {
			if (head.val != reverse.val) {
				System.out.println("The list is not a palindrome!");
				return;
			}
			head = head.next;
			reverse = reverse.next;
		}
		System.out.println("The list is a palindrome!");
	}

	// clone a reversed linkedList: CLONE create a new ListNode
	public static ListNode reverse(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			ListNode node = new ListNode(head.val);
			node.next = newHead;
			newHead = node;
			head = head.next;
		}

		System.out.print("Reversed: ");
		printListNode(newHead);
		return newHead;
	}

	// 2.6 Palindrome2 - Using stack
	public static void isPalindrome2(ListNode head) {
		Stack<Integer> stack = new Stack<Integer>();
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		// if list has odd number of element skip the middle
		if (fast != null) {
			slow = slow.next;
		}
		while (slow != null) {
			if (stack.pop() != slow.val) {
				System.out.println("The list is not a palindrome!");
				return;
			}
			slow = slow.next;
		}
		System.out.println("The list is a palindrome!");
	}

	// 2.6 Intersection
	public static void intersection(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		while (a != b) {
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		System.out.println("Intersection at : " + a.val);
	}

	// 2.7 Loop Detection
	public static boolean loopDetection(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null || fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow) {
				break;
			}
		}
		if (fast == null || fast.next == null)
			return false;
		else
			return true;
	}

	// Misc 1: Merge Lists
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			System.out.println("1: " + l2.val);
			return l2;
		}
		if (l2 == null) {
			System.out.println("2: " + l1.val);
			return l1;
		}
		if (l1.val < l2.val) {
			// System.out.println("3: " + l1.val);
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			// System.out.println("4: " + l2.val);
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	// Merge K lists
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
			return mergeTwoLists(l1, l2);
		}
		return null;
	}

	// META :: ListNode init
	public static ListNode initListNodeSorted() {
		ListNode n6 = new ListNode(6);
		ListNode n5 = new ListNode(5, n6);
		ListNode n4 = new ListNode(3, n5);
		ListNode n3 = new ListNode(3, n4);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);

		return n1;
	}

	public static ListNode initListNode() {
		ListNode n6 = new ListNode(6);
		ListNode n5 = new ListNode(3, n6);
		ListNode n4 = new ListNode(5, n5);
		ListNode n3 = new ListNode(3, n4);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);

		return n1;
	}

	public static ListNode initPalindromeList() {
		ListNode n6 = new ListNode(1);
		ListNode n5 = new ListNode(2, n6);
		ListNode n4 = new ListNode(3, n5);
		ListNode n3 = new ListNode(3, n4);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);

		return n1;
	}

	public static void printListNode(ListNode node) {
		while (node != null) {
			System.out.print(node.val + " -> ");
			node = node.next;
			if (node == null) {
				System.out.print("null\n");
			}
		}
	}

	public static void printListNode(ListNode node, String msg) {
		System.out.print(msg + " : ");
		while (node != null) {
			System.out.print(node.val + " -> ");
			node = node.next;
			if (node == null) {
				System.out.print("null\n");
			}
		}
	}

}

class PartialSum {
	public ListNode sum = null;
	public int carry = 0;
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
