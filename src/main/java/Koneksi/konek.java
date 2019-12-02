/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Shikimime
 */
public class konek {
    private static Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:rpl.db");
            return conn;
        } catch (Exception ex) {
            System.err.println("error : "+ex.getMessage());
           return conn;
        }
        
    }
}
