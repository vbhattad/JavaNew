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
public class RightAndWrongC {
    //Creat a new barchart and name it chart
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        //creat a new DAO.resultDAOImpl 
       DAO.ResultDAOImpl resultDao=new DAO.ResultDAOImpl();
        int[] array=new int[2];
        public BarChart<String, Number> getChart(){
        array=resultDao.getRightandWrong("2");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("right and wrong numbers");
        //set the series column names as right and wrong and pass the value 
        series.getData().add(new XYChart.Data<>("right",array[0]));
        series.getData().add(new XYChart.Data<>("wrong", array[1]));
        chart.getData().add(series);
        chart.setMaxSize(500, 300);
        return chart;}

}
