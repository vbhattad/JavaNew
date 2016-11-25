package pdf_function;

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
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
       DAO.ResultDAOImpl resultDao=new DAO.ResultDAOImpl();
        int[] array=new int[8];
       array=resultDao.getScoreOverDiffLevel();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1.setName("highest and lowest scores over deifficulty level ");
            series1.getData().add(new XYChart.Data<>("Easy", array[0]));
            series1.getData().add(new XYChart.Data<>("Medium", array[1]));
            series1.getData().add(new XYChart.Data<>("Hard",array[2]));
            series1.getData().add(new XYChart.Data<>("Mix",array[3]));
        //series2.setName("Pass and Fail during the last month,last quarter and over the last year");
            series2.getData().add(new XYChart.Data<>("Easy", array[4]));
            series2.getData().add(new XYChart.Data<>("Medium", array[5]));
            series1.getData().add(new XYChart.Data<>("Hard",array[6]));
            series2.getData().add(new XYChart.Data<>("Mix",array[7]));
        chart.getData().addAll(series1,series2);

        Button save = new Button("Save to pdf");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
        save.setOnAction(e -> {
            File file = chooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    Image img = chart.snapshot(null, null);
                    ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
                    com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

                    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document doc = new Document(pdfDoc);
                    doc.add(pdfImg);
                    doc.close();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }               
            }
        });

        BorderPane.setAlignment(save, Pos.CENTER);
        BorderPane.setMargin(save, new Insets(10));
        BorderPane root = new BorderPane(chart, null, null, save, null);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @param args the command line arguments
     */
  
}
