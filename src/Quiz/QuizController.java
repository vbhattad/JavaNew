/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import DAO.ResultDAOImpl;
import LoginAndSignup.SignupLoginController;
import Model.AnswerOption;
import Model.Question;
import Model.Result;
import dashboards.StudentController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darshanmohan
 */
public class QuizController implements Initializable {

    @FXML
    private Button bSubmit;

    @FXML
    private Button bPrevious;

    @FXML
    private Button bNext;

    @FXML
    private Label lQuestion;

    @FXML
    private Label lTime;

    @FXML
    private Pane pOptions;

    @FXML
    private VBox vbox;

    @FXML
    private PieChart chart;

    @FXML
    private Label lblGrade;

    @FXML
    private Label lblGradeRes;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblScoreRes;

    QuizTest test;

    private String difficultyLevel;
    private ArrayList<Question> allQuestions;
    private int questionNumber = 0;
    private int totalNoOfQuestions;

    private HashMap<Integer, ArrayList<CheckBox>> mapCheckBoxes = new HashMap<>();
    private HashMap<Integer, ArrayList<RadioButton>> mapRadioButtons = new HashMap<>();
    private HashMap<Integer, String> mapFIB = new HashMap<>();
    private final Result quizResult = new Result();

    private int secUnit;
    private int secTens;
    private int minUnit;
    private int minTens;
    private int timeCounter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        difficultyLevel = QuizTest.difficultyLevel;
        allQuestions = QuizTest.allQuestions;
        totalNoOfQuestions = QuizTest.totalNoOfQuestions;

        bPrevious.setOnAction(e -> {
            questionNumber--;
            displayQuestion(allQuestions.get(questionNumber));
        });

        bNext.setOnAction(e -> {
            questionNumber++;
            displayQuestion(allQuestions.get(questionNumber));
        });

        bSubmit.setOnAction(e -> {
            endQuiz();
        });

