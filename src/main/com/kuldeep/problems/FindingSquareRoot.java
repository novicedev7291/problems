package com.kuldeep.problems;

public class FindingSquareRoot {
    public static void main(String[] args) {
        System.out.println(new FindingSquareRoot().sqrt(4));
        //new FindingSquareRoot().squareRoot();
    }

    public double sqrt(float N){
        return sqrtHelper(N, 0, N);
    }

    private double sqrtHelper(float N, double start, double end){
        if(end < start) return 0;

        double mid = start + (end - start)/2.0d;
        double residue = (mid*mid - N);
        if(Math.abs(residue) <= 0.0001){
            return Math.round(mid*1000)/1000.0d;
        }
        else if(N < mid*mid){
            return sqrtHelper(N, start, mid);
        }else
            return sqrtHelper(N, mid, end);
    }

    public void squareRoot(){
        double low = 0;
        double high = 5;
        double eval = -1;

        while(low < high){
            double mid = low + (high- low)/2.0;
            if(mid*mid == high){
                eval = mid;
                break;
            }
            else if(mid*mid > high){
                high = mid;
            }else
                low = mid;
        }
        System.out.println(eval);
    }
}
