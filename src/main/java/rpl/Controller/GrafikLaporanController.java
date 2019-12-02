/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import Koneksi.konek;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class GrafikLaporanController implements Initializable {

    @FXML
    private Label usernametxt;

    @FXML
    private Label emailtxt;

    @FXML
    private Label namatxt;

    @FXML
    private Label saldotxt;

    @FXML
    private Label logout, Pengaturan, Pemasukan, Pengeluaran, Laporan, Ekspor, Tentang, awallbl, akhirlbl, kategorilbl;

    @FXML
    private ImageView HomeImg;

    @FXML
    private Button locationbtn;

    @FXML
    private ComboBox pilihbox, kategoribox;

    @FXML
    private DatePicker tanggalawal, tanggalakhir;

    @FXML
    private LineChart grafik;

    Connection conn;
    ResultSet rs;
    Statement st;
    private int saldosim;

    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
        private int warna;
        
        public void setSaldo(int saldo){
            this.saldosim=saldo;
        }
    
    public void setWarna(int warna){
        this.warna=warna;
    }

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
        String nilai = "";
        this.saldosim = Integer.valueOf(saldotxt);

        if (saldotxt.length() >= 4) {
            int size = saldotxt.length() % 3;
            if (size == 0) {
                size = 3;
            }
//            System.out.println(saldotxt.charAt(0));
            for (int i = 0; i < saldotxt.length(); i++) {
                if (i == size && i != 0) {
                    System.out.println(i);
                    nilai += '.';
                    nilai += saldotxt.charAt(i);
                    size += 3;
                } else {
                    nilai += saldotxt.charAt(i);
                }

            }
        } else {
            nilai = saldotxt;
        }
        this.saldotxt.setText("Rp. " + nilai + ",00");
    }

    public void Mousein() {
        logout.setTextFill(Color.web("#FFF"));
        logout.setCursor(Cursor.HAND);
    }

    public void Mouseout() {
        logout.setTextFill(Color.web("#000000"));
    }

    public void MouseinPemasukan() {
        Pemasukan.setTextFill(Color.web("#FFF"));
        Pemasukan.setCursor(Cursor.HAND);
    }

    public void MouseoutPemasukan() {
        Pemasukan.setTextFill(Color.web("#000000"));
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

        int pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin mau keluar?", null, JOptionPane.YES_NO_OPTION);

        if (pilihan == 0) {
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

    public void gotoekspor() {
        try {
            Stage stage = (Stage) this.Laporan.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TampilkanLaporan.fxml"));
            Parent root = (Parent) loader.load();

            TampilkanLaporanController hm = loader.getController();

            hm.setUsernametxt(this.usernametxt.getText());

            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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

            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
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

            stage.setScene(new Scene(root));
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

    public void Pengeluaran() {
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
            stage.setScene(new Scene(root));
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

    public void LaporanKeuangan() {
        try {
            Stage stage = (Stage) this.Laporan.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TampilkanLaporan.fxml"));
            Parent root = (Parent) loader.load();

            TampilkanLaporanController hm = loader.getController();

            hm.setUsernametxt(this.usernametxt.getText());

            stage.setScene(new Scene(root));
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

            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printall() {
        series1.setName("Pengeluaran");
        series2.setName("Pemasukan");

        try {
            conn = konek.conDB();
            st = conn.createStatement();
            rs = st.executeQuery("select * from transakasi where username='" + this.usernametxt.getText() + "' group by tanggal");
            while (rs.next()) {
                System.out.println("sini");
                if (rs.getString("jenis").equals("PEMASUKAN")) {
                    series2.getData().add(new XYChart.Data(rs.getString("tanggal"), rs.getInt("Jumlah")));
                } else {
                    series1.getData().add(new XYChart.Data(rs.getString("tanggal"), rs.getInt("jumlah")));
                }
            }

            grafik.getData().addAll(series2, series1);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void cektanggal() {
        System.out.println(!this.tanggalawal.getValue().equals(""));
        System.out.println(!this.tanggalakhir.getValue().equals(""));
        if (!this.tanggalawal.getValue().equals("") && !this.tanggalakhir.getValue().equals("")) {
            this.grafik.getData().removeAll();
            XYChart.Series series3 = new XYChart.Series();
            XYChart.Series series4 = new XYChart.Series();
            series3.setName("Pemasukan");
            series4.setName("Pengeluaran");
            try {
                conn = konek.conDB();
                st = conn.createStatement();
                rs = st.executeQuery("select * from transakasi where username='" + this.usernametxt.getText() + "' and transakasi.tanggal>='" + this.tanggalawal.getValue().toString() + "' and transakasi.tanggal<='" + this.tanggalakhir.getValue().toString() + "'");
                while (rs.next()) {
                    System.out.println("sini");
                    if (rs.getString("jenis").equals("PEMASUKAN")) {
                        series3.getData().add(new XYChart.Data(rs.getString("tanggal"), rs.getInt("Jumlah")));
                    } else {
                        series4.getData().add(new XYChart.Data(rs.getString("tanggal"), rs.getInt("jumlah")));
                    }
                }

                grafik.getData().setAll(series3, series4);
            } catch (Exception e) {
                e.printStackTrace();
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
    }
}
