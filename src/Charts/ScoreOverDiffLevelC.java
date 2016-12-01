/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author mac
 */
public class ScoreOverDiffLevelC {

    //Creat a new barchart and name it chart

    BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
    //creat a new DAO.resultDAOImpl 
    DAO.ResultDAOImpl resultDao = new DAO.ResultDAOImpl();
    int[] array = new int[8];

    public BarChart<String, Number> getChart() {
        //access to the method getScoreOverDiffLevel via resultDao and return an int array that contains the highest and lowest score for difficulty levels
        array = resultDao.getScoreOverDiffLevel();
        //creat two series,series1 display highest scores and series2 display lowest scores
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1.setName("highest scores over deifficulty level ");
        // set column name as easy,medium,hard and mix and pass all the value to it
        series1.getData().add(new XYChart.Data<>("Easy", array[0]));
        series1.getData().add(new XYChart.Data<>("Medium", array[1]));
        series1.getData().add(new XYChart.Data<>("Hard", array[2]));
        series1.getData().add(new XYChart.Data<>("Mix", array[3]));
        series2.setName("lowest scores over deifficulty level");
        series2.getData().add(new XYChart.Data<>("Easy", array[4]));
        series2.getData().add(new XYChart.Data<>("Medium", array[5]));
        series1.getData().add(new XYChart.Data<>("Hard", array[6]));
        series2.getData().add(new XYChart.Data<>("Mix", array[7]));
        chart.getData().addAll(series1, series2);
        chart.setMaxSize(500, 300);
        return chart;
    }

    public List<XYChart.Series> getSeries() {
         array = resultDao.getScoreOverDiffLevel();
        //creat two series,series1 display highest scores and series2 display lowest scores
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1.setName("highest scores over deifficulty level ");
        // set column name as easy,medium,hard and mix and pass all the value to it
        series1.getData().add(new XYChart.Data<>("Easy", array[0]));
        series1.getData().add(new XYChart.Data<>("Medium", array[1]));
        series1.getData().add(new XYChart.Data<>("Hard", array[2]));
        series1.getData().add(new XYChart.Data<>("Mix", array[3]));
        series2.setName("lowest scores over deifficulty level");
        series2.getData().add(new XYChart.Data<>("Easy", array[4]));
        series2.getData().add(new XYChart.Data<>("Medium", array[5]));
        series1.getData().add(new XYChart.Data<>("Hard", array[6]));
        series2.getData().add(new XYChart.Data<>("Mix", array[7]));
        List<XYChart.Series> seriesList = new ArrayList<>();
        seriesList.add(series1);
        seriesList.add(series2);
        return seriesList;
    
    }

}
