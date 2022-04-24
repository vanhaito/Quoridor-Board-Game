/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import enums.Direction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Box;
import model.User;
import model.Wall;

/**
 *
 * @author nc
 */
public class ClientSpectator extends JPanel {

    public static Box[][] BoxList = new Box[9][9];
    public static final Dimension player = new Dimension(70, 70);
    public static final Dimension wallH = new Dimension(160, 20);
    public static final Dimension wallV = new Dimension(20, 160);
    public static final int O_Di_Cot = 100;
    public static final int O_Di_Hang = 50;
    public static final int O_Tuong_Ngang = 170;
    public static final int O_Tuong_Doc = 120;
    public static final int Buoc_Nhay = 90;
    public static final int Icon = 50;
    public static final JPanel[][] listPanel = new JPanel[9][9];
    public static int yourWalls = 10;
    public static int opponentWalls = 10;
    public static final JPanel[][] listWallHorizontal = new JPanel[8][8];
    public static final JPanel[][] listWallVertical = new JPanel[8][8];
    public static boolean isFirstPlayer;
    public static boolean isMyTurn;
    public static boolean isWin;
    public static boolean isPlaying;
    public ObjectInputStream inStream;
    public ObjectOutputStream ouStream;
    public User user;
    JLabel iconCat = new JLabel(new ImageIcon("src/image_assets/icon-cat.png"));
    JLabel iconDog = new JLabel(new ImageIcon("src/image_assets/icon-dog.png"));
    Stack<Integer> myTurnStack = new Stack<>();
    Stack<Integer> opponentTurnStack = new Stack<>();
    public static int numRoom;
    private int vDog = 4;
    private int hDog = 8;
    private int vCat = 4;
    private int hCat = 0;

