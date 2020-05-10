package com.hy.tuna.mapping;

import com.hy.tuna.handler.type.TypeHandler;

import javax.swing.*;
import java.lang.reflect.Field;

public class FieldMapping {

    private String javaProperty;

    private String columnName;

    private Field javaField;

    private TypeHandler typeHandler;

    public FieldMapping(){

    }
    public FieldMapping(String javaProperty, String columnName,TypeHandler typeHandler) {
        this.javaProperty = javaProperty;
        this.columnName = columnName;
        this.typeHandler = typeHandler;
    }

    public void setTypeHandler(TypeHandler typeHandler) {
        this.typeHandler = typeHandler;
    }

    public String getJavaProperty() {
        return javaProperty;
    }

    public void setJavaProperty(String javaProperty) {
        this.javaProperty = javaProperty;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public TypeHandler getTypeHandler() {
        return typeHandler;
    }

    public Field getJavaField() {
        return javaField;
    }

    public void setJavaField(Field javaField) {
        this.javaField = javaField;
    }
}
