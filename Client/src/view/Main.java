/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author gsc10
 */
public class Main {
    public static void main(String[] args) throws IOException{
        Socket clientSocket = new Socket("localhost", 8888);
        System.out.println("Connect successfull");
        System.out.println(clientSocket.getInetAddress());
        
        ObjectOutputStream sendData = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream receiveData = new ObjectInputStream(clientSocket.getInputStream());
//        Login login = new Login(receiveData, sendData);
        AuthScreen authScreen= new AuthScreen(receiveData, sendData);
//        DataInputStream receiveData = new DataInputStream(clientSocket.getInputStream());
//        DataOutputStream sendData = new DataOutputStream(clientSocket.getOutputStream());
    }
}
