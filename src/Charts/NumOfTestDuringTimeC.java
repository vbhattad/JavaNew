/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author mac
 */
public class NumOfTestDuringTimeC {

    
    BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
    //creat a new DAO.resultDAOImpl 
    DAO.ResultDAOImpl resultDao = new DAO.ResultDAOImpl();
    int[] array = new int[3];

    /**
     * Get the Bar chart for Number of tests taken with time
     * @return chart
     */ 
    public BarChart<String, Number> getChart() {
        array = resultDao.getNumOfTestDuringTime();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //set the series column names as last month,last quarter and last year and pass the value 
        series.setName("Number of tests taken during the last month,last quarter and over the last year");
        series.getData().add(new XYChart.Data<>("Last month", array[0]));
        series.getData().add(new XYChart.Data<>("Last quater", array[1]));
        series.getData().add(new XYChart.Data<>("Last year", array[2]));
        chart.getData().add(series);
        chart.setMaxSize(500, 300);

        return chart;
    }

    /**
     * Get the Bar chart for Number of tests taken with time
     * @return
     */
    public XYChart.Series getSeries() {
        array = resultDao.getNumOfTestDuringTime();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //set the series column names as last month,last quarter and last year and pass the value 
        series.setName("Number of tests taken during the last month,last quarter and over the last year");
        series.getData().add(new XYChart.Data<>("Last month", array[0]));
        series.getData().add(new XYChart.Data<>("Last quater", array[1]));
        series.getData().add(new XYChart.Data<>("Last year", array[2]));

        return series;
    }
}
