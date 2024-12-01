/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.view.jenismember;

/**
 *
 * @author Fahla
 */
import Pertemuan8.MembershipORM.src.dao.JenisMemberDao;
import Pertemuan8.MembershipORM.src.model.JenisMember;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class JenisMemberButtonSimpanActionListener implements ActionListener {

    private JenisMemberFrame jenisMemberFrame; // Frame untuk input
    private JenisMemberDao jenisMemberDao; // DAO untuk menyimpan data

    // Konstruktor
    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    // Metode aksi ketika tombol disimpan ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();  // Ambil nama dari frame
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());  // Generate ID unik
        jenisMember.setNama(nama);  // Set nama jenis member

        // Menambah data jenis member ke tabel di frame
        this.jenisMemberFrame.addJenisMember(jenisMember);

        // Menyimpan data jenis member ke database melalui DAO
        this.jenisMemberDao.insert(jenisMember);
    }
}