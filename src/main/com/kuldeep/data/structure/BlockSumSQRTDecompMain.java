package com.kuldeep.data.structure;

import java.util.Arrays;

public class BlockSumSQRTDecompMain {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8};
        SQRTDecomposition o = new SQRTDecomposition(a);
        o.print();
        System.out.println();
        System.out.println(o.query(0,6));
        o.update(6,0);
        o.print();
        System.out.println();
        System.out.println(o.query(0, 5));
    }
}
