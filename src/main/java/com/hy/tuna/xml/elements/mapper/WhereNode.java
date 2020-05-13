package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;
import com.hy.tuna.sql.FilteredContext;

public class WhereNode extends DirectiveNode {

    private static final String TAG_NAME = "where";
    @Override
    public boolean apply(Context context) {
        FilteredContext filteredContext = new FilteredContext(context);
        this.nodeList.forEach(node -> node.apply(filteredContext));
        filteredContext.applyAll();
        return true;
    }
}
