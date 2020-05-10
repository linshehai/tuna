package com.hy.tuna.xml.elements.config;

import com.hy.tuna.sql.Context;
import com.hy.tuna.xml.elements.Node;

import java.util.List;

public abstract class ConfigNode implements Node {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void addNode(Node node) {

    }

    @Override
    public boolean apply(Context context) {
        return false;
    }

    @Override
    public String getNamespace() {
        return null;
    }

    @Override
    public void setNamespace(String namespace) {

    }
}
