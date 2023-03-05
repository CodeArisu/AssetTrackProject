package com.panel.swing;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class CustomTable  extends JTable{
    
    private static TableHeader head;
    private static TableCell cell;
    
    public CustomTable() {
        head = new TableHeader();
        cell = new TableCell();
        getTableHeader().setDefaultRenderer(head);
        getTableHeader().setPreferredSize(new Dimension(0,35)); 
        setDefaultRenderer(Object.class, cell);
        setRowHeight(35);
    }
    
    public static void setColumnAlignment(int column, int align) {
        head.setAlignment(column, align);
    }
    
     public static void setCellAlignment(int column, int align) {
        cell.setAlignment(column, align);
    }
    private class TableHeader extends DefaultTableCellRenderer { //sets header
        
        private Map<Integer, Integer> alignment = new HashMap<>();
        public void setAlignment(int column, int align)
        {
            alignment.put(column, align);       
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int il) {
            
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, il);
            com.setFont(new Font("Lato", Font.BOLD, 14));
            com.setBackground(Color.decode("#125fff"));
            com.setForeground(new Color(242,242,242));
            if(alignment.containsKey(il)) {
                setHorizontalAlignment(alignment.get(il));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
            
        }
    }
    
     private class TableCell extends DefaultTableCellRenderer { //sets cell
        
        private Map<Integer, Integer> alignment = new HashMap<>();
        public void setAlignment(int column, int align)
        {
            alignment.put(column, align);       
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
            
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
            
            if(isCellSelected(row, column)) {
                
            } else {
                if(row%2 == 0) {
                    com.setBackground(Color.decode("#7ea0e5"));
                }else {
                    com.setBackground(Color.decode("#5B8FB9"));
                }
            }
            com.setFont(new Font("Lato", Font.PLAIN, 14));
            com.setForeground(new Color(255,255,255));
            if(alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
            
        }
    }
    
    
    
    
    
}
