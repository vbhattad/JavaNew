/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import LoginAndSignup.SignupLoginController;
import Model.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author katha
 */
public class ResultDAOImpl extends DAOJDBCImpl {

    @Override
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "create table Result("
                    + "andrewid varchar(255) NOT NULL,"
                    + "CorrectEasy int,"
                    + "CorrectMedium int,"
                    + "CorrectHard int,"
                    + "TotalEasy int,"
                    + "TotalMedium int,"
                    + "TotalHard int,"
                    + "DT Timestamp NOT NULL,"
                    + "pass int NOT NULL)"
                    + "DiffLevel varchar(50)"
                    + "Score int";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "drop table Result";
            stmt.execute(query);
        } catch (Exception se) {
            System.out.println(se.toString());
        }
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (Exception se) {
            System.out.println("Exception closing Connection: " + se.toString());
        }
    }

    public int[] getRightandWrong(String id,String date) {
        ResultSet results = null;
        int[] intArray = new int[2];
        try (Statement stmt = con.createStatement()) {
            String query = "select CorrectEasy,CorrectMedium,CorrectHard,TotalEasy,TotalMedium,TotalHard from Result where andrewid='" + id + "',DT='"+date+"'";
            results = stmt.executeQuery(query);
            if (results.next()) {
                int CorrectEasy = results.getInt(1);
                int CorrectMedium = results.getInt(2);
                int CorrectHard = results.getInt(3);
                int wrongEasy = results.getInt(4) - CorrectEasy;
                int wrongMedium = results.getInt(5) - CorrectMedium;
                int wrongHard = results.getInt(6) - CorrectHard;
                intArray[0] = CorrectEasy + CorrectMedium + CorrectHard;
                intArray[1] = wrongEasy + wrongMedium + wrongHard;
                //System.out.println(CorrectEasy);
                //System.out.println(CorrectMedium);
                //System.out.println(CorrectHard);
                // System.out.println(wrongEasy);
                // System.out.println(wrongMedium);
                // System.out.println(wrongHard);
            }

        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }

    public int[] getPassandFall() {
        String date1 = getDate(1);
        String date2 = getDate(3);
        String date3 = getDate(12);
        ResultSet results = null;
        ResultSet results1 = null;
        ResultSet results3 = null;
        ResultSet results12 = null;
        int[] intArray = new int[6];
        int pass1;
        int pass3;
        int pass12;
        try (Statement stmt = con.createStatement()) {
            results1 = stmt.executeQuery("select pass from Result where DT> '" + date1 + "'");
            int count1 = 0;
            while (results1.next()) {
                ++count1;
            }
            results3 = stmt.executeQuery("select pass from Result where DT> '" + date2 + "'");
            int count3 = 0;
            while (results3.next()) {
                ++count3;
            }
            results12 = stmt.executeQuery("select pass from Result where DT> '" + date3 + "'");
            int count12 = 0;
            while (results12.next()) {
                ++count12;
            }

            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date1 + "'");
            if (results.next()) {
                pass1 = results.getInt(1);
                intArray[0] = pass1;
                intArray[1] = count1 - pass1;
            }
            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date2 + "'");
            if (results.next()) {
                pass3 = results.getInt(1);
                intArray[2] = pass3;
                intArray[3] = count3 - pass3;
            }
            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date3 + "'");
            if (results.next()) {
                pass12 = results.getInt(1);
                intArray[4] = pass12;
                intArray[5] = count12 - pass12;
            }

        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }
    public int[] getPassandFallForStu(String id) {
        String date1 = getDate(1);
        String date2 = getDate(3);
        String date3 = getDate(12);
        ResultSet results = null;
        ResultSet results1 = null;
        ResultSet results3 = null;
        ResultSet results12 = null;
        int[] intArray = new int[6];
        int pass1;
        int pass3;
        int pass12;
        try (Statement stmt = con.createStatement()) {
            results1 = stmt.executeQuery("select pass from Result where DT> '" + date1 + "',andrewid='" + id + "'");
            int count1 = 0;
            while (results1.next()) {
                ++count1;
            }
            results3 = stmt.executeQuery("select pass from Result where DT> '" + date2 + "',andrewid='" + id + "'");
            int count3 = 0;
            while (results3.next()) {
                ++count3;
            }
            results12 = stmt.executeQuery("select pass from Result where DT> '" + date3 + "',andrewid='" + id + "'");
            int count12 = 0;
            while (results12.next()) {
                ++count12;
            }

            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date1 + "',andrewid='" + id + "'");
            if (results.next()) {
                pass1 = results.getInt(1);
                intArray[0] = pass1;
                intArray[1] = count1 - pass1;
            }
            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date2 + "',andrewid='" + id + "'");
            if (results.next()) {
                pass3 = results.getInt(1);
                intArray[2] = pass3;
                intArray[3] = count3 - pass3;
            }
            results = stmt.executeQuery("select SUM(pass) from Result where DT> '" + date3 + "',andrewid='" + id + "'");
            if (results.next()) {
                pass12 = results.getInt(1);
                intArray[4] = pass12;
                intArray[5] = count12 - pass12;
            }

        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }

    public int[] getNumOfTestDuringTime() {
        String date1 = getDate(1);
        String date2 = getDate(3);
        String date3 = getDate(12);
        ResultSet results = null;
        int[] intArray = new int[3];
        try (Statement stmt = con.createStatement()) {
            results = stmt.executeQuery("select pass from Result where DT> '" + date1 + "'");
            int count1 = 0;
            while (results.next()) {
                ++count1;
            }
            results = stmt.executeQuery("select pass from Result where DT> '" + date2 + "'");
            int count3 = 0;
            while (results.next()) {
                ++count3;
            }
            results = stmt.executeQuery("select pass from Result where DT> '" + date3 + "'");
            int count12 = 0;
            while (results.next()) {
                ++count12;
            }

            intArray[0] = count1;
            intArray[1] = count3;
            intArray[2] = count12;
        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }

    public double[] getAveScore() throws SQLException {
        String date1 = getDate(1);
        String date2 = getDate(3);
        String date3 = getDate(12);
        ResultSet results = null;
        ResultSet results1 = null;
        ResultSet results3 = null;
        ResultSet results12 = null;
        double[] intArray = new double[3];
        int score1;
        int score3;
        int score12;
        try (Statement stmt = con.createStatement()) {
            results1 = stmt.executeQuery("select Score from Result where DT> '" + date1 + "'");
            int count1 = 0;
            while (results1.next()) {
                ++count1;
            }
            results3 = stmt.executeQuery("select Score from Result where DT>'" + date2 + "'");
            int count3 = 0;
            while (results3.next()) {
                ++count3;
            }
            results12 = stmt.executeQuery("select Score from Result where DT>'" + date3 + "'");
            int count12 = 0;
            while (results12.next()) {
                ++count12;
            }
            results = stmt.executeQuery("select SUM(Score) from Result where DT>'" + date1 + "'");
            if (results.next()) {
                score1 = results.getInt(1);
                intArray[0] = (double) score1 / (double) count1;
            }
            results = stmt.executeQuery("select SUM(Score) from Result where DT>'" + date2 + "'");
            if (results.next()) {
                score3 = results.getInt(1);
                intArray[1] = (double) score3 / (double) count3;
            }
            results = stmt.executeQuery("select SUM(Score) from Result where DT>'" + date3 + "'");
            if (results.next()) {
                score12 = results.getInt(1);
                intArray[2] = (double) score12 / (double) count12;
            }
        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }

    public int[] getScoreOverDiffLevel() {
        ResultSet results = null;

        int[] intArray = new int[8];

        try (Statement stmt = con.createStatement()) {

            results = stmt.executeQuery("select MAX(Score) from Result where DiffLevel='Easy'");
            if (results.next()) {
                intArray[0] = results.getInt(1);
            }
            results = stmt.executeQuery("select MAX(Score) from Result where DiffLevel='Medium'");
            if (results.next()) {
                intArray[1] = results.getInt(1);
            }
            results = stmt.executeQuery("select MAX(Score) from Result where DiffLevel='Hard'");
            if (results.next()) {
                intArray[2] = results.getInt(1);
            }
            results = stmt.executeQuery("select MAX(Score) from Result where DiffLevel='Mix'");
            if (results.next()) {
                intArray[3] = results.getInt(1);
            }

            results = stmt.executeQuery("select MIN(Score) from Result where DiffLevel='Easy'");
            if (results.next()) {
                intArray[4] = results.getInt(1);
            }
            results = stmt.executeQuery("select MIN(Score) from Result where DiffLevel='Medium'");
            if (results.next()) {
                intArray[5] = results.getInt(1);
            }
            results = stmt.executeQuery("select MIN(Score) from Result where DiffLevel='Hard'");
            if (results.next()) {
                intArray[6] = results.getInt(1);
            }
            results = stmt.executeQuery("select MIN(Score) from Result where DiffLevel='Mix'");
            if (results.next()) {
                intArray[7] = results.getInt(1);
            }

        } catch (Exception se) {
            System.out.println(se.toString());
        }
        return intArray;
    }

    public String getDate(int months) {
        int pastMonths = months;
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = today.format(formatter);
        String strDate = formattedDateTime.toString(); //here convert the date that you get, to a string format
        String[] split = strDate.split("\\s");
        String date = split[0];
        String[] splitDate = date.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);
        int yearDecrement = pastMonths / 12;
        int monthDecrement = pastMonths % 12;
        String decrementedDate = (year - yearDecrement) + "-" + (month - monthDecrement) + "-" + day + " " + split[1];

        return decrementedDate;
    }

    public void insertResults(Result quizResult) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String insertResult = "INSERT INTO Result VALUES ('" + quizResult.getAndrewId() + "'," +
                                                                             quizResult.getNoOfCorrectEasy() + "," +
                                                                             quizResult.getNoOfCorrectMedium() + "," +
                                                                             quizResult.getNoOfCorrectHard()+ "," +
                                                                             quizResult.getTotalNoOfEasy() + "," +
                                                                             quizResult.getTotalNoOfMedium() + "," +
                                                                             quizResult.getTotalNoOfHard() + ",'" +
                                                                             timeStamp + "'," +
                                                                             quizResult.getGrade() + ",'" +
                                                                             quizResult.getDifficultyLevel() + "'," +
                                                                             quizResult.getScore() + ")" ;
        
       
        try {
           // Connection connect = DriverManager.getConnection(url); //Create connection
            Statement statement = con.createStatement(); //Connect to DB
            statement.execute(insertResult);
         }catch (SQLException e){
             System.out.println("SQL Exception: " +e);
         }
    }

   

