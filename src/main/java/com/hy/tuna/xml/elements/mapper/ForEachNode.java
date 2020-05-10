package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;
import com.hy.tuna.utils.OgnlUtils;
import ognl.OgnlException;

import java.util.Iterator;

public class ForEachNode extends DirectiveNode {

    private static final String DEFAULT_OPEN = "(";
    private static final String DEFAULT_CLOSE = ")";
    private static final String DEFAULT_DELIMITER = ",";

    private String items;

    private String var;

    /**
     * 分割符
     */
    private String delimiter = DEFAULT_DELIMITER;

    private String open = DEFAULT_OPEN;

    private String close = DEFAULT_CLOSE;


    @Override
    public boolean apply(Context context) {
        super.apply(context);
        try {
            //collect the sql text first
            super.apply(context);
            //construct the foreach structure
            Iterable iterable = OgnlUtils.getIterable(items,context.getParameter());
            StringBuilder sb = new StringBuilder();
            sb.append(open);
            Iterator iterator = iterable.iterator();
            for (;;) {
                iterator.next();
                sb.append('?');
                if (!iterator.hasNext())
                    break;
                sb.append(',');
            }
            sb.append(close);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return true;
    }
}
