package com.kuldeep.b2bswe.trees;

import java.util.Stack;

public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree treeUtil = new SymmetricTree();
        TreeNode root = treeUtil.createBinaryTree(new int[]{5, 10, 40, 30, 28});
        treeUtil.inOrderIterative(root);
        System.out.println(String.valueOf(Character.toChars(128149)));
    }

    public TreeNode createBinaryTree(int[] arr) {
        return buildTree(arr, 0, arr.length - 1);
    }

    public void inOrderIterative(TreeNode root) {
        if(root == null) return;
        TreeNode temp = root;

        Stack<TreeNode> s = new Stack<>();
        s.push(temp);

        temp = temp.left;

        while(!s.isEmpty() || temp != null) {
            if(temp == null) {
                temp = s.pop();
                System.out.printf("%d\t", temp.val);

                temp = temp.right;
            }else {
                s.push(temp);
                temp = temp.left;
            }
        }
    }

    public void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.printf("%d\t", root.val);
        inOrder(root.right);
    }

    public TreeNode buildTree(int[] arr, int start, int end) {
        if(start > end) return null;
        if(start == end) return TreeNode.of(arr[start], null, null);

        int maxIndex = max(arr, start, end);
        return TreeNode.of(
                arr[maxIndex],
                buildTree(arr, start, maxIndex-1),
                buildTree(arr, maxIndex + 1, end)
        );
    }

    private int max(int[] arr, int start, int end) {
        int maxIndex = start;

        int i = start + 1;
        while(i <= end) {
            if(arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
            i++;
        }

        return maxIndex;
    }
}

class TreeNode {
    public final Integer val;
    public final TreeNode left;
    public final TreeNode right;

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode of(Integer val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }
}