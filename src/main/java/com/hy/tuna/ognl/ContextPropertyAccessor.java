package com.hy.tuna.ognl;

import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.PropertyAccessor;

import java.util.Map;

public class ContextPropertyAccessor implements PropertyAccessor {

    private static final String DEFAULT_KEY = "_parameter";
    @Override
    public Object getProperty(Map context, Object target, Object name) throws OgnlException {
        Map map = (Map) target;

        Object result = map.get(name);
        if(map.containsKey(name)||result!=null){
            return result;
        }

        result = map.get(DEFAULT_KEY);
        if(result instanceof Map){
            return ((Map) result).get(name);
        }
        return null;
    }

    @Override
    public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {
        Map map = (Map) target;
        map.put(name,value);
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
