/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class SignupLoginController implements Initializable {
    
    private Quiz quiz;
    
    @FXML
    private Label label;
    
    @FXML
    private Button button;
    
    @FXML
    private Button btLogin;
    
    @FXML
    private Button btSignup;
    
    @FXML
    private TextField tfUsername;
    
    @FXML
    private TextField tfAndrewID;
    
    @FXML
    private TextField tfFirstname;
    
    @FXML
    private TextField tfLastname;
    
    @FXML
    private PasswordField pfTypePassword;
    
    @FXML
    private PasswordField pfReTypePassword;
    
    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        Stage stage = (Stage) btLogin.getScene().getWindow();
        try {
            quiz.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quiz = new Quiz();
    }    
    
}
