/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomatech.karim.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kolis
 */
public class Comments implements Serializable {

    private int comment_id;
    private int car_id;
    private int comment_desc;
    private int time_stamp;
    public Comments comments[] = null;

    public Comments() {

    }

    void addComments() {

    }

    Comments[] getAllComments(int car_id) {
        try {
            String getComments = "select * from comments_tb where car_id = "
                    + car_id + "\" order by time_stamp DESC";
            KarimDatabase karim = new KarimDatabase();
            Statement stm = karim.getCon().createStatement();
            ResultSet rs = stm.executeQuery(getComments);
            rs.last();

            return this.comments;
        } catch (SQLException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the comment_id
     */
    public int getComment_id() {
        return comment_id;
    }

    /**
     * @param comment_id the comment_id to set
     */
    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    /**
     * @return the car_id
     */
    public int getCar_id() {
        return car_id;
    }

    /**
     * @param car_id the car_id to set
     */
    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    /**
     * @return the comment_desc
     */
    public int getComment_desc() {
        return comment_desc;
    }

    /**
     * @param comment_desc the comment_desc to set
     */
    public void setComment_desc(int comment_desc) {
        this.comment_desc = comment_desc;
    }

    /**
     * @return the time_stamp
     */
    public int getTime_stamp() {
        return time_stamp;
    }

    /**
     * @param time_stamp the time_stamp to set
     */
    public void setTime_stamp(int time_stamp) {
        this.time_stamp = time_stamp;
    }

}
