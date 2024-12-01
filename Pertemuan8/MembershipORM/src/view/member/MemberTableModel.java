/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan8.MembershipORM.src.view.member;

/**
 *
 * 
 */
import Pertemuan8.MembershipORM.src.model.Member;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MemberTableModel extends AbstractTableModel {

    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    // Constructor
    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;  // Cells are not editable
    }

    // Method to add a new member to the data
    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);  // Notify table about the new row
    }
}
