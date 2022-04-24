package serverquoridor;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.ObjectWrapper;
import static model.ObjectWrapper.*;
import model.Room;
import model.User;

/**
 *
 * @author gsc10
 */
public class ThreadSocket extends Thread {

    private Socket socket;
    private Dao dao;
    private ObjectWrapper ow;
    private ServerQuoridor server;
    private ObjectOutputStream sendData;
    private ObjectInputStream receiveData;
    public static final Dimension player = new Dimension(70, 70);
    public final Dimension wallH = new Dimension(160, 20);
    public final Dimension wallV = new Dimension(20, 160);
    public final int O_Di_Cot = 100;
    public final int O_Di_Hang = 50;
    public final int O_Tuong_Ngang = 170;
    public final int O_Tuong_Doc = 120;
    public final int Buoc_Nhay = 90;

    public ObjectOutputStream getSendData() {
        return sendData;
    }

    public void setSendData(ObjectOutputStream sendData) {
        this.sendData = sendData;
    }

    public ObjectInputStream getReceiveData() {
        return receiveData;
    }

    public void setReceiveData(ObjectInputStream receiveData) {
        this.receiveData = receiveData;
    }

    public ThreadSocket(Socket socket, ServerQuoridor serverQuoridor) {
        this.socket = socket;
        this.server = serverQuoridor;
        dao = new Dao();
    }

