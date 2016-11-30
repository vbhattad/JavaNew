/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author mac
 */
public class RightAndWrongC {
    
        //creat a new DAO.resultDAOImpl 
       DAO.ResultDAOImpl resultDao=new DAO.ResultDAOImpl();
        int[] array=new int[2];
        public PieChart getChart(){
            array=resultDao.getRightandWrong("2","date");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("right",array[0]),
                new PieChart.Data("wrong", array[1]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("right and wrong numbers"); 
        return chart;}

}
