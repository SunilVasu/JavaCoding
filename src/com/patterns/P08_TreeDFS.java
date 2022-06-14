package com.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class P08_TreeDFS {

	private static int maxSum = 0;
	private static Node_ maxLeaf;

	public static void main(String[] args) {
		System.out.println("##Pattern 08 : Tree Depth First Search (DFS)");

		// #1: Path Sum
		// https://leetcode.com/problems/path-sum/
		System.out.println("\n#1: Path Sum");
		Node_ root = createBinarySearchTree();
		// printTree(root, " ");
		int sum = 5;
		System.out.println("PathSum (sum=" + sum + ")=" + hasPathSum(root, sum));
		root = createBinarySearchTree();
		sum = 3;
		System.out.println("PathSum (sum=" + sum + ")=" + hasPathSum(root, sum));
		root = createBinarySearchTree();
		sum = 9;
		System.out.println("PathSum (sum=" + sum + ")=" + hasPathSum_recur(root, sum));

		// #2: Path Sum II (find all path for target sum)
		// https://leetcode.com/problems/path-sum/
		System.out.println("\n#2: Path Sum-2");
		root = createBinarySearchTree();
		List<List<Integer>> res = new LinkedList<>();
		List<Integer> currRes = new LinkedList<>();
		pathSum_2(root, sum, currRes, res);
		System.out.println("PathSum 2 =" + res);

		root = createBinarySearchTree();
		currRes = new LinkedList<>();
		pathSumAll_2(root, 0, currRes);
		System.out.println("PathSum 2 (All Sums) =" + currRes);

		// #2.1 Find all root-to-leaf path of Binary Tree
		// Same as #2, avoid only the targetSum check
		System.out.println("\n#2.1: Path Sum-2: find all root-to-leaf paths");
		root = createBinarySearchTree();
		res = new LinkedList<>();
		currRes = new LinkedList<>();
		pathSum_All(root, currRes, res);
		System.out.println("PathSum 2 (All paths)=" + res);

		// #2.2 Find the root-to-path with the max sum
		// Same as #2, keep check for maxSum
		System.out.println("\n#2.2: Path Sum-2: find max sum root-to-leaf path");
		root = createBinarySearchTree();
		System.out.println("PathSum 2 (Find maxSum path)=" + pathSum_maxSum(root));
		root = createBinarySearchTree();

		pathSum_maxSum(root, 0);
		System.out.println("maxSum=" + maxSum);

		// #3: Sum Root to Leaf Numbers
		// find sum of numbers formed by nodes
		// https://leetcode.com/problems/sum-root-to-leaf-numbers/
		System.out.println("\n#3: Sum Root to Leaf Numbers");
		root = createBinarySearchTree();
		System.out.println("sumNumbers=" + sumNumbers(root));
		root = createBinarySearchTree();
		System.out.println("sumNumbers(iter)=" + sumNumbers_iter(root));

		// #4: Path with given Seq
		// Given a Binary Tree and Seq, find if seq is present in root-to-leaf
		// path in tree
		System.out.println("\n#4: Path with given Seq");
		root = createBinarySearchTree();
		System.out.println("findPath=" + findPath(root, new int[] { 2, 1, 0 }));
		root = createBinarySearchTree();
		System.out.println("findPath=" + findPath(root, new int[] { 1, 1, 0 }));

		// #5: Path Sum III - return all path with the sum
		// https://leetcode.com/problems/path-sum-iii/
		System.out.println("\n#5: Path Sum III - return all path with the sum");
		root = createBinarySearchTree();
		sum = 3;
		System.out.println("pathSum3=" + pathSum3(root, sum));
		System.out.println("pathSum3_WithMap=" + pathSum3_WithMap(root, sum));
		// Find SumArray Sum: Similar Map approach for array
		// Given an unsorted array of integers, find the number of subarrays
		// having sum exactly equal to a given number k. Eg: (0-3)(1-4)(3-4)
		System.out.println("findSubArraySum=" + findSubArraySum(new int[] { 10, 2, -2, -20, 10 }, 10));

		// #6:Diameter of Binary Tree
		// https://leetcode.com/problems/diameter-of-binary-tree/

		// #7: Binary Tree Maximum Path Sum (similar as #6)
		// https://leetcode.com/problems/binary-tree-maximum-path-sum/

		// LEETCODE
		Node_ r = new Node_(3);
		r.left = new Node_(1);
		r.left.left = new Node_(3);
		r.right = new Node_(4);
		r.right.right = new Node_(5);
		r.right.left = new Node_(1);
		System.out.println("\n#Visible Nodes");
		System.out.println("visibleNodes (4) = " + visibleNode(root));

		r = new Node_(3);
		r.left = new Node_(3);
		r.left.left = new Node_(4);
		r.left.right = new Node_(2);
		System.out.println("visibleNodes (3) = " + visibleNode(root));

		System.out.println("\n#Invert BST");
		System.out.println("invert = " + invertBST(root).val);

		// #Traversal
		binaryTreeTraversal();
	}

	// #1: Path Sum: RT=O(N)=space
	public static boolean hasPathSum(Node_ root, int sum) {
		Stack<Node_> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty() && root != null) {
			Node_ temp = stack.pop();
			if (temp.val == sum)
				return true;
			if (temp.left != null) {
				temp.left.val += temp.val;
				stack.push(temp.left);
			}
			if (temp.right != null) {
				temp.right.val += temp.val;
				stack.push(temp.right);
			}
		}
		return false;
	}

	public static boolean hasPathSum_recur(Node_ root, int sum) {
		if (root == null)
			return false;
		if (root.val == sum && root.left == null && root.right == null)
			return true;
		return hasPathSum_recur(root.left, sum - root.val) || hasPathSum_recur(root.right, sum - root.val);
	}

	// #2: Path Sum II (find all path sum): RT = O(N^2); Space=O(N)
	public static void pathSum_2(Node_ root, int targetSum, List<Integer> currRes, List<List<Integer>> res) {
		if (root == null)
			return;
		currRes.add(root.val);
		if (root.val == targetSum && root.left == null && root.right == null) {
			res.add(new LinkedList<>(currRes));
		} else {
			pathSum_2(root.left, targetSum - root.val, currRes, res);
			pathSum_2(root.right, targetSum - root.val, currRes, res);
		}
		currRes.remove(currRes.size() - 1);
	}

	public static void pathSumAll_2(Node_ root, int sum, List<Integer> currRes) {
		if (root == null)
			return;
		sum += root.val;
		if (root.left == null && root.right == null) {
			currRes.add(sum);
		} else {
			pathSumAll_2(root.left, sum, currRes);
			pathSumAll_2(root.right, sum, currRes);
		}
	}

	// #2.1: Path Sum II (find all paths): RT = O(N^2); Space=O(N)
	public static void pathSum_All(Node_ root, List<Integer> currRes, List<List<Integer>> res) {
		if (root == null)
			return;
		currRes.add(root.val);
		if (root.left == null && root.right == null) {
			res.add(new LinkedList<>(currRes));
		} else {
			pathSum_All(root.left, currRes, res);
			pathSum_All(root.right, currRes, res);
		}
		currRes.remove(currRes.size() - 1);
	}

	// #2.2: Path Sum II (find the max path Sum): RT = O(N^2); Space=O(N)
	public static int pathSum_maxSum(Node_ root) {
		if (root == null)
			return 0;
		int left = pathSum_maxSum(root.left);
		int right = pathSum_maxSum(root.right);
		return ((left > right) ? left : right) + root.val;

	}

	public static void pathSum_maxSum(Node_ root, int sum) {
		if (null == root) {
			return;
		}
		sum += root.val;
		if (sum > maxSum && root.left == null && root.right == null) {
			maxLeaf = root;
			maxSum = sum;
		}
		pathSum_maxSum(root.left, sum);
		pathSum_maxSum(root.right, sum);
	}

	// #3: Sum of Path Numbers: RT = O(N) = Space
	// find sum of numbers formed by nodes
	public static int sumNumbers(Node_ root) {
		return sumNumbers(root, 0);
	}

	public static int sumNumbers(Node_ root, int sum) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return sum * 10 + root.val;
		else
			return sumNumbers(root.left, sum * 10 + root.val) + sumNumbers(root.right, sum * 10 + root.val);
	}

	public static int sumNumbers_iter(Node_ root) {
		Stack<Node_> stack = new Stack<>();
		stack.push(root);
		int sum = 0;
		while (!stack.isEmpty()) {
			Node_ curr = stack.pop();
			if (curr.left != null) {
				curr.left.val = curr.val * 10 + curr.left.val;
				stack.push(curr.left);
			}
			if (curr.right != null) {
				curr.right.val = curr.val * 10 + curr.right.val;
				stack.push(curr.right);
			}
			if (curr.left == null && curr.right == null) {
				sum += curr.val;
			}
		}
		return sum;
	}

	// #4: Path with given Seq: RT=O(N)=space
	public static boolean findPath(Node_ root, int[] seq) {
		return findPath(root, seq, 0);
	}

	public static boolean findPath(Node_ root, int[] seq, int seqIndex) {
		if (root == null)
			return false;
		int len = seq.length - 1;
		if (seqIndex > len || root.val != seq[seqIndex])
			return false;
		if (root.left == null && root.right == null && seqIndex == len)
			return true;
		return findPath(root.left, seq, seqIndex + 1) || findPath(root.right, seq, seqIndex + 1);
	}

	// #5: Path Sum III - return all path with the sum: RT=O(n^2); space=O(N)
	// https://leetcode.com/problems/path-sum-iii/
	public static int pathSum3(Node_ root, int sum) {
		if (root == null)
			return 0;
		// return findPath(root, sum) + pathSum3(root.left, sum) +
		// pathSum3(root.right, sum);
		return findPath(root, sum, new LinkedList<>());
	}

	// recursion
	public static int findPath(Node_ root, int sum) {
		int res = 0;
		if (root == null)
			return res;
		if (sum == root.val) {
			res++;
		}

		res += findPath(root.left, sum - root.val);
		res += findPath(root.right, sum - root.val);

		return res;
	}

	// Path Sum III: recursion-2: Using list
	public static int findPath(Node_ root, int target, List<Integer> list) {
		if (root == null)
			return 0;

		list.add(root.val);

		int count = 0;
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			if (sum == target)
				count++;
		}
		count += findPath(root.left, target, list);
		count += findPath(root.right, target, list);
		list.remove(list.size() - 1);
		return count;
	}

	// Path Sum III: Using Map
	public static int pathSum3_WithMap(Node_ root, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		return helper(root, 0, target, map);
	}

	public static int helper(Node_ root, int sum, int target, HashMap<Integer, Integer> map) {
		if (root == null)
			return 0;

		sum += root.val;
		int res = map.getOrDefault(sum - target, 0);

		map.put(sum, map.getOrDefault(sum, 0) + 1);
		res += helper(root.left, sum, target, map) + helper(root.right, sum, target, map);
		map.put(sum, map.get(sum) - 1);
		return res;
	}

	// Similar Approach: Find subArray Sum: Using Map in Array; RT=O(N)=space
	public static int findSubArraySum(int[] arr, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int res = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];

			if (sum == target) {
				res++;
			}

			if (map.containsKey(sum - target)) {
				res += map.get(sum - target);
			}

			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return res;
	}

	// ###LEETCODE###
	// Count num of visible node in BinaryTree
	// Count Good Nodes in BinaryTree: Node X is visible, if X val greatest
	// https://leetcode.com/problems/count-good-nodes-in-binary-tree/
	public static int visibleNode(Node_ root) {
		return goodNodes(root, Integer.MIN_VALUE);
	}

	public static int goodNodes(Node_ root, int max) {
		if (root == null)
			return 0;
		int res = root.val >= max ? 1 : 0;
		res += goodNodes(root.left, Math.max(root.val, max));
		res += goodNodes(root.right, Math.max(root.val, max));
		return res;
	}

	// Invert BST
	// https://leetcode.com/problems/invert-binary-tree/
	public static Node_ invertBST(Node_ root) {
		if (root == null)
			return null;
		Node_ right = invertBST(root.right);
		Node_ left = invertBST(root.left);
		root.right = right;
		root.left = left;
		return root;
	}

	// # BST Traversal
	// https://leetcode.com/problems/binary-tree-inorder-traversal/discuss/1600043/Java-General-DFS-Template-for-Tree-Tranversal
	// https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
	public static void binaryTreeTraversal() {
		System.out.println("\n#2 Binary Tree Traversal");

		// .....[1]
		// null......2
		// ........3...null
		TreeNode r = new TreeNode(1);
		r.left = null;
		r.right = new TreeNode(2);
		r.right.left = new TreeNode(3);
		r.right.right = null;

		System.out.println("inorder [1,3,2]= " + inorder(r));
		System.out.println("preorder [1,2,3]= " + preorder(r));
		System.out.println("postorder [3,2,1]= " + postorder(r));
		recurTraversal(r);

		// ...[15]
		// 10.......25
		// ......[20..30]
		TreeNode root = new TreeNode(15);
		root.left = new TreeNode(10);
		root.right = new TreeNode(25);
		root.right.left = new TreeNode(20);
		root.right.right = new TreeNode(30);
		System.out.println("\ninorder [10,15,25,20,30]= " + inorder(root)); // l->r
		System.out.println("preorder [15,10,25,20,30]= " + preorder(root)); // r->l
		System.out.println("postorder [10,20,30,25,15]= " + postorder(root));// l->r
		recurTraversal(root);

	}

	// #1 Inorder Traversal
	// https://leetcode.com/problems/binary-tree-inorder-traversal/
	// https://leetcode.com/problems/binary-tree-inorder-traversal/discuss/1600043/Java-General-DFS-Template-for-Tree-Tranversal
	public static List<Integer> inorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			list.add(root.val);
			root = root.right;
		}
		return list;
	}

	// #2 Preorder Traversal => right -> left
	// https://leetcode.com/problems/binary-tree-preorder-traversal/
	public static List<Integer> preorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			list.add(curr.val);
			if (curr.right != null) {
				stack.push(curr.right);
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}

		}
		return list;
	}

	// #3 Postorder Traversal => left -> right
	// https://leetcode.com/problems/binary-tree-postorder-traversal/
	public static List<Integer> postorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			list.add(0, curr.val);
			if (curr.left != null) {
				stack.push(curr.left);
			}
			if (curr.right != null) {
				stack.push(curr.right);
			}
		}
		return list;
	}

	// #4 traversal - recursion
	public static void recurTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		inorder(root, list);

		System.out.println("inorder recur = " + list);

		list = new ArrayList<>();
		preorder(root, list);
		System.out.println("preorder recur = " + list);

		list = new ArrayList<>();
		postorder(root, list);
		System.out.println("postorder recur = " + list);

	}

	public static void inorder(TreeNode node, List<Integer> list) {
		if (node == null)
			return;

		// #inorder
		inorder(node.left, list);
		list.add(node.val);
		inorder(node.right, list);
	}

	public static void preorder(TreeNode node, List<Integer> list) {
		if (node == null)
			return;

		// #preorder
		list.add(node.val);
		preorder(node.left, list);
		preorder(node.right, list);

	}

	public static void postorder(TreeNode node, List<Integer> list) {
		if (node == null)
			return;

		// #postorder
		postorder(node.left, list);
		postorder(node.right, list);
		list.add(node.val);
	}

	// META
	// Print Tree
	public static void printTree(Node_ node, String prefix) {
		if (node == null)
			return;
		printTree(node.right, prefix + "\t(r)");
		System.out.println(prefix + "-->" + node.val);
		printTree(node.left, prefix + "\t(l)");
	}

	// BST - balanced
	public static Node_ createBinarySearchTree() {
		Node_ n0 = new Node_(0);
		Node_ n1 = new Node_(1);
		Node_ n2 = new Node_(2);
		Node_ n3 = new Node_(3);
		Node_ n4 = new Node_(4);
		Node_ n5 = new Node_(5);
		n2.left = n1;
		n1.left = n0;
		n2.right = n4;
		n4.left = n3;
		n4.right = n5;
		return n2;
	}

}

class Node_ {
	int val;
	Node_ left;
	Node_ right;

	Node_(int val) {
		this.val = val;
	}
}
