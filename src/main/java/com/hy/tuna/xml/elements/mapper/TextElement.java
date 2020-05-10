package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;

public class TextElement extends DirectiveNode {

    private String sql;

    public TextElement(String sql){
        this.sql = sql;
    }

    @Override
    public boolean apply(Context context) {
        context.append(this.sql);
        return true;
    }
}
