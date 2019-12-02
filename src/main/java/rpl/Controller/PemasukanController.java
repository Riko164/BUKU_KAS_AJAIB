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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Koneksi.konek;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Wildan Kristian
 */
public class PemasukanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label usernametxt;

    @FXML
    private Label logout, Pengaturan, Pemasukan, Pengeluaran, Laporan, Ekspor, Tentang, jumlahrp;

    @FXML
    private ImageView HomeImg;

    @FXML
    private Button simpanbtn, kembalibtn;

    @FXML
    private ComboBox Kategoribox;

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
    String temp;

    @FXML
    private TextField Jumlahtxt, Judultxt;

    @FXML
    private DatePicker tanggaldate;

    private int penambah;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUsernametxt(String usernametxt) {
        this.usernametxt.setText(usernametxt);
    }

    public void Mousein() {
        logout.setTextFill(Color.web("#FFF"));
        logout.setCursor(Cursor.HAND);
    }

    public void Mouseout() {
        logout.setTextFill(Color.web("#000000"));
    }

    public void MouseinPemasukan() {

        Pemasukan.setCursor(Cursor.HAND);
    }

    public void MouseoutPemasukan() {

    }

    public void MouseinPengeluaran() {
        Pengeluaran.setTextFill(Color.web("#FFF"));
        Pengeluaran.setCursor(Cursor.HAND);
    }

    public void MouseoutPengeluaran() {
        Pengeluaran.setTextFill(Color.web("#000000"));
    }

    public void MouseinLaporan() {
        Laporan.setTextFill(Color.web("#FFF"));
        Laporan.setCursor(Cursor.HAND);
    }

    public void MouseoutLaporan() {
        Laporan.setTextFill(Color.web("#000000"));
    }

    public void MouseinEkspor() {
        Ekspor.setTextFill(Color.web("#FFF"));
        Ekspor.setCursor(Cursor.HAND);
    }

    public void MouseoutEkspor() {
        Ekspor.setTextFill(Color.web("#000000"));
    }

    public void MouseinPengaturan() {
        Pengaturan.setTextFill(Color.web("#FFF"));
        Pengaturan.setCursor(Cursor.HAND);
    }

    public void MouseoutPengaturan() {
        Pengaturan.setTextFill(Color.web("#000000"));
    }

    public void MouseinTentang() {
        Tentang.setTextFill(Color.web("#FFF"));
        Tentang.setCursor(Cursor.HAND);
    }

    public void MouseoutTentang() {
        Tentang.setTextFill(Color.web("#000000"));
    }

    public void MouseinHome() {
        HomeImg.setCursor(Cursor.HAND);
    }

    public void Logout() {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        try {
            Parent login = FXMLLoader.load(getClass().getResource("/fxml/SplashAct.fxml"));
            Scene scene = new Scene(login);
//        Stage stage=new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Mouseintambah() {
        simpanbtn.setCursor(Cursor.HAND);
    }

    public void MouseinKembali() {
        kembalibtn.setCursor(Cursor.HAND);
    }

    public void kembali(ActionEvent ae) {
        kembalikehome();
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

    public void SimpanPemasukan(ActionEvent ae) {
        try {
            conn = konek.conDB();
            String sqlkategori = "select id from Kategori where NamaKategori='" + Kategoribox.getValue() + "'";
            st = conn.createStatement();
            rs = st.executeQuery(sqlkategori);
            String sql = "insert into transakasi(id_kategori,jumlah,tanggal,jenis,keterangan,username) values"
                    + "('" + rs.getString("id") + "','" + this.Jumlahtxt.getText() + "','" + this.tanggaldate.getValue().toString() + "','PEMASUKAN',\"" + this.Judultxt.getText() + "\",\"" + this.usernametxt.getText() + "\")";
            st.executeUpdate(sql);
            message("Berhasil Menyimpan di Database");
            st.executeUpdate("Update user set saldo=saldo+'" + Integer.valueOf(this.Jumlahtxt.getText()) + "' where username='" + this.usernametxt.getText() + "'");

            kembali(ae);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                rs.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void isiComboBox(String isi) {
        Kategoribox.getItems().add(isi);
    }

    public void setJumlahRP() {
        if(!this.Jumlahtxt.getText().equals("")){
        try {
            Integer.valueOf(this.Jumlahtxt.getText());
            String nilai = "";
            if (this.Jumlahtxt.getText().length() >= 4) {
                int size = this.Jumlahtxt.getText().length() % 3;

                if (size == 0) {
                    size = 3;
                }

                System.out.println(this.Jumlahtxt.getText().charAt(0));

                for (int i = 0; i < this.Jumlahtxt.getText().length(); i++) {
                    if (i == size && i != 0) {
                        System.out.println(i);
                        nilai += '.';
                        nilai += this.Jumlahtxt.getText().toString().charAt(i);
                        size += 3;
                    } else {
                        nilai += this.Jumlahtxt.getText().toString().charAt(i);
                    }

                }
            } else {
                nilai = this.Jumlahtxt.getText();
            }
            System.out.println(nilai);
            this.temp = this.Jumlahtxt.getText();
            this.jumlahrp.setText("Rp. " + nilai + ",00");
        } catch (Exception e) {
            this.Jumlahtxt.setText(temp);
        }
        }else{
            this.jumlahrp.setText("Rp. 0,00");
        }

    }

   

  

    public void message(String isi) {
        Alert pesan = new Alert(Alert.AlertType.INFORMATION);
        pesan.setTitle("INFROMATION");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }
}
