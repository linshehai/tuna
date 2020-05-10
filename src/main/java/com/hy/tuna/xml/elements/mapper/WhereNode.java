package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;

public class WhereNode extends DirectiveNode {

    private static final String TAG_NAME = "where";
    @Override
    public boolean apply(Context context) {
        boolean exist = false;

        this.nodeList.forEach(node -> node.apply(context));
        return true;
    }
}
