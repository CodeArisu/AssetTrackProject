package com.panel.component;

import com.panel.model.MenuModel;
import com.panel.model.MenuSelection;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends javax.swing.JPanel implements Runnable{


    
    int hour, second, minute;

    

    public void run() {
        while (true) {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            Date date = calendar.getTime();

            SimpleDateFormat TF_1 = new SimpleDateFormat("EEE, MMM d, ''yy");
            String time1 = TF_1.format(date);
            TimeDate.setText(time1);

            SimpleDateFormat TF_2 = new SimpleDateFormat("h:mm ss a");
            String time2 = TF_2.format(date);
            TimeHour.setText(time2);
        }
    }

    private MenuSelection event;

    public void addEventMenuSelected(MenuSelection event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        
        initComponents();
        Thread thread = new Thread(this);
        thread.start();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }

    private void init() {
        listMenu1.addItem(new MenuModel(" ", "Main", MenuModel.MenuType.TITLE));
        listMenu1.addItem(new MenuModel("dashboard", "DASHBOARD", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("database", "DATABASE", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("setting", "SETTINGS", MenuModel.MenuType.MENU));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drag = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new com.panel.swing.ListMenu<>();
        TimeHour = new javax.swing.JLabel();
        TimeDate = new javax.swing.JLabel();

        drag.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/rsz_1logo_transparent.png"))); // NOI18N

        javax.swing.GroupLayout dragLayout = new javax.swing.GroupLayout(drag);
        drag.setLayout(dragLayout);
        dragLayout.setHorizontalGroup(
            dragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        dragLayout.setVerticalGroup(
            dragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE)
        );

        listMenu1.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimeHour, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(drag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TimeHour)
                        .addGap(18, 18, 18)
                        .addComponent(TimeDate)
                        .addGap(46, 46, 46))))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D grp = (Graphics2D) g;
        grp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p = new GradientPaint(0, 0, Color.decode("#2C74B3"), 0, getHeight(), Color.decode("#0A2647"));
        grp.setPaint(p);
        grp.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        grp.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(g);
    }

    private int x;
    private int y;

    public void initDrag(JFrame fram) {
        drag.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        drag.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TimeDate;
    private javax.swing.JLabel TimeHour;
    private javax.swing.JPanel drag;
    private javax.swing.JLabel jLabel1;
    private com.panel.swing.ListMenu<String> listMenu1;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
