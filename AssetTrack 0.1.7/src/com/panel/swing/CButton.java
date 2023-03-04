package test;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class CButton extends JButton {
    
    private boolean over;
    private Color fill;
    private Color line;
    
    private Color filloriginal;
    private Color fillover;
    private Color fillclick;
    private Color lineoriginal;
    private Color lineover;
    private int strokeWidth;
     
    public CButton(){
        
        setVerticalAlignment(JButton.CENTER);
        filloriginal = new Color(225,102,102);
        fillover = new Color(153,153,255);
        fillclick = new Color(102,102,255);
        lineoriginal = new Color(236,240,241);
        lineover = new Color(189, 195, 199);
        strokeWidth = 2;
        fill = filloriginal;
        line = lineoriginal;
        setOpaque(false);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBackground(filloriginal);
        setFont(new Font("Lato", Font.BOLD, 18));
        setForeground(Color.WHITE);
        
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseExited(MouseEvent e)
                {
                    fill = filloriginal;
                    line = lineoriginal;
                    over = false;
                }
            
            @Override
            public void mouseEntered(MouseEvent e)
                {
                    fill = fillover;
                    line = lineover;
                    over = true;
                }
            
            @Override
            public void mouseReleased(MouseEvent e)
                {
                    if(over)
                        {
                            fill = fillover;
                        } else {
                                fill = filloriginal;
                               }
                }
            
            @Override
            public void mousePressed(MouseEvent e)
                {
                    fill = fillclick;
                }
        });
    }
    
    @Override
    protected void paintComponent(Graphics grphcs)
    {
        if(!isOpaque())
        {
        Graphics2D gd = (Graphics2D) grphcs;
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        int s = strokeWidth;
        int w = getWidth() - (2*5);
        int h = getHeight() - (2*5);
        gd.setColor(fill);
        gd.fillRoundRect(s, s, w, h, h, h);
        }
        super.paintComponent(grphcs);
        
    }
    
}
