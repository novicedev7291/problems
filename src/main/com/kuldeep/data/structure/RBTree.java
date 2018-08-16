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

        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;

            if(other != null){
                return this.e == other.e;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(e) + Integer.hashCode(color);
        }
    }

    public void insert(int e){
        // First recursively add the new node to the tree following the principals of BST tree
        insertRecursive(root, e);

        //Repair the tree recursively to balance it.
        repairRecursively(root);
    }

    /**
     * Important method which is doing all the work for fixing the tree after a node is inserted, it works on few properties
     * which needs to be satisfied which are as below.
     * <ul>
     * <li>Property 1 (every node is either red or black) and Property 3 (all leaves are black) always holds.</li>
     * <li>Property 2 (the root is black) is checked and corrected with case 1.</li>
     * <li>Property 4 (red nodes have only black children) is threatened only by adding a red node, repainting a node from black to red, or a rotation.</li>
     * <li>Property 5 (all paths from any given node to its leaves have the same number of black nodes) is threatened only by adding a black node, repainting a node, or a rotation.</li>
     * </ul>
     *
     * <p>For more information check this out @see <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">RedBlack Tree wiki</a>
     * </p>
     *
     * @param n
     */
    private void repairRecursively(Node n) {
        if(n.parent == null){
            // Case 1: When the only node is the root node
            n.color = Node.BLACK;
            return;
        }
        if(n.parent.color == Node.BLACK){
            // Case 2: The current node's parent is black node, so property 4 (Both children of red node are black) is not invalidated
            return;
        }
        if(getUncle(n) != null && getUncle(n).color == Node.RED) {
            // Case 3: when newly added node is added to the left or right child of parent red node and uncle of the node is also red
            parent(n).color = Node.BLACK;
            getUncle(n).color = Node.BLACK;
            grandParent(n).color = Node.RED;
            repairRecursively(grandParent(n));
            return;
        }
        if(getUncle(n) != null && getUncle(n).color == Node.BLACK){
            // Case 4: When parent node is red and uncle node is black and current node happens to be the right child of parent and parent
            // is the left child of grand parent violating property 4 (red nodes have both black children)
            Node parent = n.parent;
            Node gp = grandParent(n);

            if(n.equals(gp.left.right)){
                rotateLeft(parent);
            }
            else if(n.equals(gp.right.left)){
                rotateRight(parent);
            }
        }

        if(n.parent.color == Node.RED && getUncle(n).color == Node.BLACK){
            // Case 5: When parent node is red and uncle node is black and current node is either left child of grand parent's left child or
            // right child of right child of its grand parent, still violating property 4
            Node gp = grandParent(n);
            Node parent = n.parent;

            if(n.equals(parent.left)){
                rotateRight(gp);
            }
            else if(n.equals(parent.right)){
                rotateLeft(gp);
            }

            //switch color of parent and gp to satisfy property 4
            int gpColor = gp.color;
            gp.color = parent.color;
            parent.color = gpColor;

        }

    }

    private void rotateLeft(Node y){
        Node x = y.right;
        y.right = x.left;
        x.left = y;

    }

    private void rotateRight(Node y){
        Node x = y.left;
        y.left = x.right;
        x.right = y;
    }

    private Node getUncle(Node n){
        Node gp = grandParent(n);
        Node parent = parent(n);
        if((gp != null && parent != null)){
            if(parent.equals(gp.left)){
                return gp.right;
            }
            else if(parent.equals(gp.right)){
                return gp.left;
            }
        }
        return null;
    }

    private Node parent(Node n){
        if(n != null){
            return n.parent;
        }
        return null;
    }

    private Node grandParent(Node n){
        if(n != null){
            Node parent = parent(n);
            if(parent != null){
                return parent.parent;
            }
        }
        return null;
    }

    private Node insertRecursive(Node root, int e){
        if(root == null){
            return new Node(e);
        }

        if(e < root.e){
            root.left = insertRecursive(root.left, e);
            root.left.parent = root;
        }
        else if(e > root.e){
            root.right = insertRecursive(root.right, e);
            root.right.parent = root;
        }
        else{
            return root;
        }

        return root;
    }



}
