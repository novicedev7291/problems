package com.kuldeep.algorithims;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MoveEvenToFrontTest {
    @Test
    public void shouldMoveEvenAtFrontOfArray() {
        int[] arr = {5, 12, 3, 21, 8, 7, 19, 102, 201};

        new MoveEvenToFront().move(arr);

        int[] expected = {12, 8, 102, 5, 3, 21, 7, 19, 201};

        assertArrayEquals(expected, arr);
    }

    @Test
    public void shouldMoveEventAtFrontOfGivenArray() {
        int[] arr = {2, 4, 7, 6, 1, 3, 5, 4};

        new MoveEvenToFront().move(arr);

        int[] expected = {2, 4, 6, 4, 7, 1, 3, 5};

        assertArrayEquals(expected, arr);
    }
}