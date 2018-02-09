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
public class ValidasiReceiving {
    
    Connection connection;
    public String xreceivingno="";
    public String xdate="";
    public String xsupplier="";
    public String xpartnumber="";
    public String xpartname="";
    public String xqty="";

    public ValidasiReceiving() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_part (String xno) {
        
        try {
            String cari = "SELECT * From receiving WHERE receivingno = '" +xno+"' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xreceivingno = rs.getString(2);
                xdate = rs.getString(3);
                xsupplier = rs.getString(4);
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
