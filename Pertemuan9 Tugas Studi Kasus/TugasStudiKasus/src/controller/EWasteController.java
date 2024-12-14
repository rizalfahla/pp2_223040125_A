/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author fahla
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import org.apache.ibatis.session.SqlSession;

import model.*;
import view.EWasteView;

public class EWasteController {
    private EWasteView view;
    private EWasteMapper mapper;

    public EWasteController(EWasteView view, EWasteMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddItemListener(new AddItemListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addDeleteItemListener(new DeleteItemListener());
        this.view.addUpdateItemListener(new UpdateItemListener());
    }

    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String category = view.getCategoryInput();
            String condition = view.getConditionInput();
            String additional = view.getAdditionalInput();
            String size = view.getSizeInput();
            String qty = view.getQtyInput();
            
            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                EWasteMapper mapper = session.getMapper(EWasteMapper.class);

                if (!name.isEmpty() && !category.isEmpty() && !condition.isEmpty() && !additional.isEmpty() && !size.isEmpty() && !qty.isEmpty()) {
                    double sizeDouble = Double.parseDouble(size);
                    int qtyInt = Integer.parseInt(qty);

                    EWaste ewaste = new EWaste();
                    ewaste.setNama_item(name);
                    ewaste.setKategori(category);
                    ewaste.setKondisi(condition);
                    ewaste.setTambahan(additional);
                    ewaste.setUkuran(sizeDouble);
                    ewaste.setJumlah(qtyInt);

                    mapper.insertEWasteItem(ewaste);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Item added successfully!");
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid numbers for size and quantity.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setItemList(new String[0][0]);
    
            List<EWaste> ewastes = mapper.getAllEWasteItems();
    
            String[][] itemArray = ewastes.stream()
                .map(item -> new String[] {
                    String.valueOf(item.getId()),
                    item.getNama_item(),
                    item.getKategori(),
                    item.getKondisi(),
                    item.getTambahan(),
                    String.valueOf(item.getUkuran()),
                    String.valueOf(item.getJumlah())
                })
                .toArray(String[][]::new);
    
            view.setItemList(itemArray);
        }
    }

    class DeleteItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedItemId();
            if (selectedId != null) {
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    EWasteMapper mapper = session.getMapper(EWasteMapper.class);
    
                    // Delete item by ID
                    mapper.deleteEWasteItemById(Integer.parseInt(selectedId));
                    session.commit(); // Commit transaction
    
                    JOptionPane.showMessageDialog(view, "Item deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an item to delete.");
            }
        }
    }
    
    class UpdateItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedItemId();
            if (selectedId != null) {
                String name = view.getNameInput();
                String category = view.getCategoryInput();
                String condition = view.getConditionInput();
                String additional = view.getAdditionalInput();
                String size = view.getSizeInput();
                String qty = view.getQtyInput();
    
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    EWasteMapper mapper = session.getMapper(EWasteMapper.class);
    
                    if (!name.isEmpty() && !category.isEmpty() && !condition.isEmpty() && !additional.isEmpty() && !size.isEmpty() && !qty.isEmpty()) {
                        double sizeDouble = Double.parseDouble(size);
                        int qtyInt = Integer.parseInt(qty);
    
                        EWaste ewaste = new EWaste();
                        ewaste.setId(Integer.parseInt(selectedId)); // Set the selected ID
                        ewaste.setNama_item(name);
                        ewaste.setKategori(category);
                        ewaste.setKondisi(condition);
                        ewaste.setTambahan(additional);
                        ewaste.setUkuran(sizeDouble);
                        ewaste.setJumlah(qtyInt);
    
                        mapper.updateEWasteItem(ewaste);
                        session.commit(); // Commit transaction
                        JOptionPane.showMessageDialog(view, "Item updated successfully!");
                        
                    } else {
                        JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Please enter valid numbers for size and quantity.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an item to update.");
            }
        }
    }
}

