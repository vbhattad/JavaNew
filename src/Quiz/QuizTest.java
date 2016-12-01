/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import Model.Question;
import Model.AnswerOption;
import java.util.ArrayList;

/**
 *
 * @author katha
 */
public class QuizTest {

    public static String difficultyLevel;
    public static ArrayList<Question> allQuestions;
    public static int totalNoOfQuestions;

    public void setAllQuestions(int totalQuestions, String diffLevel) {
        difficultyLevel = diffLevel;
        totalNoOfQuestions = totalQuestions;
        DAO.QuestionDAOImpl dao = new DAO.QuestionDAOImpl();
        allQuestions = dao.getQuestions(totalQuestions, difficultyLevel);
    }

    ArrayList<Question> getquestions() {
        ArrayList<AnswerOption> optionlist = new ArrayList<>();
        ArrayList<Question> questionlist = new ArrayList<>();
        optionlist.add(new AnswerOption("option1", false));
        optionlist.add(new AnswerOption("option2", true));
        optionlist.add(new AnswerOption("option3", false));
        optionlist.add(new AnswerOption("option4", false));
        questionlist.add(new Question("MC", "H", "Que1", optionlist));
        optionlist = new ArrayList<>();
        optionlist.add(new AnswerOption("option1", true));
        optionlist.add(new AnswerOption("option2", true));
        optionlist.add(new AnswerOption("option3", false));
        questionlist.add(new Question("MA", "H", "Que2", optionlist));
        optionlist = new ArrayList<>();
        optionlist.add(new AnswerOption("option", true));
        questionlist.add(new Question("FIB", "H", "Que3", optionlist));
        optionlist = new ArrayList<>();
        optionlist.add(new AnswerOption("option1", true));
        optionlist.add(new AnswerOption("option2", false));
        questionlist.add(new Question("TF", "H", "Que4", optionlist));
        return questionlist;
    }

}
