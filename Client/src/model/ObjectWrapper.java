/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author congd
 */
public class ObjectWrapper  implements Serializable{
    private static final long serialVersionUID = 20210811011L;
    public static final int LOGIN_USER = 1;
    public static final int REGISTER_USER = 2;
    public static final int GET_AVAILABLE_ROOM = 3;
    public static final int JOIN_ROOM = 4;
    public static final int START_GAME = 5;
    public static final int NAME = 6;
    public static final int PLAYING = 7;
    public static final int GET_AVAILABLE_GAME = 8;
    
    private Object data;
    
    private int performative;
    private User user;
    public ObjectWrapper() {
        //super();
    }
    public ObjectWrapper(int performative, User user, Object data) {
        //super();
        this.performative = performative;
        this.user = user;
        this.data = data;
    }
    public int getPerformative() {
        return performative;
    }
    public void setPerformative(int performative) {
        this.performative = performative;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "ObjectWrapper{" + "performative=" + performative + ", data=" + user + '}';
    }
    
}
