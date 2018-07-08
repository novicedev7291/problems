package com.kuldeep.server.data.structure;

import com.kuldeep.data.structure.MinHeap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mario on 21/07/17.
 */
public class MinHeapTest {
    private MinHeap heap;
    @Before
    public void setUp() throws Exception {
        heap = new MinHeap(10);
    }

    @Test
    public void rootOfMinHeap() throws Exception {
        heap.insert(10);
        assertEquals(heap.getParent(0), 10);
        assertEquals(heap.getLeft(0), 0);
        assertEquals(heap.getRight(0), 0);
    }

    @Test
    public void leftRightWithRoot() throws Exception {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        assertEquals(heap.getLeft(0), 20);
        assertEquals(heap.getRight(0), 30);
    }

    @Test
    public void deleteKey() throws Exception {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(15);
        heap.insert(5);
        System.out.println(heap);
        assertTrue(heap.delete(30));
    }
}
