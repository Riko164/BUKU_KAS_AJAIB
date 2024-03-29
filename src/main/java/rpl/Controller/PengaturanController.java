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
import Koneksi.konek;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class PengaturanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Connection conn;
    ResultSet rs;
    Statement st;
        private int warna;
        int saldosim;
        
        public void setSaldo(int saldo){
            this.saldosim=saldo;
        }
    
    public void setWarna(int warna){
        this.warna=warna;
    }
    
         @FXML
    private Label usernametxt;

    
    @FXML
    private Label logout,Pengaturan,Pemasukan,Pengeluaran,Laporan,Ekspor,Tentang,gantipinlbl,tambahlbl,gantiwarnalbl,edituserlbl,hapuslbl;
    
    @FXML
    private ImageView HomeImg;
    
    
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
     public void Mouseinpin(){
         gantipinlbl.setTextFill(Color.web("#1c50a3"));
         gantipinlbl.setCursor(Cursor.HAND);
     }   
     
     public void Mouseinwarna(){
         gantiwarnalbl.setTextFill(Color.web("#1c50a3"));
         gantiwarnalbl.setCursor(Cursor.HAND);
     }
     
     public void Mouseinkategori(){
         tambahlbl.setTextFill(Color.web("#1c50a3"));
         tambahlbl.setCursor(Cursor.HAND);
     }
     
     public void Mouseoutpin(){
         gantipinlbl.setTextFill(Color.web("#000"));
     }   
     
     public void Mouseoutwarna(){
         gantiwarnalbl.setTextFill(Color.web("#000"));

     }
     
     public void Mouseoutkategori(){
         tambahlbl.setTextFill(Color.web("#000"));

     }
     
     public void pinpressed(){
         
     }
     
     public void pressedwarna(){
         Alert gantiwarna = new Alert(Alert.AlertType.INFORMATION);
        gantiwarna.setTitle("EKSPORT FILE");
        gantiwarna.setHeaderText("input data");
        gantiwarna.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
        gantiwarna.initModality(Modality.WINDOW_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GantiWarna.fxml"));
            Node login = loader.load();
            DialogPane pane = gantiwarna.getDialogPane();
            GantiWarnaController edit = loader.getController();
            edit.setWarna2(1);
            pane.setContent(login);
            gantiwarna.showAndWait();
            this.warna=edit.getWarna2();
            System.out.println(edit.getWarna2());
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
     
     public void pressedkategori(){
         Stage stage = (Stage) Pengeluaran.getScene().getWindow();
        stage.close();
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TambahKategori.fxml"));
        Parent root= (Parent) loader.load();
//                  
        TambahKategoriController tk=loader.getController();
//        hm.setEmailtxt(emailtxt.getText());
//        hm.setNamatxt(namatxt.getText());
//        hm.setSaldotxt(saldotxt.getText());
        tk.setUsernametxt(usernametxt.getText());
        tk.setWarna(warna);
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
                 Scene scene = new Scene(root);
                 System.out.println("warna="+warna);
                 hm.setWarna(warna);
                 if(this.warna==1){  
                 scene.getStylesheets().add("/styles/biru.css");
                 stage.setScene(scene);
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();
                 }else{
                     scene.getStylesheets().add("/styles/hijau.css");
                  stage.setScene(scene);
                 stage.setTitle("Home");
                 stage.setResizable(true);
                 stage.show();   
                 }
            
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
    
    public void MouseinEdituser(){
        edituserlbl.setCursor(Cursor.HAND);
        edituserlbl.setTextFill(Color.web("#1c50a3"));
    }
    
    public void MouseoutEdituser(){
        edituserlbl.setTextFill(Color.web("#000"));
    }
    
    public void Editpin(){
        try{
        Stage stage = (Stage) edituserlbl.getScene().getWindow();
        stage.close();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Gantipin.fxml"));
        Parent root = (Parent) loader.load();
//        Parent parent=new Par
        GantipinController gp=loader.getController();
        gp.setUsernametxt(usernametxt.getText());
        gp.setWarna(warna);
        Scene scene=new Scene(root);
        if(warna==1){
            scene.getStylesheets().add("/styles/biru.css");
        }else{
            scene.getStylesheets().add("/styles/hijau.css");
        }
        
        stage.setScene(scene);
        stage.show();
        
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void EditUser(){
        try{
        Stage stage = (Stage) edituserlbl.getScene().getWindow();
        stage.close();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EditUser.fxml"));
        Parent root = (Parent) loader.load();
        EditUserController gp=loader.getController();
        gp.setUsernametxt(usernametxt.getText());
        gp.setSaldo(saldosim);
        gp.setWarna(warna);
        Scene scene=new Scene(root);
        if(warna==1){
            scene.getStylesheets().add("/styles/biru.css");
        }else{
            scene.getStylesheets().add("/styles/hijau.css");
        }
        
        stage.setScene(scene);
//        stage.setScene(new Scene(root));
        stage.show();
        
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void Mouseinhapus(){
        hapuslbl.setCursor(Cursor.HAND);
        hapuslbl.setTextFill(Color.web("#1c50a3"));
    }
    
    public void Mouseouthapus(){
        hapuslbl.setTextFill(Color.web("#000"));
    }
    
    public void pressedhapus(){
        try{
        Stage stage = (Stage) edituserlbl.getScene().getWindow();
        stage.close();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/HapusKategori.fxml"));
        Parent root = (Parent) loader.load();
        HapusKategoriController gp=loader.getController();
        gp.setUsernametxt(usernametxt.getText());
        gp.setWarna(warna);
        Scene scene=new Scene(root);
        if(warna==1){
            scene.getStylesheets().add("/styles/biru.css");
        }else{
            scene.getStylesheets().add("/styles/hijau.css");
        }
        
        stage.setScene(scene);
        stage.show();
        
        
        }catch(Exception e){
            e.printStackTrace();
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
