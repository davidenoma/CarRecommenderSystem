/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomatech.karim.model;


import com.nomatech.karim.utility.ArffGenerator;
import java.io.Serializable;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kolis
 */
public class Cars implements Serializable {

    private int carId;
    private int totalNumberOfCars;
    private String carName;
    private String carDesc;
    private String carImg;
    public Cars[] cars = null;

    public Cars() {

    }

    /**
     * @return the carId
     */
    public int getCarId() {
        return this.carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return this.carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the carDesc
     */
    public String getCarDesc() {
        return this.carDesc;
    }

    /**
     * @param carDesc the carDesc to set
     */
    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    /**
     * @return the carImg
     */
    public String getCarImg() {
        return this.carImg;
    }

    /**
     * @param carImg the carImg to set
     */
    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    /**
     * @return the car
     */
    public Cars[] getCars() {

        return this.cars;
    }
    
    public static void main(String[] args) {
        Cars car = new Cars();
        car.setCars();
        System.out.println(car.getCars()[0].getCarDesc());
    }
    /**
     * @param cars
     */
    public void setCars() {
        
        try {
            KarimDatabase kd = new KarimDatabase();
            Statement st = kd.getCon().createStatement();
            String sql = "select * from cars_tb order by car_id desc";
            ResultSet rs = st.executeQuery(sql);
            rs.last();
            this.cars = new Cars[rs.getRow()];
            this.setTotalNumberOfCars(rs.getRow());
            rs.beforeFirst();
            int i = 0;
            
            while (rs.next()) {
                getCars()[i] = new Cars();
                getCars()[i].setCarId(rs.getInt("car_id"));
                getCars()[i].setCarName(rs.getString("car_name"));
                getCars()[i].setCarDesc(rs.getString("car_desc"));
                getCars()[i].setCarImg(rs.getString("car_img"));
                i++;
            }
           
            kd.getCon().close();
        } catch (SQLException ex) {
            out.println("<a> error occured </a>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cars.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the totalNumberOfCars
     */
    public int getTotalNumberOfCars() {
        return totalNumberOfCars;
    }

    /**
     * @param totalNumberOfCars the totalNumberOfCars to set
     */
    public void setTotalNumberOfCars(int totalNumberOfCars) {
        this.totalNumberOfCars = totalNumberOfCars;
    }
    
    public void createTestData(int carId){
        ArffGenerator ag = new ArffGenerator();
        ag.generateArff(this.getCarId());
                        
    }
}
