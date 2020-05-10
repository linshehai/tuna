package com.hy.tuna.xml.elements;

import com.hy.tuna.sql.Context;

import java.util.ArrayList;
import java.util.List;

public class AbstractNode implements Node {

    protected String id;

    private String namespace;

    protected List<Node> nodeList;

    public AbstractNode(){
        this.nodeList = new ArrayList<>();
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public void addNode(Node node) {
        this.nodeList.add(node);
    }

    @Override
    public boolean apply(Context context) {
        this.nodeList.forEach(node->node.apply(context));
        return true;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}
