/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.EWaste.src.view.ewaste;

/**
 *
 * @author fahla
 */

import Pertemuan7.EWaste.src.dao.EWasteDao;
import Pertemuan7.EWaste.src.model.EWasteItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;

public class EWasteView extends JPanel {

    private JTextField itemNameField;
    private JTextArea notesArea;
    private JRadioButton workingButton, brokenButton;
    private JCheckBox batteryCheckBox, chargerCheckBox, screenCheckBox;
    private JComboBox<String> categoryComboBox;
    private JList<String> conditionList;
    private JSlider sizeSlider;
    private JSpinner quantitySpinner;
    private JTable wasteTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton;

    public EWasteView() {
        setLayout(new BorderLayout());

        // Panel Entri (Form Input)
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));

        // Komponen Form
        itemNameField = new JTextField(20);
        notesArea = new JTextArea(3, 20);
        workingButton = new JRadioButton("Berfungsi");
        brokenButton = new JRadioButton("Rusak");
        ButtonGroup conditionGroup = new ButtonGroup();
        conditionGroup.add(workingButton);
        conditionGroup.add(brokenButton);

        batteryCheckBox = new JCheckBox("Baterai");
        chargerCheckBox = new JCheckBox("Pengisi Daya");
        screenCheckBox = new JCheckBox("Layar");

        String[] categories = {"Ponsel", "Laptop", "Tablet", "Desktop", "Aksesori"};
        categoryComboBox = new JComboBox<>(categories);

        String[] conditions = {"Baik", "Cukup", "Buruk"};
        conditionList = new JList<>(conditions);

        sizeSlider = new JSlider(1, 100, 50);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // Tabel untuk menampilkan entri e-waste
        String[] columns = {"ID", "Nama Item", "Kategori", "Kondisi", "Tambahan", "Ukuran", "Jumlah"};
        tableModel = new DefaultTableModel(columns, 0);
        wasteTable = new JTable(tableModel);
        wasteTable.getColumnModel().getColumn(0).setMinWidth(0); // Sembunyikan kolom ID
        wasteTable.getColumnModel().getColumn(0).setMaxWidth(0);


        // Tombol Tambah, Update, dan Hapus
        addButton = new JButton("Tambahkan Item");
        updateButton = new JButton("Perbarui Item");
        deleteButton = new JButton("Hapus Item");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEntry();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEntry();
            }
        });

        // Menambahkan komponen ke panel entri
        entryPanel.add(new JLabel("Nama Item:"));
        entryPanel.add(itemNameField);
        entryPanel.add(new JLabel("Catatan:"));
        entryPanel.add(new JScrollPane(notesArea));
        entryPanel.add(new JLabel("Kondisi:"));
        entryPanel.add(workingButton);
        entryPanel.add(brokenButton);
        entryPanel.add(new JLabel("Tambahan:"));
        entryPanel.add(batteryCheckBox);
        entryPanel.add(chargerCheckBox);
        entryPanel.add(screenCheckBox);
        entryPanel.add(new JLabel("Kategori:"));
        entryPanel.add(categoryComboBox);
        entryPanel.add(new JLabel("Kondisi Fisik:"));
        entryPanel.add(new JScrollPane(conditionList));
        entryPanel.add(new JLabel("Ukuran (inci):"));
        entryPanel.add(sizeSlider);
        entryPanel.add(new JLabel("Jumlah:"));
        entryPanel.add(quantitySpinner);
        entryPanel.add(addButton);
        entryPanel.add(updateButton);
        entryPanel.add(deleteButton);

        // Panel Tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(wasteTable), BorderLayout.CENTER);
        
        loadData();

        add(entryPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
    }

    public void addEntry() {
        EWasteItem item = new EWasteItem();
        item.setNamaItem(itemNameField.getText());
        item.setKategori(categoryComboBox.getSelectedItem().toString());
        item.setKondisi(workingButton.isSelected() ? "Berfungsi" : "Rusak");

        StringBuilder tambahan = new StringBuilder();
        if (batteryCheckBox.isSelected()) tambahan.append("Baterai ");
        if (chargerCheckBox.isSelected()) tambahan.append("Pengisi Daya ");
        if (screenCheckBox.isSelected()) tambahan.append("Layar ");
        item.setTambahan(tambahan.toString());

        item.setUkuran(sizeSlider.getValue());
        item.setJumlah((Integer) quantitySpinner.getValue());

        EWasteDao dao = new EWasteDao();
        int id = dao.addItem(item); // Menambahkan item dan mendapatkan ID

        if (id != -1) {
            tableModel.addRow(new Object[]{id, item.getNamaItem(), item.getKategori(), item.getKondisi(), item.getTambahan(), item.getUkuran(), item.getJumlah()});
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan ke database!");
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data ke database.");
        }
        loadData();
    }


    public void updateEntry() {
    int selectedRow = wasteTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui.");
        return;
    }

    int id = (Integer) tableModel.getValueAt(selectedRow, 0); // ID dari kolom pertama

    EWasteItem item = new EWasteItem();
    item.setId(id); // Mengatur ID
    item.setNamaItem(itemNameField.getText());
    item.setKategori(categoryComboBox.getSelectedItem().toString());
    item.setKondisi(workingButton.isSelected() ? "Berfungsi" : "Rusak");

    StringBuilder tambahan = new StringBuilder();
    if (batteryCheckBox.isSelected()) tambahan.append("Baterai ");
    if (chargerCheckBox.isSelected()) tambahan.append("Pengisi Daya ");
    if (screenCheckBox.isSelected()) tambahan.append("Layar ");
    item.setTambahan(tambahan.toString());

    item.setUkuran(sizeSlider.getValue());
    item.setJumlah((Integer) quantitySpinner.getValue());

    EWasteDao dao = new EWasteDao();
    dao.updateItem(item); // Update ke database

    // Perbarui baris di tabel
    tableModel.setValueAt(item.getNamaItem(), selectedRow, 1);
    tableModel.setValueAt(item.getKategori(), selectedRow, 2);
    tableModel.setValueAt(item.getKondisi(), selectedRow, 3);
    tableModel.setValueAt(item.getTambahan(), selectedRow, 4);
    tableModel.setValueAt(item.getUkuran(), selectedRow, 5);
    tableModel.setValueAt(item.getJumlah(), selectedRow, 6);

    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui di database!");
    loadData();
}


    public void deleteEntry() {
    int selectedRow = wasteTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.");
        return;
    }

    int id = (Integer) tableModel.getValueAt(selectedRow, 0); // ID dari kolom pertama
    EWasteDao dao = new EWasteDao();
    dao.deleteItem(id); // Hapus entri di database

    tableModel.removeRow(selectedRow); // Hapus dari tampilan tabel
    JOptionPane.showMessageDialog(this, "Data berhasil dihapus dari database!");
    loadData();
    }
    
    public void loadData() {
    EWasteDao dao = new EWasteDao();
    List<EWasteItem> items = dao.getAllItems(); // Mendapatkan semua data dari database

    // Bersihkan tableModel sebelum menambahkan data baru
    tableModel.setRowCount(0);

    // Tambahkan setiap item ke dalam tableModel
    for (EWasteItem item : items) {
        tableModel.addRow(new Object[]{
            item.getId(),
            item.getNamaItem(),
            item.getKategori(),
            item.getKondisi(),
            item.getTambahan(),
            item.getUkuran(),
            item.getJumlah()
        });
    }
}

    

    public void resetEntry() {
        itemNameField.setText("");
        notesArea.setText("");
        workingButton.setSelected(false);
        brokenButton.setSelected(false);
        batteryCheckBox.setSelected(false);
        chargerCheckBox.setSelected(false);
        screenCheckBox.setSelected(false);
        categoryComboBox.setSelectedIndex(0);
        conditionList.clearSelection();
        sizeSlider.setValue(50);
        quantitySpinner.setValue(1);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }
}

