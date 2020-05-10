package com.hy.tuna.xml.elements.config;

import com.hy.tuna.sql.Context;
import com.hy.tuna.xml.elements.Node;

import java.util.ArrayList;
import java.util.List;

public  class Config implements Node {

    private List<Node> nodeList;

    public Config(){
        this.nodeList = new ArrayList<>();
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getNamespace() {
        return null;
    }

    @Override
    public void setNamespace(String namespace) {

    }

    @Override
    public void addNode(Node node) {
        this.nodeList.add(node);
    }

    @Override
    public boolean apply(Context context) {
        return false;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}
