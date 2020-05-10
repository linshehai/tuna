package com.hy.tuna.handler;

import com.hy.tuna.mapping.FieldMapping;
import com.hy.tuna.proxy.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultMap {

    private Class objectType;

    private List<FieldMapping> fieldMappings;

    private ObjectFactory objectFactory;

    public ResultMap(){
        this.fieldMappings = new ArrayList<>();
    }
    public void addFieldMapping(final FieldMapping fieldMapping){
        this.fieldMappings.add(fieldMapping);
    }
    public List<FieldMapping> getFieldMappings() {

        return fieldMappings;
    }

    public <E> E create() {
        return (E) objectFactory.create(this.objectType);
    }
}
