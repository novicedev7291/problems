package com.kuldeep.problems.backtracking;

public class MajeRatProblem {
    public static void main(String[] args) {
        //int[][] m = {{1 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0},{1, 0 ,1 ,0 ,0 ,1 ,1 ,0 ,0 ,0 },{1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1 },{1 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1},{1 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1}};
        int[][] m = {{1,0,0,0},{1,1,0,1},{0,1,0,0},{0,1,1,1}};
        int[][] n = new int[m[0].length][m.length];
        int[][] s = new int[m[0].length][m.length];
        for(int i = 0 ; i < n.length; i++){
            for(int j = 0; j < n[0].length; j++){
                s[i][j] = 0;
            }
        }
        for(int i = 0 ; i < n.length; i++){
            for(int j = 0; j < n[0].length; j++){
                n[i][j] = m[j][i];
            }
        }

        new MajeRatProblem().generateSolution(m,s);
        System.out.println();
        for(int i = 0 ; i < n.length; i++){
            for(int j = 0; j < n[0].length; j++){
                System.out.printf("%d ",s[i][j]);
            }
            System.out.println();
        }
        /*System.out.println();
        for(int i = 0 ; i < s.length; i++){
            for(int j = 0; j < s[0].length; j++){
                System.out.printf("%d ",n[i][j]);
            }
            System.out.println();
        }*/

    }

    void generateSolution(int[][] m, int[][] s) {
        generateSolHelper(m, s, m.length, m[0].length, 0,0);
    }

    boolean generateSolHelper(int[][] m, int[][] s, int r, int c, int i, int j){
        if(i == r || j == c || i < 0 || j < 0 || m[i][j] == 0 || s[i][j] == 1) return false;


        s[i][j] = 1;
        if(i == r-1 && j == c-1 ) {
            return true;
        }
        if(generateSolHelper(m, s, r, c, i+1, j))
            return true;
        if(generateSolHelper(m, s, r, c, i, j+1))
            return true;
        if(generateSolHelper(m, s, r, c, i-1, j))
            return true;
        if(generateSolHelper(m, s, r, c, i, j-1))
            return true;
        s[i][j]=0;
        return false;

    }
}
