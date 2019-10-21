/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author Shikimime
 */
public class Home implements Initializable {

    @FXML
    private Label usernametxt;
    
    @FXML
    private Label emailtxt;
    
    @FXML
    private Label namatxt;
    
    @FXML
    private Label saldotxt;
    
    @FXML
    private Label logout;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setUsernametxt(String usernametxt) {
        this.usernametxt.setText(usernametxt);
    }

    public void setEmailtxt(String emailtxt) {
        this.emailtxt.setText(emailtxt);
    }

    public void setNamatxt(String namatxt) {
        this.namatxt.setText(namatxt);
    }

    public void setSaldotxt(String saldotxt) {
        this.saldotxt.setText(saldotxt);
    }
  
   
    public void Mousein(){
        logout.setTextFill(Color.web("#FFF"));
    }
    
    public void Mouseout(){
        logout.setTextFill(Color.web("#000"));
        
    }
    
    public void Logout(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/Fxml/splashact.fxml"));
        Scene scene = new Scene(login);
//        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
