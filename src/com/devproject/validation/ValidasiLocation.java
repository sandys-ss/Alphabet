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
public class ValidasiLocation {
    
    Connection connection;
    public String xzone="";
    public String xdescription="";

    public ValidasiLocation() {
        
        connection = Koneksi.sambung();
        
    }
    
    public void validasi_zone (String xdescript) {
        
        try {
            String cari = "SELECT * From location WHERE location = '" +xdescript+"' ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(cari);
            
            if (rs.next()) {
                xzone = rs.getString(2);
                xdescription = rs.getString(3);
            } 
            
        } catch (SQLException ex) {
                System.out.println("SQLException: " +ex.getMessage());
                System.out.println("SQLState: " +ex.getSQLState());
                System.out.println("VendorError: " +ex.getErrorCode());
        }
    }   
}
