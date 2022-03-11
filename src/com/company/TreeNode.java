package com.company;

import java.util.ArrayList;
import java.util.List;

public class TreeNode{
    private String data = null;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;

    public TreeNode(String data) {
        this.data = data.toLowerCase();
        this.setParent(this);
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(String data) {
        TreeNode newChild = new TreeNode(data.toLowerCase());
        this.addChild(newChild);
    }

    public void addChildren(List<TreeNode> children) {
        for(TreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data.toLowerCase();
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }


    public static TreeNode findNode(TreeNode n, String s) {
        if (n.data.equals(s.toLowerCase())) {
            return n;
        } else {
            for (TreeNode child: n.getChildren()) {
                TreeNode result = findNode(child, s);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    public TreeNode findNodeTest(TreeNode n, String s) {
        if (n.data.equals(s.toLowerCase())) {
            return n;
        } else {
            for (TreeNode child: n.getChildren()) {
                TreeNode result = findNodeTest(child, s);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public static boolean checkIfFound(TreeNode n, String s){
        if(findNode(n,s) != null)
            return true;
        return false;
    }

    public void traverse(TreeNode child){
        if(child == null)
            return;
        for(TreeNode each : child.getChildren()){
            traverse(each);
        }
    }

}