/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.People;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author katha
 */
public class PeopleDAOImpl extends DAOJDBCImpl {

    @Override
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "create table quizapp.PEOPLE("
                    + "andrewid varchar(255) NOT NULL,"
                    + "firstname varchar(255) NOT NULL,"
                    + "lastname varchar(255) NOT NULL,"
                    + "password varchar(255) NOT NULL,"
                    + "membertype char NOT NULL,"
                    + "coursetype char ,"
                    + "CONSTRAINT PEOPLE_PK PRIMARY KEY(andrewid))";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    public People authenticateUser(String username, String password) {
        People user = new People();
        System.out.println("Here!" + username);
        String query = "SELECT * FROM PEOPLE WHERE andrewid = '" + username + "'";
        try {
            //Connection connect = DriverManager.getConnection(url); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
            ResultSet rs = statement.executeQuery(query);
            
            if(rs.next()){
                System.out.println(rs.getString("password"));
                if(password.equals(rs.getString("password"))){
                    
                    user.setAndrewId(rs.getString("andrewid"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    user.setPassword(rs.getString("password"));
                    user.setMemberType(rs.getString("membertype").charAt(0));
                    System.out.println(user.getAndrewId());
                }
            }
        }catch(SQLException e){
            System.out.println("SQL Exception" + e);
        }
        
        return user;
    }
    
    public boolean addUser(People user) {
    // add user to DB
    // return true if success; false if user already present
        
        String andrewID = user.getAndrewId();
        System.out.println("Here!" + andrewID);
        String query = "SELECT * from quizapp.PEOPLE where andrewid = '" + andrewID + "'";
        try {
            //Connection connect = DriverManager.getConnection(url); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
            ResultSet rs = statement.executeQuery(query);
           if(rs.next()){
               return false;
           }else{
               String firstname = user.getFirstName();
               String lastname = user.getLastName();
                String password = user.getPassword();
                char memberType = user.getMemberType();
                char courseType = user.getCourseType();
                String insertQuery = "INSERT INTO PEOPLE VALUES ('" + andrewID + "','" + firstname + "','" + lastname + "','" + password
                        + "','" + memberType + "','" + courseType + "')";
                statement.execute(insertQuery);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e);
        }
    return true;
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
    
    public Boolean removeUser(String andrewID){
        String query = "DELETE FROM PEOPLE WHERE andrewid='" + andrewID + "'";
        String test = "SELECT * FROM PEOPLE WHERE andrewid='" + andrewID + "'";
        
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(test);
            if(rs.next()){
            stmt.execute(query);
            return true;
            }
        }catch(SQLException e){
            System.out.println("SQL EXCEPTION : " + e);
        }
        
        return false;
        
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
