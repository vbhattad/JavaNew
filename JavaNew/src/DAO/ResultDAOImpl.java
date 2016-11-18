/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Statement;

/**
 *
 * @author katha
 */
public class ResultDAOImpl extends DAOJDBCImpl {

    @Override
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "create table Result("
                    + "andrewid varchar(255) NOT NULL PRIMARY KEY,"
                    + "CorrectEasy int,"
                    + "CorrectMedium int,"
                    + "CorrectHard int,"
                    + "TotalEasy int,"
                    + "TotalMedium int,"
                    + "TotalHard int,"
                    + "DT Timestamp NOT NULL,"
                    + "Grade char NOT NULL)";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "drop table Result";
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
