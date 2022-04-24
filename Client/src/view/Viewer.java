package view;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import model.User;
import view.Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nc
 */
public class Viewer extends javax.swing.JFrame {
    User user;
    ObjectInputStream receiveStream;
    ObjectOutputStream sendStream;

    public Viewer(User user, ObjectInputStream receiveStream, ObjectOutputStream sendStream, int numRoom) throws IOException {
        super("Quoridor Board Game");
        initComponents();
        this.user = user;
        this.receiveStream = receiveStream;
        this.sendStream = sendStream;
        System.out.println("strReq: " + "Hello Server");
        JFrame f = new JFrame("Chess");
        ClientSpectator chessboard = new ClientSpectator(receiveStream, sendStream, user, numRoom);
        f.setSize(1200, 1200);
        f.getContentPane().add(chessboard);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
