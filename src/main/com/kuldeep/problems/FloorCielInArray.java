package com.kuldeep.problems;

public class FloorCielInArray {
    private int[] arr = {27,37,101,104,105};
    private int floor = 0;
    private int ciel = 0;

    public FloorCielInArray(int num) {
        init(num, 0, arr.length);
    }

    public FloorCielInArray(int num, int[] arr) {
        this.arr = arr;
        init(num, 0, this.arr.length);
    }

    public static void main(String[] args) {
        FloorCielInArray o = new FloorCielInArray(105);
        System.out.println("Floor : " + o.getFloor());
        System.out.println("Ceilling : " + o.getCeilling());
    }

    public int getCeilling() {
        return ciel == 0 ? 0 : arr[ciel];
    }

    public int getFloor() {
        return floor == 0 ? 0 : arr[floor];
    }

    private void init(int num, int start, int end){
        if(start > end){
            return;
        }
        int mid = start + (end - start) / 2;

        if(num == arr[mid]){
            floor = ciel = mid;
            //return mid;
        }else if(num < arr[mid]){
            ciel = mid;
            init(num, start, mid - 1);
        }
        else{
            floor = mid;
            init(num, mid+1, end);
        }
    }


}
