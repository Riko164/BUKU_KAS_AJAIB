/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Koneksi.konek;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author ArySagala
 */
public class SigninController implements Initializable {
    
    @FXML
    private TextField usertxt, pass2txt;
    
    @FXML
    private PasswordField passtxt;

//    passtxt.textProperty().addListener()
    @FXML
    private Button btnsignin;
    
    @FXML
    private ImageView backbtn, showpass, hidepass;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    public void Login(ActionEvent ae) {
        conn = konek.conDB();
        String name = usertxt.getText();
        String pass = passtxt.getText();
        
        if (conn == null) {
            System.out.println("Tidak Konek ke Database");
        } else {
            try {
                String sql="Select * from user where username=\""+name+"\"";
                System.out.println(sql);
                st = conn.createStatement();
                   
                System.out.println("Select * from user where username="+name);
                rs=st.executeQuery(sql);
                
                System.out.println(rs.getString("username"));
                if (rs.next()) {
                    if (pass.equals(rs.getString("Pin"))) {
                        
                        Alert pesan = new Alert(AlertType.INFORMATION);
                        pesan.setContentText("berhasil login");
                        pesan.showAndWait();
                        Node node = (Node) ae.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                        Parent root = (Parent) loader.load();
//                  
                        HomeController hm = loader.getController();
                        hm.setEmailtxt(rs.getString("email"));
                        hm.setNamatxt(rs.getString("Nama_Lengkap"));
                        hm.setSaldotxt(rs.getString("saldo"));
                        hm.setUsernametxt(rs.getString("username"));
                        hm.setWarna(rs.getInt("warna"));
                        Scene scene=new Scene(root);
                        
                        if(rs.getInt("warna")==1){
                            scene.getStylesheets().add("/styles/biru.css");
                        }else{
                            scene.getStylesheets().add("/styles/hijau.css");
                        }
                        
                        stage.setScene(scene);
                        stage.setTitle("Home");
                        stage.setResizable(false);
                        
                        stage.show();
//                           Parent next = loader.load();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Username atau Password salah");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    st.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
    
    public void Mousein() {
        this.backbtn.setCursor(Cursor.HAND);
        
    }
    
    public void Mouseinbtn() {
        this.btnsignin.setCursor(Cursor.HAND);
    }
    
    public void Mouseinhide() {
        this.hidepass.setCursor(Cursor.HAND);
    }
    
    public void Mouseinshow() {
        this.showpass.setCursor(Cursor.HAND);
    }
    
    public void back() throws IOException {
        Stage stage = (Stage) this.backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SplashAct.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Splash ACT");
        stage.setScene(scene);
        stage.show();
    }
    
    public void hidepin() {
        this.passtxt.setVisible(true);
        this.pass2txt.setVisible(false);
        this.hidepass.setVisible(false);
        this.showpass.setVisible(true);
        this.passtxt.setText(this.pass2txt.getText());
        
    }
    
    public void showpin() {
        this.passtxt.setVisible(false);
        this.pass2txt.setVisible(true);
        this.hidepass.setVisible(true);
        this.showpass.setVisible(false);
        this.pass2txt.setText(this.passtxt.getText());
        
    }
    
    public void maxvalue() {
        if (this.passtxt.getText().length() > 6) {
            this.passtxt.setText(this.passtxt.getText().substring(0, 6));
            JOptionPane.showMessageDialog(null, "Password lebih dari 6");
        } else if (this.pass2txt.getText().length() > 6) {
            this.pass2txt.setText(this.pass2txt.getText().substring(0, 6));
            JOptionPane.showMessageDialog(null, "Password lebih dari 6");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
