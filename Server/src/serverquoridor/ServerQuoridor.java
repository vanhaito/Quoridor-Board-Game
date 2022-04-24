/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverquoridor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;

/**
 *
 * @author gsc10
 */
public class ServerQuoridor {
    private static List<ThreadSocket> clients = new ArrayList<>();
    public Room[] listRoom;

    public ServerQuoridor() {
        listRoom = new Room[10];
        for (int i = 0; i < listRoom.length; i++) listRoom[i] = new Room();
    }

    public static void main(String[] args) {
        try {
            ServerQuoridor server = new ServerQuoridor();
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is running");
            while (true) {
                ThreadSocket client = new ThreadSocket(serverSocket.accept(), server);
                client.start();
                System.out.println("New connection");
                clients.add(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerQuoridor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public static List<ThreadSocket> getClients() {
        return clients;
    }
}
