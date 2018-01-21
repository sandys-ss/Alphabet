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
    public String xpartname="";
    public String xlocation="";
    public String xoh="";
    public String xlandedcost="";
    public String xprice="";

    public ValidasiMaster() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_part (String xnopart) {
        
        try {
            String cari = "SELECT * From part WHERE CONCAT (id, partnumber, partname, location,"
                    + "oh, landedcost, price) LIKE '%'"+xnopart+"'%' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xpart = rs.getString(2);
                xpartname = rs.getString(3);
                xlocation = rs.getString(4);
                xoh = rs.getString(5);
                xlandedcost = rs.getString(6);
                xprice = rs.getString(7);
            } 
            
        } catch (SQLException ex) {
                System.out.println("SQLException: " +ex.getMessage());
                System.out.println("SQLState: " +ex.getSQLState());
                System.out.println("VendorError: " +ex.getErrorCode());
        }
    }   
}
