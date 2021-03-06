package com.hy.tuna.xml.elements.mapper;

import com.hy.tuna.sql.Context;
import com.hy.tuna.xml.elements.Node;
import ognl.OgnlException;

import java.util.List;

public class WhenNode extends DirectiveNode {

    private String test;

    @Override
    public boolean apply(Context context) {
        try {
            if(this.evaluator.evaluate(context.getParameter(),test)){
                return super.apply(context);
            }
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
