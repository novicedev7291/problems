package com.kuldeep.data.structure;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class MinHeapApp {
    static class MinHeap {
        List<Integer> heap;

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        private int parent(int index) {
            return (index - 1) / 2;
        }

        private int left(int index) {
            return 2* index + 1;
        }

        private int right(int index) {
            return 2 * index + 2;
        }

        public List<Integer> buildHeap(List<Integer> array) {
            List<Integer> tempHeap = new ArrayList<>();

            for ( int i = 0; i < array.size(); i++) {
                tempHeap.add(array.get(i));
                siftUp(i, tempHeap);
            }

            return tempHeap;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            if(currentIdx >= endIdx) return;

            if(left(currentIdx) >= endIdx) return;

            int left = left(currentIdx);

            int swapIdx = currentIdx;
            int right = right(currentIdx);
            if (heap.get(left) < heap.get(currentIdx)) {
                if ( right < endIdx && heap.get(right) < heap.get(left)) {
                    swapIdx = right;
                }else {
                    swapIdx = left;
                }
            }else {
                if (right < endIdx && heap.get(right) < heap.get(currentIdx)) {
                    swapIdx = right;
                }
            }

            // When value already at right place
            if (swapIdx == currentIdx) return;

            swap(heap, swapIdx, currentIdx);
            siftDown(swapIdx, endIdx, heap);
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            if ( currentIdx <= 0 ) return;

            int parentIdx = parent(currentIdx);
            while (parentIdx >= 0) {
                if (heap.get(currentIdx) >= heap.get(parentIdx)) {
                    break;
                }

                int swapIdx = currentIdx;
                int right = right(parentIdx);
                if (right < heap.size() && heap.get(currentIdx) > heap.get(right)) {
                    swapIdx = right;
                }

                swap(heap, swapIdx, parentIdx);

                currentIdx = parentIdx;
                parentIdx = parent(parentIdx);
            }
        }

        public int peek() {
            if (heap.isEmpty()) return Integer.MAX_VALUE;
            return heap.get(0);
        }

        public int remove() {
            if (heap.isEmpty()) return Integer.MAX_VALUE;
            int value = peek();

            swap(heap, 0, heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);

            heap.remove(heap.size() - 1);

            return value;
        }

        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }
    }
}
