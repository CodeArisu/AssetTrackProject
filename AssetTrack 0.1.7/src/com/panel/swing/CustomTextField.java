package com.panel.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class CustomTextField extends JTextField{
    
    private boolean mouseOver = false;
    
     public CustomTextField() {
         
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
    
}
