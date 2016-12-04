/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * Getter and Setter for Result
 */
public class Result {

    private String andrewId;
    private int noOfCorrectEasy;
    private int noOfCorrectMedium;
    private int noOfCorrectHard;
    private int totalNoOfEasy;
    private int totalNoOfMedium;
    private int totalNoOfHard;
    private int grade;
    private int score;
    private String DifficultyLevel;
    private Date dateAndTime;

    public String getAndrewId() {
        return andrewId;
    }

    public void setAndrewId(String andrewId) {
        this.andrewId = andrewId;
    }

    public int getNoOfCorrectEasy() {
        return noOfCorrectEasy;
    }

    public void setNoOfCorrectEasy(int noOfCorrectEasy) {
        this.noOfCorrectEasy = noOfCorrectEasy;
    }

    public int getNoOfCorrectMedium() {
        return noOfCorrectMedium;
    }

    public void setNoOfCorrectMedium(int noOfCorrectMedium) {
        this.noOfCorrectMedium = noOfCorrectMedium;
    }

    public int getNoOfCorrectHard() {
        return noOfCorrectHard;
    }

    public void setNoOfCorrectHard(int noOfCorrectHard) {
        this.noOfCorrectHard = noOfCorrectHard;
    }

    public int getTotalNoOfEasy() {
        return totalNoOfEasy;
    }

    public void setTotalNoOfEasy(int totalNoOfEasy) {
        this.totalNoOfEasy = totalNoOfEasy;
    }

    public int getTotalNoOfMedium() {
        return totalNoOfMedium;
    }

    public void setTotalNoOfMedium(int totalNoOfMedium) {
        this.totalNoOfMedium = totalNoOfMedium;
    }

    public int getTotalNoOfHard() {
        return totalNoOfHard;
    }

    public void setTotalNoOfHard(int totalNoOfHard) {
        this.totalNoOfHard = totalNoOfHard;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDifficultyLevel() {
        return DifficultyLevel;
    }

    public void setDifficultyLevel(String DifficultyLevel) {
        this.DifficultyLevel = DifficultyLevel;
    }

}
