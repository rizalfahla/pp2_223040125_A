/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.EWaste.src.view.main;

/**
 *
 * @author fahla
 */
import Pertemuan7.EWaste.src.view.ewaste.EWasteView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EWasteApp extends JFrame {

    private EWasteView ewasteView;

    public EWasteApp() {
        setTitle("Sistem Pengelolaan E-Waste");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ewasteView = new EWasteView();
        add(ewasteView);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opsi");
        JMenuItem newEntryItem = new JMenuItem("Entri Baru");
        JMenuItem resetItem = new JMenuItem("Atur Ulang");
        JMenuItem exitItem = new JMenuItem("Keluar");
        menu.add(newEntryItem);
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Event Handlers
        newEntryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ewasteView.resetEntry();
                JOptionPane.showMessageDialog(null, "Form Entri Baru Siap");
            }
        });

        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ewasteView.resetEntry();
                ewasteView.clearTable();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EWasteApp app = new EWasteApp();
            app.setVisible(true);
        });
    }
}
