/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Koneksi.konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Shikimime
 */
public class Register implements Initializable{

    @FXML
    private TextField namatxt;
    
    @FXML
    private TextField emailtxt;
    
    @FXML
    private TextField usernametxt;
    
    @FXML
    private PasswordField passtxt;
    
    @FXML
    private Button btnregis;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    @FXML
    public void Regis(ActionEvent event){
        String nama=namatxt.getText();
        String user=usernametxt.getText();
        String email=emailtxt.getText();
        String pass = passtxt.getText();
        conn=konek.conDB();
        
        if(conn==null){
            System.out.println("Tidak Konek ke Database");
        }else{
            try{
                st = conn.createStatement();
                String sql="insert into user(Email,Nama_Lengkap,Username,Pin,Saldo) values('"+email+"','"+nama+"','"+user+"','"+pass+"','"+0+"')";
                System.out.println(sql);
                st.execute(sql);
                System.out.println("berhasil regis");
                        Node node = (Node) event.getSource();
                       Stage stage = (Stage) node.getScene().getWindow();
                       stage.close();
                       
                       Parent next=FXMLLoader.load(getClass().getResource("/Fxml/suksesRegister.fxml"));
                       Scene scene = new Scene(next);
                       stage.setScene(scene);
                       stage.setTitle("NOTIFICATION");
                       stage.show();
                    
                
            }catch(Exception e){
                
                e.printStackTrace();
            }
        }
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
           
    }
    
}
