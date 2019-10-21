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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Shikimime
 */
public class SplashAct implements Initializable {

    
    @FXML
    public void Login21(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/Fxml/sign_in.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void Regis21(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/Fxml/register.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
