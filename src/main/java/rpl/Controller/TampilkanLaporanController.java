/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import Koneksi.konek;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ArySagala
 */
public class TampilkanLaporanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Tabel> tabelisi;

    @FXML
    private ComboBox pilihtransbox, kategoribox;

    @FXML
    private Label usernametxt, kategorilbl, waktulbl;

    @FXML
    private Label emailtxt;

    @FXML
    private Label namatxt;

    @FXML
    private Label saldotxt;

    @FXML
    private Label logout, Pengaturan, Pemasukan, Pengeluaran, Laporan, Ekspor, Tentang;

    @FXML
    private ImageView HomeImg;

    @FXML
    private DatePicker dateawal, dateakhir;

    @FXML
    private TableColumn<Tabel, String> no, jenis, kategori, judul, transaksi, jumlah;

    @FXML
    private TextField searchtxt;

    @FXML
    private Button eksportbtn;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kategorilbl.setVisible(false);
        this.eksportbtn.setDisable(true);
        waktulbl.setVisible(false);
        dateawal.setVisible(false);
        dateakhir.setVisible(false);
        kategoribox.setVisible(false);
        this.searchtxt.setDisable(true);
        final ContextMenu cxMenu = new ContextMenu();
        MenuItem cxMenuItemEdit = new MenuItem("Ubah Data");
        cxMenu.getItems().add(cxMenuItemEdit);
        MenuItem cxMenuItemDelete = new MenuItem("Hapus Data");
        cxMenu.getItems().add(cxMenuItemDelete);

        tabelisi.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cxMenu.show(tabelisi, t.getScreenX(), t.getScreenY());
                }
                if (t.getButton() == MouseButton.PRIMARY) {
                    if (cxMenu.isShowing()) {
                        cxMenu.hide();
                    }
                }
            }
        });

        cxMenuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Tabel mhs = tabelisi.getSelectionModel().getSelectedItem();
                showform(mhs);
            }
        });

        no.setCellValueFactory(new PropertyValueFactory("no"));
        kategori.setCellValueFactory(new PropertyValueFactory("namakategori"));
        jenis.setCellValueFactory(new PropertyValueFactory("jenis"));
        judul.setCellValueFactory(new PropertyValueFactory("keterangan"));
        jumlah.setCellValueFactory(new PropertyValueFactory("jumlah"));
        transaksi.setCellValueFactory(new PropertyValueFactory("tanggal"));

        cxMenuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                final Tabel mhs = tabelisi.getSelectionModel().getSelectedItem();
                int saldo = getSaldo();

                if (mhs.getJenis().equals("PEMASUKAN")) {
                    saldo -= Integer.valueOf(mhs.getJumlah());
                } else {
                    saldo += Integer.valueOf(mhs.getJumlah());
                }
                if (saldo > 0) {
                    gantiSaldo(saldo);
                    int index = tabelisi.getSelectionModel().getSelectedIndex();
                    try {
                        conn = konek.conDB();
                        st = conn.createStatement();
                        st.executeUpdate("DELETE FROM Transakasi WHERE id='" + mhs.getId() + "'");
                        tabelisi.getItems().remove(index);
                        isitabel();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    message("aksi tidak valid");
                }
            }
        });
    }

    public void gantiSaldo(int saldo) {
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            st.executeUpdate("update user set saldo='" + saldo + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSaldo() {
        int saldo = 0;
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            System.out.println("disini");
            rs = st.executeQuery("select saldo from user where username=\"" + this.usernametxt.getText() + "\"");
            saldo = rs.getInt("saldo");
            rs.close();
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saldo;
    }

    public void OPENEKSPORT() {
        Alert eksport = new Alert(AlertType.INFORMATION);
        eksport.setTitle("EKSPORT FILE");
        eksport.setHeaderText("input data");
        eksport.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
        eksport.initModality(Modality.WINDOW_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ekport2.fxml"));
            Node login = loader.load();
            DialogPane pane = eksport.getDialogPane();
            Ekport2Controller edit = loader.getController();
            edit.setTbl(tabelisi);
            pane.setContent(login);
            eksport.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showform(Tabel tbl) {
        int i = 0;

        Alert al = new Alert(AlertType.INFORMATION);
        al.setTitle("edit column");
        al.setHeaderText("input data");
        al.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
        al.initModality(Modality.WINDOW_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edittransaksi.fxml"));
            Node login = loader.load();
            DialogPane pane = al.getDialogPane();
            EdittransaksiController edit = loader.getController();
            edit.Setter(tbl, tbl.getId(), this.usernametxt.getText());
            pane.setContent(login);
            al.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isitabel();
        }

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

   

   

    public void novisibleall() {
        kategorilbl.setVisible(false);
        waktulbl.setVisible(false);
        dateawal.setVisible(false);
        dateakhir.setVisible(false);
        kategoribox.setVisible(false);
    }

    public void isitabel() {
        this.eksportbtn.setDisable(false);
        if (!this.searchtxt.getText().isEmpty()) {
            searchtxt.setText("");
        }

        try {
            conn = konek.conDB();
            if (this.pilihtransbox.getValue().toString().equals("KATEGORI")) {
                this.searchtxt.setDisable(true);
                waktulbl.setVisible(false);
                dateawal.setVisible(false);
                dateakhir.setVisible(false);
                this.kategorilbl.setVisible(true);
                this.kategoribox.setVisible(true);
                st = conn.createStatement();
                rs = st.executeQuery("Select namakategori from kategori where id_user=(select iduser from user where username=\"" + this.usernametxt.getText() + "\")");
                System.out.println("Select namakategori from kategori where id_user=(select id from user where username=\"" + this.usernametxt.getText() + "\")");
                while (rs.next()) {
                    this.kategoribox.getItems().add(rs.getString("NamaKategori"));
                }

            } else if (this.pilihtransbox.getValue().toString().equals("WAKTU")) {
                this.searchtxt.setDisable(true);
                kategorilbl.setVisible(false);
                kategoribox.setVisible(false);
                this.waktulbl.setVisible(true);
                this.dateawal.setVisible(true);
                this.dateakhir.setVisible(true);
            } else if (this.pilihtransbox.getValue().toString().equals("MAX PEMASUKAN")) {
                novisibleall();
                this.searchtxt.setDisable(true);
                st = conn.createStatement();
//                System.out.println("select * from transakasi inner join kategori where username='" + this.usernametxt.getText() + "' and transakasi.jenis='PEMASUKAN' order by jumlah desc");
                rs = st.executeQuery("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\" and transakasi.jenis='PEMASUKAN' and transakasi.id=kategori.id order by jumlah desc");

                int i = 0;
                System.out.println("disini");

                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                this.tabelisi.setEditable(true);
                while (rs.next()) {

                    isi.add(new Tabel(
                            String.valueOf(i),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
//                       System.out.println(rs.getString("namakategori"));
                    i++;
//                    tabelisi.getItems().add(String.valueOf(i++));
//                    tabelisi.getItems().add(rs.getString("jenis"));
//                    tabelisi.getItems().add(rs.getString("namakategori"));
//                    tabelisi.getItems().add(rs.getString("keterangan"));
//                    tabelisi.getItems().add(rs.getString("tanggal"));
//                    tabelisi.getItems().add(rs.getString("jumlah"));
//                    

                }
                tabelisi.setItems(isi);

            } else if (this.pilihtransbox.getValue().toString().equals("MAX PENGELUARAN")) {
                this.searchtxt.setDisable(true);
                novisibleall();
                st = conn.createStatement();
                System.out.println("select * from transakasi inner join kategori where username=\"" + this.usernametxt.getText() + "\" and transakasi.jenis='PEMASUKAN' order by jumlah desc");
                rs = st.executeQuery("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\" and transakasi.jenis='PENGELUARAN' and transakasi.id=kategori.id order by jumlah desc");

                int i = 0;
                System.out.println("disini");
                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                this.tabelisi.setEditable(true);
                while (rs.next()) {

                    isi.add(new Tabel(
                            String.valueOf(i),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
//                       System.out.println(rs.getString("namakategori"));
                    i++;
//                    tabelisi.getItems().add(String.valueOf(i++));
//                    tabelisi.getItems().add(rs.getString("jenis"));
//                    tabelisi.getItems().add(rs.getString("namakategori"));
//                    tabelisi.getItems().add(rs.getString("keterangan"));
//                    tabelisi.getItems().add(rs.getString("tanggal"));
//                    tabelisi.getItems().add(rs.getString("jumlah"));
//                    

                }
                tabelisi.setItems(isi);
            } else if (this.pilihtransbox.getValue().toString().equals("ALL")) {
                this.searchtxt.setDisable(true);
                novisibleall();
                st = conn.createStatement();
                System.out.println("select * from transakasi inner join kategori where username=\"" + this.usernametxt.getText() + "\" and transakasi.jenis='PEMASUKAN' order by jumlah desc");
                rs = st.executeQuery("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\" and transakasi.id_kategori=kategori.id");

                int i = 0;
                System.out.println("disini");
                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                this.tabelisi.setEditable(true);
                while (rs.next()) {

                    isi.add(new Tabel(
                            String.valueOf(i),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
//                       System.out.println(rs.getString("namakategori"));
                    i++;
//                    tabelisi.getItems().add(String.valueOf(i++));
//                    tabelisi.getItems().add(rs.getString("jenis"));
//                    tabelisi.getItems().add(rs.getString("namakategori"));
//                    tabelisi.getItems().add(rs.getString("keterangan"));
//                    tabelisi.getItems().add(rs.getString("tanggal"));
//                    tabelisi.getItems().add(rs.getString("jumlah"));
//                    

                }
                tabelisi.setItems(isi);
            } else if (this.pilihtransbox.getValue().toString().equals("ALL PEMASUKAN")) {
                novisibleall();
                this.searchtxt.setDisable(false);
                st = conn.createStatement();
                rs = st.executeQuery("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\"' and transakasi.id_kategori=kategori.id and transakasi.jenis='PEMASUKAN'");
                int i = 0;
                System.out.println("disini");
                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                this.tabelisi.setEditable(true);
                while (rs.next()) {

                    isi.add(new Tabel(
                            String.valueOf(i),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
                    i++;
                }
                tabelisi.setItems(isi);
            } else if (this.pilihtransbox.getValue().toString().equals("ALL PENGELUARAN")) {
                novisibleall();
                this.searchtxt.setDisable(false);
                st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Transakasi INNER JOIN Kategori WHERE username=\"" + this.usernametxt.getText() + "\" AND Transakasi.id_kategori=Kategori.id AND Transakasi.Jenis='PENGELUARAN'");
                int i = 0;
                System.out.println("disini");
                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                this.tabelisi.setEditable(true);
                while (rs.next()) {

                    isi.add(new Tabel(
                            String.valueOf(i),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
                    i++;
                }
                tabelisi.setItems(isi);
            } else {
                this.searchtxt.setDisable(true);
                novisibleall();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                rs.close();
                st.close();
            } catch (Exception e) {

            }
        }
    }

    public void kategoriaction(ActionEvent ae) {
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            rs = st.executeQuery("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\" and transakasi.id_kategori=kategori.id and kategori.namakategori= \""+ this.kategoribox.getValue().toString() + "\"");
            System.out.println("select * from transakasi inner join kategori where transakasi.username=\"" + this.usernametxt.getText() + "\" and transakasi.id_kategori=kategori.id and kategori.namakategori=\""+ this.kategoribox.getValue().toString() + "\"");
            ObservableList<Tabel> isi = FXCollections.observableArrayList();
            this.tabelisi.setEditable(true);
            int i = 0;
            while (rs.next()) {

                isi.add(new Tabel(
                        String.valueOf(i++),
                        rs.getString("namakategori"),
                        rs.getString("keterangan"),
                        rs.getString("tanggal"),
                        rs.getString("jumlah"),
                        rs.getString("jenis"),
                        rs.getString("id")
                ));

            }
            this.tabelisi.setItems(isi);

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

    public void datequery() {
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            rs = st.executeQuery("select * from transakasi inner join kategori where username=\"" + this.usernametxt.getText() + "\" and transakasi.id_kategori=kategori.id and transakasi.tanggal>=\"" + this.dateawal.getValue().toString() + "\" and transakasi.tanggal<=\"" + this.dateakhir.getValue().toString() + "\"");
            ObservableList<Tabel> isi = FXCollections.observableArrayList();
            this.tabelisi.setEditable(true);
            int i = 0;
            while (rs.next()) {

                isi.add(new Tabel(
                        String.valueOf(i++),
                        rs.getString("namakategori"),
                        rs.getString("keterangan"),
                        rs.getString("tanggal"),
                        rs.getString("jumlah"),
                        rs.getString("jenis"),
                        rs.getString("id")
                ));

            }
            this.tabelisi.setItems(isi);

        } catch (Exception e) {

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

    public void awalpick() {
        if (!this.dateakhir.equals("")) {
            datequery();
        }
    }

    public void akhirpick() {
        if (!this.dateawal.equals("")) {
            datequery();
        }
    }

    public void message(String isi) {
        Alert pesan = new Alert(AlertType.INFORMATION);
        pesan.setTitle("MESSAGE");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }

    public void changetable() {
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            if (this.pilihtransbox.getValue().toString().equals("ALL PEMASUKAN")) {

//            System.out.println("select * from transakasi inner join kategori where transakasi.jenis='PEMASUKAN' and transakasi.keterangan like '%"+this.searchtxt.getText()+"%'");
                rs = st.executeQuery("select * from transakasi inner join kategori on kategori.id=transakasi.id_kategori where transakasi.jenis='PEMASUKAN' and transakasi.keterangan like \"%" + this.searchtxt.getText() + "%\" and transakasi.username=\"" + this.usernametxt.getText() + "\"");

                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                int i = 0;
                while (rs.next()) {
                    isi.add(new Tabel(
                            String.valueOf(i++),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
                }
                this.tabelisi.setItems(isi);
            } else if (this.pilihtransbox.getValue().toString().equals("ALL PENGELUARAN")) {
                rs = st.executeQuery("select * from transakasi inner join kategori on kategori.id=transakasi.id_kategori where transakasi.jenis='PENGELUARAN' and transakasi.keterangan like \"%" + this.searchtxt.getText() + "%\" and transakasi.username=\"" + this.usernametxt.getText() + "\"");

                ObservableList<Tabel> isi = FXCollections.observableArrayList();
                int i = 0;
                while (rs.next()) {
                    isi.add(new Tabel(
                            String.valueOf(i++),
                            rs.getString("namakategori"),
                            rs.getString("keterangan"),
                            rs.getString("tanggal"),
                            rs.getString("jumlah"),
                            rs.getString("jenis"),
                            rs.getString("id")
                    ));
                }
                this.tabelisi.setItems(isi);
            }
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
            rs = st.executeQuery("select NamaKategori from kategori where Jenis='PEMASUKAN' and id_user=(select iduser from user where username=\"" + this.usernametxt.getText() + "\")");

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
            String sql = "Select * from user where username=\"" + this.usernametxt.getText() + "\"";
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
            rs = st.executeQuery("SELECT NamaKategori FROM kategori WHERE Jenis = 'PENGELUARAN' and id_user=(select iduser from user where username=\"" + this.usernametxt.getText() + "\")");
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
}
