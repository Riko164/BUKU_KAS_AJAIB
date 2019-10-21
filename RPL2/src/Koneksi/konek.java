/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shikimime
 */
public class konek {
    private static Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rpl?useTimezone=true&serverTimezone=UTC", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("error : "+ex.getMessage());
           return conn;
        }
        
    }
}
