/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusan
 */
public class DbConnectFactory {

    private static DbConnectFactory instance;
    private Connection connection;

    private DbConnectFactory() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("url");
                String user = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("user");
                String password = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("password");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static DbConnectFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectFactory();
        }
        return instance;
    }

    public Connection getConnection() {
          try {
            if (connection == null || connection.isClosed()) {
                String url = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("url");
                String user = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("user");
                String password = konfiguracija.Konfiguracija.getKonfiguracija().getProperty("password");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