//    public ObservableList<PieChart.Data> getPieData(LocalDate fromDateString, LocalDate toDateString) {
//
//    }
//
//    public List<XYChart.Series> getBarSeries(LocalDate fromDateString, LocalDate toDateString) {
//       
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series1.setName("highest scores over deifficulty level ");
//        // set column name as easy,medium,hard and mix and pass all the value to it
//        series1.getData().add(new XYChart.Data<>("Easy", array[0]));
//        series1.getData().add(new XYChart.Data<>("Medium", array[1]));
//        series1.getData().add(new XYChart.Data<>("Hard", array[2]));
//        series1.getData().add(new XYChart.Data<>("Mix", array[3]));
//        series2.setName("lowest scores over deifficulty level");
//        series2.getData().add(new XYChart.Data<>("Easy", array[4]));
//        series2.getData().add(new XYChart.Data<>("Medium", array[5]));
//        series1.getData().add(new XYChart.Data<>("Hard", array[6]));
//        series2.getData().add(new XYChart.Data<>("Mix", array[7]));
//        List<XYChart.Series> seriesList = new ArrayList<>();
//        seriesList.add(series1);
//        seriesList.add(series2);
//        return seriesList;
//    
//    }

    public ArrayList getLineSeries(LocalDate fromDateString, LocalDate toDateString){
        String andrewId = SignupLoginController.user.getAndrewId();
        ArrayList<Integer> data = new ArrayList<>();
        String fromDate = fromDateString.format(DateTimeFormatter.ISO_DATE).toString() + " 00:00:00";
        String toDate = toDateString.format(DateTimeFormatter.ISO_DATE).toString() + " 23:59:59";
        try(Statement stmt = con.createStatement()){
            String query = "Select Score from Result where andrewId='" + andrewId + "' and DT > '" + fromDate + "' && DT < '" + toDate + "'";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while(rs.next()){
                i++;
                int score = rs.getInt("Score");
                data.add(score);
                
            }
        }catch(SQLException e){
            System.out.println("SQLException " + e);
        }
        return data;
    }

    public int[] getNoPassandFall(LocalDate fromDateString, LocalDate toDateString) {
        int[] passFail = new int[2];
            String andrewId = SignupLoginController.user.getAndrewId();
        ArrayList<Integer> data = new ArrayList<>();
        String fromDate = fromDateString.format(DateTimeFormatter.ISO_DATE).toString() + " 00:00:00";
        String toDate = toDateString.format(DateTimeFormatter.ISO_DATE).toString() + " 23:59:59";
        try(Statement stmt = con.createStatement()){
            String query = "Select pass from Result where andrewId='" + andrewId + "' and DT > '" + fromDate + "' && DT < '" + toDate + "'";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while(rs.next()){
                i++;
                if(rs.getInt("pass") == 1)
                    passFail[0]++;
                else
                    passFail[1]++;
            }
        }catch(SQLException e){
            System.out.println("SQLException " + e);
        }
        return passFail;
    }
}
