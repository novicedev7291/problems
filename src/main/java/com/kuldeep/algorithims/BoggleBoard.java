package com.kuldeep.algorithims;

import com.kuldeep.data.structure.tree.TrieNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class BoggleBoard {
    private final char[][] values;
    private final TrieNode root = TrieNode.create();
    private final int[][] visited;

    private static final Logger log = LoggerFactory.getLogger(BoggleBoard.class.getName());

    private BoggleBoard(char[][] values) {
        this.values = values;
        this.visited = new int[values.length][values[0].length];
    }

    public static BoggleBoard of(char[][] chars) {
        return new BoggleBoard(chars);
    }

    public List<String> findWords(List<String> words) {
        putWordsInTrie(words);

        List<String> collector = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                if (visited[i][j] == 0) {
                    traverseDFS(i, j, new StringBuilder(), collector);
                }
            }
        }

        return new ArrayList<>(collector);
    }

    private void traverseDFS(int row, int col, StringBuilder sb, List<String> collector) {
        if (row < 0 || col < 0 || row >= values.length || col >= values[0].length) return;


        if (visited[row][col] == 1) return;

        String prefix = sb.toString();
        if (prefix.length() > 0 && !root.contains(prefix)) {
            return;
        }else if (prefix.length() > 0) {
            log.info("{} found at {},{}", prefix, row, col);
            collector.add(prefix);

//            sb = new StringBuilder();
        }

        visited[row][col] = 1;
        sb.append(values[row][col]);


        //top
        traverseDFS(row - 1, col, sb, collector);

        //right
        traverseDFS(row, col + 1, sb, collector);

        //bottom
        traverseDFS(row + 1, col, sb, collector);

        //left
        traverseDFS(row, col - 1, sb, collector);


        visited[row][col] = 0;
    }

    private void putWordsInTrie(List<String> words) {
        for (String word : words) {
            root.insert(word);
        }
    }
}
