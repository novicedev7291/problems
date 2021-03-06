package com.kuldeep.data.structure;

import com.kuldeep.data.structure.AVLTree;
import com.kuldeep.data.structure.BinarySearchTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

    //public static final int[] arr = {10,9,8,7,6,5,4,3,2,1};
    public static final int[] arr = {1,2,3,4,5,6,7,8,9,10};
    private BinarySearchTree bst;
    private AVLTree avl;

    @Before
    public void setUp(){
        bst = new BinarySearchTree();
        avl = new AVLTree();

        for (int i = 0; i < arr.length; i++){
            bst.insert(arr[i]);
            avl.insert(arr[i]);
        }

        String test = "";
        test.split(" ");
    }

    @Test
    public void shouldHaveSameInOrder(){
        bst.inorder();
        System.out.println();
        avl.inOrder();
    }

    @Test
    public void avlShouldHaveSmallHeightThanBST(){
        Assert.assertTrue(bst.height()>avl.height());
    }

    @Test
    public void shouldBalanceAfterDeletion(){
        avl.delete(3);
        Assert.assertTrue(avl.height() == 4);
    }
}
