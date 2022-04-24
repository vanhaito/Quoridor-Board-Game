/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enums.Direction;
import enums.WallType;

/**
 *
 * @author ASUS
 */
public class Wall {

    private WallType orientation;
    final public Box box1;
    final public Box box2;
    public Box box3;
    public Box box4;

    public Wall crossWall;

    public Wall(Box box1, Box box2) {
        this.box1 = box1;
        this.box2 = box2;
        if (box1.isSameRow(box2)) {
            this.orientation = WallType.Vertical;
            this.box3 = box1.getAdjacent(Direction.DOWN);
            this.box4 = box2.getAdjacent(Direction.DOWN);
            //this.crossWall=box1.getWallDown();
        } else if (box1.isSameCol(box2)) {
            this.orientation = WallType.Horizontal;
            this.box3 = box1.getAdjacent(Direction.RIGHT);
            this.box4 = box2.getAdjacent(Direction.RIGHT);
            //this.crossWall=box1.getWallRight();
        }
    }

    public Wall getCrossWall() {
        return crossWall;
    }

    public WallType getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object obj) {
        Wall otherWall=(Wall) obj;
        return (this.box1.equals(otherWall.box1) && this.box2.equals(otherWall.box2)
                && this.box3.equals(otherWall.box3) && this.box4.equals(otherWall.box4)); //To change body of generated methods, choose Tools | Templates.
    }

    

}