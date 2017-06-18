package com.kuldeep.problems;

public class MajorityElement {
	
	private int[] nums;
	private int n;
	
	class Node{
		int key,count;
		Node left, right;
		public Node(int key){
			this.key = key;
			this.count = 1;
		}
	}
	
	Node root;
	
	public MajorityElement(int[] arr) {
		root = null;
		nums = arr;
		n = arr.length;
	}
	
	public int findMajorityElement(){
		for(int i = 0; i < n; i++){
			insert(nums[i]);
		}
		return 0;
	}
	
	private void inorderRec(Node root){
		if(root != null){
			inorderRec(root.left);
			inorderRec(root.right);
		}
	}
	
	private void insert(int key){
		root = insertRec(root, key);
	}
	
	private Node insertRec(Node root,int key){
		if(root == null){
			root = new Node(key);
			return root;
		}
		if(root.key == key && root.count == n/2){
			return root;
		}
		if(key < root.key){
			root.left = insertRec(root.left, key);
		}else if(key > root.key){
			root.right = insertRec(root.right, key);
		}else{
			root.count += 1;
		}
		return root;
	}

}
