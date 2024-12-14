package model;

/**
 * Kelas ini merepresentasikan data e-waste item.
 * 
 * @author fahla
 */
public class EWaste {
    private int id;
    private String nama_item;
    private String kategori;
    private String kondisi;
    private String tambahan;
    private double ukuran;
    private int jumlah;

    // Getter dan Setter untuk id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Getter dan Setter untuk nama_item
    public String getNama_item() { return nama_item; }
    public void setNama_item(String nama_item) { this.nama_item = nama_item; }

    // Getter dan Setter untuk kategori
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    // Getter dan Setter untuk kondisi
    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }

    // Getter dan Setter untuk tambahan
    public String getTambahan() { return tambahan; }
    public void setTambahan(String tambahan) { this.tambahan = tambahan; }

    // Getter dan Setter untuk ukuran
    public double getUkuran() { return ukuran; }
    public void setUkuran(double ukuran) { this.ukuran = ukuran; }

    // Getter dan Setter untuk jumlah
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
}
