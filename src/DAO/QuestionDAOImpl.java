/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Question;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author katha
 */
public class QuestionDAOImpl extends DAOJDBCImpl {

    @Override
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "create table Question("
                    + "questionno INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "questiontype varchar(10) NOT NULL,"
                    + "difficulty char NOT NULL,"
                    + "questiondesc varchar(255) NOT NULL,"
                    + "answer varchar(255) NOT NULL,"
                    + "iscorrect char NOT NULL,"
                    + "options varchar(255))";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }
    
    public void addQuestions(String filepath){
    
    }

    public ArrayList<Question> getQuestions(int totalQuestions, String difficultyLevel) {
        ArrayList<Question> questions = new ArrayList<>();
        // insert code to get questions from DB
        return questions;
    }

    @Override
    public void dropTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "drop table Question";
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
