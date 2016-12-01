/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import Model.Question;
import java.util.ArrayList;

/**
 *
 * This class helps in setting required variables for quiz
 */
public class QuizTest {

    public static String difficultyLevel;
    public static ArrayList<Question> allQuestions;
    public static int totalNoOfQuestions;

    /*
    * Sets the questions for Quiz from DB based on chosen settings
    */
    public void setAllQuestions(int totalQuestions, String diffLevel) {
        difficultyLevel = diffLevel;
        totalNoOfQuestions = totalQuestions;
        DAO.QuestionDAOImpl dao = new DAO.QuestionDAOImpl();
        allQuestions = dao.getQuestions(totalQuestions, difficultyLevel);
    }

}
