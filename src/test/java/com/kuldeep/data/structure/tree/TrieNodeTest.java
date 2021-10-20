package com.kuldeep.data.structure.tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TrieNodeTest {

    @Test
    public void shouldInsertAndFindTheWordInTrie() {
        TrieNode trie = TrieNode.create();

        trie.insert("this");
        trie.insert("amazing");
        trie.insert("amaze");
        trie.insert("is");

        assertThat(trie.contains("this")).isTrue();
        assertThat(trie.contains("thiss")).isFalse();
        assertThat(trie.contains("amazing")).isTrue();
        assertThat(trie.contains("is")).isTrue();
        assertThat(trie.contains("amazement")).isFalse();
    }

    @Test
    public void shouldFindPrefixesInTrieForGivenWord() {
        TrieNode trie = TrieNode.create();

        trie.insert("this");
        trie.insert("amazing");
        trie.insert("amaze");
        trie.insert("is");
        trie.insert("nam");
        trie.insert("naman");

        assertThat(trie.containsPrefix("this")).isTrue();
        assertThat(trie.containsPrefix("thiss")).isFalse();
        assertThat(trie.containsPrefix("amazing")).isTrue();
        assertThat(trie.containsPrefix("amaz")).isTrue();
        assertThat(trie.containsPrefix("nama")).isTrue();
    }

}