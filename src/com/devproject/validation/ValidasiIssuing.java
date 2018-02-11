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
public class ValidasiIssuing {
    
    Connection connection;
    public String xissuingno="";
    public String xdate="";
    public String xcustomer="";
    public String xpartnumber="";
    public String xpartname="";
    public String xqty="";

    public ValidasiIssuing() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_part (String xno) {
        
        try {
            String cari = "SELECT * From issuing WHERE issuingno = '" +xno+"' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xissuingno = rs.getString(2);
                xdate = rs.getString(3);
                xcustomer = rs.getString(4);
                xpartnumber = rs.getString(5);
                xpartname = rs.getString(6);
                xqty = rs.getString(7);
            } 
            
        } catch (SQLException ex) {
                System.out.println("SQLException: " +ex.getMessage());
                System.out.println("SQLState: " +ex.getSQLState());
                System.out.println("VendorError: " +ex.getErrorCode());
        }
    }   
}
