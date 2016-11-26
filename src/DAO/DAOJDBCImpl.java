/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author katha
 */
public abstract class DAOJDBCImpl implements ICommonDAO {

    Connection con = null;

    public DAOJDBCImpl() {
        String url = "jdbc:mysql://javaproject.cafk9dtwufvd.us-west-2.rds.amazonaws.com:3306/java_project";
        String username = "admin";
        String password = "admin123";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception se) {
            System.out.println("Error obtaining connection with the database: " + se.toString());
            System.exit(-1);
        }
    }

}
