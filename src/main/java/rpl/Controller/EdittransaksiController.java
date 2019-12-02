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
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class EdittransaksiController implements Initializable {

    /**
     * Initializes the controller class.
     */
        private int warna;
    
    public void setWarna(int warna){
        this.warna=warna;
    }
    @FXML
    private TextField Judultxt, Jumlahtxt;

    @FXML
    private DatePicker tanggaldate;

    @FXML
    private ComboBox Kategoribox, jenisbox;

    @FXML
    private Button kembalibtn, simpanbtn;

    Connection conn;
    Statement st;
    ResultSet rs;
    String username;
    private int edit;
    private int saldo;
    private String id_transaksi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void kembali() {
        Stage stage = (Stage) kembalibtn.getScene().getWindow();
        stage.close();
    }

    public void MouseinKembali() {
        kembalibtn.setCursor(Cursor.HAND);
    }

    public void SimpanPemasukan() {
        try {
            conn = konek.conDB();
            st = conn.createStatement();
            System.out.println("disini");
            rs = st.executeQuery("select saldo from user where username='" + this.username + "'");
            this.saldo = rs.getInt("saldo");
            rs.close();
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.jenisbox.getValue().toString().equals("PEMASUKAN")) {
            this.saldo = saldo - this.edit;
            this.saldo += Integer.valueOf(this.Jumlahtxt.getText());
        } else {
            this.saldo = saldo + this.edit;
            this.saldo -= Integer.valueOf(this.Jumlahtxt.getText());

        }

        if (this.saldo > 0) {
            conn = konek.conDB();
            try {
                st = conn.createStatement();
                System.out.println("UPDATE Transakasi SET tanggal='" + this.tanggaldate.getValue().toString() + "' and jenis='" + this.jenisbox.getValue().toString() + "' and id_kategori=(select id from kategori where namakategori='" + this.Kategoribox.getValue().toString() + "')"
                        + " and keterangan='" + this.Judultxt.getText().toString() + "' and jumlah='" + this.Jumlahtxt.getText().toString() + "' where id='" + this.username + "'");
                st.executeUpdate("UPDATE Transakasi SET tanggal='" + this.tanggaldate.getValue().toString() + "',jenis='" + this.jenisbox.getValue().toString() + "',id_kategori=(select id from kategori where namakategori='" + this.Kategoribox.getValue().toString() + "')"
                        + " ,keterangan=\"" + this.Judultxt.getText().toString()+ "\" ,jumlah=\"" + this.Jumlahtxt.getText().toString() + "\" where id='" + this.id_transaksi + "'");
                message("berhasil update");
                st.executeUpdate("update user set saldo='" + this.saldo + "' where username=\""+this.username+"\"");
                Stage stage = (Stage) this.simpanbtn.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            message("input tidak valid");
        }

    }

    public void Mouseintambah() {
        simpanbtn.setCursor(Cursor.HAND);

    }

    public void message(String isi) {
        Alert pesan = new Alert(AlertType.INFORMATION);
        pesan.setTitle("INFORMATION");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }

    public void setJumlahRP() {

    }

    public void Setter(Tabel tab, String id_transaksi, String username) throws ParseException {
        this.Judultxt.setText(tab.getKeterangan());
        this.Jumlahtxt.setText(tab.getJumlah());
        this.Kategoribox.getItems().add(tab.getNamakategori());
        this.Kategoribox.setValue(tab.getNamakategori());
        this.jenisbox.getItems().add(tab.getJenis());
        this.jenisbox.setValue(tab.getJenis());
        if(tab.getJenis().equals("PEMASUKAN")){
            this.jenisbox.getItems().add("PENGELUARAN");
        }else{
            this.jenisbox.getItems().add("PEMASUKAN");
        }
        
        try{
            conn=konek.conDB();
            st=conn.createStatement();
            rs=st.executeQuery("select * from kategori where id_user=(select iduser from user where username=\""+username+"\") and namakategori!=\""+tab.getNamakategori()+"\"");
            System.out.println("select * from kategori where id_user=(select iduser from user where username=\""+this.username+"\") and namakategori!=\""+tab.getNamakategori()+"\"");
            while(rs.next()){
                this.Kategoribox.getItems().add(rs.getString("namakategori"));
            }
            conn.close();
            st.close();
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(tab.getTanggal());
        this.tanggaldate.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.username = username;
        this.id_transaksi = id_transaksi;
        this.edit = Integer.valueOf(tab.getJumlah());
    }

}
