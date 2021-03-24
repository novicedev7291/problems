package com.kuldeep.problems;

import java.io.*;
import java.util.*;

public class SwapNodesAlgo {
    static class TreeNode{
        int n;
        TreeNode left;
        TreeNode right;
        TreeNode(int n){ this.n = n;}
    }
    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        TreeNode root = buildTree(indexes);
        Map<Integer, List<TreeNode>> levelMap = new HashMap();
        levelOrder(root, levelMap);
        int[][] result = new int[queries.length][];
        for(int i = 0; i < queries.length; i++){
            int q = queries[i];
            int k = q;
            int mul = 2;
            while(levelMap.containsKey(k)){
                List<TreeNode> nodesAtLevel = levelMap.get(k);
                for(TreeNode t: nodesAtLevel) {
                    swapNodeHelper(t);
                }
                k = q * mul;
                mul += 1;
            }
            List<Integer> inOrderList = new ArrayList();
            inorder(root, inOrderList);
            result[i] = inOrderList.stream().mapToInt(Integer::intValue).toArray();
        }

        return result;
    }

    static void inorder(TreeNode root, List<Integer> l){
        if(root == null) return;
        inorder(root.left, l);
        l.add(root.n);
        inorder(root.right, l);
    }

    static void swapNodeHelper(TreeNode t){
        TreeNode temp = t.left;
        t.left = t.right;
        t.right = temp;
    }

    static void levelOrder(TreeNode root, Map<Integer, List<TreeNode>> m){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        int d = 1;
        while(true){
            int nodeCount = q.size();
            if(nodeCount == 0) break;
            while(nodeCount > 0){
                TreeNode t = q.poll();
                if(t.left != null){
                    q.offer(t.left);
                }
                if(t.right != null){
                    q.offer(t.right);
                }
                nodeCount--;
                if(m.containsKey(d)){
                    m.get(d).add(t);
                }
                else{
                    List<TreeNode> l = new LinkedList();
                    l.add(t);
                    m.put(d, l);
                }
                if(nodeCount == 0){
                    d += 1;
                }
            }
        }
    }

    static TreeNode buildTree(int[][] arr){
        TreeNode root = new TreeNode(1);
        Queue<TreeNode> q = new LinkedList();
        int i = 0;
        int rows = arr.length;
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            if(i < rows && arr[i][0] != -1){
                t.left = new TreeNode(arr[i][0]);
                q.offer(t.left);
            }
            if(i < rows && arr[i][1] != -1){
                t.right = new TreeNode(arr[i][1]);
                q.offer(t.right);
            }
            i++;
        }
        return root;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/ni3/my-swap-algo.output"));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

