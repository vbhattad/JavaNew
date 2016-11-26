/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentQuizTest;

import Model.Question;
import Model.Result;
import java.util.ArrayList;

/**
 *
 * @author katha
 */
public class QuizTest {

    String difficultyLevelOptions[] = {"Easy", "Medium", "Hard", "Mixed"}; // options for dropdown
    int totalQuestionOptions[] = {10, 15, 20}; // options for dropdown
    String difficultyLevel;
    int totalQuestions;
    ArrayList<Question> allQuestions;
    ArrayList<Question> answeredQuestions;
    Result quizResult = new Result();
    int counter = 0;

    public void setAllQuestions(int totalQuestions, String difficultyLevel) {
        DAO.QuestionDAOImpl dao = new DAO.QuestionDAOImpl();
        allQuestions = dao.getQuestions(totalQuestions, difficultyLevel);
    }

    public Question getNextQuestion() {
        return allQuestions.get(counter++);
    }

    public Question getPrevQuestion() {
        return allQuestions.get(counter--);
    }

}
