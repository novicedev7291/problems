package com.kuldeep.algorithims;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */

public class LeftViewBinaryTree {

    public static class TreeNode {
        Integer value;
        TreeNode left;
        TreeNode right;

        private TreeNode(int value) {
            this.value = value;
        }

        public TreeNode() {}

        public static TreeNode of(int value) {
            return new TreeNode(value);
        }

        TreeNode addLeftChild(TreeNode left) {
            this.left = left;
            return this;
        }

        TreeNode addRightChild(TreeNode right) {
            this.right = right;
            return this;
        }
    }

    public List<Integer> leftView(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        queue.offer(new TreeNode());

        List<Integer> result = new ArrayList<>();

// The below method uses extra loop so let's try with null marker at the end of node
//        while (!queue.isEmpty()) {
//            int nodesAtLevel = queue.size();
//
//            for (int i = 0 ; i < nodesAtLevel; i++) {
//                TreeNode node = queue.poll();
//                if (node == null) break;
//
//                if (i == 0) result.add(node.value);
//
//                if (node.left != null) queue.offer(node.left);
//                if (node.right != null) queue.offer(node.right);
//            }
//        }
        result.add(root.value);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // level ends
            if (node.value == null) {
                // Means all nodes of next level are also explored, so add marker
                queue.offer(new TreeNode());

                assert queue.peek() != null;

                // Means, we don't have any nodes left to explore
                if (queue.peek().value == null) return result;
                else {
                    result.add(queue.peek().value);
                    continue;
                }
            }

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return result;
    }
}
