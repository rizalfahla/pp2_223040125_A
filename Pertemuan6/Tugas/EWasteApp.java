/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan6.Tugas;

/**
 *
 * @author fahla
 */
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
        setTitle("E-Waste Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem newEntryItem = new JMenuItem("New Entry");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(newEntryItem);
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Entry Panel (Form Input)
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));

        // Form Components
        itemNameField = new JTextField(20);
        notesArea = new JTextArea(3, 20);
        workingButton = new JRadioButton("Working");
        brokenButton = new JRadioButton("Broken");
        ButtonGroup conditionGroup = new ButtonGroup();
        conditionGroup.add(workingButton);
        conditionGroup.add(brokenButton);

        batteryCheckBox = new JCheckBox("Battery");
        chargerCheckBox = new JCheckBox("Charger");
        screenCheckBox = new JCheckBox("Screen");

        String[] categories = {"Mobile", "Laptop", "Tablet", "Desktop", "Accessories"};
        categoryComboBox = new JComboBox<>(categories);

        String[] conditions = {"Good", "Fair", "Poor"};
        conditionList = new JList<>(conditions);

        sizeSlider = new JSlider(1, 100, 50);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
 
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // Table to display e-waste entries
        String[] columns = {"Item Name", "Category", "Condition", "Extras", "Size", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        wasteTable = new JTable(tableModel);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });

        // Adding components to the entry panel
        entryPanel.add(new JLabel("Item Name:"));
        entryPanel.add(itemNameField);
        entryPanel.add(new JLabel("Notes:"));
        entryPanel.add(new JScrollPane(notesArea));
        entryPanel.add(new JLabel("Condition:"));
        entryPanel.add(workingButton);
        entryPanel.add(brokenButton);
        entryPanel.add(new JLabel("Extras:"));
        entryPanel.add(batteryCheckBox);
        entryPanel.add(chargerCheckBox);
        entryPanel.add(screenCheckBox);
        entryPanel.add(new JLabel("Category:"));
        entryPanel.add(categoryComboBox);
        entryPanel.add(new JLabel("Physical Condition:"));
        entryPanel.add(new JScrollPane(conditionList));
        entryPanel.add(new JLabel("Size (inches):"));
        entryPanel.add(sizeSlider);
        entryPanel.add(new JLabel("Quantity:"));
        entryPanel.add(quantitySpinner);
        entryPanel.add(addButton);

        // Table Panel
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
                JOptionPane.showMessageDialog(null, "New Entry Form Ready");
            }
        });
        
        resetItem.addActionListener(new ActionListener() { // Menambahkan ActionListener untuk Reset
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
        String itemName = itemNameField.getText();
        String category = categoryComboBox.getSelectedItem().toString();
        String condition = workingButton.isSelected() ? "Working" : "Broken";

        StringBuilder extras = new StringBuilder();
        if (batteryCheckBox.isSelected()) extras.append("Battery ");
        if (chargerCheckBox.isSelected()) extras.append("Charger ");
        if (screenCheckBox.isSelected()) extras.append("Screen ");

        String size = String.valueOf(sizeSlider.getValue());
        String quantity = quantitySpinner.getValue().toString();

        tableModel.addRow(new Object[]{itemName, category, condition, extras.toString(), size, quantity});
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

        // Clear the table model
        tableModel.setRowCount(0);
    
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EWasteApp app = new EWasteApp();
            app.setVisible(true);
        });
    }
}

