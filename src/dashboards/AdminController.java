/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import DAO.PeopleDAOImpl;
import LoginAndSignup.UserSignUp;
import Model.People;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darshanmohan
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public Button instructorAddButton;
    
    @FXML
    public Button peopleRemoveButton;
    
    @FXML
    public TextField insAndrewId;
    
    @FXML
    public TextField insFirstName;
    
    @FXML
    public TextField insLastName;
    
    @FXML
    public TextField insPassword;
    
    @FXML
    public TextField removePeopleTextBox;
    
    @FXML
    private void peopleRemove(ActionEvent event){
     PeopleDAOImpl people = new PeopleDAOImpl();
     if(people.removeUser(removePeopleTextBox.getText())){
         System.out.println("Removed User");
     }else{
         System.out.println("User Doesn't exist to delete");
     }
      
    }
    
    @FXML
    private void instructorAdd(ActionEvent event){
         UserSignUp addFaculty = new UserSignUp();
       if(addFaculty.addUser(insFirstName.getText(), insLastName.getText(), insPassword.getText(), insAndrewId.getText(), 'F')){
           System.out.println("Got true");
       }else{
           System.out.println("Got False");
       }
    }
    
    @FXML
    private void logout() {
        Stage stage = (Stage) instructorAddButton.getScene().getWindow();
        AnchorPane page;
        try {
            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("QuizApp/HomePage.fxml"));
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
