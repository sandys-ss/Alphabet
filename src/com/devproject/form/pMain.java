/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.form;

import java.awt.event.ActionListener;

/**
 *
 * @author LC01
 */
public class pMain extends javax.swing.JPanel {

    /**
     * Creates new form pMaster
     */
    public pMain() {
        initComponents();
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new com.devproject.component.PanelBackground();
        tombol_Master2 = new com.devproject.component.Tombol_Master();
        tombol_Master7 = new com.devproject.component.Tombol_Master();
        tombol_Master8 = new com.devproject.component.Tombol_Master();
        tombol_Master9 = new com.devproject.component.Tombol_Master();
        tombol_Master10 = new com.devproject.component.Tombol_Master();
        tombol_Master11 = new com.devproject.component.Tombol_Master();
        tombol_Master12 = new com.devproject.component.Tombol_Master();
        tombol_Master13 = new com.devproject.component.Tombol_Master();

        setLayout(new java.awt.BorderLayout());

        panelBackground1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tombol_Master2.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/receive.png"))); // NOI18N
        tombol_Master2.setText("Receiving");
        tombol_Master2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 140, 170));

        tombol_Master7.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/master.png"))); // NOI18N
        tombol_Master7.setText("Part Master");
        tombol_Master7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 140, 170));

        tombol_Master8.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/location3.png"))); // NOI18N
        tombol_Master8.setText("Location");
        tombol_Master8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 140, 170));

        tombol_Master9.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/supplier.png"))); // NOI18N
        tombol_Master9.setText("Supplier");
        tombol_Master9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 140, 170));

        tombol_Master10.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/issue.png"))); // NOI18N
        tombol_Master10.setText("Issuing");
        tombol_Master10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 140, 170));

        tombol_Master11.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/inventory.png"))); // NOI18N
        tombol_Master11.setText("Inventory");
        tombol_Master11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 140, 170));

        tombol_Master12.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/sales.png"))); // NOI18N
        tombol_Master12.setText("Sales Reporting");
        tombol_Master12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 140, 170));

        tombol_Master13.setForeground(new java.awt.Color(255, 255, 255));
        tombol_Master13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/devproject/img/setting.png"))); // NOI18N
        tombol_Master13.setText("Setting");
        tombol_Master13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombol_Master13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tombol_Master13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelBackground1.add(tombol_Master13, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 410, 140, 170));

        add(panelBackground1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.devproject.component.PanelBackground panelBackground1;
    private com.devproject.component.Tombol_Master tombol_Master10;
    private com.devproject.component.Tombol_Master tombol_Master11;
    private com.devproject.component.Tombol_Master tombol_Master12;
    private com.devproject.component.Tombol_Master tombol_Master13;
    private com.devproject.component.Tombol_Master tombol_Master2;
    private com.devproject.component.Tombol_Master tombol_Master7;
    private com.devproject.component.Tombol_Master tombol_Master8;
    private com.devproject.component.Tombol_Master tombol_Master9;
    // End of variables declaration//GEN-END:variables
}
