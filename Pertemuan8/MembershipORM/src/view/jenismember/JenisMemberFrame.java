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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class JenisMemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();  // Pastikan menggunakan findAll()

        // Setting default close operation
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set layout to null for absolute positioning
        this.setLayout(null);

        // Input label and text field
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 20);  // Atur ukuran label dan posisinya
        this.add(labelInput);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);  // Atur ukuran text field dan posisinya
        this.add(textFieldNama);

        // Save button
        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);  // Atur ukuran tombol dan posisinya
        this.add(button);

        // Table setup
        tableModel = new JenisMemberTableModel(jenisMemberList);  // Inisialisasi model tabel dengan data
        JTable table = new JTable(tableModel);
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);  // Atur ukuran tabel dan posisinya
        this.add(scrollableTable);

        // Set frame size and visibility
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public String getNama() {
        return textFieldNama.getText();  // Method untuk mendapatkan input nama dari text field
    }

    public void addJenisMember(JenisMember jenisMember) {
        this.jenisMemberList.add(jenisMember);  // Menambahkan jenisMember ke dalam list
        tableModel.fireTableRowsInserted(jenisMemberList.size() - 1, jenisMemberList.size() - 1);  // Memperbarui tabel
    }
}

