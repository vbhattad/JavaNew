/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author katha
 */
public class Question {

    public Question(String questionType, char difficulty, String questionDesc, String options) {
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.questionDesc = questionDesc;
        this.options = options;
    }

    private String questionType;
    private char difficulty;
    private String questionDesc;
    private char iscorrect;
    private String options;

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(char difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

  
    public char getIscorrect() {
        return iscorrect;
    }

    public void setIscorrect(char iscorrect) {
        this.iscorrect = iscorrect;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}

class Option{
String optionDesc;
boolean isCorrect;
}
