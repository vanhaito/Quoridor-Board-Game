/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author ASUS
 */
public enum Direction {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    //cac gia tri 0, 1, 2, 3 de xac dinh 4 vi tri Adjcent trong class Box
    private final int index;

    private Direction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    //Su dung trong truong hop doi thu di chuyen
    public Direction reverse() {
        if (this == Direction.UP) {
            return Direction.DOWN;
        } else if (this == Direction.DOWN) {
            return Direction.UP;
        } else if (this == Direction.LEFT) {
            return Direction.RIGHT;
        } else  {
            return Direction.LEFT;
        }
    }
}
