/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enums.Direction;

/**
 *
 * @author ASUS
 */
public class Box {

    private Player player;
    private boolean isOccupied;
    private Coord coord;
    private Box[] adjacent;

    private Wall wallRight;
    private Wall wallDown;

    public Box(int row, int col) {
        this.coord = new Coord(row, col);
        this.adjacent = new Box[4];
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public boolean isSameRow(Box otherBox) {
        return this.coord.row == otherBox.getCoord().row;
    }

    public boolean isSameCol(Box otherBox) {
        return this.coord.col == otherBox.getCoord().col;
    }

    public void setWallRight() {
        if (this.adjacent[Direction.RIGHT.getIndex()] != null && this.coord.col != 8) {
            this.wallRight = new Wall(this, this.adjacent[Direction.RIGHT.getIndex()]);
        } else {
            this.wallRight = null;
        }
    }

    public void setWallDown() {
        if (this.adjacent[Direction.DOWN.getIndex()] != null && this.coord.row != 8) {
            this.wallDown = new Wall(this, this.adjacent[Direction.DOWN.getIndex()]);
        } else {
            this.wallDown = null;
        }
    }

    public Wall getWallDown() {
        return wallDown;
    }

    public Wall getWallRight() {
        return wallRight;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    // su dung de xac dinh cac hop o canh ben 
    public void setAdjacent(Direction direction, Box adjacentBox) {
        this.adjacent[direction.getIndex()] = adjacentBox;
    }

    public Box getAdjacent(Direction direction) {
        return adjacent[direction.getIndex()];
    }

    // su dung de xac dinh nguoi choi nao dang dung tren Box nay
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        Box otherBox = (Box) obj;
        return ((this.coord.col == otherBox.coord.col) && (this.coord.row == otherBox.coord.row)); //To change body of generated methods, choose Tools | Templates.
    }

}
