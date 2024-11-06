/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.EWaste.src.dao;

/**
 *
 * @author fahla
 */

import Pertemuan7.EWaste.src.db.MySqlConnection;
import Pertemuan7.EWaste.src.model.EWasteItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EWasteDao {

    // Menambahkan data baru ke database dan mengembalikan ID item yang ditambahkan
    public int addItem(EWasteItem item) {
        String sql = "INSERT INTO e_waste_item (nama_item, kategori, kondisi, tambahan, ukuran, jumlah) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getNamaItem());
            stmt.setString(2, item.getKategori());
            stmt.setString(3, item.getKondisi());
            stmt.setString(4, item.getTambahan());
            stmt.setInt(5, item.getUkuran());
            stmt.setInt(6, item.getJumlah());
            stmt.executeUpdate();

            // Mengambil ID yang dihasilkan oleh database
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan item: " + e.getMessage());
        }
        return -1; // Return -1 jika gagal menambahkan
    }


    // Memperbarui data yang ada berdasarkan ID
    public void updateItem(EWasteItem item) {
        String sql = "UPDATE e_waste_item SET nama_item = ?, kategori = ?, kondisi = ?, tambahan = ?, ukuran = ?, jumlah = ? WHERE id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNamaItem());
            stmt.setString(2, item.getKategori());
            stmt.setString(3, item.getKondisi());
            stmt.setString(4, item.getTambahan());
            stmt.setInt(5, item.getUkuran());
            stmt.setInt(6, item.getJumlah());
            stmt.setInt(7, item.getId());
            stmt.executeUpdate();
            System.out.println("Item berhasil diperbarui");

        } catch (SQLException e) {
            System.err.println("Gagal memperbarui item: " + e.getMessage());
        }
    }

    // Menghapus data berdasarkan ID
    public void deleteItem(int id) {
        String sql = "DELETE FROM e_waste_item WHERE id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Gunakan setInt untuk ID
            stmt.executeUpdate();
            System.out.println("Item berhasil dihapus");

        } catch (SQLException e) {
            System.err.println("Gagal menghapus item: " + e.getMessage());
        }
    }


    // Mengambil semua data e-waste dari database
    public List<EWasteItem> getAllItems() {
        List<EWasteItem> items = new ArrayList<>();
        String sql = "SELECT * FROM e_waste_item";

        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EWasteItem item = new EWasteItem();
                item.setId(rs.getInt("id"));
                item.setNamaItem(rs.getString("nama_item"));
                item.setKategori(rs.getString("kategori"));
                item.setKondisi(rs.getString("kondisi"));
                item.setTambahan(rs.getString("tambahan"));
                item.setUkuran(rs.getInt("ukuran"));
                item.setJumlah(rs.getInt("jumlah"));
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data: " + e.getMessage());
        }
        return items;
    }
}
