package com.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P15_BST {

	public static void main(String[] args) {
		// .......15
		// ..10........20
		// [8..12]..[16..25]

		// inorder: 8-10-12-15-16-20-25
		// preorder:15-10-8-12-20-16-25
		// postorder:8-12-10-16-25-20-15

		int[] keys = new int[] { 15, 10, 20, 8, 12, 16, 25 };
		TreeNode root = null;
		for (int key : keys) {
			root = insert(root, key);
		}

		// #1 Inorder Successor
		System.out.println("#1 Inorder Successor:");
		for (int key : keys) {
			TreeNode succ = findSuccessor(root, null, key);
			if (succ != null) {
				System.out.println("The successor of node " + key + " is " + succ.val);
			} else {
				System.out.println("No Successor exists for node " + key);
			}

		}

		System.out.println("inorder before delete = " + inorderTraversal(root));
		deleteNode(root, 15);
		System.out.println("inorder after delete  = " + inorderTraversal(root));

		// #2 Validate BST
		System.out.println("\n#2 Validate BST:");
		System.out.println("isValidBST (T)= " + isValidBST(root));
		System.out.println("isValidBST_2 (T)= " + isValidBST_2(root));
		System.out.println("isValidBST_3 (T)= " + isValidBST_3(root, null, null));

		TreeNode n1 = new TreeNode(15);
		n1.left = new TreeNode(10);
		n1.right = new TreeNode(1);
		prev = null;

		System.out.println("\nisValidBST (F)= " + isValidBST(n1));
		System.out.println("isValidBST_2 (F)= " + isValidBST_2(n1));
		System.out.println("isValidBST_3 (F)= " + isValidBST_3(n1, null, null));

		// #3 Inorder Traversal
		System.out.println("\n#3 Inorder Traversal:");
		inorderTraversalIter(root);
	}

	public static TreeNode insert(TreeNode root, int key) {
		if (root == null)
			return new TreeNode(key);

		if (root.val > key) {
			root.left = insert(root.left, key);
		} else {
			root.right = insert(root.right, key);
		}

		return root;
	}

	public static TreeNode findMinimum(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	// FindSuccessor
	public static TreeNode findSuccessor(TreeNode root, TreeNode succ, int key) {
		if (root == null)
			return succ;

		if (root.val == key) {
			if (root.right != null) {
				return findMinimum(root.right);
			}
		} else if (root.val > key) {
			succ = root;
			return findSuccessor(root.left, succ, key);
		} else {
			return findSuccessor(root.right, succ, key);
		}
		return succ;
	}

	// DeleteNode
	public static TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.val == key) {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else {
				TreeNode min = root.right;
				while (min.left != null) {
					min = min.left;
				}
				// TreeNode min = findMinimum(root.right);
				root.val = min.val;
				root.right = deleteNode(root.right, min.val);
				return root;
			}
		} else if (root.val > key) {
			root.left = deleteNode(root.left, key);
		} else {
			root.right = deleteNode(root.right, key);
		}
		return root;
	}

	// #2 Validate BST - level order traversal
	public static boolean isValidBST(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (prev != null && root.val <= prev.val)
				return false;
			prev = root;
			root = root.right;
		}
		return false;
	}

	static TreeNode prev = null;

	public static boolean isValidBST_2(TreeNode root) {
		if (root == null)
			return true;
		if (!isValidBST_2(root.left)) {
			return false;
		}
		if (prev != null && root.val <= prev.val) {
			return false;
		}
		prev = root;
		return isValidBST_2(root.right);
	}

	public static boolean isValidBST_3(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if ((min != null && root.val <= min) || (max != null && root.val >= max))
			return false;
		return isValidBST_3(root.left, min, root.val) && isValidBST_3(root.right, root.val, max);
	}

	// #3 Iterative inorder traversal
	// https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
	public static void inorderTraversalIter(TreeNode root) {
		System.out.println("inorderTraversal = " + inorderTraversal(root));
		System.out.println("kthSmallestElem = " + kthSmallestElem(root, 2));
		System.out.println("validateBST = " + validateBST(root));

		System.out.println("preorderTraversal = " + preorder(root));
		System.out.println("postorderTraversal = " + postorder(root));
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
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

	public static int kthSmallestElem(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (--k == 0)
				break;
			root = root.right;
		}
		return root.val;
	}

	public static boolean validateBST(TreeNode root) {
		TreeNode prev = null;
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (prev != null && root.val <= prev.val) {
				return false;
			}
			prev = root;
			root = root.right;
		}
		return true;
	}

	// preorder traversal: right -> left
	public static List<Integer> preorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			list.add(root.val);
			if (root.right != null)
				stack.push(root.right);
			if (root.left != null)
				stack.push(root.left);
		}
		return list;
	}

	// postorder traversal: left -> right
	public static List<Integer> postorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			list.add(0, root.val);
			if (root.left != null)
				stack.push(root.left);
			if (root.right != null)
				stack.push(root.right);
		}
		return list;
	}

}
