/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.AnswerOption;
import Model.Question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    
    public void addQuestions(String filepath) {
        String insertQuestions = "LOAD DATA INFILE '" + filepath + "'"
                + "INTO TABLE questions"
                + "FIELDS TERMINATED BY ','"
                + "LINES TERMINATED BY '\\n'"
                + "IGNORE 1 ROWS";
        
        try {
           // Connection connect = DriverManager.getConnection(url,username, password); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
            statement.execute(insertQuestions);
            
        }catch(SQLException e){
            System.out.println("SQL EXCEPTION : " +  e);
        }
    }

    public ArrayList<Question> getQuestions(int totalQuestions, String difficultyLevel) {
        ArrayList<Question> questions = new ArrayList<>();
       ArrayList<ResultSet> rows = new ArrayList<>();
       ArrayList<AnswerOption> options = new ArrayList<>();
       AnswerOption answerOption;
       Question question;
       try {
            //Connection connect = DriverManager.getConnection(url,username, password); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
       switch(difficultyLevel){
            case "easy":{
                String query = "SELECT * FROM QUIZAPP.Questions where difficulty='easy'";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                for(int i =0; i<totalQuestions; i++){
                  rows.add(rs);
                  rs.next();
                }
                Collections.shuffle(rows);
                for(int i=0; i<rows.size(); i++){
                    String questionType = rows.get(i).getString("questiontype");
                    String questionDifficulty = rows.get(i).getString("questiontype");
                    String questionDESC = rows.get(i).getString("questiondesc");
                    for(int j = 1;j<5;j++){
                        String optionNumber = "option" + Integer.toString(j);
                        String optionIsCorrect = "iscorrect" + Integer.toString(j);
                        answerOption = new AnswerOption(rows.get(i).getString(optionNumber), Boolean.parseBoolean(rows.get(i).getString(optionIsCorrect)));
                        options.add(answerOption);
                    }
                    
                    question = new Question(questionType,questionDifficulty,questionDESC,options);
                }
               break;
           }
             case "medium":{
                String query = "SELECT * FROM QUIZAPP.Questions where difficulty='medium'";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                for(int i =0; i<totalQuestions; i++){
                  rows.add(rs);
                  rs.next();
                }
                Collections.shuffle(rows);
                for(int i=0; i<rows.size(); i++){
                    String questionType = rows.get(i).getString("questiontype");
                    String questionDifficulty = rows.get(i).getString("questiontype");
                    String questionDESC = rows.get(i).getString("questiondesc");
                    for(int j = 1;j<5;j++){
                        String optionNumber = "option" + Integer.toString(j);
                        String optionIsCorrect = "iscorrect" + Integer.toString(j);
                        answerOption = new AnswerOption(rows.get(i).getString(optionNumber), Boolean.parseBoolean(rows.get(i).getString(optionIsCorrect)));
                        options.add(answerOption);
                    }
                    
                    question = new Question(questionType,questionDifficulty,questionDESC,options);
                }
               break;
           }
          
              case "hard":{
                String query = "SELECT * FROM QUIZAPP.Questions where difficulty='hard'";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                for(int i =0; i<totalQuestions; i++){
                  rows.add(rs);
                  rs.next();
                }
                Collections.shuffle(rows);
                for(int i=0; i<rows.size(); i++){
                    String questionType = rows.get(i).getString("questiontype");
                    String questionDifficulty = rows.get(i).getString("questiontype");
                    String questionDESC = rows.get(i).getString("questiondesc");
                    for(int j = 1;j<5;j++){
                        String optionNumber = "option" + Integer.toString(j);
                        String optionIsCorrect = "iscorrect" + Integer.toString(j);
                        answerOption = new AnswerOption(rows.get(i).getString(optionNumber), Boolean.parseBoolean(rows.get(i).getString(optionIsCorrect)));
                        options.add(answerOption);
                    }
                    
                    question = new Question(questionType,questionDifficulty,questionDESC,options);
                }
               break;
           }
               case "mixed":{
                String query = "SELECT * FROM QUIZAPP.Questions";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                for(int i =0; i<totalQuestions; i++){
                  rows.add(rs);
                  rs.next();
                }
                Collections.shuffle(rows);
                for(int i=0; i<rows.size(); i++){
                    String questionType = rows.get(i).getString("questiontype");
                    String questionDifficulty = rows.get(i).getString("questiontype");
                    String questionDESC = rows.get(i).getString("questiondesc");
                    for(int j = 1;j<5;j++){
                        String optionNumber = "option" + Integer.toString(j);
                        String optionIsCorrect = "iscorrect" + Integer.toString(j);
                        answerOption = new AnswerOption(rows.get(i).getString(optionNumber), Boolean.parseBoolean(rows.get(i).getString(optionIsCorrect)));
                        options.add(answerOption);
                    }
                    
                    question = new Question(questionType,questionDifficulty,questionDESC,options);
                }
               break;
           }
               
               
               
       }
       }catch(SQLException e){
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
