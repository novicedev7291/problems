package com.kuldeep.data.structure;

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
