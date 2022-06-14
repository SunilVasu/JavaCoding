package com.patterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class P07_TreeBFS {

	static int target = 5;

	public static void main(String[] args) {
		System.out.println("##Pattern 07 : Tree Breadth First Search (BFS)");

		// MISC 1 : Graph BFS using QUEUE
		bfs();
		// MISC 2 : Graph DFS using STACK
		dfs();
		dfs_recur();

		// #1: Binary Tree level order Traversal - [BFS-Queue]
		System.out.println("\n#1: Binary Tree level order Traversal");
		TreeNode root = createBinarySearchTree();
		levelOrder(root);

		System.out.println("\n#1.1: Binary Tree vertical Traversal");
		root = createBinarySearchTree();
		verticalTraversal(root);
		// #2: Binary Tree level Order traversal - II (reverse order)
		System.out.println("\n#2: Binary Tree level order Traversal (reverse)");
		root = createBinarySearchTree();
		levelOrder2(root);
		// #3: Binary Tree Zigzag Level Order Traversal: RT=O(N)=space
		// BFS-queue, use a toggle to switch between sublist ordering
		// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/

		// #4: Average of each levels in binary tree: RT=O(N)=space
		// Keep track of sum in each level
		// https://leetcode.com/problems/average-of-levels-in-binary-tree/

		// #5: Max level sum of a binaryTree: RT=O(n)=space
		// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
		// instead of running Sum, keep track of maxVal in each level
		System.out.println("\n#5: Maxium level sum of a binaryTree");
		root = createBinarySearchTree();
		maxLevelSum(root);

		// #6: Min Depth of binaryTree: RT=O(n)=space
		// https://leetcode.com/problems/minimum-depth-of-binary-tree/
		// instead of tracking node in each level, keep track of depth of tree
		// When we reach the first left node, we have the min depth of tree
		// minDepth=1; check for leaf => n.left==null && n.right==null
		System.out.println("\n#6: Minimum level sum of a binaryTree");
		root = createBinarySearchTree();
		System.out.println("minDepth=" + minDepth(root));

		// #7: Max Depth of binaryTree: RT=O(n)=space
		// https://leetcode.com/problems/maximum-depth-of-binary-tree/
		// maxDepth=0; maxDepth++ after each level

		// #8: Level Order Successor: RT=O(n)=space
		// Given binary tree & a node, find the level order successor of the
		// given node in tree. i.e find the node after the given node
		System.out.println("\n#8: Level Order Successor");
		root = createBinarySearchTree();
		TreeNode node = root.right;
		System.out.println("root=" + root.val + "  node=" + node.val);
		System.out.println("level Order Successor=" + levelOrderSuccessor(root, node).val);

		node = root.right.right;
		System.out.println("root=" + root.val + "  node=" + node.val);
		System.out.println("level Order Successor=" + levelOrderSuccessor(root, node));

		// #9: Connect level order Siblings: RT=O(n)=space
		// RT=O(n) -> there are n nodes, and we visit each node only once
		// Given binary tree, connect each node with its level order successor.
		// last node of each level should point to null
		// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

		// #10: Connect all level order sibling
		System.out.println("\n#10: Connect all level order Sibling");
		TreeNode2 root2 = createBinarySearchTree2();
		System.out.println("level Order Successor=" + connectAllLevelOrderSibling(root2));

		// #11: Binary Tree Right Side View
		// https://leetcode.com/problems/binary-tree-right-side-view/
		System.out.println("\n#11: Binary Tree Right Side View");
		root = createBinarySearchTree();
		System.out.println("RightSideView=" + rightSideView(root));

		// #11: Right View of a Binary Tree
		// MISC 3: Create a minimal Binary Tree
		minimalTree();

		// #M2: construct a complete binary tree from given arr in level order
		levelOrderConstruct();

	}

	// #MISC: Graph BFS - Queue
	public static void bfs() {
		System.out.println("\n#MISC: BFS - Queue");
		Edge root = createGraph();

		Queue<Edge> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Edge node = queue.remove();
			System.out.println("Node.val = " + node.val);
			visit(node);
			node.visited = true;
			for (Edge n : node.adjacent) {
				if (n.visited == false)
					queue.add(n);
			}
		}
	}

	// #MISC: Graph DFS - Stack
	public static void dfs() {
		System.out.println("\n#MISC: DFS - Stack");
		Edge root = createGraph();

		Stack<Edge> stack = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Edge node = stack.pop();
			System.out.println("Node.val = " + node.val);
			visit(node);
			node.visited = true;
			for (Edge n : node.adjacent) {
				if (n.visited == false)
					stack.push(n);
			}
		}
	}

	// #MISC: Graph DFS - recursion
	public static void dfs_recur() {
		System.out.println("\n#MISC: DFS - Recursion");
		Edge root = createGraph();
		search(root);
	}

	public static void search(Edge node) {
		if (node == null)
			return;
		System.out.println("Node.val = " + node.val);
		visit(node);
		node.visited = true;
		for (Edge n : node.adjacent) {
			if (n.visited == false)
				search(n);
		}
	}

	public static void visit(Edge n) {
		if (n.val == target) {
			System.out.println("Element " + target + " found!");
		}
	}

	// #1: Binary Tree level order Traversal: BFS - Queue
	// RT = O(N) as we go through each node once
	// Given a BT, populate array to represent its level-by-level traversal
	// https://leetcode.com/problems/binary-tree-level-order-traversal/
	public static void levelOrder(TreeNode root) {
		List<List<Integer>> res = new LinkedList<>();

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			List<Integer> sublist = new LinkedList<>();
			int level = queue.size();
			for (int i = 0; i < level; i++) {
				TreeNode n = queue.remove();
				sublist.add(n.val);
				if (n.left != null)
					queue.add(n.left);
				if (n.right != null)
					queue.add(n.right);
			}
			res.add(sublist);
		}
		System.out.println("res=" + res);
	}

	// #1.1: Binary Tree vertical traversal: RT = O(nlogn)
	public static void verticalTraversal(TreeNode root) {
		TreeMap<Integer, ArrayList<Integer>> res = new TreeMap<>();
		int hd = 0;

		Queue<Qobj> que = new LinkedList<Qobj>();
		que.add(new Qobj(0, root));

		while (!que.isEmpty()) {
			Qobj temp = que.remove();
			hd = temp.hd;
			TreeNode node = temp.node;

			if (res.containsKey(hd)) {
				res.get(hd).add(node.val);
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(node.val);
				res.put(hd, list);
			}

			if (node.left != null)
				que.add(new Qobj(hd - 1, node.left));
			if (node.right != null)
				que.add(new Qobj(hd + 1, node.right));
		}

		for (Map.Entry<Integer, ArrayList<Integer>> entry : res.entrySet()) {
			ArrayList<Integer> al = entry.getValue();
			for (Integer i : al)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	// #2 Binary Tree level Order traversal - II (reverse order): RT=O(N)=space
	// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
	public static void levelOrder2(TreeNode root) {
		List<List<Integer>> res = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			List<Integer> sublist = new LinkedList<>();
			int level = queue.size();
			for (int i = 0; i < level; i++) {
				TreeNode temp = queue.remove();
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
				sublist.add(temp.val);
			}
			res.add(0, sublist);
		}
		System.out.println("res=" + res);
	}

	// #5: Max level sum of a binaryTree: RT=O(n)=space
	public static void maxLevelSum(TreeNode root) {
		int lvl = 1;
		int maxSum = Integer.MIN_VALUE;
		int maxLvl = 0;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int level = queue.size();
			int sum = 0;
			for (int i = 0; i < level; i++) {
				TreeNode temp = queue.remove();
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
				sum += temp.val;
			}
			if (sum > maxSum) {
				maxSum = sum;
				maxLvl = lvl;
			}
			lvl++;
		}
		System.out.println("maxLevelSum=" + lvl);
	}

	// #6: Min Depth of binaryTree: RT=O(n)=space
	// https://leetcode.com/problems/minimum-depth-of-binary-tree/
	public static int minDepth(TreeNode root) {
		int depth = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int level = queue.size();
			for (int i = 0; i < level; i++) {
				TreeNode temp = queue.remove();
				if (temp.left == null && temp.right == null)
					return depth;
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);

			}
			depth++;
		}
		return depth;
	}

	// #8: Level Order Successor: RT=O(n)=space
	public static TreeNode levelOrderSuccessor(TreeNode root, TreeNode key) {
		if (root == null)
			return null;
		if (root == key) {
			// if left child exist, it will be postorder successor
			if (root.left != null)
				return root.left;
			// if right child exist, it will be postorder successor
			else if (root.right != null)
				return root.right;
			else
				return null;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode temp = queue.remove();
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);
			if (temp == key)
				break;
		}
		return queue.peek();
	}

	// #10: Connect all level order sibling
	public static TreeNode2 connectAllLevelOrderSibling(TreeNode2 root) {
		Queue<TreeNode2> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);
		while (!queue.isEmpty()) {
			TreeNode2 temp = queue.remove();
			if (temp != null) {
				temp.next = queue.peek();
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			} else if (!queue.isEmpty()) {
				queue.add(null);
			}
		}
		return root;
	}

	// #11: Binary Tree Right Side View
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int level = queue.size();
			for (int i = 0; i < level; i++) {
				TreeNode temp = queue.remove();
				if (i == level - 1) // right view; For left view::i=0
					res.add(temp.val);
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}
		}
		return res;
	}

	// #MISC: #M1 Minimal Tree: Given sorted unique array, create a BST with
	// minimal
	// height
	public static void minimalTree() {
		System.out.println("\n#MISC: Minimal Binary Tree");
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		TreeNode root = createMinimalTree(arr, 0, arr.length - 1);
		printTree(root, "");

		System.out.println("\nBalanced Tree:");
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		root = createMinimalTree(arr, 0, arr.length - 1);
		printTree(root, "");
	}

	public static TreeNode createMinimalTree(int[] arr, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalTree(arr, start, mid - 1);
		n.right = createMinimalTree(arr, mid + 1, end);
		return n;
	}

	// #M2 construct a complete binary tree from given array in level order
	public static void levelOrderConstruct() {
		System.out.println("\n#M2 Construct a complete binary tree from given arr in level order");
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6 };

		TreeNode root = insertLevelOrder(arr, new TreeNode(), 0);

		System.out.println("CHECK : 4 2 5 1 6 3 ");
		inOrderPrint(root);
	}

	public static TreeNode insertLevelOrder(int[] arr, TreeNode root, int i) {
		if (i < arr.length) {
			TreeNode temp = new TreeNode(arr[i]);
			root = temp;

			root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		}
		return root;

	}

	public static void inOrderPrint(TreeNode root) {
		if (root != null) {
			inOrderPrint(root.left);
			System.out.print(root.val + " ");
			inOrderPrint(root.right);
		}
	}

	// META
	// Print Tree
	public static void printTree(TreeNode node, String prefix) {
		if (node == null)
			return;
		printTree(node.right, prefix + "\t(r)");
		System.out.println(prefix + "-->" + node.val);
		printTree(node.left, prefix + "\t(l)");
	}

	// BST - balanced
	public static TreeNode createBinarySearchTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n1;
		n1.left = n0;
		n2.right = n4;
		n4.left = n3;
		n4.right = n5;
		return n2;
	}

	public static TreeNode2 createBinarySearchTree2() {
		TreeNode2 n0 = new TreeNode2(0);
		TreeNode2 n1 = new TreeNode2(1);
		TreeNode2 n2 = new TreeNode2(2);
		TreeNode2 n3 = new TreeNode2(3);
		TreeNode2 n4 = new TreeNode2(4);
		TreeNode2 n5 = new TreeNode2(5);
		n2.left = n1;
		n1.left = n0;
		n2.right = n4;
		n4.left = n3;
		n4.right = n5;
		return n2;
	}

	// Graphs: created (0,5)(1,4)(2,3) : anti-clockwise
	public static Edge createGraph() {
		Edge n0 = new Edge(0);
		Edge n1 = new Edge(1);
		Edge n2 = new Edge(2);
		Edge n3 = new Edge(3);
		Edge n4 = new Edge(4);
		Edge n5 = new Edge(5);
		n0.adjacent = new Edge[] { n1, n4, n5 };
		n1.adjacent = new Edge[] { n3, n4 };
		n2.adjacent = new Edge[] { n1 };
		n3.adjacent = new Edge[] { n2, n4 };
		n4.adjacent = new Edge[0];
		n5.adjacent = new Edge[0];
		return n0;
	}

}

// Graph Edge
class Edge {
	int val;
	boolean visited = false;
	Edge[] adjacent;

	Edge(int val) {
		this.val = val;
	}
}

// Binary TreeEdge
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode() {

	}
}

class TreeNode2 {
	int val;
	TreeNode2 left;
	TreeNode2 right;
	TreeNode2 next;

	TreeNode2(int val) {
		this.val = val;
	}
}

class Qobj {
	int hd;
	TreeNode node;

	Qobj(int hd, TreeNode root) {
		this.hd = hd;
		this.node = root;
	}
}
