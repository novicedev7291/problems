package com.kuldeep.design;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class Elevator {
    private final Integer id;
    private final Integer currentFloor;
    private Integer nextFloor;

    Elevator(Integer id, Integer currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
    }
}
