/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.validation;

import com.devproject.conn.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SANDYS
 */
public class ValidasiMaster {
    
    Connection connection;
    public String xpart="";
    public String xnamapart="";
    public String xstock="";
    public String xhargabeli="";
    public String xhargajual="";
    public String xtype="";

    public ValidasiMaster() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_part (String xnopart) {
        
        try {
            String cari = "SELECT * From part WHERE partnumber = '" +xnopart+"' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xpart = rs.getString(2);
                xnamapart = rs.getString(3);
                xtype = rs.getString(4);
                xstock = rs.getString(5);
                xhargabeli = rs.getString(6);
                xhargajual = rs.getString(7);
            } 
            
        } catch (SQLException ex) {
                System.out.println("SQLException: " +ex.getMessage());
                System.out.println("SQLState: " +ex.getSQLState());
                System.out.println("VendorError: " +ex.getErrorCode());
        }
    }   
}
