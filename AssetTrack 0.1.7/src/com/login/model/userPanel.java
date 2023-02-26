
package com.login.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userPanel extends javax.swing.JPanel {

    public userPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        u_namefield = new javax.swing.JTextField();
        u_passfield = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_1logo_transparent.png"))); // NOI18N

        u_namefield.setText("Username");
        u_namefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_namefieldActionPerformed(evt);
            }
        });

        u_passfield.setText("Password");

        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(u_passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(u_namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(u_namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(u_passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(loginButton)
                .addContainerGap(227, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void u_namefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_namefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_namefieldActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField u_namefield;
    private javax.swing.JPasswordField u_passfield;
    // End of variables declaration//GEN-END:variables
}