    @Override
    public void run() {
        try {
//            DataInputStream receiveData = new DataInputStream(socket.getInputStream());
//            DataOutputStream sendData = new DataOutputStream(socket.getOutputStream());
            receiveData = new ObjectInputStream(socket.getInputStream());
            sendData = new ObjectOutputStream(socket.getOutputStream());
            User user;
            User userDAO;

            while (true) {
                ow = (ObjectWrapper) receiveData.readObject();
                System.out.println(ow.toString());
                int option = ow.getPerformative();
                user = (User) ow.getUser();
                System.out.println("Check : " + option);
                //login
                if (option == LOGIN_USER) {
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Password: " + user.getPassword());
                    userDAO = dao.checkLogin(user.getUsername(), user.getPassword());
                    if (userDAO == null) {
                        sendData.writeObject(user);
                    } else {
                        sendData.writeObject(userDAO);
                    }
                }

                //register account
                if (option == REGISTER_USER) {
                    dao.addUser(user.getName(), user.getUsername(), user.getPassword(), user.getIpAddress());
                    sendData.writeObject(user);
                }

                if (option == GET_AVAILABLE_ROOM) {
                    //System.out.println("Cong");
                    ArrayList<Integer> num = new ArrayList<>();
                    for (int i = 0; i < server.listRoom.length; i++) {
                        if (server.listRoom[i].firstPlayer == null || server.listRoom[i].secondPlayer == null) {
                            num.add(i + 1);
                        }
                    }
                    System.out.println(num.size());
                    sendData.writeObject(num);
                }

                if (option == START_GAME) {
                    if (ow.getData() != null) {
                        int data = (int) ow.getData();
                        server.listRoom[data - 1].firstPlayer.getSendData().writeObject(true);
                    }
                }

//              // join room
                if (option == JOIN_ROOM) {
                    if (ow.getData() != null) {
                        int data = (Integer) ow.getData();
                        System.out.println(data);
                        boolean isFirstPlayer;
                        if (server.listRoom[data - 1].firstPlayer == null) {
                            System.out.println("check player 1");
                            server.listRoom[data - 1].firstPlayer = this;
                            isFirstPlayer = true;
                            sendData.writeObject(isFirstPlayer);
                        } else if (server.listRoom[data - 1].secondPlayer == null){
                            System.out.println("check player 2");
                            server.listRoom[data - 1].secondPlayer = this;
                            isFirstPlayer = false;
                            sendData.writeObject(isFirstPlayer);
                        }
                        else if (server.listRoom[data - 1].secondPlayer != null && server.listRoom[data - 1].firstPlayer != null){
                            System.out.println("check spectator");
                            server.listRoom[data - 1].listSpectator.add(this);
                            sendData.writeObject(server.listRoom[data - 1].chessBoard);
                        }
                    }
                }

                if (option == PLAYING) {
                    String str = (String) ow.getData();
                    String[] cuts = str.split("-");
                    int numRoom = Integer.parseInt(cuts[0]);
                    Room thisRoom=server.listRoom[numRoom-1];
                    if (cuts[1].equals("true")) {
                        String width = null;
                        String height = null;
                        String send = "";
                        String spectator = "Player1-";

                        for (int i = 2; i < cuts.length; i++) {
                            send = send + cuts[i] + "-";
                        }

                        if (cuts[2].equals("1")) {
                            if (cuts[3].equals("Down")) {
                                int X = Integer.parseInt(cuts[4]);
                                int Y = Integer.parseInt(cuts[5]);
                                width = String.valueOf((X - O_Di_Hang) / Buoc_Nhay);
                                height = String.valueOf((Y - O_Tuong_Ngang) / Buoc_Nhay);
                            } else {
                                int X = Integer.parseInt(cuts[4]);
                                int Y = Integer.parseInt(cuts[5]);
                                width = String.valueOf((X - O_Tuong_Doc) / Buoc_Nhay);
                                height = String.valueOf((Y - O_Di_Cot) / Buoc_Nhay);
                            }
                            spectator = spectator + cuts[2] + "-" + cuts[3] + "-" + width + "-" + height;
                            thisRoom.chessBoard.add(spectator);
                            if(thisRoom.listSpectator.size()>0){
                                for(int i=0; i<thisRoom.listSpectator.size(); i++){
                                    System.out.println("Check gui ng xem");
                                    if (thisRoom.listSpectator.get(i).isAlive()) {
                                        thisRoom.listSpectator.get(i).getSendData().writeObject(spectator);
                                    }
                                }
                            }
                        } else {
                            thisRoom.chessBoard.add("Player1-" + send);
                            if(thisRoom.listSpectator.size()>0){
                                for(int i=0; i<thisRoom.listSpectator.size(); i++){
                                    if (thisRoom.listSpectator.get(i).isAlive()) {
                                        thisRoom.listSpectator.get(i).getSendData().writeObject("Player1-" + send);
                                    }
                                }
                            }
                        }
                        System.out.println("abc");
                        thisRoom.secondPlayer.getSendData().writeObject(send);
                    } else {
                        String spectator = "Player2-";
                        String send = "";
                        String width = null;
                        String height = null;
                        if (cuts[2].equals("1")) {
                            if (cuts[3].equals("Down")) {
                                int X = Integer.parseInt(cuts[4]);
                                int Y = Integer.parseInt(cuts[5]);
                                width = String.valueOf(7 - ((X - O_Di_Hang) / Buoc_Nhay));
                                height = String.valueOf(7 - ((Y - O_Tuong_Ngang) / Buoc_Nhay));
                            } else {
                                int X = Integer.parseInt(cuts[4]);
                                int Y = Integer.parseInt(cuts[5]);
                                width = String.valueOf(7 - ((X - O_Tuong_Doc) / Buoc_Nhay));
                                height = String.valueOf((7 - ((Y - O_Di_Cot) / Buoc_Nhay)));
                            }
                            spectator = spectator + cuts[2] + "-" + cuts[3] + "-" + width + "-" + height;
                            thisRoom.chessBoard.add(spectator);
                            if(thisRoom.listSpectator.size()>0){
                                for(int i=0; i<thisRoom.listSpectator.size(); i++){
                                    if (thisRoom.listSpectator.get(i).isAlive()) {
                                        thisRoom.listSpectator.get(i).getSendData().writeObject(spectator);
                                    }
                                }
                            }
                        }
                        else if (cuts[2].equals("2")){
                            if (cuts[3].equals("Down")){
                                spectator = spectator + cuts[2] + "-" + "Up" + "-" + cuts[4];
                            }
                            else if (cuts[3].equals("Up")){
                                spectator = spectator + cuts[2] + "-" + "Down" + "-" + cuts[4];
                            }
                            else if (cuts[3].equals("Left")){
                                spectator = spectator + cuts[2] + "-" + "Right" + "-" + cuts[4];
                            }
                            else {
                                spectator = spectator + cuts[2] + "-" + "Left" + "-" + cuts[4];
                            }
                            thisRoom.chessBoard.add(spectator);
                            if(thisRoom.listSpectator.size()>0){
                                for(int i=0; i<thisRoom.listSpectator.size(); i++){      
                                    if (thisRoom.listSpectator.get(i).isAlive()) {
                                        thisRoom.listSpectator.get(i).getSendData().writeObject(spectator);
                                    }
                                }
                            }
                        }
                        else {
                            thisRoom.chessBoard.add(cuts[2]);
                            if(thisRoom.listSpectator.size()>0){
                                for(int i=0; i<thisRoom.listSpectator.size(); i++){
                                    if (thisRoom.listSpectator.get(i).isAlive()) {
                                        thisRoom.listSpectator.get(i).getSendData().writeObject(cuts[2]);
                                    }
                                }
                            }
                        }
                        for (int i = 2; i < cuts.length; i++) {
                            send = send + cuts[i] + "-";
                        }
                        
                        thisRoom.firstPlayer.getSendData().writeObject(send);
                    }
                }
                
                if (option == GET_AVAILABLE_GAME) {
                    ArrayList<Integer> num = new ArrayList<>();
                    for (int i = 0; i < server.listRoom.length; i++) {
                        if (server.listRoom[i].firstPlayer != null && server.listRoom[i].secondPlayer != null) {
                            num.add(i + 1);
                        }
                    }
                    System.out.println(num.size());
                    sendData.writeObject(num);
                }

                //reset option
                option = 0;
            }
        } catch (IOException ex) {
            System.out.println("1" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("2" + ex.getMessage());
        }
    }

}
