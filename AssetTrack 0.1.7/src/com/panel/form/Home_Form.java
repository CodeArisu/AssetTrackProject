package com.panel.form;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class Home_Form extends javax.swing.JPanel {

    void InventoryTable() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM `inventoryitems`";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) InventoryTable.getModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<String> row = new Vector<String>();
                row.add(rs.getString("ID"));
                row.add(rs.getString("Item_Name"));
                row.add(rs.getString("Item_Type"));
                row.add(rs.getString("Quantity"));
                row.add(rs.getString("Status"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Home_Form() {
        InventoryTable();
        updateTable();
        initComponents();

        header.setBackground(Color.decode("#00C6FB"));
        addPanel.setBackground(Color.decode("#00C6FB"));
        tablepanel.setBackground(Color.decode("#00C6FB"));
        addhead.setBackground(Color.decode("#005BEA"));
    }

    void addItem() {
        String itemName = ItemNameField.getText();
        String itemType = ItemTypeField.getText();
        int quantity = Integer.parseInt(QuantityField.getText());
        String status = StatusField.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `inventoryitems`(`ID`, `Item_Name`, `Item_Type`, `Quantity`, `Status`) VALUES (?,?,?,?)");
            long ID = (long) (Math.random() * 1000000);
            ps.setLong(1, ID);
            ps.setString(2, itemName);
            ps.setString(3, itemType);
            ps.setInt(4, quantity);
            ps.setString(5, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Item Added");
            updateTable();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void updateItem() {
        DefaultTableModel tableModel = (DefaultTableModel) InventoryTable.getModel();
        int selectedIndex = InventoryTable.getSelectedRow();
        int id = Integer.parseInt(tableModel.getValueAt(selectedIndex, 0).toString());
        String itemName = ItemNameField.getText();
        String itemType = ItemTypeField.getText();
        int quantity = Integer.parseInt(QuantityField.getText());
        String status = StatusField.getText();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("UPDATE `inventoryitems` SET `ID`=?,`Item_Name`=?,`Item_Type`=?,`Quantity`=?,`Status`=?");
            ps.setLong(1, id);
            ps.setString(2, itemName);
            ps.setString(3, itemType);
            ps.setInt(4, quantity);
            ps.setString(5, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated");
            updateTable();
            JOptionPane.showMessageDialog(null, "New Item Added");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void updateTable() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM `inventoryitems`";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) InventoryTable.getModel();
            tableModel.setRowCount(0);
            while (rs.next()) {
                Vector<String> row = new Vector<String>();
                row.add(rs.getString("ID"));
                row.add(rs.getString("Item_Name"));
                row.add(rs.getString("Item_Type"));
                row.add(rs.getString("Quantity"));
                row.add(rs.getString("Status"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        mlabel = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        mlabel3 = new javax.swing.JLabel();
        mlabel4 = new javax.swing.JLabel();
        addhead = new javax.swing.JPanel();
        mlabel2 = new javax.swing.JLabel();
        mlabel7 = new javax.swing.JLabel();
        ItemTypeField = new javax.swing.JTextField();
        ItemNameField = new javax.swing.JTextField();
        QuantityField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        StatusField = new javax.swing.JTextField();
        tablepanel = new javax.swing.JPanel();
        searchText1 = new com.panel.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        DeleteButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();

        setOpaque(false);

        icon.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        icon.setForeground(new java.awt.Color(255, 255, 255));
        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/homemain.png"))); // NOI18N

        mlabel.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        mlabel.setForeground(new java.awt.Color(255, 255, 255));
        mlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mlabel.setText("Home");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(mlabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addPanel.setBackground(new java.awt.Color(204, 204, 255));

        mlabel3.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        mlabel3.setForeground(new java.awt.Color(255, 255, 255));
        mlabel3.setText("ITEM");
        mlabel3.setToolTipText("");

        mlabel4.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        mlabel4.setForeground(new java.awt.Color(255, 255, 255));
        mlabel4.setText("QUANTITY");
        mlabel4.setToolTipText("");

        addhead.setBackground(new java.awt.Color(153, 153, 255));
        addhead.setForeground(new java.awt.Color(102, 102, 255));

        mlabel2.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        mlabel2.setForeground(new java.awt.Color(255, 255, 255));
        mlabel2.setText("ADD ITEM");
        mlabel2.setToolTipText("");

        javax.swing.GroupLayout addheadLayout = new javax.swing.GroupLayout(addhead);
        addhead.setLayout(addheadLayout);
        addheadLayout.setHorizontalGroup(
            addheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addheadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        addheadLayout.setVerticalGroup(
            addheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addheadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mlabel7.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        mlabel7.setForeground(new java.awt.Color(255, 255, 255));
        mlabel7.setText("ITEM TYPE");
        mlabel7.setToolTipText("");

        QuantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuantityFieldActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addhead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mlabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mlabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mlabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ItemTypeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ItemNameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(QuantityField, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(StatusField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(addhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(mlabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ItemTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(mlabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ItemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(mlabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(addButton)
                .addGap(51, 51, 51))
        );

        tablepanel.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/search.png"))); // NOI18N

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        EditButton.setText("Edit");

        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"123", "putanginamo", "gago", "69", null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Item Name", "Item Type", "Quantity", "Status"
            }
        ));
        jScrollPane2.setViewportView(InventoryTable);

        javax.swing.GroupLayout tablepanelLayout = new javax.swing.GroupLayout(tablepanel);
        tablepanel.setLayout(tablepanelLayout);
        tablepanelLayout.setHorizontalGroup(
            tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54)
                .addComponent(EditButton)
                .addGap(31, 31, 31)
                .addComponent(DeleteButton)
                .addGap(66, 66, 66))
            .addGroup(tablepanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tablepanelLayout.setVerticalGroup(
            tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablepanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(searchText1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DeleteButton)
                        .addComponent(EditButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ItemTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemTypeActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addItem();
    }//GEN-LAST:event_addButtonActionPerformed

    private void QuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuantityFieldActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JTextField ItemNameField;
    private javax.swing.JTextField ItemTypeField;
    private javax.swing.JTextField QuantityField;
    private javax.swing.JTextField StatusField;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel addhead;
    private javax.swing.JPanel header;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mlabel;
    private javax.swing.JLabel mlabel2;
    private javax.swing.JLabel mlabel3;
    private javax.swing.JLabel mlabel4;
    private javax.swing.JLabel mlabel7;
    private com.panel.swing.SearchText searchText1;
    private javax.swing.JPanel tablepanel;
    // End of variables declaration//GEN-END:variables
}
