/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import Koneksi.konek;
import java.net.URL;
import java.sql.Connection;
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
    
    @FXML
    private TextField Judultxt,Jumlahtxt;
    
    @FXML
    private DatePicker tanggaldate;
    
    @FXML
    private ComboBox Kategoribox,jenisbox;
    
    @FXML
    private Button kembalibtn, simpanbtn;


    Connection conn;
    Statement st;
    String username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void kembali(){
        Stage stage = (Stage) kembalibtn.getScene().getWindow();
        stage.close();
    }
    public void MouseinKembali(){
        kembalibtn.setCursor(Cursor.HAND);
    }
    
    public void SimpanPemasukan(){
        
        conn = konek.conDB();
        try{
            st = conn.createStatement();
            System.out.println("UPDATE Transakasi SET tanggal='"+this.tanggaldate.getValue().toString()+"' and jenis='"+this.jenisbox.getValue().toString()+"' and id_kategori=(select id from kategori where namakategori='"+this.Kategoribox.getValue().toString()+"')"
                    + " and keterangan='"+this.Judultxt.getText().toString()+"' and jumlah='"+this.Jumlahtxt.getText().toString()+"' where id='"+this.username+"'");
            st.executeUpdate("UPDATE Transakasi SET tanggal='"+this.tanggaldate.getValue().toString()+"',jenis='"+this.jenisbox.getValue().toString()+"',id_kategori=(select id from kategori where namakategori='"+this.Kategoribox.getValue().toString()+"')"
                    + " ,keterangan='"+this.Judultxt.getText().toString()+"' ,jumlah='"+this.Jumlahtxt.getText().toString()+"' where id='"+this.username+"'");
            message("berhasil update");
            Stage stage=(Stage) this.simpanbtn.getScene().getWindow();
            stage.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
                st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void Mouseintambah(){
        simpanbtn.setCursor(Cursor.HAND);
        
    }
    
    public void message(String isi){
        Alert pesan=new Alert(AlertType.INFORMATION);
        pesan.setTitle("INFORMATION");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }
    public void setJumlahRP(){
        
    }
    
    public void Setter(Tabel tab,String username) throws ParseException{
        this.Judultxt.setText(tab.getKeterangan());
        this.Jumlahtxt.setText(tab.getJumlah());
        this.Kategoribox.setValue(tab.getNamakategori());
        this.jenisbox.setValue(tab.getJenis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date=formatter.parse(tab.getTanggal());
        this.tanggaldate.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.username=username;
            }
  
    
}
