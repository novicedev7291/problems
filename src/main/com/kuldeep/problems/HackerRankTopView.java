package com.kuldeep.problems;

import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class HackerRankTopView {

    /*

    class Node
        int data;
        Node left;
        Node right;
    */
    public static void topView(Node root) {
        List<Integer> topViewList = new ArrayList();
        leftView(root.left, topViewList);
        topViewList.add(root.data);
        rightView(root.right, topViewList);
        topViewList.forEach(n -> System.out.printf("%d ", n));
    }

    static void leftView(Node n, List<Integer> topViewList){
        if(n == null) return;
        leftView(n.left, topViewList);
        topViewList.add(n.data);
    }

    static void rightView(Node n, List<Integer> topViewList){
        if(n == null) return;
        topViewList.add(n.data);
        rightView(n.right, topViewList);
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}
