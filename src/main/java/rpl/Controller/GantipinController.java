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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Koneksi.konek;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Wildan Kristian
 */
public class GantipinController implements Initializable {

        Connection conn;
    ResultSet rs;
    Statement st;
     private int warna;
        private int saldosim;
        
        public void setSaldo(int saldo){
            this.saldosim=saldo;
        }
    
    public void setWarna(int warna){
        this.warna=warna;
    }
         @FXML
    private Label usernametxt;

    
    @FXML
    private Label logout,Pengaturan,Pemasukan,Pengeluaran,Laporan,Ekspor,Tentang,gantipinlbl,tambahlbl,gantiwarnalbl,edituserlbl;
    
    @FXML
    private ImageView HomeImg;
    
    @FXML
    private Button simpanbtn,kembalibtn;
    
    @FXML
    private PasswordField passlamatxt,passbarutxt,conpassbarutxt;
    
    @FXML
    private ImageView showlama,showbaru,showconbaru,hidepasslama,hidepassbaru,hidepassconbaru;
    
    @FXML
    private TextField passlamatxt2,passbarutxt2,conpassbarutxt2;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setUsernametxt(String usernametxt) {
        this.usernametxt.setText(usernametxt);
    }

    
  
   
    public void Mousein(){
        logout.setTextFill(Color.web("#FFF"));
        logout.setCursor(Cursor.HAND);
    }
    
    public void Mouseout(){
        logout.setTextFill(Color.web("#000000"));
    }
    
    public void MouseinPemasukan(){
        Pemasukan.setTextFill(Color.web("#FFF"));
        Pemasukan.setCursor(Cursor.HAND);
    }
    
    public void MouseoutPemasukan(){
        Pemasukan.setTextFill(Color.web("#000000"));
    }
    public void MouseinPengeluaran(){
        Pengeluaran.setTextFill(Color.web("#FFF"));
        Pengeluaran.setCursor(Cursor.HAND);
    }
    
    public void MouseoutPengeluaran(){
        Pengeluaran.setTextFill(Color.web("#000000"));
    }
    public void MouseinLaporan(){
        Laporan.setTextFill(Color.web("#FFF"));
        Laporan.setCursor(Cursor.HAND);
    }
    
    public void MouseoutLaporan(){
        Laporan.setTextFill(Color.web("#000000"));
    }
    
    public void MouseinEkspor(){
        Ekspor.setTextFill(Color.web("#FFF"));
        Ekspor.setCursor(Cursor.HAND);
    }
    
    public void MouseoutEkspor(){
        Ekspor.setTextFill(Color.web("#000000"));
    }
    public void MouseinPengaturan(){
        Pengaturan.setTextFill(Color.web("#FFF"));
        Pengaturan.setCursor(Cursor.HAND);
    }
    
    public void MouseoutPengaturan(){
        Pengaturan.setTextFill(Color.web("#000000"));
    }
    public void MouseinTentang(){
        Tentang.setTextFill(Color.web("#FFF"));
        Tentang.setCursor(Cursor.HAND);
    }
    
    public void MouseoutTentang(){
        Tentang.setTextFill(Color.web("#000000"));
    }
    
    public void MouseinHome(){
        HomeImg.setCursor(Cursor.HAND);
    }
    
    @FXML
    private void Logout() {

        Alert keluar = new Alert(Alert.AlertType.CONFIRMATION);
        keluar.setTitle("LOGOUT");
        keluar.setContentText("Apakah anda ingin logout?");

        keluar.showAndWait();

        if (keluar.getResult() == ButtonType.OK) {
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
            try {
                Parent login = FXMLLoader.load(getClass().getResource("/fxml/SplashAct.fxml"));
                Scene scene = new Scene(login);
//        Stage stage=new Stage();
                stage.setScene(scene);
                stage.setTitle("Splash Act");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void gotoekspor() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrafikLaporan.fxml"));
            Parent root = (Parent) loader.load();
//                  
            GrafikLaporanController hm = loader.getController();
            hm.setUsernametxt(this.usernametxt.getText());
            hm.printall();
            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("salah disini gan");
        }
    }

    public void PengaturanMenu() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pengaturan.fxml"));
            Parent root = (Parent) loader.load();
//                  
            PengaturanController hm = loader.getController();
            hm.setUsernametxt(this.usernametxt.getText());
            hm.setWarna(warna);
            hm.setSaldo(Integer.valueOf(saldosim));
            Scene scene = new Scene(root);
            System.out.println("pengaturan warna" + warna);
            if (warna == 1) {
                System.out.println("sini biru");
                scene.getStylesheets().add("/styles/biru.css");
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.setResizable(true);
                stage.show();
            } else {
                System.out.println("sini hijau");
                scene.getStylesheets().add("/styles/hijau.css");
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.setResizable(true);
                stage.show();
            }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("salah disini gan");
        }
    }

    public void Pemasukan() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pemasukan.fxml"));
            Parent root = (Parent) loader.load();
