/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devproject.conn;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;

/**
 *
 * @author SANDYS
 */
public class Koneksi {
    
    static Connection koneksi;
    
    public static Connection sambung () {
        if (koneksi == null ) {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("alphabet");
            data.setUser("root");
            //data.setPassword("SANDYS");
            data.setPassword("sandys");
            try {
                koneksi = (Connection) data.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return koneksi;
    }    
}
