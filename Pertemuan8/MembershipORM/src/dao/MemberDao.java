/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.dao;

/**
 *
 * 
 */
import java.util.List;
import Pertemuan8.MembershipORM.src.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(Member member) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("mapper.MemberMapper.insert", member);
        }
        return result;
    }

    public int update(Member member) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("mapper.MemberMapper.update", member);
        }
        return result;
    }

    public int delete(Member member) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.MemberMapper.delete", member);
        }
        return result;
    }

    public List<Member> findAll() {
        List<Member> result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.selectList("mapper.MemberMapper.findAll");
        }
        return result;
    }
}
