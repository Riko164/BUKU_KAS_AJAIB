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
import rpl.Koneksi.konek;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class GantipinController implements Initializable {

        Connection conn;
    ResultSet rs;
    Statement st;
    
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
      public void kembali(ActionEvent ae){
        Stage stage = (Stage) kembalibtn.getScene().getWindow();
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
      
    public void Mouseinsimpan(){
        simpanbtn.setCursor(Cursor.HAND);
    }
    
    public void Mouseinkembali(){
        kembalibtn.setCursor(Cursor.HAND);
    }
    
    public void simpan(ActionEvent ae){
        String passlama=passlamatxt.getText();
        String passbaru=passbarutxt.getText();
        String conpassbaru=conpassbarutxt.getText();
        
        try{
        conn=konek.conDB();
        st=conn.createStatement();
        rs=st.executeQuery("select pin from user where username='"+this.usernametxt.getText()+"'");
        if(rs.next()){
            if(!passlama.equals(rs.getString("pin"))){
                JOptionPane.showMessageDialog(null, "PIN LAMA SALAH");
            }else{
                if(passbaru.equals(conpassbaru)){
                    st.executeUpdate("update user set pin='"+passbaru+"' where username='"+this.usernametxt.getText()+"'");
                    JOptionPane.showMessageDialog(null, "Berhasil ganti pass");
                    kembalikehome();
                }else{
                    JOptionPane.showMessageDialog(null, "PIN TIDAK SAMA");
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
}
