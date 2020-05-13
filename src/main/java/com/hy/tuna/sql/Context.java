package com.hy.tuna.sql;

import com.hy.tuna.Configuration;
import com.hy.tuna.mapping.ParameterMapping;

import java.util.List;

public interface Context {

    Object getParameter();

    void addParameterMapping(ParameterMapping parameterMapping);

    List<ParameterMapping> getParameterMapping();

    Context append(String text);

    String getSql();

    Configuration getConfiguration();

    Context replace(int start,int end,String str);

}
