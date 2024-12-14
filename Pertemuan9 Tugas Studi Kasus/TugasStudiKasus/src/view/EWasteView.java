/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author fahla
 */
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.EWaste;

public class EWasteView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtCategory = new JTextField(20);
    private JTextField txtCondition = new JTextField(20);
    private JTextField txtAdditional = new JTextField(20);
    private JTextField txtSize = new JTextField(20);
    private JTextField txtQty = new JTextField(20);
    private JButton btnAdd = new JButton("Add Item");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private int selectedRow = -1;
    private JTable eWasteTable;
    private DefaultTableModel tableModel;

    public EWasteView() {
        setTitle("EWaste Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for inputs
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(txtName);
        panel.add(new JLabel("Kategori:"));
        panel.add(txtCategory);
        panel.add(new JLabel("Kondisi:"));
        panel.add(txtCondition);
        panel.add(new JLabel("Tambahan:"));
        panel.add(txtAdditional);
        panel.add(new JLabel("Ukuran:"));
        panel.add(txtSize);
        panel.add(new JLabel("Jumlah:"));
        panel.add(txtQty);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel);

        // Table setup
        String[] columnNames = {"ID", "Nama Barang", "Kategori", "Kondisi", "Tambahan", "Ukuran", "Jumlah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        eWasteTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(eWasteTable);

        // Action on selecting a row
        eWasteTable.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = eWasteTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) tableModel.getValueAt(selectedRow, 0);
                txtName.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtCategory.setText((String) tableModel.getValueAt(selectedRow, 2));
                txtCondition.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtAdditional.setText((String) tableModel.getValueAt(selectedRow, 4));
                txtSize.setText((String) tableModel.getValueAt(selectedRow, 5));
                txtQty.setText((String) tableModel.getValueAt(selectedRow, 6));
            }
        });

        // Add components to frame
        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    // Getters for input fields
    public String getSelectedItemId() {
        if (selectedRow != -1) {
            return (String) tableModel.getValueAt(selectedRow, 0);
        }
        return null;
    }

    public String getNameInput() {
        return txtName.getText();
    }

    public String getCategoryInput() {
        return txtCategory.getText();
    }

    public String getConditionInput() {
        return txtCondition.getText();
    }

    public String getAdditionalInput() {
        return txtAdditional.getText();
    }

    public String getSizeInput() {
        return txtSize.getText();
    }

    public String getQtyInput() {
        return txtQty.getText();
    }

    // Methods for updating the table
    public void addItemToTable(String id, String name, String category, String condition, String additional, String size, String qty) {
        String[] row = {id, name, category, condition, additional, size, qty};
        tableModel.addRow(row);
    }

    public void setItemList(String[][] items) {
        tableModel.setRowCount(0);
        for (String[] item : items) {
            tableModel.addRow(item);
        }
    }

    // Listeners for buttons
    public void addAddItemListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addUpdateItemListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteItemListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}

