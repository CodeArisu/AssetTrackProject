package com.panel.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class CustomLoadingBar extends JProgressBar{


    public Color getColorString() {
        return colorString;
    }


    public void setColorString(Color colorString) {
        this.colorString = colorString;
    }
    
    private Color colorString = new Color(200,200,200);
    
    public CustomLoadingBar() {
        setStringPainted(true);
        setBackground(new Color(255,255,255));
        setForeground(new Color(69,124,235));
        setUI(new BasicProgressBarUI() {
            
            @Override
            protected void paintString(Graphics g, int i, int i1, int i2, int i3, int i4, Insets insets) {
                super.paintString(g, i, i1, i2, i3, i4, insets);
                
            }
        });
    }
    
}
