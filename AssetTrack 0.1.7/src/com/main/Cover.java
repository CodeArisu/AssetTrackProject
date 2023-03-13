
package com.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import test.CButton;


public class Cover extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel logo;
    private JLabel discription;
    private CButton button;
    private boolean isLogin;
    private JButton sign;
    
    
    public Cover() {
        initComponents();
        setOpaque(false);
        
        layout = new MigLayout("wrap, fill", "[center]", "push[]30[]20[]30[]push");
        setLayout(layout);
        
        init();
        
    }
    
    private void init() {
        logo = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/icon/logo.png"));
        logo.setIcon(icon);
        add(logo);
        discription = new JLabel();
        discription.setText("Welcome! To AssetTrak");
        discription.setFont(new Font("Lato", Font.BOLD, 18));
        discription.setForeground(new Color(245,245,245));
        add(discription);
        sign = new JButton("Already Have an Account? Sign In!");
        sign.setForeground(new Color(255,255,255));
        sign.setContentAreaFilled(false);
        sign.setFocusable(false);
        Font font = sign.getFont();
        Map underline = font.getAttributes();
        underline.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        sign.setFont(font.deriveFont(underline));
        sign.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
            
        });
        add(sign, "W 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D gd = (Graphics2D) g;
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p = new GradientPaint(0, 0, Color.decode("#2C74B3"), 0, getHeight(), Color.decode("#0A2647"));
        gd.setPaint(p);
        gd.fillRoundRect(0,0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }
    
    public void addEvent(ActionListener event) {
        this.event = event;
    }
    
    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(logo, "pad 0 -" + v + "% 0 " + "%");
        layout.setComponentConstraints(discription, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(sign, "pad 0 -" + v + "% 0 0");
    }
    
     public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(logo, "pad 0 -" + v + "% 0 " + "%");
        layout.setComponentConstraints(discription, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(sign, "pad 0 -" + v + "% 0 0");
    }
     
     public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(logo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(discription, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(sign, "pad 0 " + v + "% 0 " + v + "%");
    }
     
     public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(logo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(discription, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(sign, "pad 0 " + v + "% 0 " + v + "%");

    }
    private void login(boolean login) {
        if(this.isLogin != login) {
            if(login) {
                discription.setText("Welcome Back!");
                sign.setText("No existing account? Sign up now!");
            } else {
                discription.setText("Welcome to AssetTrak!");
                sign.setText("Already have an account? Sign in!");
            }
            this.isLogin = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
