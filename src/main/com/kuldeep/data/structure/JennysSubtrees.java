package com.kuldeep.data.structure;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;

public class JennysSubtrees {

    // A map of edges from a given node
    private static HashMap<Integer, Set<Integer>> connections = new HashMap<>();
    // Will be updated from stdin
    private static int radius = 0;
    
    static void debug(String s) {
        if (false)
            System.out.println(s);
    }
    
    static void putConnectionHelper(int x, int y) {
        Set<Integer> set = connections.get(x);
        if (set == null) {
            set = new HashSet<>();
            set.add(y);
            connections.put(x, set);
        } else {
            set.add(y);
        }
    }
    static void putConnection(int x, int y) {
        putConnectionHelper(x, y);
        putConnectionHelper(y, x);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        radius = in.nextInt();
        for(int a0 = 0; a0 < n-1; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            putConnection(x,y);
        }
        
        System.out.println(solveJennySubtrees());
    }
    
    static int solveJennySubtrees() {
        Set<Integer> allNodes = connections.keySet();
        Set<String> signatures = new HashSet<>();
        for (Integer root: allNodes) {
            // executed#=N
            signatures.add(generateUnrootedSignature(root));
        }
        return signatures.size();
    } 
    
    // Traverses the tree from "distanceRoot", builds up an (unrooted) subtree that is conforming to radius requirements,
    // and generates an unrooted signature of that subtree.
    static String generateUnrootedSignature(int distanceRoot) {
        Set<Integer> reachableFromDistanceRoot = getReachableNodes(distanceRoot, 0, 1);
        reachableFromDistanceRoot.add(distanceRoot);
        
        List<String> namesFromCentres = new ArrayList<>();
        
        Set<Integer> centres = findTreeCentres(reachableFromDistanceRoot);
        for (Integer subRoot: centres) {
            // executed#=2
            String rootedSig = generateRootedSignatureWithNodes(subRoot, 0, reachableFromDistanceRoot);
            namesFromCentres.add(rootedSig);
        }
        namesFromCentres.sort(null);
        return String.join(";", namesFromCentres);
    }
    
    // Any connected tree has exactly 1 or 2 "centres"
    static Set<Integer> findTreeCentres(Set<Integer> reachableNodes) {
        Set<Integer> centres = new HashSet<>(reachableNodes);
        
        while (centres.size() > 2) {
            final Set<Integer> reachableReducedNodes = new HashSet<>(centres);
            Iterator<Integer> it = centres.iterator();
            while(it.hasNext()) {
                int node = it.next();
                Set<Integer> neighbors = new HashSet<>(connections.get(node));
                neighbors.removeIf(i -> !reachableReducedNodes.contains(i));
                if (neighbors.size() <= 1) {
                    it.remove();
                }
            }
        }
        
        return centres;
    }
    
    static String generateRootedSignatureWithNodes(int root, int parent, Set<Integer> reachableNodes) {
        Set<Integer> allChildren = new HashSet<>(connections.get(root));
        Set<Integer> reachableChildren = new HashSet<>();
        Iterator<Integer> iter = allChildren.iterator();
        while(iter.hasNext()) {
            int child = iter.next();
            if (child != parent && reachableNodes.contains(child)) {
                reachableChildren.add(child);
            }
        }

        String retVal;
        if (reachableChildren.size() == 0) { // leaf
            retVal = "10";
        } else {
            List<String> names = new ArrayList<>();
            for (int child: reachableChildren) {
                String childSignature = generateRootedSignatureWithNodes(child, root, reachableNodes);
                names.add(childSignature);
            }
            names.sort(null);
            retVal = "1" + String.join("", names) + "0";
        }
        return retVal;
    }
    
    static Set<Integer> getReachableNodes(int root, int parent, int currentRadius) {
        Set<Integer> children;
        if (currentRadius > radius) {
            return Collections.emptySet();    
        } else {
            children = new HashSet<>(connections.get(root));
            // don't want cycles
            children.remove(parent);
        }

        if (children.size() == 0) {
           return Collections.emptySet();
        }
        
        // clone needed to prevent modification while iterating
        Set <Integer> allReachableNodes = new HashSet<>(children);
        for (int child: children) {
            Set<Integer> childReachable = getReachableNodes(child, root, currentRadius + 1);
            allReachableNodes.addAll(childReachable);
        }
        return allReachableNodes;
    }
}