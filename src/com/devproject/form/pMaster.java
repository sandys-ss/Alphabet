/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.form;

import com.devproject.conn.Koneksi;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LC01
 */
public class pMaster extends javax.swing.JPanel {

    /**
     * Creates new form pMaster
     */
    Connection connection;
    
    public pMaster() {
        initComponents();
        isitabel();
    }
    
    public void addActionListenerMasterImport (ActionListener l) {
        btnimport.addActionListener(l);
    }
    
    public void addActionListenerMasterback (ActionListener l) {
        btnback.addActionListener(l);
    }
    
    public void isitabel () {
        Object header [] = {"Part Number", "Part Name", "Location", "On Hand",
                            "Landed Cost", "Price List"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        tabelMaster.setModel(model);
        
        String sql = "SELECT * FROM part ORDER BY partnumber";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String kolom1 = rs.getString(2);
                String kolom2 = rs.getString(3);
                String kolom3 = rs.getString(4);
                String kolom4 = rs.getString(5);
                String kolom5 = rs.getString(6);
                String kolom6 = rs.getString(7);
                
                String kolom [] = {kolom1, kolom2, kolom3, kolom4,
                                    kolom5, kolom6};  
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMaster = new javax.swing.JTable();
        panelTransparan1 = new com.devproject.component.PanelTransparan();
        btnimport = new com.devproject.component.Tombol_Master();
        jTextField1 = new javax.swing.JTextField();
        btnback = new com.devproject.component.Tombol_Master();
        btnsearch = new com.devproject.component.Tombol_Master();
        btnnew = new com.devproject.component.Tombol_Master();

        setLayout(new java.awt.BorderLayout());

        panelBackground1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelMaster.setAutoCreateRowSorter(true);
        tabelMaster.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabelMaster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelMaster.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane1.setViewportView(tabelMaster);

        panelBackground1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1340, 610));

        panelTransparan1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnimport.setForeground(new java.awt.Color(255, 255, 255));
        btnimport.setText("Import");
        btnimport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelTransparan1.add(btnimport, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 100, 30));
        panelTransparan1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 250, 30));

        btnback.setForeground(new java.awt.Color(255, 255, 255));
        btnback.setText("Back");
        btnback.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelTransparan1.add(btnback, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

        btnsearch.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch.setText("Search");
        btnsearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelTransparan1.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 100, 30));

        btnnew.setForeground(new java.awt.Color(255, 255, 255));
        btnnew.setText("New Part");
        btnnew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelTransparan1.add(btnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 100, 30));

        panelBackground1.add(panelTransparan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1340, 50));

        add(panelBackground1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.devproject.component.Tombol_Master btnback;
    private com.devproject.component.Tombol_Master btnimport;
    private com.devproject.component.Tombol_Master btnnew;
    private com.devproject.component.Tombol_Master btnsearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.devproject.component.PanelBackground panelBackground1;
    private com.devproject.component.PanelTransparan panelTransparan1;
    private javax.swing.JTable tabelMaster;
    // End of variables declaration//GEN-END:variables
}
