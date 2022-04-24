package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import enums.Direction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Box;
import model.ObjectWrapper;
import model.User;
import model.Wall;

/**
 *
 * @author ASUS
 */
public class Game extends JPanel {

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

    public Game(boolean isFirstPlayer, ObjectInputStream inStream, ObjectOutputStream outputStream, User user, int numRoom) throws IOException {
        setLayout(null);
        initBoxes();
        this.isFirstPlayer = isFirstPlayer;
        this.isWin = false;
        this.isPlaying = true;
        if (this.isFirstPlayer) {
            this.isMyTurn = true;
        } else {
            this.isMyTurn = false;
        }
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
            a.add(new JLabel(isFirstPlayer ? new ImageIcon("src/image_assets/icon-steak.png") : new ImageIcon("src/image_assets/icon-fish.png")));
            a.setBounds(Icon + i * 90, 20, 70, 70);
            add(a);
        }
        for (int i = 0; i < 9; i++) {
            JPanel a = new JPanel();
            a.add(new JLabel(isFirstPlayer ? new ImageIcon("src/image_assets/icon-fish.png") : new ImageIcon("src/image_assets/icon-steak.png")));
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
        JButton btnGG = new JButton("Surrender");
        btnGG.setBounds(900, 800, 150, 30);
        add(btnGG);

        //set notification
        JTextArea txtNotifi = new JTextArea();
        txtNotifi.setBounds(50, 970, 890, 100);
        txtNotifi.setText("WELCOME " + user.getName().toUpperCase() + " TO QUORIDOR :3");
        txtNotifi.setEditable(false);
        txtNotifi.setBackground(null);
        txtNotifi.setFont(new Font("Times New Roman", 1, 35));
        add(txtNotifi);

        if (isFirstPlayer) {
            listPanel[0][4].add(iconCat);
            listPanel[8][4].add(iconDog);
        } else {
            listPanel[0][4].add(iconDog);
            listPanel[8][4].add(iconCat);
        }
        BoxList[0][4].setIsOccupied(true);
        BoxList[8][4].setIsOccupied(true);

        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                listPanel[i][j].setBackground(new Color(102, 153, 255));
                listPanel[i][j].setBounds(O_Di_Hang + (j * Buoc_Nhay), O_Di_Cot + (i * Buoc_Nhay), player.width, player.height);
                add(listPanel[i][j]);
                if (BoxList[i][j].getWallDown() != null) {
                    Wall wallDown = BoxList[i][j].getWallDown();
                    //JPanel b = new JPanel();
                    listWallVertical[i][j].setBackground(Color.WHITE);
                    listWallVertical[i][j].setBounds(O_Di_Hang + j * Buoc_Nhay, O_Tuong_Ngang + i * Buoc_Nhay, wallH.width, wallH.height);
                    add(listWallVertical[i][j]);
                    JPanel b = listWallVertical[i][j];
                    listWallVertical[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int X = e.getComponent().getX();
                            int Y = e.getComponent().getY();
                            int x = e.getX();
                            //System.out.println(e.getComponent());
                            if (X == 680 && (x > 90 && x <= 160)) {
                                System.out.println("No add");
                            } else if (Walls.can_add(wallDown) && yourWalls > 0 && isMyTurn && isPlaying) {
                                try {
                                    yourWalls--;
                                    if (isFirstPlayer) {
                                        txtWallDog.setText(yourWalls + " Walls");
                                    } else {
                                        txtWallCat.setText(yourWalls + " Walls");
                                    }
                                    b.setBackground(Color.GREEN);
                                    b.setBounds(X, Y, wallH.width, wallH.height);
                                    //System.out.println(X + " " + Y);
                                    add(b);
                                    //listWallVertical[i][j].setBackground(Color.GREEN);
                                    Walls.addWall(wallDown);
                                    System.out.println(wallDown.box1.getCoord().toString());
                                    //outputStream.writeUTF("1-Down-" + X + "-" + Y);
                                    String str = numRoom + "-" + isFirstPlayer + "-1-Down-" + X + "-" + Y;
                                    System.out.println(str);
                                    outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                    isMyTurn = false;
                                    txtNotifi.setText("Waiting for your opponent...");
                                } catch (IOException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (!Walls.can_add(wallDown)) {
                                txtNotifi.setText("You can not add a Wall here!!!");
                            } else if (yourWalls == 0) {
                                txtNotifi.setText("You've placed all your Walls ");
                            } else if (!isMyTurn) {
                                txtNotifi.setText("It's your opponent turn");
                            }
                        }
                    });
                }
                if (BoxList[i][j].getWallRight() != null) {
                    Wall wallRight = BoxList[i][j].getWallRight();
                    listWallHorizontal[i][j].setBackground(Color.WHITE);
                    listWallHorizontal[i][j].setBounds(O_Tuong_Doc + j * Buoc_Nhay, O_Di_Cot + i * Buoc_Nhay, wallV.width, wallV.height);
                    add(listWallHorizontal[i][j]);
                    JPanel c = listWallHorizontal[i][j];
                    listWallHorizontal[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int Y = e.getComponent().getY();
                            int X = e.getComponent().getX();
                            System.out.println(X);
                            System.out.println(Y);
                            int y = e.getY();
                            if (Y == 730 && (y <= 160 && y > 90)) {
                                System.out.println("No add");
                            } else if (Walls.can_add(wallRight) && yourWalls > 0 && isMyTurn && isPlaying) {
                                try {
                                    yourWalls--;
                                    if (isFirstPlayer) {
                                        txtWallDog.setText(yourWalls + " Walls");
                                    } else {
                                        txtWallCat.setText(yourWalls + " Walls");
                                    }
                                    c.setBackground(Color.GREEN);
                                    c.setBounds(X, Y, wallV.width, wallV.height);
                                    //System.out.println(X + " " + Y);
                                    add(c);
                                    Walls.addWall(wallRight);
                                    //outputStream.writeUTF("1-Right-" + X + "-" + Y);
                                    String str = numRoom + "-" + isFirstPlayer + "-1-Right-" + X + "-" + Y;
                                    System.out.println(str);
                                    outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                    isMyTurn = false;
                                    txtNotifi.setText("Waiting for your opponent...");
                                } catch (IOException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (!Walls.can_add(wallRight)) {
                                txtNotifi.setText("You can not add a Wall here");
                            } else if (yourWalls == 0) {
                                txtNotifi.setText("You've placed all your Walls");
                            } else if (!isMyTurn) {
                                txtNotifi.setText("It's your opponent turn");
                            }
                        }
                    });
                }
            }
        }

        myTurnStack.push(8);
        myTurnStack.push(4);
        opponentTurnStack.push(0);
        opponentTurnStack.push(4);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int col = myTurnStack.pop();
                    int row = myTurnStack.pop();
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:

                            if (col > 0 && Walls.no_wall(BoxList[row][col], BoxList[row][col - 1]) && isMyTurn && isPlaying) {
                                if (BoxList[row][col - 1].getIsOccupied()) {
                                    if (BoxList[row][col - 1].getAdjacent(Direction.LEFT) != null && !BoxList[row][col - 1].getAdjacent(Direction.LEFT).getIsOccupied() && Walls.no_wall(BoxList[row][col - 1], BoxList[row][col - 1].getAdjacent(Direction.LEFT))) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        myTurnStack.push(row);
                                        myTurnStack.push(col - 2);
                                        if (isFirstPlayer) {
                                            listPanel[row][col - 2].add(iconDog);

                                        } else {
                                            listPanel[row][col - 2].add(iconCat);
                                        }

                                        listPanel[row][col - 2].revalidate();
                                        listPanel[row][col - 2].repaint();
                                        BoxList[row][col - 2].setIsOccupied(true);
                                        try {
                                            //outputStream.writeUTF("2-Left-2");
                                            String str = numRoom + "-" + isFirstPlayer + "-2-Left-2";
                                            System.out.println(str);
                                            outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                            isMyTurn = false;
                                            txtNotifi.setText("Waiting for your opponent...");
                                        } catch (IOException ex) {
                                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    } else {
                                        myTurnStack.push(row);
                                        myTurnStack.push(col);
                                        txtNotifi.setText("You can not move this way");

                                    }
                                } else {
                                    listPanel[row][col].removeAll();
                                    listPanel[row][col].revalidate();
                                    listPanel[row][col].repaint();
                                    BoxList[row][col].setIsOccupied(false);

                                    myTurnStack.push(row);
                                    myTurnStack.push(col - 1);
                                    if (isFirstPlayer) {
                                        listPanel[row][col - 1].add(iconDog);
                                    } else {
                                        listPanel[row][col - 1].add(iconCat);
                                    }
                                    listPanel[row][col - 1].revalidate();
                                    listPanel[row][col - 1].repaint();
                                    BoxList[row][col - 1].setIsOccupied(true);
                                    try {
                                        //outputStream.writeUTF("2-Left-1");
                                        String str = numRoom + "-" + isFirstPlayer + "-2-Left-1";
                                        System.out.println(str);
                                        outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                        isMyTurn = false;
                                        txtNotifi.setText("Waiting for your opponent...");
                                    } catch (IOException ex) {
                                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } else {
                                myTurnStack.push(row);
                                myTurnStack.push(col);
                                txtNotifi.setText("You can not move this way");
                            }
                            break;
                        case KeyEvent.VK_RIGHT:

                            if (col < 8 && Walls.no_wall(BoxList[row][col], BoxList[row][col + 1]) && isMyTurn && isPlaying) {
                                if (BoxList[row][col + 1].getIsOccupied()) {
                                    if (BoxList[row][col + 1].getAdjacent(Direction.RIGHT) != null && !BoxList[row][col + 1].getAdjacent(Direction.RIGHT).getIsOccupied() && Walls.no_wall(BoxList[row][col + 1], BoxList[row][col + 1].getAdjacent(Direction.RIGHT))) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        myTurnStack.push(row);
                                        myTurnStack.push(col + 2);
                                        if (isFirstPlayer) {
                                            listPanel[row][col + 2].add(iconDog);
                                        } else {
                                            listPanel[row][col + 2].add(iconCat);
                                        }
                                        listPanel[row][col + 2].revalidate();
                                        listPanel[row][col + 2].repaint();
                                        BoxList[row][col + 2].setIsOccupied(true);
                                        try {
                                            //outputStream.writeUTF("2-Right-2");
                                            String str = numRoom + "-" + isFirstPlayer + "-2-Right-2";
                                            System.out.println(str);
                                            outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                            isMyTurn = false;
                                            txtNotifi.setText("Waiting for your opponent...");
                                        } catch (IOException ex) {
                                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        myTurnStack.push(row);
                                        myTurnStack.push(col);
                                        txtNotifi.setText("You can not move this way");
                                    }
                                } else {
                                    listPanel[row][col].removeAll();
                                    listPanel[row][col].revalidate();
                                    listPanel[row][col].repaint();
                                    BoxList[row][col].setIsOccupied(false);
                                    myTurnStack.push(row);
                                    myTurnStack.push(col + 1);
                                    if (isFirstPlayer) {
                                        listPanel[row][col + 1].add(iconDog);
                                    } else {
                                        listPanel[row][col + 1].add(iconCat);
                                    }
                                    listPanel[row][col + 1].revalidate();
                                    listPanel[row][col + 1].repaint();
                                    BoxList[row][col + 1].setIsOccupied(true);
                                    try {
                                        //outputStream.writeUTF("2-Right-1");
                                        String str = numRoom + "-" + isFirstPlayer + "-2-Right-1";
                                        System.out.println(str);
                                        outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                        isMyTurn = false;
                                        txtNotifi.setText("Waiting for your opponent...");
                                    } catch (IOException ex) {
                                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } else {
                                myTurnStack.push(row);
                                myTurnStack.push(col);
                                txtNotifi.setText("You can not move this way");
                            }
                            break;
                        case KeyEvent.VK_DOWN:

                            if (row < 8 && Walls.no_wall(BoxList[row][col], BoxList[row + 1][col]) && isMyTurn && isPlaying) {
                                if (BoxList[row + 1][col].getIsOccupied()) {
                                    if (BoxList[row + 1][col].getAdjacent(Direction.DOWN) != null && !BoxList[row + 1][col].getAdjacent(Direction.DOWN).getIsOccupied()
                                            && Walls.no_wall(BoxList[row + 1][col], BoxList[row + 1][col].getAdjacent(Direction.DOWN))) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        myTurnStack.push(row + 2);
                                        myTurnStack.push(col);
                                        if (isFirstPlayer) {
                                            listPanel[row + 2][col].add(iconDog);
                                        } else {
                                            listPanel[row + 2][col].add(iconCat);
                                        }
                                        listPanel[row + 2][col].revalidate();
                                        listPanel[row + 2][col].repaint();
                                        BoxList[row + 2][col].setIsOccupied(true);
                                        try {
                                            //outputStream.writeUTF("2-Down-2");
                                            String str = numRoom + "-" + isFirstPlayer + "-2-Down-2";
                                            System.out.println(str);
                                            outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                            isMyTurn = false;
                                            txtNotifi.setText("Waiting for your opponent...");
                                        } catch (IOException ex) {
                                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        myTurnStack.push(row);
                                        myTurnStack.push(col);
                                        txtNotifi.setText("You can not move this way");
                                    }
                                } else {
                                    listPanel[row][col].removeAll();
                                    listPanel[row][col].revalidate();
                                    listPanel[row][col].repaint();
                                    BoxList[row][col].setIsOccupied(false);
                                    myTurnStack.push(row + 1);
                                    myTurnStack.push(col);
                                    if (isFirstPlayer) {
                                        listPanel[row + 1][col].add(iconDog);
                                    } else {
                                        listPanel[row + 1][col].add(iconCat);
                                    }
                                    listPanel[row + 1][col].revalidate();
                                    listPanel[row + 1][col].repaint();
                                    BoxList[row + 1][col].setIsOccupied(true);
                                    try {
                                        //outputStream.writeUTF("2-Down-1");
                                        String str = numRoom + "-" + isFirstPlayer + "-2-Down-1";
                                        System.out.println(str);
                                        outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                        isMyTurn = false;
                                        txtNotifi.setText("Waiting for your opponent...");
                                    } catch (IOException ex) {
                                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } else {
                                myTurnStack.push(row);
                                myTurnStack.push(col);
                                txtNotifi.setText("You can not move this way");
                            }

                            break;
                        case KeyEvent.VK_UP:

                            if (row > 0 && Walls.no_wall(BoxList[row][col], BoxList[row - 1][col]) && isMyTurn && isPlaying) {
                                if (BoxList[row - 1][col].getIsOccupied()) {
                                    if (BoxList[row - 1][col].getAdjacent(Direction.UP) != null && !BoxList[row - 1][col].getAdjacent(Direction.UP).getIsOccupied()
                                            && Walls.no_wall(BoxList[row - 1][col], BoxList[row - 1][col].getAdjacent(Direction.UP))) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        myTurnStack.push(row - 2);
                                        myTurnStack.push(col);
                                        if (isFirstPlayer) {
                                            listPanel[row - 2][col].add(iconDog);
                                        } else {
                                            listPanel[row - 2][col].add(iconCat);
                                        }
                                        listPanel[row - 2][col].revalidate();
                                        listPanel[row - 2][col].repaint();
                                        BoxList[row - 2][col].setIsOccupied(true);
                                        try {
                                            //outputStream.writeUTF("2-Up-2");
                                            String str = numRoom + "-" + isFirstPlayer + "-2-Up-2";
                                            System.out.println(str);
                                            outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                            isMyTurn = false;
                                            if (row - 2 == 0) {
                                                setGameRes(true);
                                                txtNotifi.setText("You Win ^^");
                                                //outputStream.writeUTF("3");
                                                String str1 = numRoom + "-" + isFirstPlayer + "-3";
                                                System.out.println(str);
                                                outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str1));
                                            } else {
                                                txtNotifi.setText("Waiting for your opponent...");
                                            }
                                        } catch (IOException ex) {
                                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        myTurnStack.push(row);
                                        myTurnStack.push(col);
                                        txtNotifi.setText("You can not move this way");
                                    }
                                } else {
                                    listPanel[row][col].removeAll();
                                    listPanel[row][col].revalidate();
                                    listPanel[row][col].repaint();
                                    BoxList[row][col].setIsOccupied(false);
                                    myTurnStack.push(row - 1);
                                    myTurnStack.push(col);
                                    if (isFirstPlayer) {
                                        listPanel[row - 1][col].add(iconDog);
                                    } else {
                                        listPanel[row - 1][col].add(iconCat);
                                    }
                                    listPanel[row - 1][col].revalidate();
                                    listPanel[row - 1][col].repaint();
                                    BoxList[row - 1][col].setIsOccupied(true);
                                    try {
                                        //outputStream.writeUTF("2-Up-1");
                                        String str = numRoom + "-" + isFirstPlayer + "-2-Up-1";
                                        System.out.println(str);
                                        outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                                        isMyTurn = false;
                                        if (row - 1 == 0) {
                                            setGameRes(true);
                                            txtNotifi.setText("You Win ^^");
                                            //outputStream.writeUTF("3");
                                            String str1 = numRoom + "-" + isFirstPlayer + "-3";
                                            System.out.println(str);
                                            outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str1));
                                        } else {
                                            txtNotifi.setText("Waiting for your opponent...");
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } else {
                                myTurnStack.push(row);
                                myTurnStack.push(col);
                                txtNotifi.setText("You can not move this way");
                            }
                            break;
                    }
                }
            }
        });

        btnQuit.addMouseListener(new MouseAdapter() {

        });

        btnGG.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtNotifi.setText("You lose :((");
                setGameRes(false);
                try {
                    //outputStream.writeUTF("4");
                    String str = numRoom + "-" + isFirstPlayer + "-4";
                    System.out.println(str);
                    outputStream.writeObject(new ObjectWrapper(ObjectWrapper.PLAYING, user, str));
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        //bat sk
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlaying) {
                    try {
                        String str = null;
                        try {
                            str = (String) inStream.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        isMyTurn = true;
                        txtNotifi.setText("It's your turn");
                        if (!str.equals("")) {
                            String[] strs = str.split("-");
                            System.out.println(strs[0]);
//                            System.out.println(strs[1]);
//                            System.out.println(strs[2]);
                            if (strs[0].equals("1")) {
                                if (strs[1].equals("Down")) {
                                    int j = (Integer.parseInt(strs[2]) - O_Di_Hang) / Buoc_Nhay;
                                    int i = (Integer.parseInt(strs[3]) - O_Tuong_Ngang) / Buoc_Nhay;
                                    //System.out.println(i + " " + j);
                                    opponentWalls--;
                                    if (isFirstPlayer) {
                                        txtWallCat.setText(opponentWalls + " Walls");
                                    } else {
                                        txtWallDog.setText(opponentWalls + " Walls");
                                    }
                                    Wall wallDown = BoxList[7 - i][7 - j].getWallDown();
                                    JPanel b = listWallVertical[7 - i][7 - j];
                                    b.setBackground(Color.GREEN);
                                    System.out.println(Integer.parseInt(strs[2]) + " " + Integer.parseInt(strs[3]));
                                    b.setBounds(O_Di_Hang + (7 - j) * Buoc_Nhay, O_Tuong_Ngang + (7 - i) * Buoc_Nhay, wallH.width, wallH.height);
                                    add(b);
                                    Walls.addWall(wallDown);
                                    //System.out.println(wallDown.box1.getCoord().toString());
                                } else {
                                    int j = (Integer.parseInt(strs[2]) - O_Tuong_Doc) / Buoc_Nhay;
                                    int i = (Integer.parseInt(strs[3]) - O_Di_Cot) / Buoc_Nhay;
                                    System.out.println(i + " " + j);
                                    //System.out.println(i + " " + j);
                                    opponentWalls--;
                                    if (isFirstPlayer) {
                                        txtWallCat.setText(opponentWalls + " Walls");
                                    } else {
                                        txtWallDog.setText(opponentWalls + " Walls");
                                    }
                                    Wall wallRight = BoxList[7 - i][7 - j].getWallRight();
                                    JPanel b = listWallHorizontal[7 - i][7 - j];
                                    b.setBackground(Color.GREEN);
                                    System.out.println(Integer.parseInt(strs[2]) + " " + Integer.parseInt(strs[3]));
                                    b.setBounds(O_Tuong_Doc + (7 - j) * Buoc_Nhay, O_Di_Cot + (7 - i) * Buoc_Nhay, wallV.width, wallV.height);
                                    add(b);
                                    Walls.addWall(wallRight);
                                    //System.out.println(wallDown.box1.getCoord().toString());
                                }
                            } else if (strs[0].equals("2")) {
                                int col = opponentTurnStack.pop();
                                int row = opponentTurnStack.pop();
                                if (strs[1].equals("Left")) {
                                    if (strs[2].equals("1")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row);
                                        opponentTurnStack.push(col + 1);
                                        if (!isFirstPlayer) {
                                            listPanel[row][col + 1].add(iconDog);
                                        } else {
                                            listPanel[row][col + 1].add(iconCat);
                                        }
                                        listPanel[row][col + 1].revalidate();
                                        listPanel[row][col + 1].repaint();
                                        BoxList[row][col + 1].setIsOccupied(true);
                                    } else if (strs[2].equals("2")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row);
                                        opponentTurnStack.push(col + 2);
                                        if (!isFirstPlayer) {
                                            listPanel[row][col + 2].add(iconDog);
                                        } else {
                                            listPanel[row][col + 2].add(iconCat);
                                        }
                                        listPanel[row][col + 2].revalidate();
                                        listPanel[row][col + 2].repaint();
                                        BoxList[row][col + 2].setIsOccupied(true);
                                    }
                                } else if (strs[1].equals("Right")) {
                                    if (strs[2].equals("1")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row);
                                        opponentTurnStack.push(col - 1);
                                        if (!isFirstPlayer) {
                                            listPanel[row][col - 1].add(iconDog);
                                        } else {
                                            listPanel[row][col - 1].add(iconCat);
                                        }
                                        listPanel[row][col - 1].revalidate();
                                        listPanel[row][col - 1].repaint();
                                        BoxList[row][col - 2].setIsOccupied(true);
                                    } else if (strs[2].equals("2")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row);
                                        opponentTurnStack.push(col - 2);
                                        if (!isFirstPlayer) {
                                            listPanel[row][col - 2].add(iconDog);
                                        } else {
                                            listPanel[row][col - 2].add(iconCat);
                                        }
                                        listPanel[row][col - 2].revalidate();
                                        listPanel[row][col - 2].repaint();
                                        BoxList[row][col - 2].setIsOccupied(true);
                                    }
                                } else if (strs[1].equals("Up")) {
                                    System.out.println("top");
                                    if (strs[2].equals("1")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row + 1);
                                        opponentTurnStack.push(col);
                                        if (!isFirstPlayer) {
                                            listPanel[row + 1][col].add(iconDog);
                                        } else {
                                            listPanel[row + 1][col].add(iconCat);
                                        }
                                        listPanel[row + 1][col].revalidate();
                                        listPanel[row + 1][col].repaint();
                                        BoxList[row + 1][col].setIsOccupied(true);
                                    } else if (strs[2].equals("2")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row + 2);
                                        opponentTurnStack.push(col);
                                        if (!isFirstPlayer) {
                                            listPanel[row + 2][col].add(iconDog);
                                        } else {
                                            listPanel[row + 2][col].add(iconCat);
                                        }
                                        listPanel[row + 2][col].revalidate();
                                        listPanel[row + 2][col].repaint();
                                        BoxList[row + 2][col].setIsOccupied(true);
                                    }
                                } else if (strs[1].equals("Down")) {
                                    if (strs[2].equals("1")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row - 1);
                                        opponentTurnStack.push(col);
                                        if (!isFirstPlayer) {
                                            listPanel[row - 1][col].add(iconDog);
                                        } else {
                                            listPanel[row - 1][col].add(iconCat);
                                        }
                                        listPanel[row - 1][col].revalidate();
                                        listPanel[row - 1][col].repaint();
                                        BoxList[row - 1][col].setIsOccupied(true);
                                    } else if (strs[2].equals("2")) {
                                        listPanel[row][col].removeAll();
                                        listPanel[row][col].revalidate();
                                        listPanel[row][col].repaint();
                                        BoxList[row][col].setIsOccupied(false);
                                        opponentTurnStack.push(row - 2);
                                        opponentTurnStack.push(col);
                                        if (!isFirstPlayer) {
                                            listPanel[row - 2][col].add(iconDog);
                                        } else {
                                            listPanel[row - 2][col].add(iconCat);
                                        }
                                        listPanel[row - 2][col].revalidate();
                                        listPanel[row - 2][col].repaint();
                                        BoxList[row - 2][col].setIsOccupied(true);
                                    }
                                }
                            } else if (strs[0].equals("3")) {
                                setGameRes(false);
                                txtNotifi.setText("You Lose :((");
                            } else if (strs[0].equals("4")) {
                                setGameRes(true);
                                txtNotifi.setText("You Win ^^");
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }).start();

    }

    private void setGameRes(boolean result) {
        isWin = result;
        //isMyTurn = false;
        isPlaying = false;
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
