package com.hy.tuna.mapping;

import com.hy.tuna.handler.type.TypeHandler;

public class ParameterMapping extends FieldMapping {

    private Object value;

    private int index;

    public ParameterMapping(int index,Object value, TypeHandler typeHandler) {
        this.index = index;
        this.value=value;
        setTypeHandler(typeHandler);
    }
    public ParameterMapping(String javaProperty, String columnName, TypeHandler typeHandler,Object value) {
        this(javaProperty,columnName,typeHandler);
        this.value=value;
    }

    public ParameterMapping(String javaProperty, String columnName, TypeHandler typeHandler) {
        super(javaProperty, columnName, typeHandler);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
