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
public class ValidasiSupplier {
    
    Connection connection;
    public String xsupplierno="";
    public String xsuppliername="";
    public String xaddress="";
    public String xcontact1="";
    public String xcontact2="";
    public String xcontact3="";

    public ValidasiSupplier() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_part (String xsuppno) {
        
        try {
            String cari = "SELECT * From supplier WHERE suppliername = '" +xsuppno+"' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xsuppno= rs.getString(2);
                xsuppliername = rs.getString(3);
                xaddress = rs.getString(4);
                xcontact1 = rs.getString(5);
                xcontact2 = rs.getString(6);
                xcontact3= rs.getString(7);
            }
            
        } catch (SQLException ex) {
                System.out.println("SQLException: " +ex.getMessage());
                System.out.println("SQLState: " +ex.getSQLState());
                System.out.println("VendorError: " +ex.getErrorCode());
        }
    }   
}
