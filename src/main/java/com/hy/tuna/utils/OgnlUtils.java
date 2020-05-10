package com.hy.tuna.utils;

import com.hy.tuna.ognl.ContextPropertyAccessor;
import com.hy.tuna.ognl.LiteralPropertyAccessor;
import com.hy.tuna.ognl.OgnlMemberAccessor;
import ognl.MemberAccess;
import ognl.Ognl;
import ognl.OgnlException;
import ognl.OgnlRuntime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OgnlUtils {

    public static final LiteralPropertyAccessor LITERAL_PROPERTY_ACCESSOR = new LiteralPropertyAccessor();

    static {
        OgnlRuntime.setPropertyAccessor(Map.class,new ContextPropertyAccessor());
        OgnlRuntime.setPropertyAccessor(String.class, LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Integer.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(int.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Long.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(long.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Short.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(short.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Double.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(double.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Float.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(float.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(Character.class,LITERAL_PROPERTY_ACCESSOR);
        OgnlRuntime.setPropertyAccessor(char.class,LITERAL_PROPERTY_ACCESSOR);
    }
    private static final MemberAccess OGNL_MEMBER_ACCESSOR = new OgnlMemberAccessor();
    public static Object getValue(String expression,Object root) throws OgnlException {
        Map context = Ognl.createDefaultContext(root,OGNL_MEMBER_ACCESSOR);
        Object value = Ognl.getValue(expression, context, root);
        return value;
    }

    public static Iterable getIterable(String expression,Object root) throws OgnlException {
        Object value = getValue(expression,root);
        if(value instanceof Iterable){
            return (Iterable) value;
        }
        if(value instanceof Object[]){
            List<Object> resultList = new ArrayList<>();
            Object[] sources = (Object[]) value;
            for(Object obj:sources){
                resultList.add(obj);
            }
            return resultList;
        }
        throw new OgnlException("the giving expression is not a Iterable object");
    }
}
