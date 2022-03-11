package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static boolean trainBool = false;

    static List<List<String>> training = new ArrayList<>();
    static List<List<String>> testing = new ArrayList<>();

    public static void readFromFile(String fileName,int part) throws IOException {

        List<String> dictionary = new ArrayList<>();
//        List<List<String>> training = new ArrayList<>();

        File file = new File("C:\\Users\\User.WINDOWS-VCRGH5N\\Desktop\\digitSapiensTask\\src\\com\\company\\"+fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));


        String st;
        int count  = 0;
        while ((st = br.readLine()) != null){
            if(count != 0){
//                if(!checkIfNumber(st) && trainBool == true){
//                        dictionary.add(st);
//                }else
                if(checkIfNumber(st)){
                    if(dictionary.size() > 0 && trainBool){
                        training.add(dictionary);
                    }else if(dictionary.size() > 0 && !trainBool){
                        testing.add(dictionary);
                    }
                    dictionary = new ArrayList<>();
                    trainBool = !trainBool;

                }
                if(!checkIfNumber(st)){
                    dictionary.add(st);
                }
            }
            count++;
        }
        if(dictionary.size() > 0 && !trainBool){
            testing.add(dictionary);
        }
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
