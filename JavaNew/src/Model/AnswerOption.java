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
public class AnswerOption {

    private String optionDesc;
    private boolean isCorrect;

   public AnswerOption(String optionDesc, boolean isCorrect) {
        this.optionDesc = optionDesc;
        this.isCorrect = isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }
}
