package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static int resetList = 0;
    static int resetListTest = 1;
    static int numberOfSets = 0;
    static List<Integer> listOfbunches = new ArrayList<>();
    static int totalCount = 0;

    public static List<List<String>> trainFromFile(String fileName,int part) throws IOException {
        /*
            If the place of ther number read from file is odd number then it is used for training, if even it is used for testing
         */
        List<String> dictionary = new ArrayList<>();
        List<List<String>> training = new ArrayList<>();

        File file = new File("C:\\Users\\Perdorues\\Desktop\\DigitSapiensTask\\src\\com\\company\\"+fileName);

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;

        int count  = 0;
        while ((st = br.readLine()) != null){
            if(changeCursor(true,part,fileName) > count){
                count++;
                continue;
            }
            if(count != 0){
                if(!checkIfNumber(st)){
                        dictionary.add(st);

                }
            }else{
                numberOfSets = Integer.parseInt(st);
            }
            count++;
        }
        if(dictionary.size() > 0)
        {
            training.add(dictionary);
            return training;
        }
        return null;
    }


    public static List<List<String>> testFromFile(String fileName,int part) throws IOException {
        /*
            If the place of ther number read from file is odd number then it is used for training, if even it is used for testing
         */
        List<String> dictionary = new ArrayList<>();
        List<List<String>> testing = new ArrayList<>();

        File file = new File("C:\\Users\\Perdorues\\Desktop\\DigitSapiensTask\\src\\com\\company\\"+fileName);

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;

        int count  = 0;
        totalCount = 0;
        while ((st = br.readLine()) != null){
            if(changeCursor(false,part,fileName) > count){
                count++;
                continue;
            }
            if(count != 0){
                if(!checkIfNumber(st)){
                        dictionary.add(st);
                } else{
                   listOfbunches.add(Integer.parseInt(st));
                }
            }
            count++;
        }
        if(dictionary.size() > 0)
        {
            testing.add(dictionary);
            return testing;
        }
        return null;
    }

    public static int changeCursor(boolean train,int part,String fileName) throws IOException {
        int sets = 0;
        List<Integer> afterSets = new ArrayList<>();
        File file = new File("C:\\Users\\Perdorues\\Desktop\\DigitSapiensTask\\src\\com\\company\\"+fileName);

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;
        int count  = 0;
        while ((st = br.readLine()) != null){
            if(count != 0){
                if(checkIfNumber(st)){
                    afterSets.add(Integer.parseInt(st));
                }
            }else{
                sets = Integer.parseInt(st);
            }
            count++;
        }
        int sum = 1;
        if(train){
            for(int i = 0;i < part;i++){
                sum+= afterSets.get(i);
            }
        }else{
            for(int i = 0;i <= part;i++){
                sum+= afterSets.get(i);
            }
        }
        System.out.println("---: " + sum);
        return sum;
    }

    public static  boolean checkIfNumber(String s){
        try {
            int intValue = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
