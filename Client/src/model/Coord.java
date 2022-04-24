/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class Coord {
    public final int row;
    public final int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return ""+ row + " " + col; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Coord otherCoord=(Coord) obj;
        return (this.row == otherCoord.row && this.col==otherCoord.col); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
