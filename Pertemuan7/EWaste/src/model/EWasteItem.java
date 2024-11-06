/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.EWaste.src.model;

/**
 *
 * @author fahla
 */

public class EWasteItem {
    private int id;
    private String namaItem;
    private String kategori;
    private String kondisi;
    private String tambahan;
    private int ukuran;
    private int jumlah;

    // Getter dan Setter untuk setiap atribut
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaItem() { return namaItem; }
    public void setNamaItem(String namaItem) { this.namaItem = namaItem; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }

    public String getTambahan() { return tambahan; }
    public void setTambahan(String tambahan) { this.tambahan = tambahan; }

    public int getUkuran() { return ukuran; }
    public void setUkuran(int ukuran) { this.ukuran = ukuran; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
}

