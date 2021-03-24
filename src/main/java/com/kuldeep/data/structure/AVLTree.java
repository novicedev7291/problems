package com.kuldeep.data.structure;

public class AVLTree {
    class Node{
        int key;
        Node left, right;

        Node(int key){
            this.key = key;
            this.left = this.right = null;
        }
    }

    Node root;

    public void preOrder(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root) {
        if(root == null) return;
        System.out.print("\t" + root.key);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void inOrder(){
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if(root == null) return;
        inOrderTraversal(root.left);
        System.out.print("\t" + root.key);
        inOrderTraversal(root.right);
    }

    public void insert(int key){
        root = insertKey(key, root);
    }

    private Node insertKey(int key, Node root) {
        if(root == null) return new Node(key);

        if(key < root.key){
            root.left = insertKey(key, root.left);
        }
        else if(key > root.key){
            root.right = insertKey(key, root.right);
        }
        //Duplicates are not allowed
        else{
            return root;
        }

        return balanceTree(root, key);
    }

    private Node balanceTree(Node root, int key){
        int balanceFactor = findBalanceFactor(root);

        // Balance factor > 1 and key < root.left child, means left tree is heavy than right tree, rotateRight(root)
        if(balanceFactor > 1 && key < root.left.key){
            return rotateRight(root);
        }
        // Balance factor < -1 and key > root.right child, means right tree is more heavy, rotateLeft(root)
        else if(balanceFactor < -1 && key > root.right.key){
            return rotateLeft(root);
        }
        // Balance factor > 1  and key > root.left child, first rotateLeft(root.left) then rotateRight(root)
        else if(balanceFactor > 1 && key > root.left.key){
            rotateLeft(root.left);
            return rotateRight(root);
        }
        // Balance factor < -1  and key < root.right child, first rotateRight(root.right) then rotateRight(root)
        else if(balanceFactor < -1 && key < root.right.key) {
            rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public void delete(int key){
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, int key) {
        if(root == null) return null;

        if(key < root.key){
            root.left = deleteNode(root.left, key);
        }
        else if(key > root.key){
            root.right = deleteNode(root.right, key);
        }
        else{
            //Match is found need to delete the node
            // Case 1 when left node is null of node being deleted
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }

            root.key = inOrderSuccessor(root.right);
            root.right = deleteNode(root.right, root.key);
        }
        return balanceTree(root, key);
    }

    private int inOrderSuccessor(Node root) {
        int min = root.key;

        while(root.left != null){
            min = root.left.key;
            root.left = root.left.left;
        }

        return min;
    }

    private Node rotateLeft(Node root) {
        Node x = root.right;
        Node y = x.left;

        x.left = root;
        root.right = y;

        return x;
    }

    private Node rotateRight(Node root) {
        Node x = root.left;
        Node y = x.right;

        x.right = root;
        root.left = y;


        return x;
    }

    private int findBalanceFactor(Node root) {
        return height(root.left) - height(root.right);
    }

    public int height(){
        return height(root);
    }

    private int height(Node root) {
        if(root == null) return 0;

        int lHeight = height(root.left);
        int rHeight = height(root.right);

        if(lHeight > rHeight){
            return lHeight + 1;
        }
        return rHeight + 1;
    }
}
