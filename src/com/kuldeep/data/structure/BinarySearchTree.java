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
	public void inorder(Node root, Vector<Node> nodes){
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
	/*
	 * This function would make the balanced binary tree, it would also take O(n) time, 
	 * as we would visit each element of the sorted array
	 */
	public Node buildBalancedBinaryTree(Vector<Node> nodes, int start, int end){
		if(start > end){
			return null;
		}
		
		int mid = start + (end - start)/2;
		Node node = nodes.get(mid);
		
		node.left = buildBalancedBinaryTree(nodes, start, mid-1);
		node.right = buildBalancedBinaryTree(nodes, start, mid-1);
		
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

}
