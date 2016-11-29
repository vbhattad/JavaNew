/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import DAO.QuestionDAOImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darshanmohan
 */
public class InstructorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String chosenFile;
    
    @FXML
    public Button browse;
    
    @FXML
    public Button bStartQuiz;
    
    @FXML
    public Button add;
    
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
    private void browseFile(){
         FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = null;
        try {
            file = fileChooser.showOpenDialog(new Stage());
            //chosenFile = file;
        } catch (Exception e) {
            System.out.println("");
        }
       chosenFile = file.getAbsolutePath();
    }
    
    @FXML
    private void addFile() throws FileNotFoundException{
        QuestionDAOImpl questionsDAO = new QuestionDAOImpl();
        questionsDAO.addQuestions(chosenFile);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
