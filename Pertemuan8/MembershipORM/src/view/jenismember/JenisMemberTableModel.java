/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.view.jenismember;

/**
 *
 * @author Fahla
 */
import Pertemuan8.MembershipORM.src.model.JenisMember;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class JenisMemberTableModel extends AbstractTableModel {
    
    private String[] columnNames = {"Nama"};
    private List<JenisMember> data;

    // Constructor
    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    // Return number of columns
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Return number of rows
    @Override
    public int getRowCount() {
        return data.size();
    }

    // Return column name at index
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Return value at specific row and column
    @Override
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama(); // Mengambil nama dari objek JenisMember
                break;
        }
        return value;
    }

    // Determine if the cell is editable
    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Cells are not editable
    }

    // Add a new JenisMember to the data and update the table view
    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Notify the table that a row has been added
    }
}