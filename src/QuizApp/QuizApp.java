/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author darshanmohan
 */
public class QuizApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboards/Instructor.fxml"));
        Scene scene = new Scene(page);
        stage.setScene(scene);

        stage.show();

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
