package com.kuldeep.problems;

public class RemoveDupChars {
    int removeDupChars(char arr[]) {
        char[] copyArr = new char[arr.length];
        int[] index = new int[256];
        for(int i = 0; i < 256; i++){
            index[i] = 0;
        }

        for(int i = 0 ; i < arr.length; i++){
            index[(int)arr[i]]+=1;
        }

        int count = 0;
        for(int i = 0, j = 0; i < arr.length; i++){
            if(index[(int)arr[i]] != 0){
                copyArr[j] = arr[i];
                index[(int)arr[i]] = 0;
                j++;
                count++;
            }
        }

        for(int i = 0; i < count; i++){
            arr[i] = copyArr[i];
        }

        return count;
    }

    public static void main(String[] args) {
        char[] arr = {'a','p','p','l','e'};
        int count = new RemoveDupChars().removeDupChars(arr);
        for(int i = 0; i < count; i++){
            System.out.printf("%c", arr[i]);
        }
    }
}
