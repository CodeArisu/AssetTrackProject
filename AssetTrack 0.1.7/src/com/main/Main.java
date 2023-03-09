
package com.main;

import com.panel.form.Dashboard;
import com.panel.form.Database;
import com.panel.form.Settings_Form;
import com.panel.model.MenuSelection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends javax.swing.JFrame {
    
    public Main() {
        initComponents();
        setVisible(false);
        setBackground(new Color(0,0,0,0));
        MainHeader.setBackground(Color.decode("#2C74B3"));
        initDrag(Main.this);
        
        setForm(new Dashboard());
        menu1.addEventMenuSelected(new MenuSelection() 
        {
            @Override
            public void Selected (int index)
            {
                if(index == 2) {
                    setForm(new Dashboard());
                } else if (index == 3){
                    setForm(new Database());
                } else if (index == 4){
                    setForm(new Settings_Form());
                }
            }
        }); 
    } 
    private void setForm(JComponent comp)
    {
        mainpanel.removeAll();
        mainpanel.add(comp);
        mainpanel.repaint();
        mainpanel.revalidate();
    }
    
    private int x;
    private int y;

    public void initDrag(JFrame fram) {
        MainHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        MainHeader.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.panel.swing.PanelBorder();
        menu1 = new com.panel.component.Menu();
        mainpanel = new javax.swing.JPanel();
        MainHeader = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(200, 150, 1700, 1000));
        setUndecorated(true);

        mainpanel.setLayout(new java.awt.BorderLayout());

        MainHeader.setPreferredSize(new java.awt.Dimension(1600, 50));
        MainHeader.setRequestFocusEnabled(false);

        javax.swing.GroupLayout MainHeaderLayout = new javax.swing.GroupLayout(MainHeader);
        MainHeader.setLayout(MainHeaderLayout);
        MainHeaderLayout.setHorizontalGroup(
            MainHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1569, Short.MAX_VALUE)
        );
        MainHeaderLayout.setVerticalGroup(
            MainHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1569, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(MainHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 1569, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(MainHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainHeader;
    private javax.swing.JPanel mainpanel;
    private com.panel.component.Menu menu1;
    private com.panel.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
