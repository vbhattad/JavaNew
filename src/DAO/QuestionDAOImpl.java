/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.AnswerOption;
import Model.Question;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
                    + "difficulty varchar(10) NOT NULL,"
                    + "questiondesc varchar(255) NOT NULL,"
                    + "option1 varchar(255)),"
                    + "iscorrect1 varchar(5) ,"
                    + "option2 varchar(255)),"
                    + "iscorrect2 varchar(5) ,"
                    + "option3 varchar(255)),"
                    + "iscorrect3 varchar(5) ,"
                    + "option4 varchar(255)),"
                    + "iscorrect4 varchar(5))";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }
    
    public void addQuestions(String filepath) throws FileNotFoundException, IOException {
        System.out.println("Hello");
           try(Statement stmt = con.createStatement()) {
             Reader in = new FileReader(filepath); //Read from the CSV
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
    

            System.out.println("Inside");
            for (CSVRecord record : records) {
                System.out.println("Inside1");
                String query = "Insert into Question values ('" + record.get(0) + "' ,'" 
                        + record.get(1) + "' ,'"
                        + record.get(2) + "' ,'" 
                        + record.get(3) + "' ,'" 
                        + record.get(4) + "' ,'" 
                        + record.get(5) + "' ,'" 
                        + record.get(6) + "' ,'" 
                        + record.get(7) + "' ,'" 
                        + record.get(8) + "' ,'" 
                        + record.get(9) + "' ,'" 
                        + record.get(10) + "')";;
                System.out.println(query);
               stmt.execute(query);
            }
            System.out.println("Records inserted successfully!");
           }catch(SQLException e){
               System.out.println("SQL Exception" + e);
           }
        
   
       
    
    }

    public ArrayList<Question> getQuestions(int totalQuestions, String difficultyLevel) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<ResultSet> rows = new ArrayList<>();
        ArrayList<AnswerOption> options = new ArrayList<>();
        AnswerOption answerOption;
        Question question;
        String query = "";
        try {
            //Connection connect = DriverManager.getConnection(url,username, password); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
            switch (difficultyLevel) {
                case "easy": {
                    query = "SELECT * FROM Question where difficulty='E'";
                    break;
                }
                case "medium": {
                    query = "SELECT * FROM Question where difficulty='M'";
                    break;
                }
                case "hard": {
                    query = "SELECT * FROM Question where difficulty='H'";
                    break;
                }
                case "mixed": {
                    query = "SELECT * FROM Question";
                    break;
                }
            }
            ResultSet rs = statement.executeQuery(query);
            for (int i = 0; i < totalQuestions; i++) {
                options = new ArrayList<>();
                rs.next();
                String questionType = rs.getString("questiontype");
                String questionDifficulty = rs.getString("difficulty");
                String questionDESC = rs.getString("questiondesc");
                for (int j = 1; j < 5; j++) {
                    String optionNumber = "option" + Integer.toString(j);
                    String optionIsCorrect = "iscorrect" + Integer.toString(j);
                    answerOption = new AnswerOption(rs.getString(optionNumber), "correct".equals(rs.getString(optionIsCorrect)));
                    options.add(answerOption);
                }
                question = new Question(questionType, questionDifficulty, questionDESC, options);
                questions.add(question);
            }
            Collections.shuffle(questions);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION" + e);
        }
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