//                  
            PemasukanController hm = loader.getController();
            hm.setUsernametxt(this.usernametxt.getText());

            conn = konek.conDB();
            st = conn.createStatement();
            rs = st.executeQuery("select NamaKategori from kategori where Jenis='PEMASUKAN' and id_user=(select iduser from user where username='" + this.usernametxt.getText() + "')");

            while (rs.next()) {
                hm.isiComboBox(rs.getString("NamaKategori"));
            }

            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("salah disini gan");
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void kembalikehome() {
        try {
            conn = konek.conDB();
            String sql = "Select * from user where username='" + this.usernametxt.getText() + "'";
            st = conn.createStatement();
            System.out.println("sampe sini");

            Stage stage = (Stage) HomeImg.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = (Parent) loader.load();

            rs = st.executeQuery(sql);
            HomeController hm = loader.getController();
            hm.setEmailtxt(rs.getString("email"));
            hm.setNamatxt(rs.getString("Nama_Lengkap"));
            hm.setSaldotxt(rs.getString("saldo"));
            hm.setUsernametxt(rs.getString("username"));

            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();

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

    @FXML
    private void Pengeluaran() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pengeluaran.fxml"));
            Parent root = (Parent) loader.load();
//                  
            PengeluaranController hm = loader.getController();
            hm.setUsernametxt(this.usernametxt.getText());
            hm.setSaldo(this.saldosim);
            conn = konek.conDB();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT NamaKategori FROM kategori WHERE Jenis = 'PENGELUARAN' and id_user=(select iduser from user where username='" + this.usernametxt.getText() + "')");
            while (rs.next()) {
                hm.isiComboBox(rs.getString("NamaKategori"));
            }
            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("salah disini gan");
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void LaporanKeuangan() {
        try {
            Stage stage = (Stage) this.Laporan.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TampilkanLaporan.fxml"));
            Parent root = (Parent) loader.load();

            TampilkanLaporanController hm = loader.getController();

            hm.setUsernametxt(this.usernametxt.getText());

            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoabout() {
        try {
            Stage stage = (Stage) this.Tentang.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/About.fxml"));
            Parent root = (Parent) loader.load();

            AboutController hm = loader.getController();

            hm.setUsernametxt(this.usernametxt.getText());

            hm.setWarna(warna);
            Scene scene = new Scene(root);
            if (warna == 1) {
                scene.getStylesheets().add("/styles/biru.css");
            } else {
                scene.getStylesheets().add("/styles/hijau.css");
            }
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void kembali(ActionEvent ae){
        Stage stage = (Stage) kembalibtn.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pengaturan.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PengaturanController hm=loader.getController();
        hm.setWarna(warna);
//        hm.setEmailtxt(emailtxt.getText());
//        hm.setNamatxt(namatxt.getText());
//        hm.setSaldotxt(saldotxt.getText());
        hm.setUsernametxt(usernametxt.getText());
        Scene scene=new Scene(root);
        if(warna==1){
            scene.getStylesheets().add("/styles/biru.css");
        }else{
            scene.getStylesheets().add("/styles/hijau.css");
        }
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
    public void Mouseinsimpan(){
        simpanbtn.setCursor(Cursor.HAND);
    }
    
    public void Mouseinkembali(){
        kembalibtn.setCursor(Cursor.HAND);
    }
    
    public void simpan(ActionEvent ae){
        String passlama;
        if(passlamatxt.isVisible()){
            passlama=passlamatxt.getText();
        }else{
            passlama=passlamatxt2.getText();
        }
        String passbaru;
        if(passbarutxt.isVisible()){
            passbaru=passbarutxt.getText();
        }else{
            passbaru=passbarutxt2.getText();
        }
        
        String conpassbaru;
        if(conpassbarutxt.isVisible()){
            conpassbaru=conpassbarutxt.getText();
        }else{
            conpassbaru=conpassbarutxt2.getText();
        }
        
        try{
        conn=konek.conDB();
        st=conn.createStatement();
        rs=st.executeQuery("select pin from user where username='"+this.usernametxt.getText()+"'");
        if(rs.next()){
            System.out.println(rs.getString("pin"));
            System.out.println(passlama);
            if(!passlama.equals(rs.getString("pin"))){
                message("PIN lama salah");
            }else{
                if(passbaru.equals(conpassbaru)){
                    st.executeUpdate("update user set pin='"+passbaru+"' where username='"+this.usernametxt.getText()+"'");
                    message("Berhasil ganti PIN");
                    kembalikehome();
                }else{
                    message("PIN tidak sama");
                }
            }
        }
        
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            conn.close();
            st.close();
            rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        Stage stage= (Stage) simpanbtn.getScene().getWindow();
    }
    
    public void Mouseineye(){
        showlama.setCursor(Cursor.HAND);
        showbaru.setCursor(Cursor.HAND);
        showconbaru.setCursor(Cursor.HAND);
        hidepasslama.setCursor(Cursor.HAND);
        hidepassbaru.setCursor(Cursor.HAND);
        hidepassconbaru.setCursor(Cursor.HAND);
        
    }
    
    public void seepasslama(){
        passlamatxt.setVisible(false);
        passlamatxt2.setVisible(true);
        passlamatxt2.setText(passlamatxt.getText());
        showlama.setVisible(false);
    }
    
    
    
    public void seepassbaru(){
        passbarutxt.setVisible(false);
        passbarutxt2.setVisible(true);
        passbarutxt2.setText(passbarutxt.getText());
        showbaru.setVisible(false);
    }
    
    public void seeconpassbaru(){
        conpassbarutxt.setVisible(false);
        conpassbarutxt2.setVisible(true);
        conpassbarutxt2.setText(conpassbarutxt.getText());
        showconbaru.setVisible(false);
    }
    
    public void hidepasslama(){
        passlamatxt2.setVisible(false);
        passlamatxt.setVisible(true);
        passlamatxt.setText(passlamatxt2.getText());
        showlama.setVisible(true);
    }
    
    public void hidepassbaru(){
        passbarutxt2.setVisible(false);
        passbarutxt.setVisible(true);
        passbarutxt.setText(passbarutxt2.getText());
        showbaru.setVisible(true);
    }
    
    public void hidepassconbaru(){
        conpassbarutxt2.setVisible(false);
        conpassbarutxt.setVisible(true);
        conpassbarutxt.setText(conpassbarutxt2.getText());
        showconbaru.setVisible(true);
    }
    
    public void maxinput(){
        if(passlamatxt.getText().toString().length()>6){
            passlamatxt.setText(passlamatxt.getText().substring(0,6));
        }else if(passlamatxt2.getText().toString().length()>6){
            passlamatxt2.setText(passlamatxt2.getText().substring(0,6));
        }else if(passbarutxt.getText().toString().length()>6){
            passbarutxt.setText(passbarutxt.getText().substring(0,6));
        }else if(passbarutxt2.getText().toString().length()>6){
            passbarutxt2.setText(passbarutxt2.getText().substring(0,6));
        }else if(conpassbarutxt.getText().toString().length()>6){
            conpassbarutxt.setText(conpassbarutxt.getText().substring(0,6));
        }else if(conpassbarutxt2.getText().toString().length()>6){
            conpassbarutxt2.setText(conpassbarutxt2.getText().substring(0,6));
        }else{
            
        }
    }
    public void message(String isi) {
        Alert pesan = new Alert(AlertType.INFORMATION);
        pesan.setTitle("INFROMATION");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }
    
    
}
