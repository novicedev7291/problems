package com.kuldeep.data.structure.tree;

import java.util.*;
import java.lang.*;

class BTNode
{
    int data;
    BTNode left, right;
    BTNode(int d)
    {
        data= d;
        left = right = null;
    }
}

class BTHelper
{
    int preStart;
    int preEnd;
    BTHelper(int pS, int pE)
    {
        preStart = pS;
        preEnd = pE;
    }
    int getIdxOfRootInInorder(int in[], int st, int end, int val)
    {
        int i;
        for(i=st;i<=end;i++)
            if(in[i] == val)
                return i;
        return -1;
    }
    BTNode makeTree(int in[], int inStart, int inEnd, int pre[])
    {

        if(inStart > inEnd)
            return null;

        if(preStart>preEnd)
        {
            return null;
        }

        BTNode root = new BTNode(pre[preStart]);
        int rIdx = getIdxOfRootInInorder(in, inStart, inEnd, pre[preStart]);
        preStart++;
        root.left = makeTree(in, inStart, rIdx-1, pre);
        root.right = makeTree(in, rIdx+1, inEnd, pre);

        return root;
    }
}

class Problem
{
    BTNode root;


    void printOrderHelper(BTNode left, BTNode right, Map<Integer, List<Integer>> mp,Integer level){
        if(left == null || right == null) return;
        if(mp.containsKey(level)){
            List<Integer> nodesAtLevel= mp.get(level);
            nodesAtLevel.add(left.data);
            nodesAtLevel.add(right.data);
            mp.put(level, nodesAtLevel);
        }
        else{
            List<Integer> el = emptyList();
            el.add(left.data);
            el.add(right.data);
        }
        printOrderHelper(left.left, right.right, mp, level + 1);
        printOrderHelper(right.left, left.right, mp, level + 1);
    }
    BTNode printSpecificLevelOrder(BTNode node)
    {
        Map<Integer, List<Integer>> mp = new TreeMap<>();
        Integer level = 1;
        printOrderHelper(node.left, node.right, mp, level + 1);
        System.out.printf("%d ", node.data);
        return node;
    }

    List<Integer> emptyList(){
        return new ArrayList<Integer>();
    }
}

public class TreeSpecificOrder {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int t = sc.nextInt();
        char c = (char)('a'+t);
        while(t--!=0)
        {
            TreeSpecificOrder tree = new TreeSpecificOrder();
            int n = sc.nextInt();
            int i;
            int pre[] =new int[n], in[] = new int[n];
            for(i=0;i<n;i++)
                pre[i]=sc.nextInt();
            for(i=0;i<n;i++)
                in[i] = sc.nextInt();
            //target= sc.nextInt();
            //k= sc.nextInt();
            BTHelper bth = new BTHelper(0, n-1);
            BTNode root = bth.makeTree(in, 0, n-1, pre);
            Problem ac=new Problem();
            ac.printSpecificLevelOrder(root);
            System.out.println(" ");
        }
    }
}
