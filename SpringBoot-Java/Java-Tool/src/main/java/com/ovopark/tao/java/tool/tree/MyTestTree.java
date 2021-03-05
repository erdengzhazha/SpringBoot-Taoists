package com.ovopark.tao.java.tool.tree;

import java.util.List;

public class MyTestTree {

    private int id;

    private String name;

    private int parentId;

    private int no_order;

    private List<MyTestTree> childrens;

    public MyTestTree(int id, String name, int parentId,int no_order) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.no_order = no_order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<MyTestTree> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<MyTestTree> childrens) {
        this.childrens = childrens;
    }

    public int getNo_order() {
        return no_order;
    }

    public void setNo_order(int no_order) {
        this.no_order = no_order;
    }
}
