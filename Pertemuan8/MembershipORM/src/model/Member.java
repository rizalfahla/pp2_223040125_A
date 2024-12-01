/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.model;

/**
 *
 * @author Fahla
 */

public class Member {
    private String id;
    private String nama;
    private String jenisMemberId;
    private JenisMember jenisMember;

    // Setter untuk id
    public void setId(String id) {
        this.id = id;
    }

    // Getter untuk id
    public String getId() {
        return id;
    }

    // Setter untuk nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Getter untuk jenisMemberId
    public String getJenisMemberId() {
        return jenisMemberId;
    }

    // Setter untuk jenisMemberId
    public void setJenisMemberId(String jenisMemberId) {
        this.jenisMemberId = jenisMemberId;
    }

    // Setter untuk jenisMember
    public void setJenisMember(JenisMember jenisMember) {
        this.jenisMember = jenisMember;
    }

    // Getter untuk jenisMember
    public JenisMember getJenisMember() {
        return jenisMember;
    }
}

