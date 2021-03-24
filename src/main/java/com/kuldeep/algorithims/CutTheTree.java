package com.kuldeep.algorithims;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Solution {

    /*
     * Complete the 'cutTheTree' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY data
     *  2. 2D_INTEGER_ARRAY edges
     */
    static class Node{
        int i;
        int value;
        int sum = 0;
        List<Node> children = new ArrayList();
        Node(int i, int value){
            this.i = i;
            this.value = value;
        }
    }
    static int min = Integer.MAX_VALUE;
    static int treeSum = 0;
    static Set<Integer> seen = new HashSet();
    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
        Map<Integer, Node> nodesM = new HashMap();
        for(int i = 1; i <= data.size(); i++){
            Node t = new Node(i, data.get(i-1));
            nodesM.put(i, t);
            treeSum += data.get(i-1);
        }
        for(List<Integer> e : edges){
            Integer n1 = e.get(0);
            Integer n2 = e.get(1);
            Node node1 = nodesM.get(n1);
            Node node2 = nodesM.get(n2);
            node1.children.add(node2);
            node2.children.add(node1);
        }
        Node root = nodesM.get(1);
        cutTheTreeHelper(root);
        return min;
    }
    private static int cutTheTreeHelper(Node node){
        if(seen.contains(node.i)) return 0;
        seen.add(node.i);
        node.sum += node.value;
        for(Node t : node.children){
            if(!seen.contains(t.i)){
                node.sum += cutTheTreeHelper(t);
            }
        }
        int diff = Math.abs(2*node.sum - treeSum);
        min = Math.min(diff, min);
        return node.sum;
    }

}

public class CutTheTree {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(CutTheTree.class.getResourceAsStream("input06.txt")));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Solution.cutTheTree(data, edges);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}