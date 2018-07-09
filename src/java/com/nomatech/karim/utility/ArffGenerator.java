/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomatech.karim.utility;

import com.nomatech.karim.model.KarimDatabase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author kolis
 */
public class ArffGenerator {

    String carName;
    String arffFile;
    int carId;
    
   public void generateArff(int carId) {
        String comments[] = null;
        KarimDatabase karim = new KarimDatabase();
        try {
            Statement stm = karim.getCon().createStatement();
            ResultSet st = stm.executeQuery("select * from comments_tb where car_id = " + carId);
            st.last();
            comments = new String[st.getRow()];
            st.beforeFirst();
            int i = 0;
            while (st.next()) {
                comments[i] = st.getString("comment_desc").trim();//note the effects on the treatment on the 2 or more letter words. 
                i++;
            }
            st.close();
            //stm.close();
            st = stm.executeQuery("select car_name from cars_tb where car_id = " + carId);
            st.next();
            carName = st.getString("car_name");
            karim.getCon().close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArffGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            arffFile = "C:\\Users\\Os\\Desktop\\" + carName + ".arff";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arffFile))) {
                writer.write(
                        "@relation test\n"
                        + "\n"
                        + "@attribute text string\n"
                        + "@attribute class {spam, ham}\n"
                        + "\n"
                        + "@data");
            }

            try (FileWriter fw = new FileWriter(arffFile, true)) {
                fw.write("\n");
                for (String comment : comments) {
                    fw.write("'" + comment + "'");
                    fw.write(",?");
                    fw.write("\n");
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(ArffGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.carId = carId;

    }

    public static void main(String[] args) {

        BufferedReader breader = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("C:\\Users\\os\\Desktop\\train.arff");
            Instances inst = source.getDataSet();
            inst.setClassIndex(inst.numAttributes() - 1);
            
            breader = new BufferedReader(new FileReader("C:\\Users\\os\\Desktop\\train.arff"));
            Instances train = new Instances(breader);
            train.setClassIndex(train.numAttributes() - 1);
            
            breader = new BufferedReader(new FileReader("C:\\Users\\Os\\Desktop\\test.arff"));
            Instances test = new Instances(breader);
            test.setClassIndex(train.numAttributes() - 1);
            
            breader.close();
            
            NaiveBayes tree = new NaiveBayes();
            tree.buildClassifier(train);
            
            Instances labelled = new Instances(test);
            for (int i = 0; i < test.numInstances(); i++) {
                double clsLabel = tree.classifyInstance(test.instance(i));
                labelled.instance(i).setClassValue(clsLabel);
                String output = labelled.lastInstance().toString();
                String[] arr = output.split(",");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArffGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArffGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ArffGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
