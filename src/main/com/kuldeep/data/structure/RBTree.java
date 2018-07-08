package com.kuldeep.data.structure;

public class RBTree {
    private Node root, parent;

    class Node{
        public static final String BLACK = "B";
        public static final String RED = "R";
        int e;
        Node left, right;
        String color = RED;

        Node(int e){
            this.e = e;
            this.right = this.left = null;
        }
    }

    public Node insert(Node root, int e){
        if(root == null){
            root = new Node(e);
            return root;
        }

        if(e < root.e){
            root.left = insert(root.left, e);
        }
        else{
            root.right = insert(root.right, e);
        }

        parent = root;

        return root;

    }



}
