package com.patterns;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.ctci.meta.ListNode;

public class P06_ReverseLinkedList {

	public static void main(String[] args) {
		System.out.println("##Pattern 6 : In-place Reversal of a LinkedList");

		System.out.println("\n#1 Reverse:");
		Node head = init();
		print(head, "Before head");
		Node newHead = reverse(head);
		print(newHead, "After newHead");
		head = init();
		print(head, "Before (recur) head");
		newHead = reverse_recur(head, null);
		print(newHead, "After (recur) newHead");

		System.out.println("\n#2 Reverse sublist:");
		head = init_5();
		print(head, "Before reverse");
		head = reverse2(head, 2, 4);
		print(head, "reverse sublist");
		head = init_odd();
		print(head, "Before reverse");
		head = reverse2(head, 4, 6);
		print(head, "reverse sublist");

		System.out.println("\n#3 Reverse k group:");
		head = init_5();
		print(head, "Before reverse");
		head = reverseKgroup(head, 2);
		print(head, "After reverse 2 group");
		head = init_even();
		// head is null, commet the sysout
		print(head, "Before reverse");
		head = reverseKgroup_2(head, 2);
		print(head, "after reverse");

		System.out.println("\n#4 Rotate list k:");
		head = init_5();
		print(head, "Before rotate");
		head = rotate(head, 2);
		print(head, "after rotate");

		System.out.println("\n#6 Add two number");
		addNumbers();
	}

	// #1 Reverse: RT=O(n), Space=O(1)
	public static Node reverse(Node head) {
		Node newHead = null;
		while (head != null) {
			Node next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}

		return newHead;
	}

	public static Node reverse_recur(Node head, Node newHead) {
		while (head != null) {
			Node next = head.next;
			head.next = newHead;
			return reverse_recur(next, head);
		}
		return newHead;
	}

	// #2 Reverse LinkedList sub-list:
	public static Node reverse2(Node head, int m, int n) {
		Node dummy = new Node(0);
		dummy.next = head;
		Node prev = dummy;
		for (int i = 0; i < m - 1; i++)
			prev = prev.next;
		Node start = prev.next;
		Node then = start.next;
		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = prev.next;
			prev.next = then;
			then = start.next;
		}
		return dummy.next;
	}

	// #3 Reverse Nodes in K-group
	public static Node reverseKgroup(Node head, int k) {

		Node dummy = new Node(0);
		dummy.next = head;
		Node start = dummy;

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

	public static Node reverseKgroup_2(Node head, int k) {

		Node dummy = new Node(0);
		dummy.next = head;
		Node start = dummy;

		int i = 0;
		boolean toogle = true;
		while (head != null) {
			i++;
			if (i % k == 0 && toogle) {
				start = reverse(start, head.next);
				head = start.next;
			}
			if (i % k == 0 && !toogle) {
				start = reverse(start, head.next);
				head = start.next;
			} else {

				head = head.next;
			}
		}
		return dummy.next;
	}

	// reverse between begin & end
	// After reverse:: head -> tail, newHead -> head
	public static Node reverse(Node start, Node end) {
		Node head = start.next;
		Node prev = start;// newHead
		Node first = head; // dummy
		while (head != end) {
			Node next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		System.out.println("begin:" + start.val + "  newHead:" + prev.val);
		start.next = prev;
		System.out.println("first:" + first.val + "   head:" + head.val);
		first.next = head;
		print(start, "inter");
		return first;
	}

	public static Node reverse_2(Node start, Node end, boolean toogle) {
		Node head = start.next;
		Node prev = start;// newHead
		Node first = head; // dummy
		while (head != end) {
			Node next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		start.next = prev;
		first.next = head;
		print(start, "inter");
		if (toogle) {
			while (head != end) {
				head = head.next;
			}
			first.next = head;
		}
		return first;
	}

	// #4 Reverse alternating K-elem sublist

	// #5 Rotate a List
	public static Node rotate(Node head, int k) {
		Node dummy = new Node(0);
		dummy.next = head;
		Node fast = dummy, slow = dummy;
		int size = 0;
		while (fast.next != null) {
			fast = fast.next;
			size++;
		}
		for (int j = size - (k % size); j > 0; j--) {
			slow = slow.next;
		}
		System.out.println("fast=" + fast.val + "   slow.next=" + slow.next.val);
		System.out.println("dummy=" + dummy.next.val);
		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}

	// #6 Add two number
	// https://leetcode.com/problems/add-two-numbers/
	// https://leetcode.com/problems/add-two-numbers-ii/
	// https://leetcode.com/problems/add-to-array-form-of-integer/
	public static void addNumbers() {
		// Add in reverse Order
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		ListNode res = addNumber_reverse(l1, l2);
		print(res, "addNumbers_reverse (342+465=807)");

		// numbers in order
		l1 = new ListNode(7);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		l1.next.next.next = new ListNode(3);

		l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		res = addNumber_inOrder(l1, l2);
		print(res, "addNumbers_inOrder (7243+564=7807)");

		// Add to Array-Form of Integer
		int[] nums = new int[] { 1, 2, 0, 0 };
		int k = 34;

		int curr = k;
		int i = nums.length - 1;
		List<Integer> list = new LinkedList<>();
		while (i >= 0 || curr > 0) {
			if (i >= 0) {
				curr += nums[i];
				i--;
			}
			list.add(0, curr % 10);
			curr = curr / 10;
		}
		System.out.println("Array-Form list=" + list);
	}

	public static ListNode addNumber_reverse(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		int sum = 0, carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int x = l1 != null ? l1.val : 0;
			int y = l2 != null ? l2.val : 0;

			sum = x + y + carry;
			curr.next = new ListNode(sum % 10);
			carry = sum / 10;

			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
			curr = curr.next;
		}

		return dummy.next;
	}

	public static ListNode addNumber_inOrder(ListNode l1, ListNode l2) {
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		while (l1 != null) {
			s1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			s2.push(l2.val);
			l2 = l2.next;
		}
		int sum = 0;
		ListNode curr = new ListNode(0);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			if (!s1.isEmpty()) {
				sum += s1.pop();
			}
			if (!s2.isEmpty()) {
				sum += s2.pop();
			}
			curr.val = sum % 10;
			ListNode head = new ListNode(sum / 10);
			head.next = curr;
			curr = head;

			sum = sum / 10;
		}
		return curr.val == 0 ? curr.next : curr;
	}

	// META
	public static void print(Node n, String msg) {
		System.out.print(msg + ": ");
		while (n != null) {
			System.out.print(n.val + " -> ");
			n = n.next;
		}
		System.out.println("null");
	}

	public static void print(ListNode n, String msg) {
		System.out.print(msg + ": ");
		while (n != null) {
			System.out.print(n.val + " -> ");
			n = n.next;
		}
		System.out.println("null");
	}

	public static Node init() {
		Node head = new Node(2);
		head.next = new Node(4);
		head.next.next = new Node(6);
		head.next.next.next = new Node(8);
		head.next.next.next.next = new Node(10);
		return head;
	}

	public static Node init_5() {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		return head;
	}

	public static Node init_even() {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		return head;
	}

	public static Node init_odd() {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		return head;
	}

}

class Node {
	int val;
	Node next;

	Node(int val) {
		this.val = val;
	}

	Node(int val, Node next) {
		this.val = val;
		this.next = next;
	}
}