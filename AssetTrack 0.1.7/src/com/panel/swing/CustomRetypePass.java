package com.panel.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class CustomRetypePass extends JTextField{

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private boolean mouseOver = false;
    private final Animator animate;
    private boolean animateHint = true;
    private float location;
    private boolean show;
    private String labelText = "Label";
    private Color lineColor = new Color(3,155,216);
    
     public CustomRetypePass() {
        setBorder(new EmptyBorder(20,3,10,3));
        setSelectionColor(new Color(76,204,255));
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
        
        addFocusListener(new FocusAdapter() {
            
            @Override
            public void focusGained(FocusEvent fe) {
                showing(false);
            }
            @Override
            public void focusLost(FocusEvent fe) {
                showing(true);
            }
        });
                
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                animateHint = getText().equals("");
            }
            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                repaint();
            }
        
        };
        
        animate = new Animator(300, target);
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
       
    }
     
      private void showing(boolean action) {
                if(animate.isRunning()) {
                    animate.stop();
                } else {
                    location = 1;
                }
                animate.setStartFraction(1f - location);
                show = action;
                location = 1f - location;
                animate.start();
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
          gd.setColor(Color.decode("#2C74B3"));
      }else {
          gd.setColor(new Color(0,0,0));
      }
      gd.fillRect(2, height - 1 , width - 4, 1);
      createHintText(gd);
      gd.dispose();
    }
    
    private void createHintText(Graphics2D g) {
        Insets in = getInsets();
        g.setColor(new Color(150,150,150));
        FontMetrics ft = g.getFontMetrics();
        Rectangle2D r = ft.getStringBounds(getLabelText(), g);
        double height = getHeight() - in.top - in.bottom;
        double textY = (height - r.getHeight()) / 2;
        double size;
        if(animateHint) {
            if(show) {
                size = 18*(1-location);
            } else {
                size = 18*location;
            }
        } else {
            size = 18;
        }
        g.drawString(getLabelText(), in.right, (int)(in.top + textY + ft.getAscent() - size));
    }
    
    private void createLineStyle(Graphics2D g) {
        if(isFocusOwner()) {
            double width = getWidth() - 4;
            int height = getHeight();
            g.setColor(getLineColor());
            double size;
            if(show) {
                size = width * (1 - location);
            } else {
                size = width * location;
            }
            double x = (width - size) / 2;
            g.fillRect((int)(x*2), height-2, (int) size, 2);
        }
    }
    
    @Override
    public void setText(String string) {
        if(!getText().equals(string)) {
            showing(string.equals(""));
        }
        super.setText(string);
    }
}  
    
