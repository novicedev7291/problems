package com.kuldeep.problems;

import com.kuldeep.data.structure.BinarySearchTree;

import java.util.logging.Logger;

public class MonkWatchingFight {
    public static Logger log = Logger.getLogger(MonkWatchingFight.class.getName());

    public static void main(String[] args){
        int[] arr = {2,1 ,3, 4, 6, 11, -1, 35, 27, 29, 10, 14};
        BinarySearchTree bst = new BinarySearchTree();

        for(int i = 0; i < 12; i++){
            bst.insert(arr[i]);
        }

        bst.preOrder();
        System.out.println();
        log.info(bst.height()+"");
        System.out.println();
        bst.inorder();
        bst.delete(11);
        System.out.println();
        bst.inorder();


    }


}
