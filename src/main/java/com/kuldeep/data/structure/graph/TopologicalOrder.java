package com.kuldeep.data.structure.graph;

import java.util.*;

public class TopologicalOrder {
    Stack<Integer> stk;
    void topSort(Map<Integer, List<Integer>> g){
        stk = new Stack<Integer>();
        Set<Integer> visited = new HashSet();
        for(Map.Entry<Integer, List<Integer>> e : g.entrySet()){
            dfs(g, visited, e.getKey());
        }

        System.out.println();
        while(!stk.isEmpty()){
            System.out.printf("%d -> ", stk.pop());
        }
    }

    void dfs(Map<Integer, List<Integer>> g, Set<Integer> visited, Integer s){
        if(visited.contains(s)) return;

        visited.add(s);
        List<Integer> edges = g.get(s);
        if(edges != null){
            for(Integer e: edges){
                dfs(g, visited, e);
            }
        }
        stk.push(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Map<Integer, List<Integer>> g = new TreeMap<>();
        while(t-- != 0){
            int V = sc.nextInt();
            int E = sc.nextInt();

            List<Integer> l = g.get(V);
            if(l == null){
                List<Integer> edges = new ArrayList();
                edges.add(E);
                g.put(V, edges);
            }
            else{
                l.add(E);
            }
            List<Integer> rl = g.get(E);
            if(rl == null){
                rl = new ArrayList<>();
                rl.add(V);
                g.put(E, rl);
            }else{
                rl.add(V);
            }
        }
        new TopologicalOrder().topSort(g);
    }
}
