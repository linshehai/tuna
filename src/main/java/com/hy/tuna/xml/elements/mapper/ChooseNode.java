package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;
import com.hy.tuna.xml.elements.Node;

import java.util.List;

public class ChooseNode extends DirectiveNode {

    @Override
    public boolean apply(Context context) {
        super.apply(context);
        return false;
    }
}
