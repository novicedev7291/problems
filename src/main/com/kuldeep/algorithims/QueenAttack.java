package com.kuldeep.algorithims;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QueenAttack {
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int moves = 0;
        int rTL = -1;
        int cTL = -1;
        int rT = -1;
        int cT = -1;
        int rTR = -1;
        int cTR = -1;
        int rR = -1;
        int cR = -1;
        int rBR = -1;
        int cBR = -1;
        int rB = -1;
        int cB = -1;
        int rBL = -1;
        int cBL = -1;
        int rL = -1;
        int cL = -1;
        for(int i =0 ;i < obstacles.length; i++){
            int rObstacle = obstacles[i][0];
            int cObstacle = obstacles[i][1];
            //Left
            if((cObstacle > cL || cL == -1) && cObstacle < c_q && rObstacle == r_q){
                rL = rObstacle;
                cL = cObstacle;
            }
            //Top
            if((rObstacle < rT || rT == -1) && rObstacle > r_q && cObstacle == c_q)
            {
                rT = rObstacle;
                cT = cObstacle;
            }
            // Right
            if((cObstacle < cR || cR == -1) && cObstacle > c_q && rObstacle == r_q){
                rR = rObstacle;
                cR = cObstacle;
            }
            //Bottom
            if((rObstacle > rB || rB == -1) && rObstacle < r_q && cObstacle == c_q){
                rB = rObstacle;
                cB = cObstacle;
            }
            //Top left diagonal
            if(((rObstacle < rTL && cObstacle > cTL) || rTL == -1) && (r_q - rObstacle == c_q - cObstacle)
                    && (rObstacle > r_q && cObstacle < c_q)){
                rTL = rObstacle;
                cTL = cObstacle;
            }
            //Top right diagonal
            if(((rObstacle < rTR && cObstacle < cTR) || rTR == -1) && (r_q - rObstacle == c_q - cObstacle)
                    && (rObstacle > r_q && cObstacle > c_q)){
                rTR = rObstacle;
                cTR = cObstacle;
            }
            //Bottom right diagonal
            if(((rObstacle > rBR && cObstacle < cBR) || rBR == -1) && (r_q - rObstacle == c_q - cObstacle)
                    && (rObstacle < r_q && cObstacle > c_q)){
                rBR = rObstacle;
                cBR = cObstacle;
            }
            //Bottom left diagonal
            if(((rObstacle > rBL && cObstacle > cBL) || rBL == -1) && (r_q - rObstacle == c_q - cObstacle)
                    && (rObstacle < r_q && cObstacle < c_q)){
                rBL = rObstacle;
                cBL = cObstacle;
            }
        }
        moves += (rT != -1) ? (rT - r_q - 1) : n - r_q;
        moves += (cR != -1) ? (cR - c_q - 1) : n - c_q;
        moves += (rB != -1) ? (r_q - rB - 1) : r_q - 1;
        moves += (cL != -1) ? (c_q - cL - 1) : c_q - 1;

        moves += (rTL != -1) ? rTL - r_q - 1: Math.min(n - r_q, c_q-1);
        moves += (cTR != -1) ? cTR - c_q-1 : Math.min(n - r_q, n - c_q);
        moves += (rBR != -1) ? r_q- rBR - 1 : Math.min(r_q-1, n-c_q);
        moves += (cBL != -1) ? c_q - cBL-1 : Math.min(r_q-1, c_q-1);
        return moves;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);
        System.out.println(result);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
