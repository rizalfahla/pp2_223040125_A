/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.mapper;

/**
 *
 * @author Fahla
 */

import java.util.List;
import Pertemuan8.MembershipORM.model.JenisMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface JenisMemberMapper {

    @Insert("INSERT INTO jenis_member (id, nama) VALUES (#{id}, #{nama})")
    Integer insert(JenisMember jenisMember);

    @Update("UPDATE jenis_member SET nama = #{nama} WHERE id = #{id}")
    Integer update(JenisMember jenisMember);

    @Delete("DELETE FROM jenis_member WHERE id = #{id}")
    Integer delete(JenisMember jenisMember);

    @Select("SELECT * FROM jenis_member")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama")
    })
    List<JenisMember> findAll();
}
