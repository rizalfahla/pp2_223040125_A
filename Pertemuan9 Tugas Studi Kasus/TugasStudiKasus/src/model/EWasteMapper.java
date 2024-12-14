/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fahla
 */

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface EWasteMapper {
    @Select("SELECT * FROM e_waste_item")
    List<EWaste> getAllEWasteItems();

    @Insert("INSERT INTO e_waste_item (nama_item, kategori, kondisi, tambahan, ukuran, jumlah) VALUES (#{nama_item}, #{kategori}, #{kondisi}, #{tambahan}, #{ukuran}, #{jumlah})")
    void insertEWasteItem(EWaste ewaste);

    @Update("UPDATE e_waste_item SET nama_item = #{nama_item}, kategori = #{kategori}, kondisi = #{kondisi}, tambahan = #{tambahan}, ukuran = #{ukuran}, jumlah = #{jumlah} WHERE id = #{id}")
    void updateEWasteItem(EWaste ewaste);

    @Delete("DELETE FROM e_waste_item WHERE id = #{id}")
    void deleteEWasteItemById(int id);
}



