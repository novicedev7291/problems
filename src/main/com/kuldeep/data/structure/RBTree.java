package com.kuldeep.data.structure;

public class RBTree {
    private Node root;

    class Node{
        public static final int BLACK = 0;
        public static final int RED = 1;
        int e, color;
        Node left, right, parent, uncle;

        Node(int e){
            this.e = e;
            this.right = this.left = null;
            this.color = RED;
        }
    }

    public Node insert(Node root, int e){


        return root;

    }



}
