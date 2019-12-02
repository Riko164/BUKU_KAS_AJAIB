/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

/**
 *
 * @author Shikimime
 */
public class Tabel {
    private String  no;
    private String jenis;
    private String namakategori;
    private String keterangan;
    private String tanggal;
    private String jumlah;
    private String id;
    
    
    
    public Tabel(String no,String namakategori,String keterangan,String tanggal,String jumlah,String jenis,String id){
        this.no=no;
        this.namakategori=namakategori;
        this.keterangan=keterangan;
        this.jumlah=jumlah;
        this.jenis=jenis;
        this.tanggal=tanggal;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamakategori() {
        return namakategori;
    }

    public void setNamakategori(String namakategori) {
        this.namakategori = namakategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
    
    
    
}
