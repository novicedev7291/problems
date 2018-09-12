package com.kuldeep.data.structure.lists;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class DLLNode
{
    int data;
    DLLNode d;
    DLLNode t;
    DLLNode(int v) {data = v; d= null; t=null;}
}
class BeingZeroDLL
{
    DLLNode last;
    DLLNode insert(DLLNode head, int data)
    {
        if(head == null){
            head = new DLLNode(data);
            last = head;
        }else{
            DLLNode l = new DLLNode(data);
            last.d = l;
            last = l;
        }
        return head;

    }
    DLLNode clone(DLLNode h)
    {
        if(h == null) return null;
        DLLNode firstHead = h, newHead = null, secondHead;

        Map<Integer, Queue<DLLNode>> pointerMap = new HashMap<>();

        while(firstHead != null){
            if(pointerMap.containsKey(firstHead.data)){
                Queue<DLLNode> q = pointerMap.get(firstHead.data);
                q.add(firstHead.d);
                pointerMap.put(firstHead.data, q);
            }else{
                Queue<DLLNode> q = new LinkedList();
                q.add(firstHead.d);
                pointerMap.put(firstHead.data, q);
            }
            firstHead =  firstHead.d;
        }

        firstHead = h;

        while(firstHead != null){
            newHead = insert(newHead, firstHead.data);
            firstHead = firstHead.d;
        }

        firstHead = h;
        secondHead = newHead;

        while(firstHead != null){
            DLLNode current = firstHead;
            firstHead = firstHead.d;
            current.d = secondHead;
            secondHead.t = current;
            secondHead = secondHead.d;
        }

        secondHead = newHead;

        while(secondHead != null){
            if(secondHead.t != null && secondHead.t.t != null){
                secondHead.t = secondHead.t.t.d;
            }
            else{
                secondHead.t = null;
            }
            secondHead = secondHead.d;
        }

        firstHead = h;
        while(firstHead != null){
            Queue<DLLNode> q = pointerMap.get(firstHead.data);
            if(q != null && !q.isEmpty()){
                DLLNode next = q.remove();
                firstHead.d = next;
            }else{
                firstHead.d = null;
            }
            firstHead = firstHead.d;
        }

        return newHead;
    }
}

public class DoublyTroublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5};
        BeingZeroDLL o = new BeingZeroDLL();
        DLLNode head = o.insert(null, 1);
        for(int i = 0 ; i < arr.length; i++){
            o.insert(head, arr[i]);
        }

        head.d.t = head;
        head.d.d.t = head;
        DLLNode temp = head;
        DLLNode temp1 = o.clone(head);
        System.out.println();
        while (temp != null){
            System.out.printf(" %d -> ", temp.data);
            if(temp.t != null){
                System.out.printf("[%d]", temp.t.data);
            }
            temp = temp.d;
        }
        System.out.println();
        while (temp1 != null){
            System.out.printf(" %d -> ", temp1.data);
            if(temp1.t != null){
                System.out.printf("[%d]", temp1.t.data);
            }
            temp1 = temp1.d;
        }
        System.out.println();

    }
}
