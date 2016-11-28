/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import StudentQuizTest.QuizTest;
import quizpage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class StudentController implements Initializable {
    
    QuizTest quiz = new QuizTest();
    
    @FXML
    private Button bStartQuiz;
    
    @FXML
    private Slider sNumberOfQuestions;
    
    @FXML
    private RadioButton rbEasy;
    
    @FXML
    private RadioButton rbMedium;
    
    @FXML
    private RadioButton rbHard;
    
    @FXML
    private RadioButton rbMixed;
    
    @FXML Label lLogout;
    
    @FXML
    private void logout() {
        Stage stage = (Stage) bStartQuiz.getScene().getWindow();
        AnchorPane page;
        try {
            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("javanew/FXMLDocument.fxml"));
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void startQuiz(ActionEvent event) {
        
        System.out.println("Number of questiosns: " + sNumberOfQuestions.getValue());
        
        System.out.println("\nEasy: " + rbEasy.isSelected());
        System.out.println("Medium: " + rbMedium.isSelected());
        System.out.println("Hard: " + rbHard.isSelected());
        System.out.println("Mixed: " + rbMixed.isSelected());
        
        Stage stage = (Stage) bStartQuiz.getScene().getWindow();
        try {
            quiz.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
