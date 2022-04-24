/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverquoridor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
/**
 *
 * @author gsc10
 */
public class Dao {
    private Connection conn;

    public Dao() {
        String dbUrl = "jdbc:mysql://localhost:3306/sys";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "Cong2000");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User checkLogin(String username, String password) {
        User user = null;
        try {
            String sql1 = "SELECT * FROM sys.user WHERE username = ? AND password = ?";
            PreparedStatement pstm = conn.prepareStatement(sql1);
            pstm.setString(1, username);
            pstm.setString(2, password);      
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void addUser(String name, String username, String password, String ipAddress) {
        String sql = "INSERT INTO `sys`.`user` (`name`, `username`, `password`, `ipaddress`) VALUES (?, ?, ?, ?);";
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.setString(4, ipAddress);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
