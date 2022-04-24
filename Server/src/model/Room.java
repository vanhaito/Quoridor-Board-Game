/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import serverquoridor.ThreadSocket;

/**
 *
 * @author nc
 */
public class Room {
    public ThreadSocket firstPlayer = null;
    public ThreadSocket secondPlayer = null;
    public List<ThreadSocket> listSpectator;
    public ArrayList<String> chessBoard;

    public Room() {
        this.listSpectator = new ArrayList<>();
        this.chessBoard = new ArrayList<>();
    }
}
