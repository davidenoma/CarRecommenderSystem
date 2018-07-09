/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomatech.karim.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kolis
 */
public class KarimDatabase {

    private Connection con;

    Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/karin_motors", "root", ""));
        return getCon();
    }

    void disonnect() throws SQLException, ClassNotFoundException {
        if (getCon() != null) {
            getCon().close();
        }

    }

    /**
     * @return the con
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connection getCon() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/karin_motors", "root", ""));
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }
}
