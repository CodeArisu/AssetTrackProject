package com.panel.form;

import com.main.Main;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends javax.swing.JPanel {
    
    public String status;
    private int x;
    private int y;
    String[] typeComboBox = {" ", "Bakery", "Beverages", "Canned", "Dairy", "Dry/Baking Goods", "Frozen Foods", "Meat", "Produce", "Cleaners", "Paper Goods", "Personal Care", "Others"};
    
    
    
    public Dashboard() {
        initComponents();
        Inventory();
        logTable();
        header.setBackground(Color.decode("#2C74B3"));
    }

    private void Inventory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");

            Statement st = con.createStatement();
            String sql = "select * from inventoryitems";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel) a.getModel();

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
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addItem() {
        String itemName = itemNameField.getText();
        String itemType = itemTypeBox.getSelectedItem().toString();
        int quantity = Integer.parseInt(quantityField.getText());
        String status = statusField.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `inventoryitems`(`Item_Name`, `Item_Type`, `Quantity`, `Status`) VALUES (?,?,?,?)");


            ps.setString(1, itemName);
            ps.setString(2, itemType);
            ps.setInt(3, quantity);
            ps.setString(4, status);
            ps.executeUpdate();
            Inventory();
            LogUpdate(status);
            JOptionPane.showMessageDialog(null, "New Item Added");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void deleteItem() {
        DefaultTableModel model = (DefaultTableModel) a.getModel();
        int selectedIndex = a.getSelectedRow();
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
                LogUpdate("Deleted");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void updateItem() {
        DefaultTableModel model = (DefaultTableModel) a.getModel();
        String itemName = itemNameField.getText();
        String itemType = itemTypeBox.getSelectedItem().toString();
        int quantity = Integer.parseInt(quantityField.getText());
        int selectedIndex = a.getSelectedRow();
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
            LogUpdate(status);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    void onClickTableAction()throws IOException, SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) a.getModel();
        int selectedIndex = a.getSelectedRow();
        itemNameField.setText(tableModel.getValueAt(selectedIndex, 1).toString());
        String getType = tableModel.getValueAt(selectedIndex, 2).toString();
        for (int i = 0; i < typeComboBox.length;i++) {
            if (typeComboBox[i].equals(getType)) {
                itemTypeBox.setSelectedIndex(i);
                break;
            }
        }
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
            DefaultTableModel tableModel = (DefaultTableModel) a.getModel();
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
    
     void logTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM `update_logger`";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel) updateLog.getModel();

            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<String> row = new Vector<String>();
                row.add(rs.getString("logID"));
                row.add(rs.getString("name_item"));
                row.add(rs.getString("updatedEvent"));
                row.add(rs.getString("changes"));
                

                tableModel.addRow(row);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void LogUpdate(String status)  {
        DefaultTableModel mainTable = (DefaultTableModel) a.getModel();
        int selectedIndex = a.getSelectedColumn();
//        int id = Integer.parseInt(mainTable.getValueAt(selectedIndex, 0).toString());
      
        String itemName = itemNameField.getText();
        
        
        try {
            Date date = new Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboardinformation", "root", "");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `update_logger`(`name_Item`, `updatedEvent`, `changes`) VALUES (?,?,?)");

                
                ps.setString(1, itemName);
                ps.setDate(2, sqldate);
                ps.setString(3, status);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "The Item is updated to the Log Table");
                logTable();
                
            

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        mlabel1 = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        quantityField = new javax.swing.JTextField();
        itemNameField = new javax.swing.JTextField();
        statusField = new javax.swing.JTextField();
        mlabel3 = new javax.swing.JLabel();
        mlabel6 = new javax.swing.JLabel();
        mlabel7 = new javax.swing.JLabel();
        mlabel8 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        itemTypeBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        a = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        updateLog = new javax.swing.JTable();
        mlabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(193, 193, 193));

        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                headerMouseMoved(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        mlabel1.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        mlabel1.setForeground(new java.awt.Color(255, 255, 255));
        mlabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mlabel1.setText("DASHBOARD");

        icon1.setFont(new java.awt.Font("Myanmar Text", 1, 36)); // NOI18N
        icon1.setForeground(new java.awt.Color(255, 255, 255));
        icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/dashboardmain.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mlabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                        .addComponent(icon1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(mlabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(236, 236, 236));

        mlabel3.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel3.setForeground(new java.awt.Color(30, 30, 30));
        mlabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mlabel3.setText("Note:");

        mlabel6.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel6.setForeground(new java.awt.Color(30, 30, 30));
        mlabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mlabel6.setText("Item Name:");

        mlabel7.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel7.setForeground(new java.awt.Color(30, 30, 30));
        mlabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mlabel7.setText("Item Type:");

        mlabel8.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel8.setForeground(new java.awt.Color(30, 30, 30));
        mlabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mlabel8.setText("Quantity:");

        addButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        addButton.setForeground(new java.awt.Color(0, 153, 0));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_icons8-add-new-100.png"))); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(204, 0, 0));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8-remove-24.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_icons8-xbox-x-48.png"))); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        updateButton.setForeground(new java.awt.Color(0, 153, 0));
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_icons8-arrow-up-64.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        itemTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Bakery", "Beverages", "Canned", "Dairy", "Dry/Baking Goods", "Frozen Foods", "Meat", "Produce", "Cleaners", "Paper Goods", "Personal Care", "Others" }));
        itemTypeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTypeBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mlabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(mlabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itemNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(itemTypeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(mlabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(mlabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusField)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mlabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mlabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mlabel8)
                                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addButton)
                                    .addComponent(deleteButton)
                                    .addComponent(clearButton)
                                    .addComponent(updateButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mlabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemTypeBox))))
                .addContainerGap())
        );

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        a.setModel(new javax.swing.table.DefaultTableModel(
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
        a.setRowHeight(35);
        a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(a);
        if (a.getColumnModel().getColumnCount() > 0) {
            a.getColumnModel().getColumn(0).setMaxWidth(200);
            a.getColumnModel().getColumn(1).setResizable(false);
            a.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        updateLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Item Name", "Date", "Status"
            }
        ));
        jScrollPane2.setViewportView(updateLog);

        mlabel4.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel4.setForeground(new java.awt.Color(30, 30, 30));
        mlabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mlabel4.setText("Recent Updates");

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/search.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mlabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(searchField)))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchField)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(mlabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
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
        itemTypeBox.getEditor().setItem("");
        quantityField.setText("");
        statusField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateItem();
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void aMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            try {
                onClickTableAction();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_aMouseClicked

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged

    }//GEN-LAST:event_headerMouseDragged

    private void headerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_headerMouseMoved

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed

    }//GEN-LAST:event_headerMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       searchOnTable();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void itemTypeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTypeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemTypeBoxActionPerformed

    public void getCurrentUserLogin(String getUsername, String getPassword) {
       try { Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root", "");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `user_info` WHERE `username`= '" + getUsername + "' AND `password` = '" + getPassword + "'");
            ResultSet rs = ps.executeQuery();
       } catch (SQLException sqlException) {
            sqlException.printStackTrace();
       }
    }
    private static class st {

        private static ResultSet executeQuery(String sql) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public st() {
        } 
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable a;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel icon1;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JComboBox<String> itemTypeBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel mlabel1;
    private javax.swing.JLabel mlabel3;
    private javax.swing.JLabel mlabel4;
    private javax.swing.JLabel mlabel6;
    private javax.swing.JLabel mlabel7;
    private javax.swing.JLabel mlabel8;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField statusField;
    private javax.swing.JButton updateButton;
    private javax.swing.JTable updateLog;
    // End of variables declaration//GEN-END:variables
}
