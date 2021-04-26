package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCodeCourseSchedule {
    public static void main(String[] args) {
        LeetCodeCourseSchedule sol = new LeetCodeCourseSchedule();

//        final int[] order = sol.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3,1}, {3,2}});
        final int[] order = sol.findOrder(1, new int[][]{});
        for(int el : order) {
            System.out.println(el);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = createGraph(prerequisites);

        int totalDeps = graph.size();

        Map<Integer, Integer> indegrees = calcIndegrees(graph);

        List<Integer> result = new ArrayList();

        Deque<Integer> queue = new LinkedList();

        indegrees.forEach((k, v) -> {
            if(v == 0) {
                queue.offer(k);
            }
        });

        int count = 0;

        while(!queue.isEmpty()) {
            Integer vertex = queue.poll();

            for(Integer child : graph.get(vertex)) {
                indegrees.compute(child, (k , v) -> v - 1);

                if(indegrees.get(child) == 0) {
                    queue.offer(child);
                }
            }

            count += 1;

            result.add(vertex);
        }

        if(count != totalDeps)
            return new int[]{};


        int[] temp = new int[result.size()];
        int i = 0;
        for(Integer r : result) {
            temp[i] = r;
            i++;
        }

        return temp;
    }

    private Map<Integer, Integer> calcIndegrees(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> in = new HashMap();

        graph.forEach(
                (k, v) -> {
                    in.computeIfAbsent(k, key1 -> 0);
                    v.forEach(child -> in.compute(child, (key2, val) -> val == null ? 1 :  val + 1));
                }
        );

        return in;
    }

    private Map<Integer, List<Integer>> createGraph(int[][] preq) {
        Map<Integer, List<Integer>> g = new HashMap();

        for(int[] relation: preq) {
            List<Integer> edges = g.computeIfAbsent(relation[1], k -> new ArrayList());
            edges.add(relation[0]);

            g.computeIfAbsent(relation[0], k -> new ArrayList<>());
        }

        return g;
    }
}
