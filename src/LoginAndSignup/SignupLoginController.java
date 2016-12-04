/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginAndSignup;


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

public class SignupLoginController implements Initializable {

    /**
     * Logged in user. Global value
     */
    public static People user;

    @FXML
    private Label lblSignupWarning;

    @FXML
    private Label lblLoginWarning;

    @FXML
    private Button btLogin;

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

    /*
    * Sign up button action
     */
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
            boolean isSuccess;
            if (fName.trim().isEmpty() || lName.trim().isEmpty() || pass.trim().isEmpty() || repass.trim().isEmpty() || andrewId.trim().isEmpty()) {
                lblSignupWarning.setText("* All the fields are required");
            } else if (!pass.equals(repass)) {
                lblSignupWarning.setText("* The passwords do not match");
            } else {
                isSuccess = objsignup.addUser(tfFirstname.getText(), tfLastname.getText(), pfTypePassword.getText(), tfAndrewID.getText(), 'S');
                if (isSuccess) { // Signup success
                    lblSignupWarning.setText("Sign Up Successful ! Please login to continue.");
                } else { // User already present
                    lblSignupWarning.setText("* The user is already present");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(SignupLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    * Login button action
     */
    @FXML
    private void LoginButtonAction(ActionEvent event) {

        try {
            UserLogin objlogin = new UserLogin();
            String userName = tfUsername.getText();
            String pass = pfPassword.getText();
            if (userName.trim().isEmpty() || pass.trim().isEmpty()) { // Error handling
                lblLoginWarning.setText("* All the fields are required");
            } else {
                user = objlogin.authenticateUser(userName, pass); // Authenticate user
                System.out.println(user.getAndrewId());
                switch (user.getMemberType()) {
                    case 'S': { // The logged in user is Student. Move to student dashboard
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        AnchorPane page;
                        try {
                            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Student.fxml"));

                            Scene scene = new Scene(page);
                            stage.setScene(scene);

                            stage.setResizable(false);

                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    break;
                    case 'A': // Logged in user is Admin. Move to admin dashboard
                    {
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        AnchorPane page;
                        try {
                            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Admin.fxml"));
                            Scene scene = new Scene(page);
                            stage.setResizable(false);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    case 'F': {
                        // Logged in user is faculty. Move to Faculty dashboard
                        Stage stage = (Stage) btLogin.getScene().getWindow();
                        TabPane page;
                        try {
                            page = (TabPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Instructor.fxml"));
                            Scene scene = new Scene(page);
                            stage.setResizable(false);
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
    }

}
