/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.Membership.src.view.main;

/**
 *
 * @author fahla
 */
import Pertemuan7.Membership.src.dao.JenisMemberDao;
import Pertemuan7.Membership.src.dao.MemberDao;
import Pertemuan7.Membership.src.view.jenismember.JenisMemberFrame;
import Pertemuan7.Membership.src.view.member.MemberFrame;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class MainFrame extends JFrame {
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500); // Perbaiki sintaksis ukuran

        this.jenisMemberDao = new JenisMemberDao();
        this.memberDao = new MemberDao();

        this.jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        this.memberFrame = new MemberFrame(memberDao, jenisMemberDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember = new JButton("Jenis Member"); // Perbaiki sintaksis teks
        this.buttonMember = new JButton("Member"); // Perbaiki sintaksis teks

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);

        this.add(buttonJenisMember);
        this.add(buttonMember);
    }

    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    public JButton getButtonMember() {
        return buttonMember;
    }

    public void showJenisMemberFrame() {
        if (jenisMemberFrame == null) {
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao); // Inisialisasi jika null
        }
        jenisMemberFrame.setVisible(true); // Perbaiki pemanggilan setVisible
    }

    public void showMemberFrame() {
        if (memberFrame == null) {
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        }
        memberFrame.populateComboJenis();
        memberFrame.setVisible(true); // Perbaiki pemanggilan setVisible
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true); // Perbaiki pemanggilan setVisible
            }
        });
    }
}

