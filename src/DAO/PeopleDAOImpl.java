/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.People;
import java.sql.Statement;

/**
 *
 * @author katha
 */
public class PeopleDAOImpl extends DAOJDBCImpl {

    @Override
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "create table PEOPLE("
                    + "andrewid varchar(255) NOT NULL,"
                    + "firstname varchar(255) NOT NULL,"
                    + "lastname varchar(255) NOT NULL,"
                    + "password varchar(255) NOT NULL,"
                    + "membertype char NOT NULL,"
                    + "coursetype char NOT NULL,"
                    + "CONSTRAINT PEOPLE_PK PRIMARY KEY(andrewid))";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    public People authenticateUser(String username, String password) {
        People user = new People();
        // Insert Query
        return user;
    }
    
    public void addUser(People user){
    // addd user to DB
    }

    @Override
    public void dropTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "drop table PEOPLE";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (Exception se) {
            System.out.println("Exception closing Connection: " + se.toString());
        }
    }

}
