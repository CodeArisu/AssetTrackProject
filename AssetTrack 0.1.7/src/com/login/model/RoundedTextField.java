package com.login.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {

    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedTextField() {
        super();
    }

    public RoundedTextField(int columns) {
        super(columns);
    }

    public RoundedTextField(String text) {
        super(text);
    }

    public RoundedTextField(String text, int columns) {
        super(text, columns);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isEnabled()) {
            g2.setColor(getBackground());
        } else {
            g2.setColor(Color.gray);
        }

        g2.fillRoundRect(0, 0, size.width - 1, size.height - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        backgroundColor = bg;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int radius) {
        cornerRadius = radius;
        repaint();
    }
}