/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ArySagala
 */
public class GantiWarnaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int warna2 = 1;

    public void setWarna2(int warna2) {
        this.warna2 = warna2;
    }
    public int getWarna2(){
        return warna2;
    }
    @FXML
    private ComboBox warna;

    @FXML
    private Button pilihbtn, backbtn;

    @FXML
    private void ganti() {
        System.out.println(warna.getValue().toString().equals("biru"));
        if (warna.getValue().toString().equalsIgnoreCase("biru")) {
            warna2 = 1;
            System.out.println("masuk biru");
        } else {
            warna2 = 2;
        }
        try{
            Connection conn=Koneksi.konek.conDB();
            Statement st=conn.createStatement();
            st.executeUpdate("update user set warna="+this.warna2+"");
            conn.close();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        back();
    }

    @FXML
    private void back() {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        stage.close();
    }

    public void message(String isi) {
        Alert pesan = new Alert(Alert.AlertType.INFORMATION);
        pesan.setTitle("INFORMATION");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
