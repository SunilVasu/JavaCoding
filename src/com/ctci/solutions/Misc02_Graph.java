package com.ctci.solutions;

public class Misc02_Graph {
	public static void main(String[] args) {
		System.out.println("#Misc Graph");

		System.out.println("\n#1 Create Minimal BST Tree");
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // not balanced
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 }; // balanced
		TreeNode_ root = minimalTree(arr);

		Tree_ tree = new Tree_();

		System.out.println("\n#2 Searching Element in BST");
		TreeNode_ node = tree.search(root, 5);

		if (node == null) {
			System.out.println("#Search = Element not found!");
		} else {
			System.out.println("#Search found = " + node.value);
		}

		System.out.println("\n#3 Finding Minimum in BST");
		System.out.println("#Minimum = " + tree.findMinimum(root).value);

		System.out.println("\n#3 Finding Maximum in BST");
		System.out.println("#Maximum = " + tree.findMaximum(root).value);

		System.out.println("\n#4 Insert element");
		int elem = 10;
		node = tree.insert(root, elem);
		System.out.println("After Insert: " + elem);
		printTree(node, "");

		System.out.println("\n#5 Delete element");
		node = tree.delete(root, elem);
		System.out.println("After Delete: " + elem);
		printTree(node, "");

		elem = 7;
		node = tree.delete(root, elem);
		System.out.println("After Delete: " + elem);
		printTree(node, "");

		System.out.println("\n#6 New BST");
		TreeNode_ rootNode = new TreeNode_(52);
		rootNode = tree.insert(rootNode, 15);
		rootNode = tree.insert(rootNode, 56);
		rootNode = tree.insert(rootNode, 9);
		rootNode = tree.insert(rootNode, 16);
		rootNode = tree.insert(rootNode, 54);
		rootNode = tree.insert(rootNode, 3);
		rootNode = tree.insert(rootNode, 10);
		rootNode = tree.insert(rootNode, 65);
		rootNode = tree.insert(rootNode, 72);
		rootNode = tree.insert(rootNode, 61);
		rootNode = tree.insert(rootNode, 80);
		System.out.println("\n#Print BST::");
		printTree(rootNode, "");
		rootNode = tree.delete(rootNode, 80);
		rootNode = tree.delete(rootNode, 56);
		System.out.println("\n#Print BST::");
		printTree(rootNode, "");

	}

	// Create MinimalTree from array
	public static TreeNode_ minimalTree(int[] arr) {
		TreeNode_ root = createMinimalBST(arr, 0, arr.length - 1);
		printTree(root, "");
		return root;
	}

	public static TreeNode_ createMinimalBST(int arr[], int start, int end) {
		if (end < start)
			return null;
		int mid = (start + end) / 2;
		TreeNode_ n = new TreeNode_(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}

	// META
	// PRINTING: This is not inorder traversal; for printing only
	public static void printTree(TreeNode_ node, String prefix) {
		if (node == null)
			return;
		printTree(node.right, prefix + "\t(r)");
		System.out.println(prefix + "-->" + node.value);
		printTree(node.left, prefix + "\t(l)");
	}
}

class TreeNode_ {
	int value;
	TreeNode_ left;
	TreeNode_ right;

	public TreeNode_(int val) {
		this.value = val;
		left = null;
		right = null;
	}

	public void setLeftChild(TreeNode_ n) {
		left = n;
	}

	public void setRightChild(TreeNode_ n) {
		right = n;
	}
}

class Tree_ {
	public static TreeNode_ search(TreeNode_ node, int x) {
		if (node == null || node.value == x)
			return node;
		if (node.value > x)
			return search(node.left, x);
		else
			return search(node.right, x);
	}

	// return the left most element
	public static TreeNode_ findMinimum(TreeNode_ root) {
		if (root == null)
			return null;
		if (root.left == null) {
			return root;
		} else {
			return findMinimum(root.left);
		}
	}

	public static TreeNode_ findMaximum(TreeNode_ root) {
		if (root == null)
			return null;
		if (root.right == null)
			return root;
		else
			return findMaximum(root.right);
	}

	public static TreeNode_ insert(TreeNode_ root, int x) {
		if (root == null)
			return new TreeNode_(x);
		if (root.value > x) {
			root.left = insert(root.left, x);
		} else if (root.value < x) {
			root.right = insert(root.right, x);
		}
		return root;
	}

	public static TreeNode_ delete(TreeNode_ root, int key) {
		if (root == null) {
			return null;
		}
		if (root.value == key) {
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else {
				// substitue by SMALLEST from right; and delete that node
				TreeNode_ curr = root.right;
				while (curr.left != null) {
					curr = curr.left;
				}
				root.value = curr.value;
				root.right = delete(root.right, curr.value);

				// substitue by LARGEST from left; and delete that node
				// TreeNode_ curr = root.left;
				// while (curr.right != null) {
				// curr = curr.right;
				// }
				// root.value = curr.value;
				// root.left = delete(root.left, curr.value);
			}
		} else if (root.value > key) {
			root.left = delete(root.left, key);
		} else {
			root.right = delete(root.right, key);
		}
		return root;
	}
}