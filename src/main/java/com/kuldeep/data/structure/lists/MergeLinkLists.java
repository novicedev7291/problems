package com.kuldeep.data.structure.lists;

public class MergeLinkLists {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        ListNode l1 = createList(arr1);
        ListNode l2 = createList(arr2);
        ListNode mlHead = new Solution().mergeTwoLists(l1, l2);
        ListNode temp = mlHead;
        while (temp != null){
            System.out.printf(temp.val+"->");
            temp = temp.next;
        }
    }

    public static ListNode createList(int[] arr){
        ListNode head = null;
        for(int i = 0 ; i < arr.length; i++){
            head = insert(head, arr[i]);
        }
        return head;
    }

    public static ListNode insert(ListNode head, int val){
        if(head == null) return new ListNode(val);
        ListNode node = head;
        while(node.next != null) {
            node = node.next;
        }
        node.next = new ListNode(val);
        return head;
    }

}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val > l2.val){
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        ListNode head = l1;

        while(l2 != null){
            while(l1.next != null && l1.next.val < l2.val){
                l1 = l1.next;
            }

           ListNode nextL2 = l2.next;
           l2.next = l1.next;
           l1.next = l2;
           l2 = nextL2;
        }

        return head;
    }
}
