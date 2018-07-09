package com.nomatech.karim.utility;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stemmers.LovinsStemmer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class NaiveBayesClass {

    public static int TRAIN = 0;  //0 for train
    public static int TEST = 1;  //>0 for test

    public static void main(String args[]) throws FileNotFoundException, IOException, Exception {

        //filter
        StringToWordVector filter = new StringToWordVector();

        Classifier naive = new NaiveBayes();
        //training data

        //DataSource train = new DataSource("C:\\Users\\Makinde\\Desktop\\myfile.arff");
      // removeStopWordsInFile("C:\\Users\\os\\Desktop\\train.arff", TRAIN);
        DataSource train = new DataSource("C:\\Users\\os\\Desktop\\train.arff");
        //testing data
      //  removeStopWordsInFile("C:\\Users\\os\\Desktop\\test.arff", TEST);
        DataSource testSource = new DataSource("C:\\Users\\os\\Desktop\\test.arff");

        ///start
        Instances trainDataset = train.getDataSet();
        int lastIndex = trainDataset.numAttributes() - 1;
        trainDataset.setClassIndex(lastIndex);

        filter.setInputFormat(trainDataset);
        filter.setIDFTransform(true);
        filter.setLowerCaseTokens(true);
        LovinsStemmer stemmer = new LovinsStemmer();
        filter.setStemmer(stemmer);

        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(filter);
        fc.setClassifier(naive);
        fc.buildClassifier(trainDataset);

        trainDataset = Filter.useFilter(trainDataset, fc.getFilter());
        System.out.println("Training concluded");

        //testing data
        Instances test;
        test = testSource.getDataSet();
        test.setClassIndex(lastIndex);

        filter.setInputFormat(test);
        Instances test2 = Filter.useFilter(test, fc.getFilter());

        naive.buildClassifier(trainDataset);

        //stop
        for (int i = 0; i < test2.numInstances(); i++) {
            System.out.println(test.instance(i));
            double index = naive.classifyInstance(test2.instance(i));
            String className = trainDataset.attribute(0).value((int) index);
            System.out.println(className);
        }

    }

    public static List<String> defaultStopWords() {
        Stopwords stopwords = new Stopwords();
        List<String> words = new ArrayList<>(100);
        Enumeration elements = stopwords.elements();
        while (elements.hasMoreElements()) {
            words.add((String) elements.nextElement());
        }
        return words;
    }

    public static void removeStopWordsInFile(String filepath, int type) throws IOException {
        //bufferedReader helps in reading file by line
        try ( //read file from file path
                FileReader fileReader = new FileReader(filepath); BufferedReader bufferedReader = new BufferedReader(fileReader); //to save file to disk
                //depending on the 2nd parameter save file as train or test
                FileWriter fileWriter = new FileWriter(type == 0 ? "C:\\Users\\os\\Desktop\\train.arff" : "C:\\Users\\os\\Desktop\\test.arff"); 
//bufferedWriter helps in writing file by line
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            ArrayList<String> s;
            String line;

            //read file till EOF [End of File]
            while ((line = bufferedReader.readLine()) != null) {
                //StringBuilder a better way to append string to string
                StringBuilder sb = new StringBuilder();
                //ignore all weka declarations
                if (line.contains("@")) {
                    bufferedWriter.write(line);
                    bufferedWriter.write("\n");
                    continue;
                    //leave all line spaces the same
                } else if (line.equals("\n")) {
                    bufferedWriter.write("\n");
                    continue;
                    //remove stop words in string
                } else {
                    String[] arr;
                    arr = line.split(" ");//splits string into array of words
                    s = new ArrayList<>(Arrays.asList(arr));//convert array into arraylist
                    //loop over arraylist and remove al stop words
                    for (String i : s) {
                        Stopwords stopwords = new Stopwords();
                        if (stopwords.is(i)) {
                            sb.append("");
                        } else {
                            sb.append(" ").append(i).append(" ");
                        }
                    }
                }
                s.clear();//clears arraylist
                //write line to string
                bufferedWriter.write(sb.toString().replaceAll("\\s{2,}", " ").trim());//replace all multiple spaces with a single space
                bufferedWriter.write("\n");//write line break to file to avoid lumping of lines together
            }
        }
    }
}
