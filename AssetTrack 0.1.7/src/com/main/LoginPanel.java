
package com.main;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

public class LoginPanel extends javax.swing.JPanel {

    public LoginPanel() {
        initComponents();
        
        usernameLabel.setFont( new Font("Lato", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(255,255,255));
        passwordLabel.setFont( new Font("Lato", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(255,255,255));
        
        loginButton.setText("Login");
        loginButton.setFont( new Font("Lato", Font.BOLD, 14));
        loginButton.setForeground(new Color(102,102,102));
        loginButton.setFocusable(false);
        
        registerNewMem.setFont( new Font("Lato", Font.BOLD, 12));
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoImage = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registerNewMem = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        u_namefield = new com.panel.swing.CustomTextField();
        u_passfield = new com.panel.swing.CustomPassField();

        setBackground(new java.awt.Color(51, 153, 255));

        logoImage.setBackground(new java.awt.Color(255, 255, 255));
        logoImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_1logo_transparent.png"))); // NOI18N

        loginButton.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerNewMem.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        registerNewMem.setForeground(new java.awt.Color(255, 255, 255));
        registerNewMem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerNewMem.setText("New to the Company? Register!");
        registerNewMem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerNewMem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerNewMemMouseClicked(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        usernameLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        passwordLabel.setText("Password");

        u_namefield.setForeground(new java.awt.Color(51, 51, 51));
        u_namefield.setOpaque(true);
        u_namefield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                u_namefieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                u_namefieldFocusLost(evt);
            }
        });
        u_namefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_namefieldActionPerformed(evt);
            }
        });

        u_passfield.setForeground(new java.awt.Color(51, 51, 51));
        u_passfield.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        u_passfield.setOpaque(true);
        u_passfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                u_passfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                u_passfieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(u_namefield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(u_passfield, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(registerNewMem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(logoImage)
                .addGap(58, 58, 58)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(u_namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(u_passfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerNewMem)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String getUsername = u_namefield.getText();
        String getPassword = u_passfield.getText();
        
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage", "root", "");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `login_information` WHERE `usernameField` = '"+getUsername+"' AND `passwordField`= '"+getPassword+"'");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Login successful!");
                this.setVisible(false);
                
                com.main.Admin admin = new com.main.Admin();
                admin.setVisible(false);
                
                com.main.Main Main = new com.main.Main();
                Main.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerNewMemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerNewMemMouseClicked
        JOptionPane.showMessageDialog(null, "To access the system, please contact the administrator!");
    }//GEN-LAST:event_registerNewMemMouseClicked

    private void u_namefieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_u_namefieldFocusGained
        if (u_namefield.getText().equals("Username")){
            u_namefield.setText("");
            u_namefield.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_u_namefieldFocusGained

    private void u_namefieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_u_namefieldFocusLost
        if (u_namefield.getText().equals("Username") || u_namefield.getText().equals("")){
            u_namefield.setForeground(new Color (102, 102, 102));
            u_namefield.setText("Username");
        }
    }//GEN-LAST:event_u_namefieldFocusLost

    private void u_namefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_namefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_namefieldActionPerformed

    private void u_passfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_u_passfieldFocusLost
        if (u_passfield.getText().equals("Password")|| u_passfield.getText().equals("")){
            u_passfield.setForeground(new Color(102, 102, 102));
            u_passfield.setText("Password");
    }
    }//GEN-LAST:event_u_passfieldFocusLost

    private void u_passfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_u_passfieldFocusGained
        if (u_passfield.getText().equals("Password")){
            u_passfield.setText("");
            u_passfield.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_u_passfieldFocusGained
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoImage;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel registerNewMem;
    private com.panel.swing.CustomTextField u_namefield;
    private com.panel.swing.CustomPassField u_passfield;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
