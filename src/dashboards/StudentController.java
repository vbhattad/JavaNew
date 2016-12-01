/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import LoginAndSignup.SignupLoginController;
import Quiz.QuizTest;
import DAO.QuestionDAOImpl;
import DAO.ResultDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private BarChart studentBarChart;

    @FXML
    private PieChart studentPieChart;
    
    
    @FXML
    private LineChart<Number, Number> studentLineChart = new LineChart(new NumberAxis(), new NumberAxis()) ;

    @FXML
    private Pane studentPieChartPane;

    @FXML
    private Pane studentLineChartPane;

    LocalDate toDateString;
    LocalDate fromDateString;

    @FXML
    private void fromDate(ActionEvent event) {
        fromDateString = fromDatePicker.getValue();

        toDatePicker.setDisable(false);

    }

    @FXML
    private void toDate(ActionEvent event) {
        
        toDateString = toDatePicker.getValue();

        ResultDAOImpl result = new ResultDAOImpl();
     
        int[] passFail = result.getNoPassandFall(fromDateString, toDateString);
        studentPieChart.getData().clear();
        studentLineChart.getData().clear();
        studentPieChart.setTitle("Result of all the tests taken over the chosen period");
        studentLineChart.setTitle("Scores of all the tests taken over the chosen period");
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Pass", passFail[0]),
                        new PieChart.Data("Fail", passFail[1]));

        studentPieChart.setData(pieChartData);
        studentPieChartPane.getChildren().clear();
        studentPieChartPane.getChildren().add(studentPieChart);
        
   
       
        ArrayList<Integer> data = new ArrayList<>();
        data = result.getLineSeries(fromDateString, toDateString);
        XYChart.Series series1 = new XYChart.Series();
   
         studentLineChart.getXAxis().setLabel("Number of Tests");
        int x = 0;
        for (Integer i : data) {
            x++;
            series1.getData().add(new XYChart.Data(Integer.toString(x), i));
        }
      
        
        studentLineChart.getData().addAll(series1);

    }

    @FXML
    private void studentSaveAsPDF(ActionEvent event) {

    }

}
