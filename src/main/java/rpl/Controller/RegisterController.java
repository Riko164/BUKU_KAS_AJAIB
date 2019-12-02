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
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author ArySagala
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField namatxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField usernametxt, pass2txt;

    @FXML
    private PasswordField passtxt;

    @FXML
    private Button btnregis;
    @FXML
    private ImageView backbtn, showpass, hidepass;

    Connection conn;
    Statement st;
    ResultSet rs;
    private String temp;

    @FXML
    public void Regis(ActionEvent event) throws SQLException {
        String nama = namatxt.getText();
        String user = usernametxt.getText();
        String email = emailtxt.getText();
        String pass = passtxt.getText();
        conn = konek.conDB();
        if (email.contains("@")) {
            if (conn == null) {
                System.out.println("Tidak Konek ke Database");
            } else {
                try {
                    st = conn.createStatement();
                    String sql = "insert into user(Email,Nama_Lengkap,Username,Pin,Saldo,warna) values(\"" + email + "\",\"" + nama + "\",\"" + user + "\",\"" + pass + "\",\"" + 0 + "\",1)";
                    System.out.println(sql);
                    st.executeUpdate(sql);
                    System.out.println("berhasil regis");
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Parent next = FXMLLoader.load(getClass().getResource("/fxml/SuksesRegistrasi.fxml"));
                    Scene scene = new Scene(next);
                    stage.setScene(scene);
                    stage.setTitle("NOTIFICATION");

                    stage.show();

                } catch (Exception e) {

                    e.printStackTrace();
                } finally {
                    try {

                        st.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Alert me = new Alert(AlertType.INFORMATION);
            me.setContentText("email harus mengandung @");
            me.showAndWait();
            this.emailtxt.setText("");
        }
//        conn.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void maxpass() {
        Alert pesan = new Alert(AlertType.INFORMATION);
        try {

            if (this.passtxt.isVisible()) {
                Integer.valueOf(passtxt.getText());
                temp = passtxt.getText();
            } else {
                Integer.valueOf(pass2txt.getText());
                temp = pass2txt.getText();
            }
            if (this.passtxt.getText().length() > 6) {

                this.passtxt.setText(this.passtxt.getText().substring(0, 6));

                pesan.setContentText("Password lebih dari 6");
                pesan.showAndWait();
            } else if (this.pass2txt.getText().length() > 6) {

                this.pass2txt.setText(this.pass2txt.getText().substring(0, 6));

                pesan.setContentText("Password lebih dari 6");
                pesan.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("temp");
            passtxt.setText(temp);
            pass2txt.setText(temp);
        }
    }

    public void Mousein() {
        this.btnregis.setCursor(Cursor.HAND);
        this.backbtn.setCursor(Cursor.HAND);
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
}
