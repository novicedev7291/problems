package com.kuldeep.problems.backtracking;

public class MinCostTollGate {
    public static void main(String[] args) {
        int[][] cost = {{2,9,1,7},{3,0,1,1},{6,2,1,8},{1,5,2,2},{7,3,2,9},{5,4,1,1}};
        System.out.println(new MinCostTollGate().minCostOfTollTravel(cost, 6));
    }


    public int minCostOfTollTravel(int cost[][], int startlane){
        int rows = cost.length;
        int cols = cost[0].length;
        return minCostPathHelper(cost, rows, cols, startlane - 1, 0);
    }

    int minCostPathHelper(int[][] cost, int rows, int cols, int i, int j) {
        if(i < 0 || i >= rows) return Integer.MAX_VALUE;

        if(j == cols - 1){
            return cost[i][j];
        }

        // Move to prev lane i - 1 and j + 1 and ask for cost
        int prev = minCostPathHelper(cost, rows, cols, i - 1, j+1);
        // Move cur lane i and j + 1 and ask for cost
        int cur = minCostPathHelper(cost, rows, cols, i, j+1);
        //Move to next lane i +1, j+1 and ask for cost
        int next = minCostPathHelper(cost, rows, cols, i + 1, j+1);

        return cost[i][j] + Math.min(prev, Math.min(cur, next));
    }
}
