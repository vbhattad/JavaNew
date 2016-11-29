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
import java.awt.Color;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Charts.AveScoreC;
import Charts.NumOfTestDuringTimeC;
import Charts.PassAndFailC;
import Charts.ScoreOverDiffLevelC;
import Model.People;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darshanmohan
 */
public class InstructorController implements Initializable {

    @FXML
    private Tab Graph;
    @FXML
    private AnchorPane GraphScene;
    @FXML
    private Button ButtonPDF;

    @FXML
    private Pane Diff;
    @FXML
    private Pane Num;
    @FXML
    private Pane Pass;
    @FXML
    private Pane Ave;

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
    private void browseFile() {
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
    private void addFile() throws FileNotFoundException {
        try {
            QuestionDAOImpl questionsDAO = new QuestionDAOImpl();
            questionsDAO.addQuestions(chosenFile);
        } catch (IOException ex) {
            Logger.getLogger(InstructorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AveScoreC a = new AveScoreC();
        NumOfTestDuringTimeC n = new NumOfTestDuringTimeC();
        PassAndFailC p = new PassAndFailC();
        ScoreOverDiffLevelC s = new ScoreOverDiffLevelC();

        try {
            Ave.getChildren().add(a.getChart());
            Num.getChildren().add(n.getChart());

            Pass.getChildren().add(p.getChart());

            Diff.getChildren().add(s.getChart());

        } catch (SQLException ex) {
            Logger.getLogger(InstructorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void savePDF(ActionEvent event) throws FileNotFoundException, IOException {
        Image img1 = Ave.snapshot(null, null);
        Image img2 = Num.snapshot(null, null);
        Image img3 = Diff.snapshot(null, null);
        Image img4 = Pass.snapshot(null, null);
        File file = new File("chart.pdf");
        // Read the image as BufferedImage object
        PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        ImageData imgData1 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img1, null), null);
        ImageData imgData2 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img2, null), null);
        ImageData imgData3 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img3, null), null);
        ImageData imgData4 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img4, null), null);
        com.itextpdf.layout.element.Image pdfImg1 = new com.itextpdf.layout.element.Image(imgData1);
        com.itextpdf.layout.element.Image pdfImg2 = new com.itextpdf.layout.element.Image(imgData2);
        com.itextpdf.layout.element.Image pdfImg3 = new com.itextpdf.layout.element.Image(imgData3);
        com.itextpdf.layout.element.Image pdfImg4 = new com.itextpdf.layout.element.Image(imgData4);
        // add the page to the document object
        doc.add(pdfImg1);
        doc.add(pdfImg2);
        doc.add(pdfImg3);
        doc.add(pdfImg4);
        doc.close();

    }
}
