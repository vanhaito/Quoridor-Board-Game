/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Address;
import model.ObjectWrapper;
import model.User;

/**
 *
 * @author gsc10
 */
public class MainScreen extends javax.swing.JFrame {

    ObjectInputStream receiveStream;
    ObjectOutputStream sendStream;
    User user;
    Address address;

    public MainScreen(ObjectInputStream receiveStream, ObjectOutputStream sendStream, User user) {
        super("Quoridor Board Games");
        initComponents();
        this.receiveStream = receiveStream;
        this.sendStream = sendStream;
        this.user = user;
        jLabel1.setText("WELCOME " + user.getName().toUpperCase() + ",");
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(99, 104, 168));
        jPanel1.setForeground(new java.awt.Color(99, 104, 168));
        jPanel1.setToolTipText("");

        jButton1.setBackground(new java.awt.Color(99, 104, 168));
        jButton1.setFont(new java.awt.Font("Segoe Script", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(236, 215, 193));
        jButton1.setText("SHOW AVAILABLE GAME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(99, 104, 168));
        jButton2.setFont(new java.awt.Font("Segoe Script", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(236, 215, 193));
        jButton2.setText("SHOW AVAILABLE ROOM");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(99, 104, 168));
        jButton3.setFont(new java.awt.Font("Segoe Script", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(236, 215, 193));
        jButton3.setText("LOG OUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(236, 215, 193));
        jLabel1.setText("WELCOME HOANG,");

        jComboBox1.setBackground(new java.awt.Color(99, 104, 168));
        jComboBox1.setFont(new java.awt.Font("Segoe Script", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(236, 215, 193));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(236, 215, 193));
        jLabel2.setText("TO QUORIDOR GAME!");

        jComboBox2.setBackground(new java.awt.Color(99, 104, 168));
        jComboBox2.setFont(new java.awt.Font("Segoe Script", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(236, 215, 193));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(338, 338, 338)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(330, 330, 330)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // tao phong
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println("Check click");
        jComboBox2.removeAllItems();
        ArrayList<Integer> num = null;
        try {
            ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.GET_AVAILABLE_GAME, user, null);
            System.out.println(user.getName());
            sendStream.writeObject(ow);
            num = (ArrayList<Integer>) receiveStream.readObject();
            if (num.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Players Online");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Select "
                + "room: ");
        for (int i = 0; i < num.size(); i++) {
            model.addElement("ROOM: " + num.get(i));
        }
        System.out.println(num);
        jComboBox2.setModel(model);
    }//GEN-LAST:event_jButton1ActionPerformed

    // thoat
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AuthScreen authScreen = new AuthScreen(receiveStream, sendStream);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    // tham gia phong
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.out.println("Check click");
        jComboBox1.removeAllItems();
        ArrayList<Integer> num = null;
        try {
            ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.GET_AVAILABLE_ROOM, user, null);
            System.out.println(user.getName());
            sendStream.writeObject(ow);
            num = (ArrayList<Integer>) receiveStream.readObject();
            if (num.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Players Online");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Select "
                + "room: ");
        for (int i = 0; i < num.size(); i++) {
            model.addElement("ROOM: " + num.get(i));
        }
        System.out.println(num);
        jComboBox1.setModel(model);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    // xoa phong
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            try {
//                System.out.println(evt.getItem().toString());
//                User u = new User(evt.getItem().toString());
//                int i = 0;
//                for (i = 0; i < address.nameVector.size(); i++) {
//                    if (evt.getItem().toString().equals(address.nameVector.get(i))) {
//                        break;
//                    }
//                }
//                QuoridorClient quoridorClient = new QuoridorClient(address.ipVector.get(i), address.portVector.get(i), this.user, receiveStream, sendStream);
//                //System.out.println(address.ipVector.get(i) + "-" + address.portVector.get(i));
//                setVisible(false);
//                try {
//                    ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.REMOVE_SERVER, u);
//                    sendStream.writeObject(ow);
//                } catch (IOException ex) {
//                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String str = evt.getItem().toString();
            System.out.println(str);
            String[] cutStr = str.split(" ");
            int numRoom = Integer.parseInt(cutStr[1]);
            System.out.println(numRoom);
            try {
                ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.JOIN_ROOM, user, numRoom);
                sendStream.writeObject(ow);
                boolean isFirstPlayer = (boolean) receiveStream.readObject();
                setVisible(false);
                if (isFirstPlayer) {
                    new FirstPlayer(user, receiveStream, sendStream, numRoom);
                }
                else {
                    new SecondPlayer(user, receiveStream, sendStream, numRoom);
                    sendStream.writeObject(new ObjectWrapper(ObjectWrapper.START_GAME, user, numRoom));
                }
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String str = evt.getItem().toString();
            System.out.println(str);
            String[] cutStr = str.split(" ");
            int numRoom = Integer.parseInt(cutStr[1]);
            System.out.println(numRoom);
            try {
                ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.JOIN_ROOM, user, numRoom);
                sendStream.writeObject(ow);
                setVisible(false);
                new Viewer(user, receiveStream, sendStream, numRoom);
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
