package com.kuldeep.data.structure.graph;

import java.io.*;
import java.util.*;

public class BalancedForest {

    class Graph{
        int value;
        List<Integer> adjacents = new ArrayList();
        Graph(int value){
            this.value = value;
        }
    }

    class TreeNode{
        int value;
        List<TreeNode> children = new ArrayList();
        int enterTime;
        int exitTime;
        TreeNode parent;
        long valueSum;
        TreeNode(int value){
            this.value = value;
        }
    }

    int time;

    // Complete the balancedForest function below.
    public int balancedForest(int[] c, int[][] edges) {
        // Create initial tree/graph from the data
        Graph[] g = new Graph[c.length];
        for(int i = 0; i < c.length; i++){
            g[i] = new Graph(c[i]);
        }
        for(int i = 0; i < edges.length; i++){
            int index1 = edges[i][0]-1;
            int index2 = edges[i][1]-1;
            g[index1].adjacents.add(index2);
            g[index2].adjacents.add(index1);
        }
        if(g.length < 3){
            return -1;
        }
        int minHeightRootIndex = findMinHeightRootIndex(g);
        TreeNode root = buildTreeFromGraph(minHeightRootIndex, g);
        Map<Long, List<Integer>> valueSumEnterTimes = new HashMap();
        Map<Long, List<Integer>> valueSumExitTimes = new HashMap();
        computeValueSumOfSubtrees(root, valueSumEnterTimes, valueSumExitTimes);

        return 0;
    }

    private void computeValueSumOfSubtrees(TreeNode root, Map<Long, List<Integer>> valueSumEnterTimes, Map<Long, List<Integer>> valueSumExitTimes){
        root.valueSum = root.value;
        for(TreeNode child : root.children){
            computeValueSumOfSubtrees(child, valueSumEnterTimes,valueSumExitTimes);
            root.valueSum += child.valueSum;
        }
        addSumValuesToEnterTimes(root, valueSumEnterTimes);
        addSumValuesToExitTimes(root, valueSumExitTimes);
    }

    private void addSumValuesToEnterTimes(TreeNode node, Map<Long, List<Integer>> m){
        long sum = node.valueSum;
        if(m.containsKey(sum)){
            m.get(sum).add(node.enterTime);
        }
        else{
            m.put(sum, new ArrayList());
            m.get(sum).add(node.enterTime);
        }
    }

    private void addSumValuesToExitTimes(TreeNode node, Map<Long, List<Integer>> m){
        long sum = node.valueSum;
        if(m.containsKey(sum)){
            m.get(sum).add(node.exitTime);
        }
        else{
            m.put(sum, new ArrayList());
            m.get(sum).add(node.exitTime);
        }
    }

    private TreeNode buildTreeFromGraph(int rootIndex, Graph[] g){
        time = 1;
        boolean[] visited = new boolean[g.length];
        return buildTree(rootIndex, g, visited, null);
    }

    private TreeNode buildTree(int rootIndex, Graph[] g, boolean[] visited, TreeNode parent){
        Graph node = g[rootIndex];
        TreeNode root = new TreeNode(node.value);
        visited[rootIndex] = true;
        root.enterTime = time;
        for(int i = 0; i < node.adjacents.size(); i++){
            int index = g[rootIndex].adjacents.get(i);
            if(!visited[index]){
                visited[index] = true;
                root.children.add(buildTree(index, g, visited, root));
            }
        }
        time++;
        root.exitTime = time;
        return root;
    }

    private int findMinHeightRootIndex(Graph[] g){
        Queue<Integer> q = new LinkedList();
        boolean[] visited = new boolean[g.length];
        for(int i = 0; i < g.length; i++){
            if(g[i].adjacents.size() == 1){
                visited[i] = true;
                q.offer(i);
            }
        }

        int rootIndex = -1;
        while(!q.isEmpty()){
            int head = q.poll();
            rootIndex = head;
            for(int i = 0; i < g[i].adjacents.size(); i++){
                if(!visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        return rootIndex;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n - 1][2];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int result = new BalancedForest().balancedForest(c, edges);
        }

        scanner.close();
    }
}

