package com.hy.tuna.ognl;

import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.PropertyAccessor;

import java.util.Map;

public class LiteralPropertyAccessor implements PropertyAccessor {
    @Override
    public Object getProperty(Map context, Object target, Object name) throws OgnlException {
        return target;
    }

    @Override
    public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {

    }

    @Override
    public String getSourceAccessor(OgnlContext context, Object target, Object index) {
        return null;
    }

    @Override
    public String getSourceSetter(OgnlContext context, Object target, Object index) {
        return null;
    }
}
