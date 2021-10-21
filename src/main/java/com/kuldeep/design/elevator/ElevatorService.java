package com.kuldeep.design.elevator;

import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class ElevatorService {
    private final List<Elevator> elevators;

    ElevatorService(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public boolean call(Direction up, int floorNo) {

        return false;
    }
}
