
import view.Walls;
import view.Game;
import enums.Direction;
import java.util.Arrays;
import model.Coord;
import model.Wall;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
//        Game.initBoxes();
        System.out.println("init complete");
      
        Walls.addWall(new Wall(Game.BoxList[0][0], Game.BoxList[0][1]));
        Wall newWall=new Wall(Game.BoxList[1][0], Game.BoxList[1][1]);
//        System.out.println(newWall.getOrientation() +" new wall");
//        boolean canAdd= Walls.no_wall(Game.BoxList[1][1], Game.BoxList[1][2]);
//        if(canAdd) System.out.println("no wall");
//        else System.out.println("have wall");
//        if (Walls.can_add(newWall)) {
//            System.out.println(1);
//        }
    }
}
