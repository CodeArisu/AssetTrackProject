package com.panel.form;

import java.awt.Color;
import java.io.IOException;
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
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Dashboard_Form extends javax.swing.JPanel {

    public Dashboard_Form() {
        initComponents();
        Inventory();
        header.setBackground(Color.decode("#2C74B3"));
    }

    private void Inventory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");

            Statement st = con.createStatement();
            String sql = "select * from inventoryitems";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel) Inventory.getModel();

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addItem() {
        String itemName = itemNameField.getText();
        String itemType = itemTypeField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String status = statusField.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `inventoryitems`(`ID`, `Item_Name`, `Item_Type`, `Quantity`, `Status`) VALUES (?,?,?,?,?)");
            long ID = (long) (Math.random() * 1000000);
            ps.setLong(1, ID);
            ps.setString(2, itemName);
            ps.setString(3, itemType);
            ps.setInt(4, quantity);
            ps.setString(5, status);
            ps.executeUpdate();
            Inventory();
            JOptionPane.showMessageDialog(null, "New Item Added");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void deleteItem() {
        DefaultTableModel model = (DefaultTableModel) Inventory.getModel();
        int selectedIndex = Inventory.getSelectedRow();
        try {
            String itemID = model.getValueAt(selectedIndex, 0).toString();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the record", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
                PreparedStatement ps = connection.prepareStatement("DELETE FROM `inventoryitems` WHERE ID = ?");
                ps.setString(1, itemID);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Delete");
                Inventory();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void updateItem() {
        DefaultTableModel model = (DefaultTableModel) Inventory.getModel();
        String itemName = itemNameField.getText();
        String itemType = itemTypeField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        int selectedIndex = Inventory.getSelectedRow();
        int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
        String status = statusField.getText();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("UPDATE `inventoryitems` SET `Item_Name`=?,`Item_Type`=?,`Quantity`=?,`Status`=? WHERE `ID`=?");
            ps.setString(1, itemName);
            ps.setString(2, itemType);
            ps.setInt(3, quantity);
            ps.setString(4, status);
            ps.setInt(5, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated");
            Inventory();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    void onClickTableAction()throws IOException, SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) Inventory.getModel();
        int selectedIndex = Inventory.getSelectedRow();
        itemNameField.setText(tableModel.getValueAt(selectedIndex, 1).toString());
        itemTypeField.setText(tableModel.getValueAt(selectedIndex, 2).toString());
        quantityField.setText(tableModel.getValueAt(selectedIndex, 3).toString());
        statusField.setText(tableModel.getValueAt(selectedIndex, 4).toString());
    }
    
    void searchOnTable() {
        String searchedItem = searchField.getText();
        int columnCount;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `inventoryitems` WHERE `ID` LIKE '%" + searchedItem + "%' OR `Item_Name` LIKE '%" + searchedItem + "%' OR `Item_Type` LIKE '%" + searchedItem + "%' OR `Quantity` LIKE '%" + searchedItem + "%' OR 'Status' LIKE '%" + searchedItem + "%'");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            DefaultTableModel tableModel = (DefaultTableModel) Inventory.getModel();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                Vector vector = new Vector();
                for (int i = 0; i <= columnCount; i++) {
                    vector.add(rs.getLong("ID"));
                    vector.add(rs.getString("Item_Name"));
                    vector.add(rs.getString("Item_Type"));
                    vector.add(rs.getInt("Quantity"));
                    vector.add(rs.getString("Status"));
                }
                tableModel.addRow(vector);
                tableModel.fireTableDataChanged();
            }
            
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        mlabel = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Inventory = new javax.swing.JTable();
        itemTypeField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        itemNameField = new javax.swing.JTextField();
        statusField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        setOpaque(false);

        mlabel.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        mlabel.setForeground(new java.awt.Color(255, 255, 255));
        mlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mlabel.setText("Dashboard");

        icon.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        icon.setForeground(new java.awt.Color(255, 255, 255));
        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/dashboardmain.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mlabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Inventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Item Name", "Item Type", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Inventory.setRowHeight(35);
        Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Inventory);
        if (Inventory.getColumnModel().getColumnCount() > 0) {
            Inventory.getColumnModel().getColumn(1).setResizable(false);
            Inventory.getColumnModel().getColumn(3).setResizable(false);
            Inventory.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setText("Item Name");

        jLabel2.setText("Item Type");

        jLabel3.setText("Quantity");

        jLabel4.setText("Status");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clearButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton)))
                        .addGap(18, 18, 18)
                        .addComponent(updateButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(itemNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(itemTypeField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(quantityField)
                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(searchButton)))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
                            .addComponent(updateButton))
                        .addGap(18, 18, 18)
                        .addComponent(clearButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addItem();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteItem();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        itemNameField.setText("");
        itemTypeField.setText("");
        quantityField.setText("");
        statusField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateItem();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryMouseClicked
         if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            try {
                onClickTableAction();
            } catch (IOException ex) {
                 java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
                
    }//GEN-LAST:event_InventoryMouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchOnTable();
    }//GEN-LAST:event_searchButtonActionPerformed
    private static class st {

        private static ResultSet executeQuery(String sql) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public st() {
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Inventory;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel icon;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JTextField itemTypeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mlabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField statusField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
