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
import rpl.Koneksi.konek;

/**
 * FXML Controller class
 *
 * @author Wildan Kristian
 */
public class PengeluaranController implements Initializable {
    @FXML
    private Label usernametxt;

    @FXML
    private Label showjumlahtxt;
    
    @FXML
    private Label logout,Pengaturan,Pemasukan,Pengeluaran,Laporan,Ekspor,Tentang;
    
    @FXML
    private ImageView HomeImg;
    
    @FXML
    private Button simpanbtn,kembalibtn;
    
    @FXML
    private ComboBox kategoribox;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    @FXML
    private TextField jumlahtxt,judultxt;
    
    @FXML
    private DatePicker tanggaldate;
    
    
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
    
    public void Logout(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/fxml/SplashAct.fxml"));
        Scene scene = new Scene(login);
//        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
     
     

    public void MouseinTambah(){
        simpanbtn.setCursor(Cursor.HAND);
    }
    
    public void MouseinKembali(){
        kembalibtn.setCursor(Cursor.HAND);
    }
    
    
    public void kembali(ActionEvent ae){
        try{
            conn = konek.conDB();
            String sql = "Select * from user where username='"+this.usernametxt.getText()+"'";
            st = conn.createStatement();
            System.out.println("sampe sini");
       
                JOptionPane.showMessageDialog(null, "berhasil insert");
                
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                 Parent root= (Parent) loader.load();
               
                 rs=st.executeQuery(sql);
                 HomeController hm=loader.getController();
                 hm.setEmailtxt(rs.getString("email"));
                 hm.setNamatxt(rs.getString("Nama_Lengkap"));
                 hm.setSaldotxt(rs.getString("saldo"));
                 hm.setUsernametxt(rs.getString("username"));
                       
                 stage.setScene(new Scene(root));
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                try{
                rs.close();
                st.close();
                conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
    
    
     public void PengaturanMenu(){
        Stage stage = (Stage) Pengeluaran.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pengaturan.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PengaturanController hm=loader.getController();
//        hm.setEmailtxt(emailtxt.getText());
//        hm.setNamatxt(namatxt.getText());
//        hm.setSaldotxt(saldotxt.getText());
        hm.setUsernametxt(usernametxt.getText());
        
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void simpanPengeluaran(ActionEvent ae){
        try{
        conn = konek.conDB();
        String sqlKategori = "SELECT id FROM Kategori WHERE NamaKategori = '"+kategoribox.getValue()+"'";
        st = conn.createStatement();
        rs = st.executeQuery(sqlKategori);
        
        String sql = "INSERT INTO Transakasi (id_kategori,Jumlah,Tanggal,Jenis,keterangan,username) VALUES "
                + "('"+rs.getString("id")+"','"+this.jumlahtxt.getText()+"','"+this.tanggaldate.getValue().toString()+"','PENGELUARAN','"+this.judultxt.getText()+"','"+this.usernametxt.getText()+"')";
        st.executeUpdate(sql);
        //cuk, database mu tambahain kolom username
        JOptionPane.showMessageDialog(null,"Berhasil Menyimpan di Database");
//        conn.close();
//        st.close();
//        rs.close();
//        conn = konek.conDB();
//        st = conn.createStatement();
        st.executeUpdate("UPDATE user SET saldo = saldo-'"+this.jumlahtxt.getText()+"' WHERE username = '"+this.usernametxt.getText()+"' ");
        kembali(ae);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            conn.close();
            rs.close();
            st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    public void isiComboBox(String isi){
        kategoribox.getItems().add(isi);
    }
    
    public void setJumlahRP(){
        System.out.println(this.jumlahtxt.getText());
        showjumlahtxt.setText("Rp. " + this.jumlahtxt.getText()+ ",00");
    }
    
    public void Pengeluaran(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pengeluaran.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PengeluaranController hm=loader.getController();
        hm.setUsernametxt(this.usernametxt.getText());
        
        conn = konek.conDB();
        st = conn.createStatement();
        rs = st.executeQuery("SELECT NamaKategori FROM kategori WHERE Jenis = 'PENGELUARAN'");
        while(rs.next()){
            hm.isiComboBox(rs.getString("NamaKategori"));
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("salah disini gan");
        }finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void Pemasukan(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pemasukan.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PemasukanController hm=loader.getController();
        hm.setUsernametxt(this.usernametxt.getText());
        
        conn = konek.conDB();
        st = conn.createStatement();
        rs=st.executeQuery("select NamaKategori from kategori where Jenis='PEMASUKAN' and id_user=(select iduser from user where username='"+this.usernametxt.getText()+"')");
        
        
        while(rs.next()){
            hm.isiComboBox(rs.getString("NamaKategori"));
        }
        
        
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("salah disini gan");
        }finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void kembalikehome(){
        try{
            conn = konek.conDB();
            String sql="Select * from user where username='"+this.usernametxt.getText()+"'";
            st = conn.createStatement();
            System.out.println("sampe sini");
       
                
                
                
                Stage stage = (Stage) HomeImg.getScene().getWindow();
                stage.close();
                
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                 Parent root= (Parent) loader.load();
               
                 rs=st.executeQuery(sql);
                 HomeController hm=loader.getController();
                 hm.setEmailtxt(rs.getString("email"));
                 hm.setNamatxt(rs.getString("Nama_Lengkap"));
                 hm.setSaldotxt(rs.getString("saldo"));
                 hm.setUsernametxt(rs.getString("username"));
                       
                 stage.setScene(new Scene(root));
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                try{
                rs.close();
                st.close();
                conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
}
