package com.hy.tuna.ognl;

import com.hy.tuna.utils.OgnlUtils;
import ognl.OgnlException;

public class ExpressionEvaluator {


    public boolean evaluate(Object root,String expression) throws OgnlException {
        Object value = OgnlUtils.getValue(expression,root);
        if(value instanceof Boolean){
            return (boolean) value;
        }
        return false;
    }
}
