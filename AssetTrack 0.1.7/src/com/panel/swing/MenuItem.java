
package com.panel.swing;

import com.panel.model.MenuModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {
    
     private boolean selected;
     private boolean hover;
            
    public MenuItem(MenuModel data) {
        initComponents();
        setOpaque(false);
        if(data.getType() == MenuModel.MenuType.MENU)
        {
            micon.setIcon(data.icons());
            mlabel.setText(data.getName());
        } else if (data.getType() == MenuModel.MenuType.TITLE)
        {
             micon.setText(data.getName());
             micon.setFont(new Font("Lato", 1, 12));
             mlabel.setVisible(false);
        } else 
        {
            mlabel.setText(" ");
        }
        
    }
    
     public void setSelected(boolean selected) 
    {
        this.selected = selected;
        repaint();
    }
     
     public void setHover(boolean hover)
    {
         this.hover = hover;
         repaint();
    }
    
    public MenuItem() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        micon = new javax.swing.JLabel();
        mlabel = new javax.swing.JLabel();

        micon.setForeground(new java.awt.Color(255, 255, 255));

        mlabel.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        mlabel.setForeground(new java.awt.Color(255, 255, 255));
        mlabel.setText("ITEM MENU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(micon)
                .addGap(25, 25, 25)
                .addComponent(mlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(micon, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
     @Override
    protected void paintComponent(Graphics grphcs)
    {
        if (selected || hover) 
        {
            Graphics2D grp = (Graphics2D) grphcs;
            grp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(selected) 
                {
                grp.setColor(new Color(255,255,255,80));
                    } else 
                    {
                    grp.setColor(new Color(255,255,255,20));
                    }
            grp.fillRoundRect(0,0, getWidth(), getHeight(), 5, 5);
        }
        super.paintComponent(grphcs); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel micon;
    private javax.swing.JLabel mlabel;
    // End of variables declaration//GEN-END:variables
}
