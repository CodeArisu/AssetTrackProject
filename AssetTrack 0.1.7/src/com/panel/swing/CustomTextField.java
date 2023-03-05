package com.panel.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustomTextField extends JTextField{


    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
    }
    
    private boolean mouseOver = false;
    
    private Icon prefixIcon;
    private Icon suffixIcon;
    
     public CustomTextField() {
        setBorder(new EmptyBorder(20,3,10,3));
        setOpaque(false);
         
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me) 
                {
                    mouseOver = true;
                    repaint();

                }
            public void mouseExited(MouseEvent me) 
                {
                    mouseOver = false;
                    repaint();
                }   
        });
        
        setFont(new Font("lato", Font.PLAIN, 18));
        setForeground(new Color(255,255,255));
        

    }
    
    @Override
    public void paint(Graphics gpcs) {
      super.paint(gpcs);
      Graphics2D gd = (Graphics2D) gpcs;
      gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      gd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
      int width = getWidth();
      int height = getHeight();
      if(mouseOver) {
          gd.setColor(new Color(153,153,255));
      }else {
          gd.setColor(new Color(255,255,255));
      }
      gd.fillRect(2, height - 1 , width - 4, 1);
      gd.dispose();
    }
    
    private void paintIcon(Graphics g) {
        Graphics2D gd = (Graphics2D) g;
        if(prefixIcon != null) {
            Image prefix = ((ImageIcon)prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            gd.drawImage(prefix, 0, y, this);
        }
        if(suffixIcon != null) {
            Image prefix = ((ImageIcon)prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            gd.drawImage(prefix, getWidth() - suffixIcon.getIconWidth(), y, this);
        }
    }
    
    private void initBorder() {
        int left = 5;
        int right = 5;
        
        if(prefixIcon!=null){
            left = prefixIcon.getIconWidth();
        }
        if(suffixIcon!=null) {
            right = suffixIcon.getIconWidth();
        }
        setBorder(BorderFactory.createEmptyBorder(5, left, 5, right));
    }
    
}
