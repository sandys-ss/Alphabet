/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.form;

import com.devproject.conn.Koneksi;
import com.devproject.validation.ValidasiMaster;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author LC01
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private static Connection connection;
    
    public Main() {
        initComponents();
        card();
        aksi_tombol();
    }
    
    private void card () {
        pCard.add(pMain, "panelutama");
        pCard.add(pMaster, "panelmaster");
        pCard.add(pMdetail, "panelmdetail");
        
        CardLayout c1 = (CardLayout)pCard.getLayout();
        c1.show(pCard, "panelutama");        
        
    }
    
    private void aksi_tombol () {
        //pMain Action
        pMain.addActionListenerMaster(new Aksi_menuUtama_master());
        
        //pMaster Action
        pMaster.addActionListenerMasterImport(new Aksi_masterimport());
        pMaster.addActionListenerMasterback(new Aksi_masterback());
        pMaster.addActionListenerMasterTabel(new Aksi_mastertabel());
        pMaster.addActionListenerMastersearch(new Aksi_mastersearch());
        pMaster.addKeyListenerMasterSearch(new Aksi_mastersearchkey());
        pMaster.addActionListenerMasterrefresh(new Aksi_masterrefresh());
        pMaster.addActionListenerMasternew(new Aksi_masternew());
        
        //pMdetail Action
        pMdetail.addActionListenerMdetailback(new Aksi_mdetailback());
        pMdetail.addActionListenerMdetailsave(new Aksi_mdetailsave());
        
    }
    
    class Aksi_menuUtama_master implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmaster");
            isitabel();
        }
    }
    
    class Aksi_masterimport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                readXLSXFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class Aksi_masterback implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelutama");
            pMaster.setTxtsearch("");
        }
    }
    
    class Aksi_mastertabel implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getClickCount();
            if (x == 2) {
                //System.out.println("Klik okke. saya part detail");
                CardLayout c1 = (CardLayout) pCard.getLayout();
                c1.show(pCard, "panelmdetail");
                isipartdetail();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

    }
    
    class Aksi_mdetailback implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmaster");
            isitabel();
        }
    }
    
    class Aksi_mastersearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            searchresult();
        }
    }
    
    class Aksi_mastersearchkey implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
           if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
               searchresult();
           } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
               isitabel();
           } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
               isitabel();
               pMaster.setTxtsearch("");
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    class Aksi_masterrefresh implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isitabel();
            pMaster.setTxtsearch("");
        }
        
    }
    
    class Aksi_masternew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmdetail");
        }
        
    }
    
    class Aksi_mdetailsave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            insert();
        }
        
    }
       
    // Method
    
    public static void readXLSXFile() throws IOException {
        InputStream ExcelFileToRead = new FileInputStream("D:/Test.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();

                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else {
                    //U Can Handel Boolean, Formula, Errors
                }
            }
            System.out.println();
        }

    }
    
    private void isipartdetail () {
        int xrow = pMaster.getTabelMaster().getSelectedRow();
        String partnumber = (String) pMaster.getTabelMaster().getValueAt(xrow, 0);
        String partname = (String) pMaster.getTabelMaster().getValueAt(xrow, 1);
        String location = (String) pMaster.getTabelMaster().getValueAt(xrow, 2);
        String onhand = (String) pMaster.getTabelMaster().getValueAt(xrow, 3);
        String landedcost = (String) pMaster.getTabelMaster().getValueAt(xrow, 4);
        String pricelist = (String) pMaster.getTabelMaster().getValueAt(xrow, 5);
        
        pMdetail.setTxtpartnumber(partnumber);
        pMdetail.setTxtpartname(partname);
        pMdetail.setTxtlocation(location);
        pMdetail.setTxtoh(onhand);
        pMdetail.setTxtlandedcost(landedcost);
        pMdetail.setTxtpricelist(pricelist);
    }
    
    public void searchresult() {
        Object header [] = {"No Part", "Nama Part", "Type", "Stock",
                            "Harga Beli", "Harga Jual"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pMaster.getTabelMaster().setModel(model);
        
        String sql = "SELECT * From part WHERE CONCAT (id, partnumber, partname, location,"
                    + "oh, landedcost, price) LIKE '%"+pMaster.getTxtsearch().getText()+"%' ";
        
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
    
    public void isitabel () {
        Object header [] = {"Part Number", "Part Name", "Location", "On Hand",
                            "Landed Cost", "Price List"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pMaster.getTabelMaster().setModel(model);
        
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
    
    public void insert () {
        String partnumber = pMdetail.getTxtpartnumber().getText();
        String partname = pMdetail.getTxtpartname().getText();
        String location = pMdetail.getTxtlocation().getText();
        String oh = pMdetail.getTxtoh().getText();
        String landedcost = pMdetail.getTxtlandedcost().getText();
        String price  = pMdetail.getTxtpricelist().getText();
        
        String insert = "INSERT INTO part (partnumber,partname,location,oh,"
            + "landedcost, price) VALUES (?,?,?,?,?,?);" ;
        
        ValidasiMaster valid = new ValidasiMaster();
        valid.validasi_part(partnumber);
        
        if (valid.xpart == "") {
            if (partnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Number masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtpartnumber().requestFocus();
            } else if (partname.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Name masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtpartname().requestFocus();
            } else if (location.equals("")) {
                JOptionPane.showMessageDialog(null, "Location masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtlocation().requestFocus();
            } else if (oh.equals("")) {
                JOptionPane.showMessageDialog(null, "On Hand masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtoh().requestFocus();
            } else if (landedcost.equals("")) {
                JOptionPane.showMessageDialog(null, "Landed Cost masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtlandedcost().requestFocus();
            } else if (price.equals("")) {
                JOptionPane.showMessageDialog(null, "Price List masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtpricelist().requestFocus();
            } else {
                try {
                    connection = Koneksi.sambung();
                    PreparedStatement statement = null;
                    statement = connection.prepareStatement(insert);
                    statement.setString(1, partnumber);
                    statement.setString(2, partname);
                    statement.setString(3, location);
                    statement.setInt(4, Integer.valueOf(oh));
                    statement.setInt(5, Integer.valueOf(landedcost));
                    statement.setInt(6, Integer.valueOf(price));
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    JOptionPane.showMessageDialog(null,"Data berhasil Disimpan",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                 //clear();
                 //isitabel();
            }    
        } else {
            JOptionPane.showMessageDialog(null,"Data Sudah Ada",
                 "Informasi",JOptionPane.WARNING_MESSAGE);
            pMdetail.setTxtpartnumber("");
            pMdetail.getTxtpartnumber().requestFocus();
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

        pCard = new javax.swing.JPanel();
        pMaster = new com.devproject.form.pMaster();
        pMain = new com.devproject.form.pMain();
        pMdetail = new com.devproject.form.pMdetail();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pCard.setLayout(new java.awt.CardLayout());
        pCard.add(pMaster, "card3");
        pCard.add(pMain, "card2");
        pCard.add(pMdetail, "card4");

        getContentPane().add(pCard, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1366, 728));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pCard;
    private com.devproject.form.pMain pMain;
    private com.devproject.form.pMaster pMaster;
    private com.devproject.form.pMdetail pMdetail;
    // End of variables declaration//GEN-END:variables
}
