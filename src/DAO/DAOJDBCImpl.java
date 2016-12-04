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

    /**
     * Parent class for all the Database Connections.
     */
    public DAOJDBCImpl() {
        String url = "jdbc:mysql://java.cvxefrn7yc6n.us-west-2.rds.amazonaws.com:3306/quizapp"; //Hosted on AWS
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
