package com.ctci.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class S4_TreeGraph {

	public static void main(String[] args) {
		System.out.println("###Tree and Graph###");
		// MISC 1: DFS
		dfs();
		// MISC 2: BFS
		bfs();

		// 4.1 Route Between Node
		routeBetweenNode();
		// 4.2 Minimal Tree
		TreeNode root = minimalTree();
		// 4.3 List of Depths: create LL of nodes at each depth
		createLevelLinkedList(root);
		createLevelLinkedList_BFS(root);
		// 4.4 Check balanced: check if a binary tree is balanced
		System.out.println("\n#4.4 Check balanced");
		System.out.println("Check balanced : " + isBalanced(root));
		// 4.5 Validate BST
		validateBST(root);

		// 4.6 Successor
		// 4.7 Build Order
		// 4.8 First Common Ancestor
		// 4.9 BST Sequence
		// 4.10 Check Subtree
		// 4.11 Random Node
		// 4.12 Paths with Sum
	}

	// MISC 1: DFS -> using RECURSION
	public static void dfs() {
		System.out.println("#MISC 1: DFS -> uses RECURSION");
		Node root = createGraph();
		search(root);
	}

	public static void search(Node node) {
		if (node == null)
			return;
		System.out.println("Node.value=" + node.value);
		visit(node);
		node.visited = true;
		for (Node n : node.adjacent) {
			if (n.visited == false)
				search(n);
		}
	}

	public static void visit(Node root) {
		if (root.value == 5) {
			System.out.println("Element 5 found!");
		}
	}

	// MISC 2: BFS -> using QUEUE
	public static void bfs() {
		System.out.println("\n#MISC 2: BFS -> uses QUEUE");
		Node root = createGraph();

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.println("Node.value = " + node.value);
			visit(node);
			node.visited = true;
			for (Node n : node.adjacent) {
				if (n.visited == false) {
					queue.add(n);
				}
			}
		}
	}

	// 4.1 Route Between Node
	public static void routeBetweenNode() {
		System.out.println("\n#4.1 Route Between Node");
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n0.adjacent = new Node[] { n1, n4, n5 };
		n1.adjacent = new Node[] { n3, n4 };
		n2.adjacent = new Node[] { n1 };
		n3.adjacent = new Node[] { n2, n4 };
		n4.adjacent = new Node[0];
		n5.adjacent = new Node[0];

		Graph g = new Graph();
		g.nodes = new Node[] { n0, n1, n2, n3, n4, n5 };
		Node start = n0;
		Node end = n2;
		for (Node n : g.nodes) {
			n.visited = false;
		}
		System.out.println("BFS:Path b/w " + start.value + " & " + end.value + " : " + searchBFS(start, end) + "\n");
		for (Node n : g.nodes) {
			n.visited = false;
		}

		System.out.println("DFS:Path b/w " + start.value + " & " + end.value + " : " + searchDFS(start, end) + "\n");
	}

	public static boolean searchBFS(Node start, Node end) {
		if (start == end) {
			return true;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			Node node = q.remove();
			System.out.println("Node.value = " + node.value);
			if (end.value == node.value) {
				return true;
			}
			node.visited = true;
			for (Node n : node.adjacent) {
				if (n.visited == false) {
					q.add(n);
				}
			}
		}
		return false;
	}

	public static boolean searchDFS(Node start, Node end) {
		if (start == null) {
			return false;
		}
		System.out.println("Node.value = " + start.value);
		if (start == end) {
			return true;
		}
		start.visited = true;
		for (Node n : start.adjacent) {
			if (n.visited == false) {
				return searchDFS(n, end);
			}
		}
		return false;
	}

	// 4.2 Minimal Tree: Given sorted uniq array, create a BST with minimal
	// height
	public static TreeNode minimalTree() {
		System.out.println("\n#4.2 Minimal Tree");
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // not balanced
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 }; // balanced
		TreeNode root = createMinimalBST(arr, 0, arr.length - 1);
		printTree(root, "");
		return root;
	}

	public static TreeNode createMinimalBST(int arr[], int start, int end) {
		if (end < start)
			return null;
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}

	// 4.3 List of Depths: create LL of nodes at each depth
	public static void createLevelLinkedList(TreeNode root) {
		System.out.println("\n#4.3 List of Depths");
		ArrayList<LinkedList<Integer>> lists = new ArrayList<>();
		createLevelLinkedList(root, lists, 0);
		for (LinkedList<Integer> list : lists) {
			System.out.println("Lists : " + list);
		}
	}

	public static void createLevelLinkedList(TreeNode node, ArrayList<LinkedList<Integer>> lists, int level) {
		if (node == null)
			return;
		LinkedList<Integer> list = null;
		if (lists.size() == level) {
			list = new LinkedList<>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(node.value);
		createLevelLinkedList(node.left, lists, level + 1);
		createLevelLinkedList(node.right, lists, level + 1);
	}

	public static void createLevelLinkedList_BFS(TreeNode root) {
		System.out.println("\n#4.3 List of Depths - BFS");

		ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
		LinkedList<TreeNode> current = new LinkedList<>();
		if (root != null) {
			current.add(root);
		}
		while (current.size() > 0) {
			result.add(current);
			LinkedList<TreeNode> parents = current;
			current = new LinkedList<>();
			for (TreeNode parent : parents) {
				if (parent.left != null) {
					current.add(parent.left);
				}
				if (parent.right != null) {
					current.add(parent.right);
				}
			}

		}

		for (LinkedList<TreeNode> list : result) {
			System.out.print("Lists : [");
			for (TreeNode node : list) {
				System.out.print(node.value + " ");
			}
			System.out.println("]");
		}
	}

	// 4.4 Check balanced: check if a binary tree is balanced
	public static boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		int height = getHeight(root.left) - getHeight(root.right);
		if (Math.abs(height) > 1) {
			return false;
		} else {
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}

	public static int getHeight(TreeNode root) {
		if (root == null)
			return -1;
		return Math.max(getHeight(root.left) + getHeight(root.right), 0) + 1;
	}

	// 4.5 Validate BST
	public static void validateBST(TreeNode root) {
		System.out.println("\n#4.5 Validate BST");

		System.out.println("validateBST (Iterative - inorder) : " + checkBST_iter(root));

		System.out.println("validateBST (Recursion - inorder) : " + checkBST(root));

		System.out.println("validateBST (recursion) : " + checkBST(root, null, null));

		TreeNode n = new TreeNode(50);
		n.left = new TreeNode(20);
		n.right = new TreeNode(10);

		System.out.println("validateBST (Iterative - inorder) : " + checkBST_iter(n));

		System.out.println("validateBST (Recursion - inorder) : " + checkBST(n));

		System.out.println("validateBST (recursion) : " + checkBST(n, null, null));
	}

	public static boolean checkBST_iter(TreeNode root) {
		// Iterative - Inorder
		Stack<TreeNode> stack = new Stack<>();
		TreeNode prev = null;
		boolean res = true;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (prev != null && prev.value > root.value)
				res = false;
			prev = root;
			root = root.right;
		}
		return res;
	}

	public static TreeNode prev;

	// Recursion - Inorder
	public static boolean checkBST(TreeNode root) {
		if (root == null)
			return true;

		if (!checkBST(root.left))
			return false;

		if (prev != null && prev.value > root.value) {
			return false;
		}
		prev = root;
		return checkBST(root.right);
	}

	// Recursion
	public static boolean checkBST(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if ((min != null && root.value <= min) || (max != null && root.value >= max))
			return false;
		return checkBST(root.left, min, root.value) && checkBST(root.right, root.value, max);
	}

	// 4.6 Succesor

	// META
	public static Node createGraph() {
		Node n0 = new Node(0); // Root
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n0.adjacent = new Node[] { n1, n4, n5 };
		n1.adjacent = new Node[] { n3, n4 };
		n2.adjacent = new Node[] { n1 };
		n3.adjacent = new Node[] { n2, n4 };
		n4.adjacent = new Node[0];
		n5.adjacent = new Node[0];
		return n0;
	}

	// PRINTING: This is not inorder traversal; for printing only
	public static void printTree(TreeNode node, String prefix) {
		if (node == null)
			return;
		printTree(node.right, prefix + "\t(r)");
		System.out.println(prefix + "-->" + node.value);
		printTree(node.left, prefix + "\t(l)");
	}

}

class Tree {
	Node root;
}

class Graph {
	Node[] nodes;
}

class Node {
	int value;
	boolean visited;
	Node[] adjacent;

	Node(int val) {
		this.value = val;
		this.visited = false;
	}
}

class TreeNode {
	int value;
	boolean visited;
	TreeNode left;
	TreeNode right;

	TreeNode(int val) {
		this.value = val;
		left = null;
		right = null;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.value = val;
		this.left = left;
		this.right = right;
	}
}