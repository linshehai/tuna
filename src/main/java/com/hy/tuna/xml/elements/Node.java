package com.hy.tuna.xml.elements;

import com.hy.tuna.sql.Context;

import java.util.List;

public interface Node {

    String getId();

    String getNamespace();

    void setNamespace(String namespace);

    void addNode(Node node);

    boolean apply(Context context);
}
