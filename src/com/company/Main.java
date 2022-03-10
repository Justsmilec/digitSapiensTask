package com.company;


import com.sun.source.tree.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Main {

    public static List<TreeNode> listTree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
	// write your code here

//        TreeNode roo1t = new TreeNode("root");
//        roo1t.addChild("child1");
//        roo1t.addChild("child2");
//
//
//        for(TreeNode node : roo1t.getChildren()) {
//            System.out.println(node.getData());
//        }
//
//        roo1t.traverse(roo1t);
//        System.out.println("---: " + TreeNode.checkIfFound(roo1t,"child1"));


        List<List<String>> training  = FileHandler.trainFromFile("example.in",1);

        List<List<String>> testing  = FileHandler.testFromFile("example.in",1);



        training.forEach(setItem -> {

            setItem.forEach(item -> {
                TreeNode tree = null;

                if(checkIfSpecificTreeHasOneWord(split(item)[0]) != null){
                    tree = checkIfSpecificTreeHasOneWord(split(item)[0]);
                }else if(checkIfSpecificTreeHasOneWord(split(item)[1])  != null){
                    tree = checkIfSpecificTreeHasOneWord(split(item)[1]);
                }


                if(tree == null){
                    TreeNode root = new TreeNode("root");
                    root.addChild(split(item)[0]);
                    root.addChild(split(item)[1]);
                    listTree.add(root);

                } else{
                    int index;
                    if(listTree.size() > 0 ){
                        index = listTree.indexOf(tree.getParent());
                        if(index != -1){
                            tree.addChild(split(item)[0]);
                            tree.addChild(split(item)[1]);
                            listTree.set(index,tree);
                        }
                    }
                }
            });
        });


//        listTree.forEach(item -> {
//            for(TreeNode node : item.getChildren()) {
//                System.out.println(node.getData());
//            }
//            System.out.println("\n---- \n");
//
//        });

        testing.forEach(setItem -> {
            setItem.forEach(item -> {
                if(checkSinonimity(item)){
                    System.out.println(" " + item +" -- synonumous");
                } else{
                    System.out.println(" " + item +" -- different");

                }
            });
        });

    }

    public static TreeNode checkIfSpecificTreeHasOneWord(String str){
        TreeNode tr = null;
        if(listTree.size() > 0){
//            Optional<TreeNode> tree = listTree.stream().filter(item -> TreeNode.checkIfFound(item,str)).findFirst();
                for(int i = 0;i < listTree.size();i++){
                    if(TreeNode.findNode(listTree.get(i).getParent(),str) != null){
                        return TreeNode.findNode(listTree.get(i),str).getParent();
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
        else if(checkIfSpecificTreeHasOneWord(split(list)[0]) != null && checkIfSpecificTreeHasOneWord(split(list)[1]) != null){
           return true;
        }
        else
            return false;
    }
}
