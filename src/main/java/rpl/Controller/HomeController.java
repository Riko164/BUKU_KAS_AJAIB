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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Koneksi.konek;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class HomeController implements Initializable {

     @FXML
    private Label usernametxt;
    
    @FXML
    private Label emailtxt;
    
    @FXML
    private Label namatxt;
    
    @FXML
    private Label saldotxt;
    
    @FXML
    private Label logout,Pengaturan,Pemasukan,Pengeluaran,Laporan,Ekspor,Tentang,awallbl,akhirlbl,kategorilbl;
    
    @FXML
    private ImageView HomeImg;
    
    @FXML
    private Button locationbtn;
    
    @FXML
    private ComboBox pilihbox,kategoribox;
    
    @FXML
    private DatePicker awaldate,akhirdate;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    private int saldosim;
    
    
    

    
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
        String nilai="";
        this.saldosim=Integer.valueOf(saldotxt);
        
        if(saldotxt.length()>=4){
            int size=saldotxt.length()%3;
            if(size==0){
                size=3;
            }
//            System.out.println(saldotxt.charAt(0));
        for(int i=0;i<saldotxt.length();i++){
                if(i==size && i!=0){
                    System.out.println(i);
                    nilai+='.';
                    nilai+=saldotxt.charAt(i);
                    size+=3;
                }else{
                    nilai+=saldotxt.charAt(i);
                }
                
            
        }
        }else{
            nilai=saldotxt;
        }
        this.saldotxt.setText("Rp. "+nilai+",00");
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
    
    public void Logout(){
        
        int pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin mau keluar?", null, JOptionPane.YES_NO_OPTION);
        
        if(pilihan==0){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        try{
        Parent login=FXMLLoader.load(getClass().getResource("/fxml/SplashAct.fxml"));
        Scene scene = new Scene(login);
//        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Splash Act");
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        
    }
    
    public void gotoekspor(){
         Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GrafikLaporan.fxml"));
        Parent root= (Parent) loader.load();
//                  
        GrafikLaporanController hm=loader.getController();
        hm.setUsernametxt(this.usernametxt.getText());
        
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("salah disini gan");
        }
    }
    
    public void PengaturanMenu(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pengaturan.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PengaturanController hm=loader.getController();
        hm.setUsernametxt(this.usernametxt.getText());
        
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(true);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("salah disini gan");
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
    public void Pengeluaran(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Pengeluaran.fxml"));
        Parent root= (Parent) loader.load();
//                  
        PengeluaranController hm=loader.getController();
        hm.setUsernametxt(this.usernametxt.getText());
        hm.setSaldo(this.saldosim);
        conn = konek.conDB();
        st = conn.createStatement();
        rs = st.executeQuery("SELECT NamaKategori FROM kategori WHERE Jenis = 'PENGELUARAN' and id_user=(select iduser from user where username='"+this.usernametxt.getText()+"')");
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
    

    
    public void LaporanKeuangan(){
        try{
        Stage stage = (Stage) this.Laporan.getScene().getWindow();
                stage.close();
                
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TampilkanLaporan.fxml"));
                 Parent root= (Parent) loader.load();
               
                 TampilkanLaporanController hm=loader.getController();
                 
                 hm.setUsernametxt(this.usernametxt.getText());
                       
                 stage.setScene(new Scene(root));
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    
    public void gotoabout(){
         try{
        Stage stage = (Stage) this.Tentang.getScene().getWindow();
                stage.close();
                
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/About.fxml"));
                 Parent root= (Parent) loader.load();
               
                 AboutController hm=loader.getController();
                 
                 hm.setUsernametxt(this.usernametxt.getText());
                       
                 stage.setScene(new Scene(root));
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
