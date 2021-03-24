package com.kuldeep.problems;

public class PrintSumOfEvenNumberToN {
    public static void main(String[] args) {
        printStarTriangle(4);

    }

    private static void printEvenSeriesTillN(int N){
        for(int i = 1, j = 2; i <= N; i++, j += 2){
            if(j == 2){
                System.out.printf((j-1)+"\t");
                continue;
            }
            System.out.printf(j+"\t");
        }
    }

    private static void printSumOfAllEvenNos(int N){
        int sum = 0;
        for(int i = 2; i <= N; i+=2){
            sum += i;
        }
        System.out.println(sum);
    }

    private static void printAlternateSignWithoutMultiplesOfFour(int n){
        int i = 1, j = 1;
        while(i <= n){
            if(i % 2 == 0){
                if(j % 4 == 0){
                    j++;
                    System.out.printf(j * -1 +" ");
                }
                else{
                    System.out.printf(j * -1 +" ");
                }
            }
            else{
                if(j % 4 == 0){
                    j++;
                    System.out.printf(j +" ");
                }
                else
                    System.out.printf(j+" ");
            }
            i++;
            j++;
        }
    }

    private static void printAmazingSeries(int n){
        int a1 = 1, a2 = -2;
        System.out.printf(a1+" ");
        System.out.printf(a2+" ");
        for(int i = 1; i < n; i++){
            a1+=3;
            a2+=-4;
            System.out.printf(a1+" ");
            System.out.printf(a2+" ");
        }
    }

    private static void printFibSeries(int n){
        int a = 0, b = 1;
        if(n == 1){
            System.out.printf("%d ",a);
        }
        System.out.printf("%d ",a);
        System.out.printf("%d ", b);

        for(int i = 2; i < n; i++){
            int c = a + b;
            a = b;
            b = c;
            System.out.printf("%d ", c);
        }
    }

    private static void printStarTriangle(int rows){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j <= i; j++){
                System.out.printf("* ");
            }
            System.out.println();
        }
    }

}
