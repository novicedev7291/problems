package com.kuldeep.data.structure;

public class MinHeap {
    private int count = 0;
    private int capacity;
    private int[] arr;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public int getLeft(int i){
        int temp = left(i);
        if(temp < 0 || temp > capacity) return 0;
        return arr[temp];
    }

    public int getRight(int i){
        int temp = right(i);
        if(temp < 0 || temp > capacity) return 0;
        return arr[temp];
    }

    public int getParent(int i){
        if(i == 0) return arr[i];
        int temp = parent(i);
        if(temp < 0) return 0;
        return arr[temp];
    }

    protected int parent(int i){
        return (i - 2)/2;
    }

    protected int left(int i){
        return 2*i + 1; //return left
    }

    protected int right(int i){
        return 2*i + 2;
    }

    public void insert(int key){
        if(count == capacity) throw new StackOverflowError();
        arr[count++] = key;
        int i = count - 1;

        while (i != 0 && arr[parent(i)] > key){
            swap(i, parent(i));
            i = parent(i); // Important step to iterate through hierarchy until you reached root.
        }
    }

    public boolean delete(int k){
        int keyIndex = search(k);
        if(keyIndex == -1) return false;
        else if (keyIndex == count - 1){
            count--;
            return true;
        }
        else{
            swap(keyIndex, count - 1);
            count--;
            minify(count);
            return true;
        }
    }

    private int search(int k) {
        return binarySearch(k, 0, count-1);
    }

    private int binarySearch(int k, int i, int j) {
        if(j <= i) return -1;

        int mid = i + (j - i)/2;

        if(k == arr[mid]){
            return mid;
        }else if(k < arr[mid]){
            return binarySearch(k, 0, mid - 1);
        }else{
            return binarySearch(k, mid+1, j);
        }
    }

    private void minify(int i) {
        if(i <= 0) return;
        int l = left(i);
        int r = right(i);
        if(l <= count && arr[i] > arr[l]){
            swap(i, l);
            minify(l);
        }else if(r <= count && arr[i] > arr[r]){
            swap(i, r);
            minify(r);
        }
    }

    private void swap(int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder("[");
        for(int i = 0 ; i < arr.length; i++){
            sb.append(arr[i]+",");
        }
        return sb.append("]").toString();
    }
}
