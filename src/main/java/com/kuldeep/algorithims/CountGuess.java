package com.kuldeep.algorithims;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CountGuess {
    static class Node{
        int r;
        int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    // Complete the countLuck function below.
    static String countLuck(String[] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length();
        int[][] visited = new int[N][M];
        Node[][] paths = new Node[N][M];
        int[] X = {0,-1, 0, 1};
        int[] Y = {1, 0, -1, 0};
        int is = 0, js = 0;
        int id = 0, jd = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                char ch = matrix[i].charAt(j);
                if(ch == 'X'){
                    visited[i][j] = 1;
                }
                else if(ch == 'M'){
                    is = i;
                    js = j;
                    continue;
                }
                else if(ch == '*'){
                    id = i;
                    jd = j;
                    continue;
                }
            }
        }
        Node source = new Node(is, js);
        visited[is][js] = 1;
        Queue<Node> q = new LinkedList();
        q.offer(source);
        int count = 0;

        while(!q.isEmpty()){
            Node n = q.poll();
            if(visited[n.r][n.c] == 0){
                visited[n.r][n.c] = 1;
            }
            for(int l = 0; l < 4; l++){
                int tx = n.r + X[l];
                int ty = n.c + Y[l];
                if(isValid(tx,ty,N,M) && !isVisited(tx, ty, visited)){
                    q.offer(new Node(tx, ty));
                    visited[tx][ty] = 1;
                    paths[tx][ty] = n;
                }
            }
        }

        if(visited[id][jd] != 1) return "Oops!";
        int x = id, y = jd;
        while(x != is || y != js){
            Node t = paths[x][y];
            x = t.r;
            y = t.c;
            if(matrix[x].charAt(y) != 'M' && hasMoreThanTwoBranch(x,y,matrix, 2)){
                count += 1;
            }
        }

        if(hasMoreThanTwoBranch(is, js, matrix, 1)){
            count += 1;
        }

        return count == k ? "Impressed" : "Oops!";
    }
    static boolean hasMoreThanTwoBranch(int x, int y, String[] m, int branches){
        int count = 0;
        int[] X = {0,-1, 0, 1};
        int[] Y = {1, 0, -1, 0};
        for(int i = 0; i < 4; i++){
            int k = x + X[i];
            int l = y + Y[i];
            if(isValid(k, l, m.length, m[0].length()) && m[k].charAt(l) != 'X'){
                count += 1;
            }
        }
        return count > branches;
    }

    static boolean isVisited(int x, int y, int[][] visited){
        return visited[x][y] == 1;
    }

    static boolean isValid(int x, int y, int rows, int cols){
        return (x >= 0 && x < rows) && (y >= 0 && y < cols);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            String[] matrix = new String[n];

            for (int i = 0; i < n; i++) {
                String matrixItem = scanner.nextLine();
                matrix[i] = matrixItem;
            }

            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = countLuck(matrix, k);
            System.out.println(result);
            //bufferedWriter.write(result);
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
