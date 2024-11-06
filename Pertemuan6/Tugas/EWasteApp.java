/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package Pertemuan6.Tugas;

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
import java.awt.event.ActionListener;

public class EWasteApp extends JFrame {

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

    public EWasteApp() {
        setTitle("Sistem Pengelolaan E-Waste");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout());

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
        String[] columns = {"Nama Item", "Kategori", "Kondisi", "Tambahan", "Ukuran", "Jumlah"};
        tableModel = new DefaultTableModel(columns, 0);
        wasteTable = new JTable(tableModel);

        JButton addButton = new JButton("Tambahkan Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
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

        // Panel Tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(wasteTable), BorderLayout.CENTER);

        mainPanel.add(entryPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        add(mainPanel);

        // Event Handlers
        newEntryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEntry();
                JOptionPane.showMessageDialog(null, "Form Entri Baru Siap");
            }
        });
        
        resetItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetForm();
        }
    });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addEntry() {
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

        EWasteDao dao = new EWasteDao(); dao.addItem(item);
        tableModel.addRow(new Object[]{item.getNamaItem(), item.getKategori(), item.getKondisi(), item.getTambahan(), item.getUkuran(), item.getJumlah()});
        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan ke database!");
    }
    
        private void resetEntry() {
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
    
    private void resetForm() {
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

        // Hapus model tabel
        tableModel.setRowCount(0);
    
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EWasteApp app = new EWasteApp();
            app.setVisible(true);
        });
    }
}