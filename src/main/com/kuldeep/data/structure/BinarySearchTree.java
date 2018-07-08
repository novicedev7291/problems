package com.kuldeep.data.structure;

import java.util.Vector;

public class BinarySearchTree {
	class Node {
		public int key;
		public Node left,right;
		
		public Node(int key) {
			this.key = key;
			this.left = this.right = null;
		}
	}
	
	Node root;
	
	public BinarySearchTree() {
		root = null;
	}
	/*
	 * Function to insert into BST. Worst case time complexity be O(h) where h is the
	 * height of the tree, for a skewed tree it may become n and time complexity O(n).
	 */
	public void insert(int key){
		root = insertRec(root, key);
	}
	
	/*
	 * Inorder traversal to traverse BST in sorted order always
	 */
	private void inorder(Node root, Vector<Node> nodes){
		if(root == null){
			return;
		}
		/*
		 * It will traverse till the left leaf is available in tree, when not found start adding to the Vector array
		 */
		inorder(root.left, nodes);
		nodes.add(root);
		/*
		 * Then traverse the right part of the main root, and start adding node from start into vector array.
		 * It will take O(n) time as need to traverse all element into tree.
		 */
		inorder(root.right, nodes);
	}

	public void preOrder(){
		preOrderTraversal(root);
	}

	private void preOrderTraversal(Node root){
		if(root == null){
			return;
		}

		System.out.print("\t" + root.key);
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}

	public void inorder(){
		inorderTraversal(root);
	}

	private void inorderTraversal(Node root){
		if(root == null) return;

		inorderTraversal(root.left);
		System.out.print("\t"+root.key);
		inorderTraversal(root.right);
	}
	/*
	 * This function would make the balanced binary tree(assumes array is sorted always), it would also take O(n) time,
	 * as we would visit each element of the sorted array
	 */
	public Node buildBalancedBinaryTree(Vector<Node> nodes, int start, int end){
		if(start > end){
			return null;
		}
		
		int mid = start + (end - start)/2;
		Node node = nodes.get(mid);
		
		node.left = buildBalancedBinaryTree(nodes, start, mid-1);
		node.right = buildBalancedBinaryTree(nodes, mid-1, end);
		
		return node;
	}
	
	private Node insertRec(Node root, int key){
		/*
		 * If tree is empty then return a new node which root.
		 */
		if(root == null){
			root = new Node(key);
			return root;
		}
		/*
		 * Check if key is less than root's key then it should go to the left 
		 * of tree.
		 */
		if(key < root.key){
			root.left = insertRec(root.left, key);
		}
		/*
		 * If key is greater than move new node to right.
		 */
		else if(key > root.key){
			root.right = insertRec(root.right, key);
		}
		
		return root;
	}
	
	public Node search(Node root,int key){
		if(root == null || root.key == key) return root;
		
		if(root.key > key)
			search(root.left, key);
		return search(root.right, key);
	}

	public void delete(int key){
		root = deleteNode(root, key);
	}

	private Node deleteNode(Node root, int key) {
		if(root == null) return null;

		//Find the matching node with key to be deleted
		// If less then traverse the left tree
		if(key < root.key){
			root.left = deleteNode(root.left, key);
		}// if greater then travers the right tree
		else if(key > root.key){
			root.right = deleteNode(root.right, key);
		}
		else //Matching node is found
		{
			if(root.left == null){
				return root.right;
			}else if(root.right == null){
				return root.left;
			}
			root.key = inorderSuccessor(root.right);
			root.right = deleteNode(root.right, root.key);
		}
		return root;
	}

	private int inorderSuccessor(Node node) {
		int min = node.key;
		while(node.left != null){
			min = node.left.key;
			node = node.left;
		}
		return min;
	}

	public int height(){
		return getHeight(root);
	}

	private int getHeight(Node root) {
		if(root == null){
			return 0;
		}

		int lHeight = getHeight(root.left);
		int rHeight = getHeight(root.right);

		if(lHeight > rHeight){
			return lHeight  + 1;
		}
		return rHeight + 1;
	}

}
