package com.panel.component;

import com.panel.model.MenuModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class SidePanel extends javax.swing.JPanel {

    public SidePanel() {
        initComponents();
        setOpaque(false);
        menuList1.setOpaque(false);
        init();
    }
    
     private void init() //edit desired list side panel
    {
        menuList1.addItem(new MenuModel("dashboard", "DASHBOARD", MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("database", "DATABASE", MenuModel.MenuType.MENU));
        
        menuList1.addItem(new MenuModel("", "", MenuModel.MenuType.EMPTY));
   
        menuList1.addItem(new MenuModel("", "Account", MenuModel.MenuType.TITLE));
        menuList1.addItem(new MenuModel("account", "Admin", MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("setting", "Settings", MenuModel.MenuType.MENU));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dragPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuList1 = new com.panel.swing.MenuList<>();

        dragPanel.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_1logo_transparent.png"))); // NOI18N

        javax.swing.GroupLayout dragPanelLayout = new javax.swing.GroupLayout(dragPanel);
        dragPanel.setLayout(dragPanelLayout);
        dragPanelLayout.setHorizontalGroup(
            dragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        dragPanelLayout.setVerticalGroup(
            dragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dragPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dragPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuList1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintChildren(Graphics grphcs)
    {
        Graphics2D grp = (Graphics2D) grphcs;
        grp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint paint = new GradientPaint(0, 0, Color.decode("#00C6FB"), 0, getHeight(), Color.decode("#005BEA"));
        grp.setPaint(paint);
        grp.fillRoundRect(0,0, getWidth(), getHeight(), 15, 15);
        grp.fillRect(getWidth()-20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    
     private int x;
    private int y;
    
    public void inDrag(JFrame frame)
    {
        dragPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) 
            {
                x = evt.getX();
                y = evt.getY();
            }
        });
        
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) 
            {
               frame.setLocation(evt.getXOnScreen()-x, evt.getYOnScreen()-y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dragPanel;
    private javax.swing.JLabel jLabel1;
    private com.panel.swing.MenuList<String> menuList1;
    // End of variables declaration//GEN-END:variables
}
