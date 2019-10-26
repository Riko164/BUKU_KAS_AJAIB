/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ArySagala
 */
public class SplashActController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnlogin,btnregis;
    
    public void Mousein(){
        btnlogin.setCursor(Cursor.HAND);
    }
    
    public void Mousein2(){
        btnregis.setCursor(Cursor.HAND);
    }
    public void register(ActionEvent event){
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void signin(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/fxml/Signin.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
