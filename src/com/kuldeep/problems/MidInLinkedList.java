package com.kuldeep.problems;

public class MidInLinkedList {
	private class Node{
		private int key;
		private Node next;
		
		public Node(int key) {
			this.key = key;
			this.next = null;
		}
		
		public void addNext(Node next){
			this.next = next;
		}
		
		public Node next(){
			return this.next;
		}
	}
	
	private Node tail;
	private Node head;
	
	public void insert(int key){
		if(tail == null){
			tail = new Node(key);
			head = tail;
		}else{
			Node temp = new Node(key);
			tail.addNext(temp);
			tail = temp;
		}
	}
	
	public int delete(int key){
		if(head != null && head.key == key){
			head = head.next;
			return head.key;
		}
		else{
			Node start = head;
			while(start.next != null){
				if(start.next.key == key){
					Node toDelete = start.next;
					start.next = start.next.next;
					int info = toDelete.key;
					toDelete = null;
					return info;
				}
				start = start.next;
			}
		}
		return 0;
	}
	
	public boolean find(int key){
		Node start = head;
		while(start != null){
			if(start.key == key){
				return true;
			}
			start = start.next();
		}
		return false;
	}

	/*
	 * find mid of linked list in one pass with complexity O(n/2)
	 */
	public int mid(){
		Node start = head, mid = start;
		int i = 0;
		while(start.next != null){
			i++;
			if(i%2 == 0){
				mid = mid.next;
			}
			start = start.next;
		}
		
		/*if(i%2 == 1){
			mid = mid.next;
		}*/
		return mid.key;
	}
	
	public static void main(String[] args){
		int[] nums = {1,4,2,10,23,19,7,89};
		MidInLinkedList obj = new MidInLinkedList();
		
		for(int i = 0; i < nums.length; i++){
			obj.insert(nums[i]);
		}
		
		System.out.println(obj.delete(7));
		System.out.println("Mid : " + obj.mid());
		
	}
}
