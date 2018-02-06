/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.form;

import com.devproject.conn.Koneksi;
import com.devproject.validation.ValidasiLocation;
import com.devproject.validation.ValidasiMaster;
import com.devproject.validation.ValidasiZone;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
        pCard.add(pMdetailnew, "panelmdetailnew");
        pCard.add(pLocation, "panellocation");
        pCard.add(pSupplier, "panelsupplier");
        
        CardLayout c1 = (CardLayout)pCard.getLayout();
        c1.show(pCard, "panelutama"); 
        
        setGlassPane(pGlass);
        
    }
    
    private void aksi_tombol () {
        //pMain Action
        pMain.addActionListenerMaster(new Aksi_menuUtama_master());
        pMain.addActionListenerLocation(new Aksi_menuUtama_location());
        pMain.addActionListenerSupplier(new Aksi_menuUtama_supplier());
        
        //pMaster Action
        pMaster.addActionListenerMasterImport(new Aksi_masterimport());
        pMaster.addActionListenerMasterback(new Aksi_masterback());
        pMaster.addActionListenerMasterTabel(new Aksi_mastertabel());
        pMaster.addActionListenerMastersearch(new Aksi_mastersearch());
        pMaster.addKeyListenerMasterSearch(new Aksi_mastersearchkey());
        pMaster.addActionListenerMasterrefresh(new Aksi_masterrefresh());
        pMaster.addActionListenerMasternew(new Aksi_masternew());
        pMaster.addActionListenerMasterexport(new Aksi_masterexport());
        
        //pMdetail Action
        pMdetail.addActionListenerMdetailback(new Aksi_mdetailback());
        pMdetail.addActionListenerMdetailupdate(new Aksi_mdetailupdate());
        pMdetail.addActionListenerMdetaildelete(new Aksi_mdetaildelete());
        
        //pMdetailnew
        pMdetailnew.addActionListenerMdetailsave(new Aksi_mdetailsave());
        pMdetailnew.addActionListenerMdetailback(new Aksi_mdetailnewback());
        
        //pLocation
        pLocation.addActionListenerLocationback(new Aksi_locationback());
        pLocation.addActionListenerLocationinsert(new Aksi_locationinsert());
        pLocation.addActionListenerZoneTabel(new Aksi_zonetabel());
        pLocation.addActionListenerLocationdelete(new Aksi_locationdelete());
        pLocation.addActionListenerLocationsearch(new Aksi_locationsearch());
        pLocation.addKeyListenerLocationSearch(new Aksi_locationsearchkey());
        pLocation.addActionListenerLocationimport(new Aksi_locationimport());
        pLocation.addActionListenerLocationclear(new Aksi_locationclear());
        pLocation.addActionListenerLocationexport(new Aksi_locationexport());
        
        pLocation.addActionListenerLocationinsert2(new Aksi_locationinsert2());
        pLocation.addActionListenerZoneTabel2(new Aksi_zonetabel2());
        pLocation.addActionListenerLocationdelete2(new Aksi_locationdelete2());
        pLocation.addActionListenerLocationsearch2(new Aksi_locationsearch2());
        pLocation.addKeyListenerLocationSearch2(new Aksi_locationsearchkey2());
        pLocation.addActionListenerLocationimport2(new Aksi_locationimport2());
        pLocation.addActionListenerLocationclear2(new Aksi_locationclear2());
        pLocation.addActionListenerLocationimport(new Aksi_locationimport());
        pLocation.addActionListenerLocationexport2(new Aksi_locationexport2());
        
        //pSupplier
        pSupplier.addActionListenerSupplierback(new Aksi_supplierback());
        pSupplier.addActionListenerSuppliertabel(new Aksi_suppliertabel());
        pSupplier.addActionListenerSupplierclear(new Aksi_supplierclear());
        pSupplier.addActionListenerSupplierrefresh(new Aksi_supplierrefresh());
        
    }
    
    class Aksi_menuUtama_master implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmaster");
            isitabelpart();
        }
    }
    
    class Aksi_menuUtama_location implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panellocation");
            isitabelzone();
            isitabellocation();
        }
    }
    
    class Aksi_menuUtama_supplier implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelsupplier");
            isitabelsupplier();
        }
    }
    
    class Aksi_masterimport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            getGlassPane().setVisible(true);
            importexcelpart();
            getGlassPane().setVisible(false);
        }
    }
    
     class Aksi_masterexport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            getGlassPane().setVisible(true);
            exportpart();
            getGlassPane().setVisible(false);
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
            isitabelpart();
        }
    }
    
    class Aksi_mastersearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            searchresultpart();
        }
    }
    
    class Aksi_mastersearchkey implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
           if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
               searchresultpart();
           } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
               isitabelpart();
           } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
               isitabelpart();
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
            isitabelpart();
            pMaster.setTxtsearch("");
        }
        
    }
    
    class Aksi_masternew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmdetailnew");
            isicombomdetailnew();
            
        }
        
    }
    
    class Aksi_mdetailsave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            insertpart();
        }
    }
    
    class Aksi_mdetailnewback implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            clear_mdetailnew();
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmaster");
        }
    }
    
    class Aksi_mdetailupdate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updatepart();
        }
        
    }
    
    class Aksi_mdetaildelete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deletepart();
        }
        
    }
    
    class Aksi_locationback implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelutama");
            clearzone();
            clearzone2();
        }
        
    }
    
    class Aksi_locationinsert implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           insertzone();
        }
        
    }
    
    class Aksi_zonetabel implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getClickCount();
            if (x == 2) {
               int xrow = pLocation.getTabelzone().getSelectedRow();
               String zone = (String) pLocation.getTabelzone().getValueAt(xrow, 0);
               String description = (String) pLocation.getTabelzone().getValueAt(xrow, 1);
               
               pLocation.setTxtzone(zone);
               pLocation.setTxtdescription(description);
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
    
    class Aksi_locationdelete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           deletelocation();
        }
        
    }
    
    class Aksi_locationsearchkey implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
           if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
               searchresultzone();
           } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
               isitabelzone();
           } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
               isitabelzone();
               pLocation.setTxtsearch("");
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    class Aksi_locationsearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           searchresultzone();
        }
        
    }
    
    class Aksi_locationinsert2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           insertzone2();
        }
        
    }
    
    class Aksi_zonetabel2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getClickCount();
            if (x == 2) {
               int xrow = pLocation.getTabellocation().getSelectedRow();
               String zone = (String) pLocation.getTabellocation().getValueAt(xrow, 0);
               String description = (String) pLocation.getTabellocation().getValueAt(xrow, 1);
               
               pLocation.setTxtzone2(zone);
               pLocation.setTxtdescription2(description);
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
    
    class Aksi_locationdelete2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           deletelocation2();
        }
        
    }
    
    class Aksi_locationsearchkey2 implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
           if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
               searchresultzone2();
           } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
               isitabellocation();
           } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
               isitabellocation();
               pLocation.setTxtsearch2("");
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    class Aksi_locationsearch2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           searchresultzone2();
        }
        
    }
    
    class Aksi_locationimport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getGlassPane().setVisible(true);
            importexcelzone();
            getGlassPane().setVisible(false);
            isitabelzone();
        }
        
    }
    
    class Aksi_locationclear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearzone();
        }
        
    }
    
    class Aksi_locationimport2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getGlassPane().setVisible(true);
            importexcelzone2();
            getGlassPane().setVisible(false);
            isitabellocation();
        }
        
    }
    
    class Aksi_locationclear2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearzone2();
        }
        
    }
    
    class Aksi_locationexport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getGlassPane().setVisible(true);
            exportzone();
            getGlassPane().setVisible(false);
            isitabelzone();
        }
        
    }
    
    class Aksi_locationexport2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getGlassPane().setVisible(true);
            exportzone2();
            getGlassPane().setVisible(false);
            isitabellocation();
        }
        
    }
    
    class Aksi_supplierback implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelutama");
            clearsupplier();
        }
        
    }
    
    class Aksi_suppliertabel implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getClickCount();
            if (x == 2) {
                isifieldsupplier();
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
    
    class Aksi_supplierclear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearsupplier();
        }
        
    }
    
    class Aksi_supplierrefresh implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isitabelsupplier();
        }
        
    }

      
    // Method
    private static ArrayList readExcelFilePart(String fileName) {
        ArrayList cellArrayLisstHolder = new ArrayList();
        try {
            /**
             * Creating Input Stream*
             */
            FileInputStream myInput = new FileInputStream(fileName);

            /**
             * Create a workbook using the File System*
             */
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

            /**
             * Get the first sheet from workbook*
             */
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /**
             * We now need something to iterate through the cells.*
             */
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                ArrayList cellStoreArrayList = new ArrayList();
                while (cellIter.hasNext()) {
                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    cellStoreArrayList.add(myCell);
                }
                cellArrayLisstHolder.add(cellStoreArrayList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellArrayLisstHolder;
    }
    
    private void importexcelpart() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            String fileName = selectedFile.getAbsolutePath();

            ArrayList dataHolder = readExcelFilePart(fileName);

            try {
                String query = "insert into part (partnumber, partname, zone, location, oh,"
                        + "landedcost, price) values (?, ?, ?, ?, ?, ?, ?)";
                connection = Koneksi.sambung();
                PreparedStatement statement = null;
                statement = connection.prepareStatement(query);
                int count = 0;

                ArrayList cellStoreArrayList = null;

                //insert into database
                for (int i = 1; i < dataHolder.size(); i++) {
                    cellStoreArrayList = (ArrayList) dataHolder.get(i);
                    try {
                        statement.setString(1, ((XSSFCell) cellStoreArrayList.get(0)).toString());
                        statement.setString(2, ((XSSFCell) cellStoreArrayList.get(1)).toString());
                        statement.setString(3, ((XSSFCell) cellStoreArrayList.get(2)).toString());
                        statement.setString(4, ((XSSFCell) cellStoreArrayList.get(3)).toString());
                        statement.setString(5, ((XSSFCell) cellStoreArrayList.get(4)).toString());
                        statement.setString(6, ((XSSFCell) cellStoreArrayList.get(5)).toString());
                        statement.setString(7, ((XSSFCell) cellStoreArrayList.get(6)).toString());
                        statement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //System.out.print("Import Sukses !");
                JOptionPane.showMessageDialog(null, "Data berhasil Disimpan",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                isitabelpart();
            } catch (SQLException ex) {
                //System.out.print("Export gagal");
                JOptionPane.showMessageDialog(null, ex.getErrorCode(),
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void importexcelzone() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            String fileName = selectedFile.getAbsolutePath();

            ArrayList dataHolder = readExcelFilePart(fileName);

            try {
                String query = "insert into zone (zone, description) values (?, ?)";
                connection = Koneksi.sambung();
                PreparedStatement statement = null;
                statement = connection.prepareStatement(query);
                int count = 0;

                ArrayList cellStoreArrayList = null;

                //insert into database
                for (int i = 1; i < dataHolder.size(); i++) {
                    cellStoreArrayList = (ArrayList) dataHolder.get(i);
                    try {
                        statement.setString(1, ((XSSFCell) cellStoreArrayList.get(0)).toString());
                        statement.setString(2, ((XSSFCell) cellStoreArrayList.get(1)).toString());
                        statement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //System.out.print("Import Sukses !");
                JOptionPane.showMessageDialog(null, "Data berhasil Disimpan",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                isitabelpart();
            } catch (SQLException ex) {
                //System.out.print("Export gagal");
                JOptionPane.showMessageDialog(null, ex.getErrorCode(),
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void importexcelzone2() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            String fileName = selectedFile.getAbsolutePath();

            ArrayList dataHolder = readExcelFilePart(fileName);

            try {
                String query = "insert into location (zone, location) values (?, ?)";
                connection = Koneksi.sambung();
                PreparedStatement statement = null;
                statement = connection.prepareStatement(query);
                int count = 0;

                ArrayList cellStoreArrayList = null;

                //insert into database
                for (int i = 1; i < dataHolder.size(); i++) {
                    cellStoreArrayList = (ArrayList) dataHolder.get(i);
                    try {
                        statement.setString(1, ((XSSFCell) cellStoreArrayList.get(0)).toString());
                        statement.setString(2, ((XSSFCell) cellStoreArrayList.get(1)).toString());
                        statement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //System.out.print("Import Sukses !");
                JOptionPane.showMessageDialog(null, "Data berhasil Disimpan",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                isitabelpart();
            } catch (SQLException ex) {
                //System.out.print("Export gagal");
                JOptionPane.showMessageDialog(null, ex.getErrorCode(),
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void exportpart () {
        final String sql = "SELECT * FROM part ORDER BY partnumber;";

        PreparedStatement statement = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Part");
            XSSFRow rowhead = sheet.createRow((short) 0);

            XSSFCellStyle myStyle = workbook.createCellStyle();
            myStyle.setFillForegroundColor(new XSSFColor(Color.BLACK));
            myStyle.setFillBackgroundColor(new XSSFColor(Color.WHITE));

            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            myStyle.setFont(font);

            Cell c0 = rowhead.createCell(0);
            c0.setCellValue("Part Number");
            c0.setCellStyle(myStyle);

            Cell c1 = rowhead.createCell(1);
            c1.setCellValue("Part Name");
            c1.setCellStyle(myStyle);
            
            Cell c2 = rowhead.createCell(2);
            c2.setCellValue("Zone");
            c2.setCellStyle(myStyle);

            Cell c3 = rowhead.createCell(3);
            c3.setCellValue("Location");
            c3.setCellStyle(myStyle);

            Cell c4 = rowhead.createCell(4);
            c4.setCellValue("On Hand");
            c4.setCellStyle(myStyle);

            Cell c5 = rowhead.createCell(5);
            c5.setCellValue("Landend Cost");
            c5.setCellStyle(myStyle);

            Cell c6 = rowhead.createCell(6);
            c6.setCellValue("Price list");
            c6.setCellStyle(myStyle);

            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int i = rs.getRow();
                XSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(rs.getString("partnumber"));
                row.createCell(1).setCellValue(rs.getString("partname"));
                row.createCell(2).setCellValue(rs.getString("zone"));
                row.createCell(3).setCellValue(rs.getString("location"));
                row.createCell(4).setCellValue(rs.getString("oh"));
                row.createCell(5).setCellValue(rs.getString("landedcost"));
                row.createCell(6).setCellValue(rs.getString("price"));
                i++;
            }
            JFileChooser pilih = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Excel File", "xlsx");
            pilih.setFileFilter(filter);
            int value = pilih.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                File file = new File(pilih.getSelectedFile() + ".xlsx");
                String yemi = file.getPath();
                FileOutputStream fileOut = new FileOutputStream(yemi);
                workbook.write(fileOut);
                fileOut.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Export",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void exportzone () {
        final String sql = "SELECT * FROM zone ORDER BY zone;";

        PreparedStatement statement = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Warehouse Zone");
            XSSFRow rowhead = sheet.createRow((short) 0);

            XSSFCellStyle myStyle = workbook.createCellStyle();
            myStyle.setFillForegroundColor(new XSSFColor(Color.BLACK));
            myStyle.setFillBackgroundColor(new XSSFColor(Color.WHITE));

            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            myStyle.setFont(font);

            Cell c0 = rowhead.createCell(0);
            c0.setCellValue("Zone");
            c0.setCellStyle(myStyle);

            Cell c1 = rowhead.createCell(1);
            c1.setCellValue("Description");
            c1.setCellStyle(myStyle);

            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int i = rs.getRow();
                XSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(rs.getString("zone"));
                row.createCell(1).setCellValue(rs.getString("description"));
                i++;
            }
            JFileChooser pilih = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Excel File", "xlsx");
            pilih.setFileFilter(filter);
            int value = pilih.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                File file = new File(pilih.getSelectedFile() + ".xlsx");
                String yemi = file.getPath();
                FileOutputStream fileOut = new FileOutputStream(yemi);
                workbook.write(fileOut);
                fileOut.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Export",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void exportzone2 () {
        final String sql = "SELECT * FROM location ORDER BY zone;";

        PreparedStatement statement = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Warehouse Location");
            XSSFRow rowhead = sheet.createRow((short) 0);

            XSSFCellStyle myStyle = workbook.createCellStyle();
            myStyle.setFillForegroundColor(new XSSFColor(Color.BLACK));
            myStyle.setFillBackgroundColor(new XSSFColor(Color.WHITE));

            XSSFFont font = workbook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            myStyle.setFont(font);

            Cell c0 = rowhead.createCell(0);
            c0.setCellValue("Zone");
            c0.setCellStyle(myStyle);

            Cell c1 = rowhead.createCell(1);
            c1.setCellValue("Location");
            c1.setCellStyle(myStyle);

            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int i = rs.getRow();
                XSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(rs.getString("zone"));
                row.createCell(1).setCellValue(rs.getString("location"));
                i++;
            }
            JFileChooser pilih = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Excel File", "xlsx");
            pilih.setFileFilter(filter);
            int value = pilih.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                File file = new File(pilih.getSelectedFile() + ".xlsx");
                String yemi = file.getPath();
                FileOutputStream fileOut = new FileOutputStream(yemi);
                workbook.write(fileOut);
                fileOut.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Export",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void isipartdetail () {
        int xrow = pMaster.getTabelMaster().getSelectedRow();
        String partnumber = (String) pMaster.getTabelMaster().getValueAt(xrow, 0);
        String partname = (String) pMaster.getTabelMaster().getValueAt(xrow, 1);
        String zone = (String) pMaster.getTabelMaster().getValueAt(xrow, 2);
        String location = (String) pMaster.getTabelMaster().getValueAt(xrow, 3);
        String onhand = (String) pMaster.getTabelMaster().getValueAt(xrow, 4);
        String landedcost = (String) pMaster.getTabelMaster().getValueAt(xrow, 5);
        String pricelist = (String) pMaster.getTabelMaster().getValueAt(xrow, 6);
        
        pMdetail.setTxtpartnumber(partnumber);
        pMdetail.setTxtpartname(partname);
        pMdetail.setTxtoh(onhand);
        pMdetail.setTxtlandedcost(landedcost);
        pMdetail.setTxtpricelist(pricelist);
        
        String sql = "SELECT description FROM zone ORDER BY description";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                pMdetail.setCmbzone(rs.getString("description"), zone);
            }
               rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        String sql2 = "SELECT location FROM location ORDER BY location";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql2);
            while (rs.next()) {
                pMdetail.setCmblocation(rs.getString("location"), location);
            }
               rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private void searchresultpart () {
        Object header [] = {"Part Number", "Part Name", "Zone", "Location",
                            "On Hand", "Landed Cost", "Price List"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pMaster.getTabelMaster().setModel(model);
        
        String sql = "SELECT * From part WHERE CONCAT (id, partnumber, partname, zone, location,"
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
                String kolom7 = rs.getString(8);
                
                String kolom [] = {kolom1, kolom2, kolom3, kolom4,
                                    kolom5, kolom6, kolom7};
                
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void isitabelpart () {
        Object header [] = {"Part Number", "Part Name", "Zone", "Location", "On Hand",
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
                String kolom7 = rs.getString(8);
                
                String kolom [] = {kolom1, kolom2, kolom3, kolom4,
                                    kolom5, kolom6, kolom7};  
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
    }
    
    private void insertpart () {
        String partnumber = pMdetailnew.getTxtpartnumber().getText();
        String partname = pMdetailnew.getTxtpartname().getText();
        String zone = pMdetailnew.getCmbzone().getSelectedItem().toString();
        String location = pMdetailnew.getCmblocation().getSelectedItem().toString();
        String oh = pMdetailnew.getTxtoh().getText();
        String landedcost = pMdetailnew.getTxtlandedcost().getText();
        String price  = pMdetailnew.getTxtpricelist().getText();
        
        String insert = "INSERT INTO part (partnumber,partname,zone,location,oh,"
            + "landedcost, price) VALUES (?,?,?,?,?,?,?);" ;
        
        ValidasiMaster valid = new ValidasiMaster();
        valid.validasi_part(partnumber);
        
        if (valid.xpart == "") {
            if (partnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Number masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getTxtpartnumber().requestFocus();
            } else if (partname.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Name masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getTxtpartname().requestFocus();
            } else if (zone.equals("")) {
                JOptionPane.showMessageDialog(null, "Zone masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getCmbzone().requestFocus();
            } else if (location.equals("")) {
                JOptionPane.showMessageDialog(null, "Location masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getCmblocation().requestFocus();
            } else if (oh.equals("")) {
                JOptionPane.showMessageDialog(null, "On Hand masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getTxtoh().requestFocus();
            } else if (landedcost.equals("")) {
                JOptionPane.showMessageDialog(null, "Landed Cost masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getTxtlandedcost().requestFocus();
            } else if (price.equals("")) {
                JOptionPane.showMessageDialog(null, "Price List masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetailnew.getTxtpricelist().requestFocus();
            } else {
                try {
                    connection = Koneksi.sambung();
                    PreparedStatement statement = null;
                    statement = connection.prepareStatement(insert);
                    statement.setString(1, partnumber);
                    statement.setString(2, partname);
                    statement.setString(3, zone);
                    statement.setString(4, location);
                    statement.setInt(5, Integer.valueOf(oh));
                    statement.setInt(6, Integer.valueOf(landedcost));
                    statement.setInt(7, Integer.valueOf(price));
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    JOptionPane.showMessageDialog(null,"Data berhasil Disimpan",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                clear_mdetailnew();
                
                CardLayout c1 = (CardLayout) pCard.getLayout();
                c1.show(pCard, "panelmaster");
                isitabelpart();
            }    
        } else {
            JOptionPane.showMessageDialog(null,"Data Sudah Ada",
                 "Informasi",JOptionPane.WARNING_MESSAGE);
            pMdetailnew.setTxtpartnumber("");
            pMdetailnew.getTxtpartnumber().requestFocus();
        }
    }
    
    private void clear_mdetailnew () {
        pMdetailnew.setTxtpartnumber("");
        pMdetailnew.setTxtpartname("");
        pMdetailnew.setCmbzone("");
        pMdetailnew.setCmblocation("");
        pMdetailnew.setTxtoh("");
        pMdetailnew.setTxtlandedcost("");
        pMdetailnew.setTxtpricelist("");
    }
    
    private void updatepart () {  
        String partnumber = pMdetail.getTxtpartnumber().getText();
        String partname = pMdetail.getTxtpartname().getText();
        String zone = pMdetail.getCmbzone().getSelectedItem().toString();
        String location = pMdetail.getCmblocation().getSelectedItem().toString();
        String oh = pMdetail.getTxtoh().getText();
        String landedcost = pMdetail.getTxtlandedcost().getText();
        String price  = pMdetail.getTxtpricelist().getText();
        
        String sql = "UPDATE part SET partname = '"+partname+"', zone = '"+zone+"', "
                + "location= '"+location+"', oh = '"+oh+"',"
                + "landedcost = '"+landedcost+"', price = '"+price+"' "
                + "WHERE partnumber = '"+partnumber+"' ";
        
        if (partnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Number masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtpartnumber().requestFocus();
            } else if (partname.equals("")) {
                JOptionPane.showMessageDialog(null, "Part Name masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getTxtpartname().requestFocus();
            } else if (zone.equals("")) {
                JOptionPane.showMessageDialog(null, "Zone masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getCmbzone().requestFocus();
            } else if (location.equals("")) {
                JOptionPane.showMessageDialog(null, "Location masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pMdetail.getCmblocation().requestFocus();
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
                    statement = connection.prepareStatement(sql);
                    Statement stm = connection.createStatement();
                    stm.executeUpdate(sql);
                    stm.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    JOptionPane.showMessageDialog(null,"Data berhasil Di Update",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                    clear_mdetail();
                    
                    CardLayout c1 = (CardLayout) pCard.getLayout();
                    c1.show(pCard, "panelmaster");
                    isitabelpart();
            }
    }
    
    private void clear_mdetail () {
        pMdetail.setTxtpartnumber("");
        pMdetail.setTxtpartname("");
        pMdetail.setTxtoh("");
        pMdetail.setCmbzone("", "");
        pMdetail.setCmblocation("", "");
        pMdetail.setTxtlandedcost("");
        pMdetail.setTxtpricelist("");
    }
    
    private void deletepart () {
        String partnumber = pMdetail.getTxtpartnumber().getText();
        String sql = "DELETE FROM part WHERE partnumber = '"+partnumber+"' ";
        
        if (partnumber.equals("")) {
            JOptionPane.showMessageDialog(null, "Part Number masih kosong", "Informasi",
                    JOptionPane.WARNING_MESSAGE);
            pMdetail.getTxtpartnumber().requestFocus();
        } else {
            int pilih = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus Data ini ?",
                "Warning", JOptionPane.YES_NO_OPTION);
        
        if (pilih == JOptionPane.YES_OPTION) {
           try {
               connection = Koneksi.sambung();
               PreparedStatement statement = null;
               statement = connection.prepareStatement(sql);
               Statement stm = connection.createStatement();
               stm.execute(sql);
               stm.close();
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
           clear_mdetail();
            
            CardLayout c1 = (CardLayout) pCard.getLayout();
            c1.show(pCard, "panelmaster");
            isitabelpart();
        }
        }
    }
    
    private void isitabelzone () {
        Object header [] = {"Zone", "Description"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pLocation.getTabelzone().setModel(model);
        
        String sql = "SELECT * FROM zone ORDER BY description";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String kolom1 = rs.getString(2);
                String kolom2 = rs.getString(3);
                
                String kolom [] = {kolom1, kolom2};  
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
    }
    
    private void isitabellocation () {
        Object header [] = {"Zone", "Location"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pLocation.getTabellocation().setModel(model);
        
        String sql = "SELECT * FROM location ORDER BY location";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String kolom1 = rs.getString(2);
                String kolom2 = rs.getString(3);
                
                String kolom [] = {kolom1, kolom2};  
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
    }
    
     private void insertzone () {
        String zone = pLocation.getTxtzone().getText();
        String description = pLocation.getTxtdescription().getText();
        
        String insert = "INSERT INTO zone (zone, description) VALUES (?,?);" ;
        
        ValidasiZone valid = new ValidasiZone();
        valid.validasi_zone(description);
        
        if (valid.xdescription == "") {
            if (zone.equals("")) {
                JOptionPane.showMessageDialog(null, "Zone masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pLocation.getTxtzone().requestFocus();
            } else if (description.equals("")) {
                JOptionPane.showMessageDialog(null, "Description masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pLocation.getTxtdescription().requestFocus();
            } else {
                try {
                    connection = Koneksi.sambung();
                    PreparedStatement statement = null;
                    statement = connection.prepareStatement(insert);
                    statement.setString(1, zone);
                    statement.setString(2, description);
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    JOptionPane.showMessageDialog(null,"Data berhasil Disimpan",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                clear_mdetailnew();
                
                pLocation.setTxtzone("");
                pLocation.setTxtdescription("");
                isitabelzone();
            }    
        } else {
            JOptionPane.showMessageDialog(null,"Data Sudah Ada",
                 "Informasi",JOptionPane.WARNING_MESSAGE);
            pLocation.setTxtzone("");
            pLocation.setTxtdescription("");
            pLocation.getTxtzone().requestFocus();
        }
    }
     
    private void insertzone2 () {
        String zone = pLocation.getTxtzone2().getText();
        String description = pLocation.getTxtdescription2().getText();
        
        String insert = "INSERT INTO location (zone, location) VALUES (?,?);" ;
        
        ValidasiLocation valid = new ValidasiLocation();
        valid.validasi_zone(description);
        
        if (valid.xdescription == "") {
            if (zone.equals("")) {
                JOptionPane.showMessageDialog(null, "Zone masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pLocation.getTxtzone2().requestFocus();
            } else if (description.equals("")) {
                JOptionPane.showMessageDialog(null, "Location masih Kosong !", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            pLocation.getTxtdescription2().requestFocus();
            } else {
                try {
                    connection = Koneksi.sambung();
                    PreparedStatement statement = null;
                    statement = connection.prepareStatement(insert);
                    statement.setString(1, zone);
                    statement.setString(2, description);
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    JOptionPane.showMessageDialog(null,"Data berhasil Disimpan",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                clear_mdetailnew();
                
                pLocation.setTxtzone2("");
                pLocation.setTxtdescription2("");
                isitabellocation();
            }    
        } else {
            JOptionPane.showMessageDialog(null,"Data Sudah Ada",
                 "Informasi",JOptionPane.WARNING_MESSAGE);
            pLocation.setTxtzone2("");
            pLocation.setTxtdescription2("");
            pLocation.getTxtzone2().requestFocus();
        }
    }
     
    private void deletelocation () {
        String zone = pLocation.getTxtzone().getText();
        String description = pLocation.getTxtdescription().getText();
        String sql = "DELETE FROM zone WHERE zone = '"+zone+"' AND "
                + "description = '"+description+"' ";
        
        if (zone.equals("")|| description.equals("")) {
            JOptionPane.showMessageDialog(null, "Zone atau Description masih kosong", "Informasi",
                    JOptionPane.WARNING_MESSAGE);
            pLocation.getTxtzone().requestFocus();
        } else {
            int pilih = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus Data ini ?",
                "Warning", JOptionPane.YES_NO_OPTION);
        
        if (pilih == JOptionPane.YES_OPTION) {
           try {
               connection = Koneksi.sambung();
               PreparedStatement statement = null;
               statement = connection.prepareStatement(sql);
               Statement stm = connection.createStatement();
               stm.execute(sql);
               stm.close();
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
            pLocation.setTxtzone("");
            pLocation.setTxtdescription("");
            pLocation.getTxtzone().requestFocus();
            isitabelzone();
        }
        }
    } 
    
    private void deletelocation2 () {
        String zone = pLocation.getTxtzone2().getText();
        String description = pLocation.getTxtdescription2().getText();
        String sql = "DELETE FROM location WHERE zone = '"+zone+"' AND "
                + "location = '"+description+"' ";
        
        if (zone.equals("")|| description.equals("")) {
            JOptionPane.showMessageDialog(null, "Zone atau Location masih kosong", "Informasi",
                    JOptionPane.WARNING_MESSAGE);
            pLocation.getTxtzone2().requestFocus();
        } else {
            int pilih = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus Data ini ?",
                "Warning", JOptionPane.YES_NO_OPTION);
        
        if (pilih == JOptionPane.YES_OPTION) {
           try {
               connection = Koneksi.sambung();
               PreparedStatement statement = null;
               statement = connection.prepareStatement(sql);
               Statement stm = connection.createStatement();
               stm.execute(sql);
               stm.close();
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
            pLocation.setTxtzone2("");
            pLocation.setTxtdescription2("");
            pLocation.getTxtzone2().requestFocus();
            isitabellocation();
        }
        }
    } 
    
    private void searchresultzone () {
        Object header [] = {"Zone", "Description"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pLocation.getTabelzone().setModel(model);
        
        String sql = "SELECT * From zone WHERE CONCAT (id, zone, description)"
                    + "LIKE '%"+pLocation.getTxtsearch().getText()+"%' ";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String kolom1 = rs.getString(2);
                String kolom2 = rs.getString(3);
                
                String kolom [] = {kolom1, kolom2};
                
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void searchresultzone2 () {
        Object header [] = {"Zone", "Location"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pLocation.getTabellocation().setModel(model);
        
        String sql = "SELECT * From location WHERE CONCAT (id, zone, location)"
                    + "LIKE '%"+pLocation.getTxtsearch2().getText()+"%' ";
        
        try {
            connection = Koneksi.sambung();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String kolom1 = rs.getString(2);
                String kolom2 = rs.getString(3);
                
                String kolom [] = {kolom1, kolom2};
                
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void clearzone () {
        pLocation.setTxtzone("");
        pLocation.setTxtdescription("");
        pLocation.setTxtsearch("");
        isitabelzone();
    }
    
    private void clearzone2 () {
        pLocation.setTxtzone2("");
        pLocation.setTxtdescription2("");
        pLocation.setTxtsearch2("");
        isitabellocation();
    }
    
    private void isicombomdetailnew () {
         String sql = "SELECT description FROM zone ORDER BY description";

            try {
                connection = Koneksi.sambung();
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    pMdetailnew.setCmbzone(rs.getString("description"));
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String sql2 = "SELECT location FROM location ORDER BY location";

            try {
                connection = Koneksi.sambung();
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(sql2);
                while (rs.next()) {
                    pMdetailnew.setCmblocation(rs.getString("location"));
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    
    private void isitabelsupplier () {
        Object header [] = {"Supplier Name", "Address", "Contact 1", "Contact 2", 
                            "Contact 3"};
   
        DefaultTableModel model = new DefaultTableModel(null, header) {
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        pSupplier.getTabelsupplier().setModel(model);
        
        String sql = "SELECT * FROM supplier ORDER BY id";
        
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
                
                String kolom [] = {kolom1, kolom2, kolom3, kolom4,
                                    kolom5};  
                model.addRow(kolom);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
    }
    
    private void isifieldsupplier () {
        int xrow = pSupplier.getTabelsupplier().getSelectedRow();
        String suppliername = (String) pSupplier.getTabelsupplier().getValueAt(xrow, 0);
        String address = (String) pSupplier.getTabelsupplier().getValueAt(xrow, 1);
        String contact1 = (String) pSupplier.getTabelsupplier().getValueAt(xrow, 2);
        String contact2 = (String) pSupplier.getTabelsupplier().getValueAt(xrow, 3);
        String contact3 = (String) pSupplier.getTabelsupplier().getValueAt(xrow, 4);
        
        pSupplier.setTxtsuppliername(suppliername);
        pSupplier.setTxtaddress(address);
        pSupplier.setTxtcontact1(contact1);
        pSupplier.setTxtcontact2(contact2);
        pSupplier.setTxtcontact3(contact3);
    }
    
    private void clearsupplier () {
        pSupplier.setTxtsuppliername("");
        pSupplier.setTxtaddress("");
        pSupplier.setTxtcontact1("");
        pSupplier.setTxtcontact2("");
        pSupplier.setTxtcontact3("");
        pSupplier.setTxtsearch("");
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
        pMdetailnew = new com.devproject.form.pMdetailnew();
        pGlass = new com.devproject.form.pGlass();
        pLocation = new com.devproject.form.pLocation();
        pSupplier = new com.devproject.form.pSupplier();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pCard.setLayout(new java.awt.CardLayout());
        pCard.add(pMaster, "card3");
        pCard.add(pMain, "card2");
        pCard.add(pMdetail, "card4");
        pCard.add(pMdetailnew, "card5");
        pCard.add(pGlass, "card6");
        pCard.add(pLocation, "card7");
        pCard.add(pSupplier, "card8");

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
    private com.devproject.form.pGlass pGlass;
    private com.devproject.form.pLocation pLocation;
    private com.devproject.form.pMain pMain;
    private com.devproject.form.pMaster pMaster;
    private com.devproject.form.pMdetail pMdetail;
    private com.devproject.form.pMdetailnew pMdetailnew;
    private com.devproject.form.pSupplier pSupplier;
    // End of variables declaration//GEN-END:variables
}
