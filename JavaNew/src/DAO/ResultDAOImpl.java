/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
                    + "pass int NOT NULL)"
                    + "DiffLevel varchar(50)"
                    + "Score int";
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
    
    public ResultSet getRightandWrong(String id){
        ResultSet results = null;
    try (Statement stmt = con.createStatement()) {
            String query = "select numberOfRight,numberOfWrong from Result where andrewid="+ id;
            results = stmt.executeQuery(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
     return results;
    }
    
    public ResultSet getPassandFall(){
        ResultSet results = null;
    try (Statement stmt = con.createStatement()) {
            String query = "select pass from Result where DT>";
            results = stmt.executeQuery(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
     return results;
    }
    
    public ResultSet getNumOfTestDuringTime(){
        ResultSet results = null;
    try (Statement stmt = con.createStatement()) {
            String query = "select pass from Result where date>";
            results = stmt.executeQuery(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
     return results;
    }
    
    public ResultSet getAveScore(){
        ResultSet results = null;
        
    try (Statement stmt = con.createStatement()) {
            String query = "select CorrectEasy,CorrectMedium,CorrectHard from Result where DT>";
            results = stmt.executeQuery(query);
            int count = 0;
            while (results.next()) {
                                ++count;   
            }
        } catch (Exception se) {
            System.out.println(se.toString());
        }
     return results;
    }
    
    public ResultSet getScoreOverDiffLevel(){
        ResultSet results = null;
    try (Statement stmt = con.createStatement()) {
            String query = "select Score,DiffLevel from Result ";
            results = stmt.executeQuery(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
     return results;
    }
}
