/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import Charts.AveScoreC;
import Charts.NumOfTestDuringTimeC;
import Charts.PassAndFailC;
import Charts.RightAndWrongC;
import Charts.ScoreOverDiffLevelC;
import Charts.StuPassAndFailC;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class StudentReportController implements Initializable {

    @FXML
    private Pane left;
    @FXML
    private Pane right;
    @FXML
    private Button savePDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        RightAndWrongC a = new RightAndWrongC();
        StuPassAndFailC n = new StuPassAndFailC();
        left.getChildren().add(a.getChart());
        right.getChildren().add(n.getChart());
        
    }    
    public void savePDF(ActionEvent event) throws FileNotFoundException, IOException {
        Image img1 = left.snapshot(null, null);
        Image img2 = right.snapshot(null, null);
        
        File file = new File("chartForStu.pdf");
        // Read the image as BufferedImage object
        PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        ImageData imgData1 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img1, null), null);
        ImageData imgData2 = ImageDataFactory.create(SwingFXUtils.fromFXImage(img2, null), null);
        
        com.itextpdf.layout.element.Image pdfImg1 = new com.itextpdf.layout.element.Image(imgData1);
        com.itextpdf.layout.element.Image pdfImg2 = new com.itextpdf.layout.element.Image(imgData2);
        
        // add the page to the document object
        doc.add(pdfImg1);
        doc.add(pdfImg2);
        
        doc.close();

    }
}
