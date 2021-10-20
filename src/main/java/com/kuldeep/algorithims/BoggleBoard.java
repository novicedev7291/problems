package com.kuldeep.algorithims;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class BoggleBoard {
    private final char[][] values;

    private BoggleBoard(char[][] values) {
        this.values = values;
    }

    public static BoggleBoard of(char[][] chars) {
        return new BoggleBoard(chars);
    }

    public List<String> findWords(List<String> words) {
        return emptyList();
    }
}
