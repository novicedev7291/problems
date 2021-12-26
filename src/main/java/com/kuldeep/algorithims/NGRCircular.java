package com.kuldeep.algorithims;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class NGRCircular {
    public static void main(String[] args) {
       int[] arr = {2, 5, -3, -4, 6, 7, 2};

        System.out.println(nextGreaterElement(arr));
    }

    private static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];

        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * arr.length - 1; i >= 0; i--) {
            int circularIndex = i % arr.length;

            while (!stack.isEmpty()) {
                if (stack.peek() <= arr[circularIndex]) {
                    stack.pop();
                }else {
                    result[circularIndex] = stack.peek();
                    break;
                }
            }

            stack.push(arr[circularIndex]);
        }

        return result;
    }
}