        start();
    }

    private void start() {
        vbox = new VBox(25);
        setTimer();
        displayQuestion(allQuestions.get(questionNumber));

    }

    private void displayQuestion(Question que) {
        enableDisableButton();
        vbox.getChildren().clear();
        pOptions.getChildren().clear();

        lQuestion.setText((questionNumber + 1) + ". " + que.getQuestionDesc());

        RadioButton radio;
        ArrayList<CheckBox> MACheckBoxes;
        ArrayList<RadioButton> RadioButtons;
        ToggleGroup togglegroup;

        switch (que.getQuestionType()) {

            case "MC":
                if (que.getIsAnswered()) {
                    RadioButtons = mapRadioButtons.get(questionNumber);
                    RadioButtons.stream().forEach(rb -> vbox.getChildren().add(rb));
                    pOptions.getChildren().add(vbox);
                } else {
                    RadioButtons = new ArrayList<>();
                    togglegroup = new ToggleGroup();
                    for (AnswerOption option : que.getOptionList()) {
                        radio = new RadioButton(option.getOptionDesc());
                        radio.setToggleGroup(togglegroup);
                        radio.setUserData(option.getOptionDesc());
                        radio.selectedProperty().addListener((obs, oldval, newval) -> {
                            if (newval) {
                                computeRadiobtnAns();
                            }
                        });
                        RadioButtons.add(radio);
                        vbox.getChildren().add(radio);
                    }
                    pOptions.getChildren().add(vbox);
                    mapRadioButtons.put(questionNumber, RadioButtons);
                }
                break;

            case "MA":
                if (que.getIsAnswered()) {
                    MACheckBoxes = mapCheckBoxes.get(questionNumber);
                    MACheckBoxes.stream().forEach(cb -> vbox.getChildren().add(cb));
                    pOptions.getChildren().add(vbox);
                } else {
                    CheckBox check;
                    MACheckBoxes = new ArrayList<>();
                    for (AnswerOption option : que.getOptionList()) {
                        check = new CheckBox(option.getOptionDesc());
                        check.setAllowIndeterminate(false);
                        check.setSelected(false);
                        check.setUserData(option.getOptionDesc());
                        check.selectedProperty().addListener((obs, oldval, newval) -> {
                            computeCheckBoxAns();
                        });
                        MACheckBoxes.add(check);
                        vbox.getChildren().add(check);
                    }
                    mapCheckBoxes.put(questionNumber, MACheckBoxes);
                    pOptions.getChildren().add(vbox);
                }
                break;

            case "TF":
                if (que.getIsAnswered()) {
                    RadioButtons = mapRadioButtons.get(questionNumber);
                    RadioButtons.stream().forEach(rb -> vbox.getChildren().add(rb));
                    pOptions.getChildren().add(vbox);
                } else {
                    RadioButtons = new ArrayList<>();
                    togglegroup = new ToggleGroup();

                    radio = new RadioButton("True");
                    radio.setToggleGroup(togglegroup);
                    radio.setUserData("True");
                    radio.selectedProperty().addListener((obs, oldval, newval) -> {
                        if (newval) {
                            computeTF();
                        }
                    });
                    RadioButtons.add(radio);
                    vbox.getChildren().add(radio);
                    radio = new RadioButton("False");
                    radio.setToggleGroup(togglegroup);
                    radio.setUserData("False");
                    radio.selectedProperty().addListener((obs, oldval, newval) -> {
                        if (newval) {
                            computeTF();
                        }
                    });
                    RadioButtons.add(radio);
                    vbox.getChildren().add(radio);
                    pOptions.getChildren().add(vbox);
                    mapRadioButtons.put(questionNumber, RadioButtons);
                }
                break;

            case "FIB":

                TextField tfAnswer = new TextField();
                tfAnswer.setPrefSize(150, 20);
                
                tfAnswer.setAlignment(Pos.CENTER);
                Label ans = new Label("Answer: ");
                HBox ltf = new HBox(20);
                ltf.getChildren().addAll(ans,tfAnswer);
                if (que.getIsAnswered()) {
                    tfAnswer.setText(mapFIB.get(questionNumber));
                }

                tfAnswer.textProperty().addListener((observable, oldValue, newValue) -> {
                    computeTextAns(newValue);
                });
                vbox.getChildren().add(ltf);
                pOptions.getChildren().add(vbox);
                break;

            default:
                break;

        }
    }

    private void computeTextAns(String inputAnswer) {
        mapFIB.put(questionNumber, inputAnswer);
        Question que = allQuestions.get(questionNumber);
        que.setIsAnswered(true);
        String correctAns = que.getOptionList().get(0).getOptionDesc();
        if (correctAns.equalsIgnoreCase(inputAnswer)) {
            allQuestions.get(questionNumber).setIscorrect(true);
        } else {
            allQuestions.get(questionNumber).setIscorrect(false);
        }
    }

    private void computeCheckBoxAns() {
        Question que = allQuestions.get(questionNumber);
        que.setIsAnswered(true);
        int totalcorrect = 0;
        List<AnswerOption> correctOptions = que.getOptionList().stream().filter(option -> option.getIsCorrect() == true).collect(Collectors.toList());
        for (CheckBox cb : mapCheckBoxes.get(questionNumber)) {
            if (cb.isSelected()) {
                boolean isOptionmatch = correctOptions.stream().anyMatch(option -> option.getOptionDesc() == cb.getUserData());
                if (isOptionmatch) {
                    totalcorrect++;
                } else {
                    totalcorrect--;
                }
            }
        }
        if (correctOptions.size() == totalcorrect) {
            // All correct checkboxes are ticked
            allQuestions.get(questionNumber).setIscorrect(true);
        } else {
            allQuestions.get(questionNumber).setIscorrect(false);
        }
    }

    private void computeRadiobtnAns() {
        Question que = allQuestions.get(questionNumber);
        que.setIsAnswered(true);
        String correctAns = que.getOptionList().stream().filter(option -> option.getIsCorrect() == true).findFirst().get().getOptionDesc();
        mapRadioButtons.get(questionNumber).stream().filter((rb) -> (rb.isSelected())).forEach((rb) -> {
            if (correctAns.equalsIgnoreCase(rb.getUserData().toString())) {
                allQuestions.get(questionNumber).setIscorrect(true);
                System.out.println("Radio correct");
            } else {
                allQuestions.get(questionNumber).setIscorrect(false);
                System.out.println("Radio InCorrect");
            }
        });
    }

    private void computeTF() {
        Question que = allQuestions.get(questionNumber);
        que.setIsAnswered(true);
        String correctAns = que.getOptionList().stream().findFirst().get().getOptionDesc();
        mapRadioButtons.get(questionNumber).stream().filter((rb) -> (rb.isSelected())).forEach((rb) -> {
            if (correctAns.equalsIgnoreCase(rb.getUserData().toString())) {
                allQuestions.get(questionNumber).setIscorrect(true);
                System.out.println("Radio correct");
            } else {
                allQuestions.get(questionNumber).setIscorrect(false);
                System.out.println("Radio InCorrect");
            }
        });
    }

    private void enableDisableButton() {

        if (questionNumber <= 0) {
            bPrevious.setDisable(true);
            questionNumber = 0;
        } else if (questionNumber >= allQuestions.size() - 1) {
            bNext.setDisable(true);
            questionNumber = allQuestions.size() - 1;
        } else {
            bPrevious.setDisable(false);
            bNext.setDisable(false);
        }
    }

    private void calculateGrade() {
        int totalCorrectAns = quizResult.getNoOfCorrectEasy() + quizResult.getNoOfCorrectMedium() + quizResult.getNoOfCorrectHard();
        quizResult.setScore(totalCorrectAns);
        int totalQues = allQuestions.size();
        double percentage = totalCorrectAns / (double) totalQues;
        if (percentage < 0.5) {
            quizResult.setGrade(0);
        } else {
            quizResult.setGrade(1);
        }
    }

    private void setTimer() {
        secUnit = 9;
        secTens = 5;
        minUnit = (totalNoOfQuestions % 10) - 1;
        minTens = totalNoOfQuestions / 10;
        timeCounter = totalNoOfQuestions * 60;

        Timer otimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (timeCounter == 0) {
                        otimer.cancel();
                        lTime.setText("Time's up !");
                        endQuiz();
                    } else {
                        if (secUnit == -1) {
                            secUnit = 9;
                            secTens--;
                        }
                        if (secTens == -1) {
                            secTens = 5;
                            minUnit--;
                        }
                        if (minUnit == -1) {
                            minUnit = 9;
                            minTens--;
                        }
                        lTime.setText(minTens + "" + minUnit + ":" + secTens + "" + secUnit);
                        secUnit--;
                        timeCounter--;
                    }

                });
            }
        };
        otimer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void endQuiz() {
        int totalQuestions = allQuestions.size();
        int totalCorrect = 0;
        quizResult.setDifficultyLevel(difficultyLevel);
        quizResult.setAndrewId(SignupLoginController.user.getAndrewId());
        switch (difficultyLevel.toLowerCase()) {
            case "easy":
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect()).count();
                quizResult.setNoOfCorrectEasy(totalCorrect);
                quizResult.setTotalNoOfEasy(totalQuestions);
                break;
            case "medium":
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect()).count();
                quizResult.setNoOfCorrectMedium(totalCorrect);
                quizResult.setTotalNoOfMedium(totalQuestions);
                break;
            case "hard":
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect()).count();
                quizResult.setNoOfCorrectHard(totalCorrect);
                quizResult.setTotalNoOfHard(totalQuestions);
                break;
            case "mixed":
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect() && "E".equals(que.getDifficulty())).count();
                totalQuestions = (int) allQuestions.stream().filter(que -> "E".equals(que.getDifficulty())).count();
                quizResult.setNoOfCorrectEasy(totalCorrect);
                quizResult.setTotalNoOfEasy(totalQuestions);
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect() && "M".equals(que.getDifficulty())).count();
                totalQuestions = (int) allQuestions.stream().filter(que -> "M".equals(que.getDifficulty())).count();
                quizResult.setNoOfCorrectMedium(totalCorrect);
                quizResult.setTotalNoOfMedium(totalQuestions);
                totalCorrect = (int) allQuestions.stream().filter(que -> que.getIscorrect() && "H".equals(que.getDifficulty())).count();
                totalQuestions = (int) allQuestions.stream().filter(que -> "H".equals(que.getDifficulty())).count();
                quizResult.setNoOfCorrectHard(totalCorrect);
                quizResult.setTotalNoOfHard(totalQuestions);
                break;
            default:
                break;
        }
        calculateGrade();
        ResultDAOImpl res = new ResultDAOImpl();
        res.insertResults(quizResult);
        showResult();
    }

    private void showResult() {
        StudentController.quizPane.getChildren().remove(bNext);
        StudentController.quizPane.getChildren().remove(bPrevious);
        StudentController.quizPane.getChildren().remove(lQuestion);
        StudentController.quizPane.getChildren().remove(lTime);
        StudentController.quizPane.getChildren().remove(pOptions);
        changeBtnFunction();
        showGrade();
        showChart();
    }

    public void showGrade() {
        lblGrade = new Label("Grade :");
        lblGrade.setLayoutX(395.0);
        lblGrade.setLayoutY(91.0);
        lblGrade.setStyle("-fx-text-fill: orange;");
        lblGradeRes = new Label();
        if (quizResult.getGrade() == 1) {
            lblGradeRes.setText("Pass");
            lblGradeRes.setStyle("-fx-text-fill: green;");
        } else {
            lblGradeRes.setText("Fail");
            lblGradeRes.setStyle("-fx-text-fill: red;");
        }
        lblGradeRes.setLayoutX(443.0);
        lblGradeRes.setLayoutY(91.0);
        lblScore = new Label("Score :");
        lblScore.setLayoutX(709);
        lblScore.setLayoutY(91);
        lblScore.setStyle("-fx-text-fill: orange;");
        lblScoreRes = new Label(quizResult.getScore() + "/" + totalNoOfQuestions);
        lblScoreRes.setLayoutX(755);
        lblScoreRes.setLayoutY(91);
        lblScoreRes.setStyle("-fx-text-fill: blue;");
        StudentController.quizPane.getChildren().addAll(lblGrade, lblGradeRes, lblScore, lblScoreRes);

    }

    public void showChart() {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Right", quizResult.getScore()),
                        new PieChart.Data("Wrong", (totalNoOfQuestions - quizResult.getScore())));
        chart.setData(pieChartData);
        chart.setTitle("Your Quiz Result");
        chart.setLayoutX(377);
        chart.setLayoutY(139);
        chart.setPrefSize(441, 282);
        chart.setAnimated(true);
    }

    private void changeBtnFunction() {
        bSubmit.setText("Home");
        bSubmit.setStyle("-fx-background-color: green;");
        bSubmit.setOnAction(e -> {
            movetoStudentDashboard();
        });
    }

    private void movetoStudentDashboard() {

        try {
            AnchorPane page;
            Stage stage = (Stage) bSubmit.getScene().getWindow();
            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Student.fxml"));
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
