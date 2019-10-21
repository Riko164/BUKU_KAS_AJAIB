/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Koneksi.konek;
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
public class Loginform implements Initializable {

    
    
    
    @FXML
    private TextField usertxt;
    
    @FXML
    private PasswordField passtxt;
    
//    passtxt.textProperty().addListener()
    
    @FXML
    private Button btnsignin;
       
    
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    
    public void Login(ActionEvent ae){
        conn = konek.conDB();
        String name= usertxt.getText();
        String pass= passtxt.getText();
        
        if(conn==null){
            System.out.println("Tidak Konek ke Database");
        }else{
            try{
               st = conn.createStatement();
               rs = st.executeQuery("Select * from user where username='"+ name +"'");
               
               if(rs.next()){
                   if(pass.equals(rs.getString("Pin"))){
                       
                       
                       
                       JOptionPane.showMessageDialog(null, "Berhasil Login");
                       Node node = (Node) ae.getSource();
                       Stage stage = (Stage) node.getScene().getWindow();
                       stage.close();
                      

                        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Home.fxml"));
                        Parent root= (Parent) loader.load();
//                  
                       Home hm=loader.getController();
                        hm.setEmailtxt(rs.getString("email"));
                      hm.setNamatxt(rs.getString("Nama_Lengkap"));
                       hm.setSaldotxt(rs.getString("saldo"));
                       hm.setUsernametxt(rs.getString("username"));
                       
                       stage.setScene(new Scene(root));
                       stage.setTitle("Home");
                       stage.show();
//                       Parent next = loader.load();

                   }else{
                       JOptionPane.showMessageDialog(null, "Username atau Password salah");
                   }
               }else{
                   JOptionPane.showMessageDialog(null, "Username atau Password salah");
               }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
            
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
