package com.hy.tuna.handler;

import com.hy.tuna.exceptions.ObjectNotFoundException;
import com.hy.tuna.handler.type.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class TypeRegistry {

    private Map<Class<?>, TypeHandler> typeHandlerMap;
    public TypeRegistry(){
        TypeHandler typeHandler;
        this.typeHandlerMap = new HashMap<>();
        typeHandler = new StringHandler();
        register(String.class,typeHandler);
        typeHandler = new IntegerHandler();
        register(Integer.class,typeHandler);
        register(int.class,typeHandler);
        typeHandler = new DoubleHandler();
        register(Double.class,typeHandler);
        register(double.class,typeHandler);
        typeHandler = new LongHandler();
        register(Long.class,typeHandler);
        register(long.class,typeHandler);
        typeHandler = new DateHandler();
        register(Date.class,typeHandler);
        register(java.util.Date.class,typeHandler);
        typeHandler = new FloatHandler();
        register(float.class,typeHandler);
        register(Float.class,typeHandler);
        typeHandler = new BooleanHandler();
        register(Boolean.class,typeHandler);
        register(boolean.class,typeHandler);
        typeHandler = new ShortHandler();
        register(Short.class,typeHandler);
        register(short.class,typeHandler);
    }

    public void register(Class<?> type,TypeHandler typeHandler){
        this.typeHandlerMap.put(type,typeHandler);
    }

    public <T> TypeHandler<T> getTypeHandler(Class<?> type) throws ObjectNotFoundException {
        TypeHandler<T> typeHandler = this.typeHandlerMap.get(type);
        if(typeHandler==null){
            throw new ObjectNotFoundException("there are no handler for type "+type);
        }
        return typeHandler;
    }
}
