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
public class SuksesRegistrasiController implements Initializable {

     
    @FXML
    private Button btnsign;
    
    @FXML
    public void signin(ActionEvent event){
        try{
            Node node = (Node) event.getSource();
                       Stage stage = (Stage) node.getScene().getWindow();
                       stage.close();

                       Parent next=FXMLLoader.load(getClass().getResource("/fxml/Signin.fxml"));
                       Scene scene = new Scene(next);
                       stage.setScene(scene);
                       stage.setTitle("Login");
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
