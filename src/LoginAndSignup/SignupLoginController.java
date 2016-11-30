/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginAndSignup;

import StudentQuizTest.QuizTest;

import LoginAndSignup.UserSignUp;
import LoginAndSignup.UserLogin;
import Model.People;
import dashboards.StudentController;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class SignupLoginController implements Initializable {

    //private Quiz quiz;
    private QuizTest quiz = new QuizTest();
    
    public static People user;

    @FXML
    private Label label;

    @FXML
    private Label lblSignupWarning;

    @FXML
    private Label lblLoginWarning;

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

//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        
//        Stage stage = (Stage) btLogin.getScene().getWindow();
//        try {
//            quiz.start(stage);
//        } catch (Exception ex) {
//            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    @FXML
    private void SignupButtonAction(ActionEvent event) {

        try {
            UserSignUp objsignup = new UserSignUp();
            String fName = tfFirstname.getText();
            String lName = tfLastname.getText();
            String pass = pfTypePassword.getText();
            String repass = pfReTypePassword.getText();
            String andrewId = tfAndrewID.getText();
            System.out.println(fName.length());
            boolean isSuccess = true;
            if (fName.trim().isEmpty() || lName.trim().isEmpty() || pass.trim().isEmpty() || repass.trim().isEmpty() || andrewId.trim().isEmpty()) {
                System.out.println(fName);
                lblSignupWarning.setText("* All the fields are required");
            } else if (!pass.equals(repass)) {
                lblSignupWarning.setText("* The passwords do not match");
            } else {
                isSuccess = objsignup.addUser(tfFirstname.getText(), tfLastname.getText(), pfTypePassword.getText(), tfAndrewID.getText(), 'S');
                if (isSuccess) {
                    lblSignupWarning.setText("Sign Up Successful ! Please login to continue.");
                } else {
                    System.out.println("I got false from the DB");
                    lblSignupWarning.setText("* The user is already present");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LoginButtonAction(ActionEvent event) {

        try {
            //Stage stage = (Stage) btLogin.getScene().getWindow();
            //quiz.start(stage);
            UserLogin objlogin = new UserLogin();
            String userName = tfUsername.getText();
            String pass = pfPassword.getText();
            if (userName.trim().isEmpty() || pass.trim().isEmpty()) {
                lblLoginWarning.setText("* All the fields are required");
            } else {
                user = objlogin.authenticateUser(userName, pass);
                System.out.println(user.getAndrewId());
                switch (user.getMemberType()) {
                    case 'S': {
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        AnchorPane page;
                        try {
                            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Student.fxml"));
                            Scene scene = new Scene(page);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } break;
                    case 'A': // move to admin dashboard{
                    {
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        AnchorPane page;
                        try {
                            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Admin.fxml"));
                            Scene scene = new Scene(page);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } 
                break;
                    case 'F': {
                            // move to Faculty dashboard
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        TabPane page;
                        try {
                            page = (TabPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Instructor.fxml"));
                            Scene scene = new Scene(page);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    default:
                        lblLoginWarning.setText("* Invalid UserName/Password");
                        break;
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quiz = new QuizTest();
    }

}
