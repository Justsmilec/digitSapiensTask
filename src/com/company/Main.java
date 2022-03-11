package com.company;


import com.sun.source.tree.Tree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Main {

    public static List<TreeNode> listTree = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        FileHandler.readFromFile("example_big.in",1);
        List<List<String>> training = FileHandler.training;
        List<List<String>> testing = FileHandler.testing;
//
//        System.out.println("000:: " + training.get(0).size());
//        training.forEach(tr -> {
//            tr.forEach(item -> {
//                System.out.println(item);
//            });
//        });
        training.forEach(setItem -> {
            setItem.forEach(item -> {
                TreeNode tree = null;
                if(checkIfSpecificTreeHasOneWord(split(item)[0].toLowerCase(Locale.ROOT)) != null){
                    tree = checkIfSpecificTreeHasOneWord(split(item)[0].toLowerCase(Locale.ROOT));
                }else if(checkIfSpecificTreeHasOneWord(split(item)[1].toLowerCase(Locale.ROOT))  != null){
                    tree = checkIfSpecificTreeHasOneWord(split(item)[1].toLowerCase(Locale.ROOT));
                }

                if(tree == null){
                    TreeNode root = new TreeNode(split(item)[0].toLowerCase());
                    root.addChild(split(item)[1].toLowerCase());
                    listTree.add(root);

                } else{
                    int index;
                    if(listTree.size() > 0 ){
                        index = listTree.indexOf(tree.getParent());

                        if(index != -1){
                            if(TreeNode.checkIfFound(tree,split(item)[0])){
                                TreeNode.findNode(tree,split(item)[0]).addChild(split(item)[1].toLowerCase());
                            } else if(TreeNode.checkIfFound(tree,split(item)[1])){
                                TreeNode.findNode(tree,split(item)[1]).addChild(split(item)[0].toLowerCase());

                            }

                            listTree.set(index,tree);
                        }
                    }
                }
            });
        });


        testing.forEach(setItem -> {
            setItem.forEach(item -> {
                if(checkSinonimity(item)){
                    System.out.println("synonyms");
                } else{
                    System.out.println("different");

                }
            });
        });

    }

    public static TreeNode checkIfSpecificTreeHasOneWord(String str){
        TreeNode tr = null;
        str = str.toLowerCase(Locale.ROOT);
        if(listTree.size() > 0){
//            Optional<TreeNode> tree = listTree.stream().filter(item -> TreeNode.checkIfFound(item,str)).findFirst();
                for(int i = 0;i < listTree.size();i++){
                    if(TreeNode.findNode(listTree.get(i).getParent(),str) != null){
                        return (TreeNode.findNode(listTree.get(i),str) != null ) ? TreeNode.findNode(listTree.get(i),str).getParent() : null;
                    }
                }
        }
        return null;
    }
    public static String[] split(String list){
        String[] splitStr = list.split("\\s+");
        return  splitStr;
    }


    public static boolean checkSinonimity(String list){
        if(split(list)[0].toLowerCase(Locale.ROOT).equals( split(list)[1].toLowerCase(Locale.ROOT))){
            return true;
        }
        else if(split(list)[1].toLowerCase(Locale.ROOT).equals( split(list)[0].toLowerCase(Locale.ROOT))){
            return true;
        }
        else if(checkIfSpecificTreeHasOneWord(split(list)[0].toLowerCase(Locale.ROOT)) != null && checkIfSpecificTreeHasOneWord(split(list)[1].toLowerCase(Locale.ROOT)) != null){
           return true;
        }
        else
            return false;
    }
}
