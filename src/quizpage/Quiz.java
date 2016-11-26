/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizpage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class Quiz extends Application {
    
    static String[] questions = {"Q1", "Q2", "Q3", "Q4"};
    static String[] questionType = {"MCQ", "True/False", "Fill in the blanks", "MA"};

    static int questionNumber = 0;

    static Label question;
    static Label options;

    static ToggleGroup togglegroup;

    static HBox hbox = new HBox(50);
    static VBox vbox;
    static HBox hbButtons;

    static Button bNext = new Button("Next");
    static Button bPrevious = new Button("Previous");
    
    static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        
        question = new Label("What is the best object-oriented programming language in the world?\nExplain your answer with comparisons to other languages");
        question.setPadding(new Insets(50, 50, 50, 50));

        options = new Label("a. Swift. Because it's Apple. That's why.\nb. Java, man. Come on!\nc. C++. Classic, brah.\nd. None of the above. Lol");
        options.setPadding(new Insets(50, 50, 50, 50));

        togglegroup = new ToggleGroup();

        hbButtons = new HBox(75);
        
        Button bPrevious = new Button("Previous");
        bPrevious.setMinWidth(hbButtons.getPrefWidth());

        bPrevious.setOnAction(e -> {
            
            questionNumber--;
            options.setText(Integer.toString(questionNumber));
            
            // Clear any previous children of hbox if present
            
            
            typeBasedDisplay(questionType);

        });

        Button bNext = new Button("Next");
        bNext.setMinWidth(hbButtons.getPrefWidth());

        bNext.setOnAction(e -> {

            questionNumber++;
            options.setText(Integer.toString(questionNumber));
            
            
            typeBasedDisplay(questionType);
            
        });

        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.getChildren().addAll(bPrevious, bNext);

        typeBasedDisplay(questionType);

        vbox = new VBox(25);

        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(50, 50, 50, 50));

        vbox.getChildren().addAll(question, options);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(hbButtons);

        scene = new Scene(vbox, 1280, 768);
        
        stage.setScene(scene);

        stage.show();
        
    }
    
    static void enableDisableButton() {
    
        if (questionNumber <= 0) {
            bPrevious.setDisable(true);
            questionNumber = 0;
        }
        
        else if (questionNumber >= questionType.length - 1) {
            bNext.setDisable(true);
            questionNumber = questionType.length - 1;
        }
        
        else {
            
            bPrevious.setDisable(false);
            bNext.setDisable(false);
            
        }
    }
    
    static void typeBasedDisplay(String[] questionType) {
        
        enableDisableButton();
        
        hbox.getChildren().clear();
        hbox.setAlignment(Pos.CENTER);
        
        options.setText(Integer.toString(questionNumber));
        
        switch (questionType[questionNumber]) {

                case "MCQ":
                    
                    //question.setText("Question Description");
                    options.setText("a. Swift. Because it's Apple. That's why.\nb. Java, man. Come on!\nc. C++. Classic, brah.\nd. None of the above. Lol");
                    
                    RadioButton optionA = new RadioButton("a");
                    optionA.setToggleGroup(togglegroup);

                    RadioButton optionB = new RadioButton("b");
                    optionB.setToggleGroup(togglegroup);

                    RadioButton optionC = new RadioButton("c");
                    optionC.setToggleGroup(togglegroup);

                    RadioButton optionD = new RadioButton("d");
                    optionD.setToggleGroup(togglegroup);

                    hbox.getChildren().addAll(optionA, optionB, optionC, optionD);
                    
                    break;
                    
                case "MA":
                    
                    question.setText("Question Description");
                    options.setText("a. Swift. Because it's Apple. That's why.\nb. Java, man. Come on!\nc. C++. Classic, brah.\nd. None of the above. Lol");
                    
                    CheckBox checkA = new CheckBox("a");
                    checkA.setAllowIndeterminate(false);
                    checkA.setSelected(false);
                    
                    CheckBox checkB = new CheckBox("b");
                    checkB.setAllowIndeterminate(false);
                    checkB.setSelected(false);
                    
                    CheckBox checkC = new CheckBox("c");
                    checkC.setAllowIndeterminate(false);
                    checkC.setSelected(false);
                    
                    CheckBox checkD = new CheckBox("d");
                    checkD.setAllowIndeterminate(false);
                    checkD.setSelected(false);
                    
                    hbox.getChildren().addAll(checkA, checkB, checkC, checkD);
                    
                    break;    

                case "True/False":

                    question.setText("T/F");
                    options.setText("");

                    RadioButton tru = new RadioButton("True");
                    tru.setToggleGroup(togglegroup);

                    RadioButton fals = new RadioButton("False");
                    fals.setToggleGroup(togglegroup);

                    hbox.getChildren().addAll(tru, fals);
                    
                    break;

                case "Fill in the blanks":

                    question.setText("Fill in the blanks");
                    options.setText("");

                    Label lblAnswer = new Label("Answer:");
                    lblAnswer.setPadding(new Insets(0, 15, 40, 0));

                    TextField tfAnswer = new TextField();
                    tfAnswer.setPadding(new Insets(15, 15, 40, 0));

                    tfAnswer.setMinHeight(50);
                    tfAnswer.setMaxHeight(100);

                    tfAnswer.alignmentProperty();
                    hbox.getChildren().addAll(lblAnswer, tfAnswer);
                    
                    break;

                default:
                    
                    question.setText("Error.");
                    
                    break;

            }
        
    }
    
    
    
}
