package com.kuldeep.data.structure.lists;

import java.util.Scanner;

public class LinkedLists {
    class LLNode
    {
        int data;
        LLNode next;
        LLNode(int d) {data = d; next = null; }
    }

    void printList(LLNode head)
    {
        LLNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    LLNode insertInBegining(LLNode head, int data){
        LLNode t = new LLNode(data);
        t.next = head;
        return t;
    }

    LLNode deleteNode(LLNode head, int data){
        LLNode prev = null, temp = head;

        if(head != null && head.data == data) {
            head = head.next;
            return head;
        }

        while(temp != null && temp.data != data){
            prev = temp;
            temp = temp.next;
        }

        if(temp == null) return head;

        prev.next = temp.next;

        return head;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0){
            int N = sc.nextInt();

            LinkedLists ll = new LinkedLists();
            LLNode head = null;
            for(int i = 0 ; i < N; i++){
                head = ll.insertInBegining(head, sc.nextInt());
            }

            ll.printList(head);
            //head = ll.deleteNode(head, 5);
            head = ll.deleteNode(head, 5);
            System.out.println(head.data);
            head = ll.deleteNode(head, 3);
            //System.out.println(head.data);
            ll.printList(head);
            head = ll.deleteNode(head, 3);
            //ll.deleteNode(head, 1);
            ll.printList(head);
        }

    }
}
