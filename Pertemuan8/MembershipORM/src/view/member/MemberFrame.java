/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.view.member;

/**
 *
 * 
 */
import Pertemuan8.MembershipORM.src.dao.JenisMemberDao;
import Pertemuan8.MembershipORM.src.dao.MemberDao;
import Pertemuan8.MembershipORM.src.model.JenisMember;
import Pertemuan8.MembershipORM.src.model.Member;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        // Initialize member and jenisMember lists
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        // Create and configure components
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        // Set up table model
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Add action listener for the save button
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        // Add components to frame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        // Set frame size and layout
        this.setSize(400, 500);
        this.setLayout(null);

        // Populate the combo box with jenis member
        populateComboJenis();
    }

    // Method to populate combo box with jenis member options
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    // Get the name entered by the user
    public String getNama() {
        return textFieldNama.getText();
    }

    // Get the selected JenisMember object from the combo box
    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    // Add a new member to the table
    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    // Show an alert message
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}