package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import model.Box;
import model.Wall;
import model.Coord;

/**
 *
 * @author ASUS
 */
public class Walls {

    public static ArrayList<Wall> wallList= new ArrayList<>();
    public static HashMap<Coord, ArrayList<Coord>> blocked_coords= new HashMap<>();

    
    public static void addWall(Wall wall) {
        wallList.add(wall);
        Coord coord1 = wall.box1.getCoord();
        Coord coord2 = wall.box2.getCoord();
        //System.out.println("coord2: " + coord2.toString());
        Coord coord3 = wall.box3.getCoord();
        Coord coord4 = wall.box4.getCoord();
     
        
        if (!blocked_coords.containsKey(coord1)) {
            //System.out.println("start if");
            ArrayList<Coord> coordList = new ArrayList<>();
            coordList.add(coord2);
            blocked_coords.put(coord1, coordList);
        } else {
            //System.out.println("start else");
            blocked_coords.get(coord1).add(coord2);
        }

        if (!blocked_coords.containsKey(coord2)) {
            ArrayList<Coord> coordList = new ArrayList<>();
            coordList.add(coord1);
            blocked_coords.put(coord2, coordList);
        } else {
            blocked_coords.get(coord2).add(coord1);
        }

        if (!blocked_coords.containsKey(coord3)) {
            ArrayList<Coord> coordList = new ArrayList<>();
            coordList.add(coord4);
            blocked_coords.put(coord3, coordList);
        } else {
            blocked_coords.get(coord3).add(coord4);
        }

        if (!blocked_coords.containsKey(coord4)) {
            ArrayList<Coord> coordList = new ArrayList<>();
            coordList.add(coord3);
            blocked_coords.put(coord4, coordList);
        } else {
            blocked_coords.get(coord4).add(coord3);
        }
        //System.out.println("end if");
    }

    public static boolean contain_wall(Wall wall) {
        Coord coord1 = wall.box1.getCoord();
        Coord coord2 = wall.box2.getCoord();
        Coord coord3 = wall.box3.getCoord();
        Coord coord4 = wall.box4.getCoord();

        if (blocked_coords.containsKey(coord1)) {
            if (blocked_coords.get(coord1).contains(coord2)) {
                //System.out.println("contain");
                return true;
            }
        }
        if (blocked_coords.containsKey(coord3)) {
            if (blocked_coords.get(coord3).contains(coord4)) {
                //System.out.println("contain");
                return true;
            }
        }
        return false;
    }

    public static boolean wall_in_wall(Wall otherWall) {
        //return true neu co tuong trung nhau
        for (Wall w : wallList) {
            //System.out.println("check");
            if (w.equals(otherWall)) {
                return true;
            }
        }
        return false;
    }

    public static boolean can_add(Wall wall) {
        if (!contain_wall(wall) && !wall_in_wall(wall.getCrossWall())) {
            //System.out.println("add wall accept");
            return true;
        }
        //System.out.println("add wall denied");
        return false;
    }

    public static boolean no_wall(Box box1, Box box2) {
        if (blocked_coords.containsKey(box1.getCoord())) {
            return !blocked_coords.get(box1.getCoord()).contains(box2.getCoord());
        }
        return true;
    }
}
