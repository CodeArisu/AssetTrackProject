package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class LoadingScreen extends javax.swing.JDialog {

    public LoadingScreen(java.awt.Frame parent, boolean modal) {
         super(parent, modal);
         initComponents();
         getContentPane().setBackground(new Color(221,221,221));
         panel.setBackground(Color.decode("#2C74B3"));
         load.setFont(new Font("Lato", Font.PLAIN, 15));
    }
    
    private void doTask(String intask, int progress) throws Exception{
        load.setText(intask);
        Thread.sleep(1000);
        loading.setValue(progress);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        load = new javax.swing.JLabel();
        load1 = new javax.swing.JLabel();
        loading = new com.panel.swing.CustomLoadingBar();
        load2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel.setBackground(new java.awt.Color(102, 102, 255));

        load.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        load.setForeground(new java.awt.Color(255, 255, 255));
        load.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load.setText("Loading...");

        load1.setForeground(new java.awt.Color(255, 255, 255));
        load1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/ezgif.com-resize.gif"))); // NOI18N

        load2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        load2.setForeground(new java.awt.Color(255, 255, 255));
        load2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        load2.setText("WELCOME");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(load2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(load1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(246, 246, 246))))
            .addComponent(load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(load2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(load1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(load)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   doTask("Loading...", 10);
                   doTask("Loading Table...", 30);
                   doTask("Connecting Table...", 31);
                   doTask("Connecting Table...", 32);
                   doTask("Connecting Table...", 33);
                   doTask("Loading Data...", 40);
                   doTask("Gathering Data...", 50);
                   doTask("Loading Assets...", 60);
                   doTask("Loading Assets...", 70);
                   doTask("Loading Assets...", 100);
                   doTask("Loading Assets...", 100);
                   doTask("Complete", 100);
                   dispose();
               }catch(Exception e) {
                   e.printStackTrace();
               }
           }
       
       }).start();
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel load;
    private javax.swing.JLabel load1;
    private javax.swing.JLabel load2;
    private com.panel.swing.CustomLoadingBar loading;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
