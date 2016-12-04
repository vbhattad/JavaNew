/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * Getter and setter for question
 */
public class Question {

    public Question(String questionType, String difficulty, String questionDesc, ArrayList<AnswerOption> optionList) {
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.questionDesc = questionDesc;
        this.optionList = optionList;
    }

    private String questionType;
    private String difficulty;
    private String questionDesc;
    private boolean iscorrect;
    private boolean isAnswered;
    private String options;
    private ArrayList<AnswerOption> optionList;
    
    public boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }
    

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

  
    public boolean getIscorrect() {
        return iscorrect;
    }

    public void setIscorrect(boolean iscorrect) {
        this.iscorrect = iscorrect;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }


    public ArrayList<AnswerOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<AnswerOption> optionList) {
        this.optionList = optionList;
    }
}


