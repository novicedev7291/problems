package com.kuldeep.design.elevator;

import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class ElevatorDesignTest {

    @Test
    public void blablablatestMethodDontKnowTheName() {
        ElevatorService elevatorService = new ElevatorService(emptyList());

        int floorNo = 2;
        boolean accepted = elevatorService.call(Direction.UP, floorNo);

        assertTrue(accepted);
    }
}
