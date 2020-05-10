package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.ognl.ExpressionEvaluator;
import com.hy.tuna.sql.Context;
import com.hy.tuna.xml.elements.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class DirectiveNode implements Node {


    private String id;

    protected ExpressionEvaluator evaluator;

    public DirectiveNode(){
        this.evaluator = new ExpressionEvaluator();
        this.nodeList = new ArrayList<>();
    }

    protected List<Node> nodeList;

    @Override
    public String getId() {
        return this.id;
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
        this.nodeList.forEach(node -> node.apply(context));
        return true;
    }
}
