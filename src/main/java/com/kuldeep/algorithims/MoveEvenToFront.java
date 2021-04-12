package com.kuldeep.algorithims;

class MoveEvenToFront {
    public void move(int[] arr) {
        int j = 0, i = 0;
        int n = arr.length;

        while(j < n) {
            if(arr[j] % 2 ==0) {
                int temp = arr[j];

                shiftRight(arr, i, j);

                arr[i] = temp;
                i++;
            }
            j++;
        }
    }

    private void shiftRight(int[] arr, int from, int to) {
        while(to > from) {
            arr[to] = arr[to - 1];
            to--;
        }
    }
}
