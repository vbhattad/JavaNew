/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import LoginAndSignup.SignupLoginController;
import Quiz.QuizTest;
import DAO.QuestionDAOImpl;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class StudentController implements Initializable {

    QuizTest quiz = new QuizTest();

    String choosenDiff = "easy";
    int totalQuestions = 10;
    int availableLimit;

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

    @FXML
    Label lLogout;

    @FXML
    Label lLogoutGraph;

    @FXML
    Label ltotalQuestions;

    @FXML
    ToggleGroup difficultyLevel;
    @FXML
    private Label lWarning;

    @FXML
    private Label welcome;
    
    @FXML 
    public static AnchorPane quizPane;

    @FXML
    private void logout() {
        Stage stage = (Stage) bStartQuiz.getScene().getWindow();
        AnchorPane page;
        try {
            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("QuizApp/HomePage.fxml"));
            Scene scene = new Scene(page);
            
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void startQuiz(ActionEvent event) {

        System.out.println("Number of questiosns: " + sNumberOfQuestions.getValue());
        Stage stage = (Stage) bStartQuiz.getScene().getWindow();
        
        try {
            quiz.setAllQuestions(totalQuestions, choosenDiff);
            //quiz.start(stage);
            quizPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Quiz/Quiz.fxml"));
            Scene scene = new Scene(quizPane);
            
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbEasy.setUserData("easy");
        rbEasy.setSelected(true);
        rbMedium.setUserData("medium");
        rbHard.setUserData("hard");
        rbMixed.setUserData("mixed");
        QuestionDAOImpl dao = new QuestionDAOImpl();
        availableLimit = dao.getTotalNumberOfQuestions(choosenDiff);

        sNumberOfQuestions.setMin(5);
        sNumberOfQuestions.setValue(5);
        sNumberOfQuestions.setMax(availableLimit);

        ltotalQuestions.setText(Integer.toString(5));

        sNumberOfQuestions.setShowTickLabels(true);
        sNumberOfQuestions.setShowTickMarks(true);
        sNumberOfQuestions.setMajorTickUnit(5);

        sNumberOfQuestions.valueProperty().addListener((ov, old_val, new_val) -> {
            totalQuestions = new_val.intValue();
            ltotalQuestions.setText(Integer.toString(totalQuestions));
        });
        //sNumberOfQuestions.setBlockIncrement(0.1f);

        difficultyLevel.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (difficultyLevel.getSelectedToggle() != null) {
                System.out.println("selected " + difficultyLevel.getSelectedToggle().getUserData());
                choosenDiff = difficultyLevel.getSelectedToggle().getUserData().toString();
                availableLimit = dao.getTotalNumberOfQuestions(choosenDiff);
                sNumberOfQuestions.setMax(availableLimit);
            }
        });

    }

}
