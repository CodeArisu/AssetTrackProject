package com.main;

import com.panel.swing.CustomPassField;
import com.panel.swing.CustomTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import test.CButton;


public class LogAndReg extends javax.swing.JLayeredPane {
    
    private CustomTextField usertext;
    private CustomPassField passtext;
    private CustomPassField retypePass;
    private Main main;
    private Admin admin;
    
    public LogAndReg() {
        initComponents();
        initRegister();
        initLogin();
        Login.setVisible(false);
        Register.setVisible(true);
    }
    
    private void initRegister() {
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create New Account");
        label.setFont(new Font("Lato", 1, 30));
        label.setForeground(Color.decode("#0A2647"));
        Register.add(label);
        
        usertext = new CustomTextField();
        usertext.setLabelText("Username");
        Register.add(usertext, "w 60%");
        
        passtext = new CustomPassField();
        passtext.setLabelText("Password");
        Register.add(passtext, "w 60%");
        
        retypePass = new CustomPassField();
        retypePass.setLabelText("Re-Type Password");
        Register.add(retypePass, "w 60%");
        
        
        //Create Account button creation
        CButton cb = new CButton();
        cb.setForeground(new Color(250,250,250));
        cb.setText("CREATE ACCOUNT");
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Credentials are being processed. Please use the default credentials.");
                }
        });
        Register.add(cb, "w 30%, h 50");
        
    }
    
    //Login Part
    private void initLogin() {
        Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("Lato", 1, 30));
        label.setForeground(Color.decode("#0A2647"));
        Login.add(label);
        usertext = new CustomTextField();
        usertext.setLabelText("Username");
        Login.add(usertext, "w 60%");
        passtext = new CustomPassField();
        passtext.setLabelText("Password");
        Login.add(passtext, "w 60%");
        JButton fb = new JButton("Forgot Password?");
        fb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "That Sucks, Contact the Administrators Instead");
            }
        });
        fb.setForeground(new Color(100,100,100));
        fb.setFont(new Font("Lato", 1, 15));
        fb.setContentAreaFilled(false);
        fb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.add(fb);
        CButton cb = new CButton();
        cb.setForeground(new Color(250,250,250));
        cb.setText("LOG IN");
        
        // Login button function, checks database if user & password is present
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonActionPerformed();
            }
            
        });
        Login.add(cb, "w 30%, h 50");
    }
    
    public void showRegister(boolean show) {
        if(show) {
            Register.setVisible(true);
            Login.setVisible(false);
        } else {
            Register.setVisible(false);
            Login.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        Register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        Login.setBackground(new java.awt.Color(238, 238, 238));

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        add(Login, "card2");

        Register.setBackground(new java.awt.Color(238, 238, 238));

        javax.swing.GroupLayout RegisterLayout = new javax.swing.GroupLayout(Register);
        Register.setLayout(RegisterLayout);
        RegisterLayout.setHorizontalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        RegisterLayout.setVerticalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        add(Register, "card2");
    }// </editor-fold>//GEN-END:initComponents
    
    // Login button real function
    private void loginButtonActionPerformed() {                                            
        String getUsername = usertext.getText();
        String getPassword = passtext.getText();
        
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage", "root", "");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `login_information` WHERE `usernameField` = '"+getUsername+"' AND `passwordField`= '"+getPassword+"'");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Login successful!");
                this.setVisible(false);
                
                admin = new Admin();
                admin.setVisible(false);
                main = new Main();
                main.setVisible(true);
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
            }
        }
        catch(SQLException sqlException){
        }
      
    }
    

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Register;
    // End of variables declaration//GEN-END:variables
}
