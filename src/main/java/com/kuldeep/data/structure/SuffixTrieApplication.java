package com.kuldeep.data.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class SuffixTrieApplication {
    public static void main(String[] args) {
        SuffixTrie trie = new SuffixTrie("babc");
        System.out.println(trie.contains("abc"));
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(String str) {
            for ( int i = 0; i < str.length(); i++) {
                insertSuffixAtPosition(i, str);
            }
        }

        private void insertSuffixAtPosition(int pos, String str) {
            TrieNode current = root;
            for (int i = pos; i < str.length(); i++) {
                TrieNode charNode = current.children.get(str.charAt(i));
                if ( charNode == null ) {
                    charNode = new TrieNode();
                    current.children.put(str.charAt(i), charNode);
                }
                current = charNode;
            }
            current.children.put(endSymbol, new TrieNode());
        }

        public boolean contains(String str) {
            TrieNode current = root;

            for (int i = 0; i < str.length(); i++) {
                current = current.children.get(str.charAt(i));
                if (current == null) return false;
            }

            return current.children.get(endSymbol) != null;
        }
    }
}
