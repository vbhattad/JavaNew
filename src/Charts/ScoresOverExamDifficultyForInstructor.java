package Charts;
//Reference:http://docs.oracle.com/javafx/2/charts/jfxpub-charts.html
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/**
/**
 *
 * @author mac
 */
public class ScoresOverExamDifficultyForInstructor extends Application {
 @Override
    public void start(Stage primaryStage) throws Exception {
        //Creat a new barchart and name it chart
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        //creat a new DAO.resultDAOImpl 
       DAO.ResultDAOImpl resultDao=new DAO.ResultDAOImpl();
        int[] array=new int[8];
        //access to the method getScoreOverDiffLevel via resultDao and return an int array that contains the highest and lowest score for difficulty levels
       array=resultDao.getScoreOverDiffLevel();
       //creat two series,series1 display highest scores and series2 display lowest scores
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1.setName("highest scores over deifficulty level ");
        // set column name as easy,medium,hard and mix and pass all the value to it
            series1.getData().add(new XYChart.Data<>("Easy", array[0]));
            series1.getData().add(new XYChart.Data<>("Medium", array[1]));
            series1.getData().add(new XYChart.Data<>("Hard",array[2]));
            series1.getData().add(new XYChart.Data<>("Mix",array[3]));
        series2.setName("lowest scores over deifficulty level");
            series2.getData().add(new XYChart.Data<>("Easy", array[4]));
            series2.getData().add(new XYChart.Data<>("Medium", array[5]));
            series1.getData().add(new XYChart.Data<>("Hard",array[6]));
            series2.getData().add(new XYChart.Data<>("Mix",array[7]));
        chart.getData().addAll(series1,series2);
        //creat a button
        Button save = new Button("Save to pdf");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
        //use lambda expression to set the button to listen to an action
        save.setOnAction(e -> {
            // creat a file that save the entire stage to a pdf file
            File file = chooser.showSaveDialog(primaryStage);
            if (file != null) {
                //try export the pdf file
                try {
                    //transform the chart to an image
                    Image img = chart.snapshot(null, null);
                    ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
                    com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);
                    //set up the pdf writer
                    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document doc = new Document(pdfDoc);
                    //add the image to the pdf document
                    doc.add(pdfImg);
                    doc.close();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }               
            }
        });
        //set the stage and put the button in the middle
        BorderPane.setAlignment(save, Pos.CENTER);
        //set the margin as 10
        BorderPane.setMargin(save, new Insets(10));
        //set a root with chart in the middle and button in the bottom
        BorderPane root = new BorderPane(chart, null, null, save, null);
        //set a new scenc with the root inside it
        Scene scene = new Scene(root, 600, 600);
        //add the scene into this stage
        primaryStage.setScene(scene);
        //show this stage
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @param args the command line arguments
     */
  
}
