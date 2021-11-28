package com.kuldeep.threads;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class MergeSortedArrayTest {
    @Test
    public void shouldReturnSingleSortedArray() throws InterruptedException {
        int[] a = {1, 4, 6, 8, 10, 15, 20, 24, 45};
        int[] b = {-1, 3, 5, 7, 9, 13, 14, 18, 27, 31, 71};
        int[] result = ConcurrentMergeHelper.merge(a, b);

        int[] expected = {-1,1,3,4,5,6,7,8,9,10,13,14,15,18,20,24,27,31,45,71};
        assertThat(result)
                .isEqualTo(expected);
    }
}
