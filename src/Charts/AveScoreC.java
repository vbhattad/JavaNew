/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import java.sql.SQLException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author mac
 */
public class AveScoreC {
    
    //Creat a new barchart and name it chart
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        //creat a new DAO.resultDAOImpl 
       DAO.ResultDAOImpl resultDao=new DAO.ResultDAOImpl();
        double [] array=new double[3];
        //get the vlaue from database via a method:getAveScore and pass the value to an int array
        public BarChart<String, Number> getChart() throws SQLException{
        array=resultDao.getAveScore();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //set the series column names as last month,last quarter and last year and pass the value 
        series.setName("Average student score over last month, quarter and year");
         series.getData().add(new XYChart.Data<>("Last month", array[0]));
            series.getData().add(new XYChart.Data<>("Last quater", array[1]));
            series.getData().add(new XYChart.Data<>("Last year", array[2]));
        chart.getData().add(series);
        chart.setMaxSize(500, 300);
        return chart;}
}
