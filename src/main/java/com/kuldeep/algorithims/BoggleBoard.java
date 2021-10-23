package com.kuldeep.algorithims;

import com.kuldeep.data.structure.tree.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class BoggleBoard {
    private final char[][] values;
    private final TrieNode root = TrieNode.create();
    private final int[][] visited;

    private BoggleBoard(char[][] values) {
        this.values = values;
        this.visited = new int[values.length][values[0].length];
    }

    public static BoggleBoard of(char[][] chars) {
        return new BoggleBoard(chars);
    }

    public List<String> findWords(List<String> words) {
        putWordsInTrie(words);

        Set<String> collector = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                traverseDFS(i, j, root, collector);
            }
        }

        return new ArrayList<>(collector);
    }

    private void traverseDFS(int row, int col, TrieNode node, Set<String> collector) {
        if (visited[row][col] == 1) return;

        char letter = values[row][col];
        int idx = getIdx(letter);

        TrieNode child = node.childAt(idx);
        if (Objects.isNull(child)) {
            return;
        }

        visited[row][col] = 1;
        if (child.isEndOfWord()) {
            collector.add(child.word());
        }

        List<Pair> neighbours = getNeighbours(row, col);
        for (Pair neighbour : neighbours) {
            traverseDFS(neighbour.row, neighbour.col, child, collector);
        }

        visited[row][col] = 0;
    }

    private List<Pair> getNeighbours(int row, int col) {
        List<Pair> neighbours = new ArrayList<>();
        if (row - 1 >= 0) {
            neighbours.add(new Pair(row - 1, col));
        }
        if (row - 1 >= 0 && col + 1 < values[0].length) {
            neighbours.add(new Pair(row - 1, col + 1));
        }
        if (col + 1 < values[0].length) {
            neighbours.add(new Pair(row, col + 1));
        }
        if (row + 1 < values.length && col + 1 < values[0].length) {
            neighbours.add(new Pair(row + 1, col + 1));
        }
        if ( row + 1 < values.length) {
            neighbours.add(new Pair(row + 1, col));
        }
        if (row + 1 < values.length && col - 1 >= 0) {
            neighbours.add(new Pair(row + 1, col - 1));
        }
        if ( col - 1 >= 0) {
            neighbours.add(new Pair(row, col - 1));
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            neighbours.add(new Pair(row - 1, col - 1));
        }
        return neighbours;
    }

    private int getIdx(char ch) {
        return ch - '!';
    }

    private void putWordsInTrie(List<String> words) {
        for (String word : words) {
            root.insert(word);
        }
    }

    class Pair {
        int row;
        int col;
        Pair (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
