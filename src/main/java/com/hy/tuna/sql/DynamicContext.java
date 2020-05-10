package com.hy.tuna.sql;

import com.hy.tuna.Configuration;
import com.hy.tuna.mapping.MethodWrapper;
import com.hy.tuna.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicContext implements Context {

    private StringBuilder sqlBuilder = new StringBuilder();

    private Configuration configuration;

    private Object parameterObject;

    private List<ParameterMapping> parameterMappings;

    public DynamicContext(Configuration configuration,Object parameterObject) {
        this.configuration = configuration;
        this.parameterObject = parameterObject;
        this.parameterMappings = new ArrayList<>();
    }

    @Override
    public Object getParameter() {
        return parameterObject;
    }

    @Override
    public void addParameterMapping(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }

    @Override
    public List<ParameterMapping> getParameterMapping() {
        return this.parameterMappings;
    }


    @Override
    public Context append(String text) {
        this.sqlBuilder.append(text).append(" ");
        return this;
    }

    @Override
    public String getSql() {
        return this.sqlBuilder.toString();
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
