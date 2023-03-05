
package com.main;

import com.panel.form.Dashboard_Form;
import com.panel.form.Home_Form;
import com.panel.form.Settings_Form;
import com.panel.model.MenuSelection;
import java.awt.Color;
import javax.swing.JComponent;

public class Main extends javax.swing.JFrame {
    
    public Main() {
        initComponents();
        setVisible(false);
        setBackground(new Color(0,0,0,0));
        menu1.initDrag(Main.this);
        
        setForm(new Home_Form());
        menu1.addEventMenuSelected(new MenuSelection() 
        {
            @Override
            public void Selected (int index)
            {
                if(index == 0) {
                    setForm(new Home_Form());
                } else if (index == 1){
                    setForm(new Dashboard_Form());
                } else if (index == 2){
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.panel.swing.PanelBorder();
        menu1 = new com.panel.component.Menu();
        mainpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(200, 150, 1700, 1000));
        setUndecorated(true);

        mainpanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
            .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainpanel;
    private com.panel.component.Menu menu1;
    private com.panel.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