    public ClientSpectator(ObjectInputStream inStream, ObjectOutputStream outputStream, User user, int numRoom) throws IOException {
        setLayout(null);
        initBoxes();
        this.inStream = inStream;
        this.ouStream = outputStream;
        this.user = user;
        this.numRoom = numRoom;

        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                listPanel[i][j] = new JPanel();
                if (i < 8 && j < 8) {
                    listWallHorizontal[i][j] = new JPanel();
                    listWallVertical[i][j] = new JPanel();
                    System.out.println(i + " " + j);
                }
            }
        }

        //set icon
        for (int i = 0; i < 9; i++) {
            JPanel a = new JPanel();
            a.add(new JLabel(new ImageIcon("src/image_assets/icon-steak.png")));
            a.setBounds(Icon + i * 90, 20, 70, 70);
            add(a);
        }
        for (int i = 0; i < 9; i++) {
            JPanel a = new JPanel();
            a.add(new JLabel(new ImageIcon("src/image_assets/icon-fish.png")));
            a.setBounds(Icon + i * 90, 890, 70, 70);
            add(a);
        }

        //set so luong tuong Cat
        JPanel jCat = new JPanel();
        jCat.add(new JLabel(new ImageIcon("src/image_assets/icon-cat.png")));
        jCat.setBounds(900, 150, 70, 70);
        add(jCat);
        JTextArea txtWallCat = new JTextArea();
        txtWallCat.setBounds(1000, 170, 140, 40);
        txtWallCat.setText(opponentWalls + " Walls");
        txtWallCat.setForeground(new Color(128, 128, 128));
        txtWallCat.setEditable(false);
        txtWallCat.setBackground(null);
        txtWallCat.setFont(new Font("Times New Roman", 1, 35));
        add(txtWallCat);

        //set so luong tuong Dog
        JPanel jDog = new JPanel();
        jDog.add(new JLabel(new ImageIcon("src/image_assets/icon-dog.png")));
        jDog.setBounds(900, 300, 70, 70);
        add(jDog);
        JTextArea txtWallDog = new JTextArea();
        txtWallDog.setBounds(1000, 320, 140, 40);
        txtWallDog.setText(yourWalls + " Walls");
        txtWallDog.setForeground(new Color(222, 184, 135));
        txtWallDog.setEditable(false);
        txtWallDog.setBackground(null);
        txtWallDog.setFont(new Font("Times New Roman", 1, 35));
        add(txtWallDog);

        //set Button
        JButton btnQuit = new JButton("Quit");
        //btnQuit.setBackground(Color.red);
        btnQuit.setBounds(900, 900, 150, 30);
        add(btnQuit);

        //set notification
        listPanel[0][4].add(iconCat);
        listPanel[8][4].add(iconDog);
        BoxList[0][4].setIsOccupied(true);
        BoxList[8][4].setIsOccupied(true);

        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                listPanel[i][j].setBackground(new Color(102, 153, 255));
                listPanel[i][j].setBounds(O_Di_Hang + (j * Buoc_Nhay), O_Di_Cot + (i * Buoc_Nhay), player.width, player.height);
                add(listPanel[i][j]);
            }
        }

        try {
            ArrayList<String> chessBoard = (ArrayList<String>) inStream.readObject();
            for (String str : chessBoard) {
                System.out.println(str);
            }
            for (String str : chessBoard) {
                String[] cuts = str.split("-");
                if (cuts[1].equals("1")) {
                    int j = Integer.parseInt(cuts[3]);
                    int i = Integer.parseInt(cuts[4]);
                    if (cuts[2].equals("Down")) {
                        System.out.println("Add Wall Down");
                        JPanel b = new JPanel();
                        b.setBackground(Color.GREEN);
                        b.setBounds(O_Di_Hang + (j * Buoc_Nhay), O_Tuong_Ngang + (i * Buoc_Nhay), wallH.width, wallH.height);
                        add(b);
                    } else {
                        System.out.println("Add Wall Right");
                        JPanel b = new JPanel();
                        b.setBackground(Color.GREEN);
                        b.setBounds(O_Tuong_Doc + (j * Buoc_Nhay), O_Di_Cot + (i * Buoc_Nhay), wallV.width, wallV.height);
                        add(b);
                    }

                } else if (cuts[1].equals("2")) {
                    if (cuts[2].equals("Left")) {
                        if (cuts[3].equals("1")) {

                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog][vDog - 1].add(iconDog);
                                listPanel[hDog][vDog - 1].revalidate();
                                listPanel[hDog][vDog - 1].repaint();
                                vDog -= 1;
                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat][vCat - 1].add(iconCat);
                                listPanel[hCat][vCat - 1].revalidate();
                                listPanel[hCat][vCat - 1].repaint();
                                vCat -= 1;
                            }
                        } else if (cuts[3].equals("2")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog][vDog - 2].add(iconDog);
                                listPanel[hDog][vDog - 2].revalidate();
                                listPanel[hDog][vDog - 2].repaint();
                                vDog -= 2;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat][vCat - 2].add(iconCat);
                                listPanel[hCat][vCat - 2].revalidate();
                                listPanel[hCat][vCat - 2].repaint();
                                vCat -= 2;
                            }
                        }
                    } else if (cuts[2].equals("Right")) {
                        if (cuts[3].equals("1")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog][vDog + 1].add(iconDog);
                                listPanel[hDog][vDog + 1].revalidate();
                                listPanel[hDog][vDog + 1].repaint();
                                vDog += 1;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat][vCat + 1].add(iconCat);
                                listPanel[hCat][vCat + 1].revalidate();
                                listPanel[hCat][vCat + 1].repaint();
                                vCat += 1;
                            }
                        } else if (cuts[3].equals("2")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog][vDog + 2].add(iconDog);
                                listPanel[hDog][vDog + 2].revalidate();
                                listPanel[hDog][vDog + 2].repaint();
                                vDog += 2;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat][vCat + 2].add(iconCat);
                                listPanel[hCat][vCat + 2].revalidate();
                                listPanel[hCat][vCat + 2].repaint();
                                vCat += 2;
                            }
                        }
                    } else if (cuts[2].equals("Up")) {
                        if (cuts[3].equals("1")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog - 1][vDog].add(iconDog);
                                listPanel[hDog - 1][vDog].revalidate();
                                listPanel[hDog - 1][vDog].repaint();
                                hDog -= 1;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat - 1][vCat].add(iconCat);
                                listPanel[hCat - 1][vCat].revalidate();
                                listPanel[hCat - 1][vCat].repaint();
                                hCat -= 1;
                            }
                        } else if (cuts[3].equals("2")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog - 2][vDog].add(iconDog);
                                listPanel[hDog - 2][vDog].revalidate();
                                listPanel[hDog - 2][vDog].repaint();
                                hDog -= 2;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat - 2][vCat].add(iconCat);
                                listPanel[hCat - 2][vCat].revalidate();
                                listPanel[hCat - 2][vCat].repaint();
                                hCat -= 2;
                            }
                        }
                    } else if (cuts[2].equals("Down")) {
                        if (cuts[3].equals("1")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog + 1][vDog].add(iconDog);
                                listPanel[hDog + 1][vDog].revalidate();
                                listPanel[hDog + 1][vDog].repaint();
                                hDog += 1;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat + 1][vCat].add(iconCat);
                                listPanel[hCat + 1][vCat].revalidate();
                                listPanel[hCat + 1][vCat].repaint();
                                hCat += 1;
                            }
                        } else if (cuts[3].equals("2")) {
                            if (cuts[0].equals("Player1")) {
                                listPanel[hDog][vDog].removeAll();
                                listPanel[hDog][vDog].revalidate();
                                listPanel[hDog][vDog].repaint();
                                listPanel[hDog + 2][vDog].add(iconDog);
                                listPanel[hDog + 2][vDog].revalidate();
                                listPanel[hDog + 2][vDog].repaint();
                                hDog += 2;

                            } else {
                                listPanel[hCat][vCat].removeAll();
                                listPanel[hCat][vCat].revalidate();
                                listPanel[hCat][vCat].repaint();
                                listPanel[hCat + 2][vCat].add(iconCat);
                                listPanel[hCat + 2][vCat].revalidate();
                                listPanel[hCat + 2][vCat].repaint();
                                hCat += 2;
                            }
                        }
                    }

                } else {

                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientSpectator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Update
        new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        }).start();
    }

    private static void initBoxes() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                BoxList[i][j] = new Box(i, j);
                BoxList[i][j].setIsOccupied(false);
            }
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                BoxList[i][j].setAdjacent(Direction.UP, BoxList[i - 1][j]);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                BoxList[i][j].setAdjacent(Direction.DOWN, BoxList[i + 1][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                BoxList[i][j].setAdjacent(Direction.LEFT, BoxList[i][j - 1]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                BoxList[i][j].setAdjacent(Direction.RIGHT, BoxList[i][j + 1]);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                BoxList[i][j].setWallRight();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                BoxList[i][j].setWallDown();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (BoxList[i][j].getWallRight() != null) {
                    BoxList[i][j].getWallRight().crossWall = BoxList[i][j].getWallDown();
                }
                if (BoxList[i][j].getWallDown() != null) {
                    BoxList[i][j].getWallDown().crossWall = BoxList[i][j].getWallRight();
                }
            }
        }
    }
}
