package com.hy.tuna.mapping;

import com.hy.tuna.handler.type.TypeHandler;
import com.hy.tuna.proxy.BeanFactory;
import com.hy.tuna.proxy.ObjectFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapping {

    private String table;

    private String catalog;

    private Class objectType;

    private List<FieldMapping> fieldMappings;

    private ObjectFactory objectFactory;

    public ObjectMapping(){
        this.fieldMappings = new ArrayList<>();
        this.objectFactory = new BeanFactory();
    }

    public <T> void addFieldMapping(String property, String columnName, TypeHandler<T> typeHandler){
        FieldMapping fieldMapping = new FieldMapping(property, columnName, typeHandler);
        this.fieldMappings.add(fieldMapping);
    }
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public Class getObjectType() {
        return objectType;
    }

    public void setObjectType(Class objectType) {
        this.objectType = objectType;
    }

    public List<FieldMapping> getFieldMappings() {
        return fieldMappings;
    }

    public void setFieldMappings(List<FieldMapping> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public <T> T create() {
        return (T) this.objectFactory.create(this.objectType);
    }

    public Field getJavaField(String javaProperty) throws NoSuchFieldException {
        Field field = this.getObjectType().getDeclaredField(javaProperty);
        field.setAccessible(true);
        return field;
    }

    public FieldMapping getParamMapping(String name) throws NoSuchFieldException {
        if(this.fieldMappings==null){
            throw new NoSuchFieldException(String.format("there are no field name call %s",name));
        }
        for(FieldMapping fieldMapping:fieldMappings){
            if(fieldMapping.getJavaProperty().equals(name)){
                return fieldMapping;
            }
        }
        throw new NoSuchFieldException(String.format("there are no field name call %s",name));
    }
}
