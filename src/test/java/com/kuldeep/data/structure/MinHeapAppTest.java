package com.kuldeep.data.structure;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MinHeapAppTest {

    @Test
    public void shouldPassMinHeapTest() {
        //given
        Integer[] arr = {48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41};

        MinHeapApp.MinHeap heap = new MinHeapApp.MinHeap(asList(arr));

        heap.insert(76);

        assertEquals(heap.peek(), -5);
        assertEquals(heap.remove(), -5);
        assertEquals(heap.peek(), 2);
        assertEquals(heap.remove(), 2);
        assertEquals(heap.peek(), 6);

        heap.insert(87);
        assertEquals(heap.peek(), 6);
    }

    @Test
    public void shouldPassTwoElementEdgeCase() {
        //given
        Integer[] arr = {48,78};

        MinHeapApp.MinHeap heap = new MinHeapApp.MinHeap(asList(arr));

        heap.insert(23);

        assertEquals(23, heap.remove());
        assertEquals(48, heap.remove());
    }

}