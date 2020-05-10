package com.hy.tuna.handler;

import com.hy.tuna.Configuration;
import com.hy.tuna.exceptions.ObjectNotFoundException;
import com.hy.tuna.exceptions.TunaParseException;
import com.hy.tuna.handler.type.TypeHandler;
import com.hy.tuna.mapping.MethodWrapper;
import com.hy.tuna.mapping.ParameterMapping;
import com.hy.tuna.sql.Context;
import com.hy.tuna.utils.OgnlUtils;
import ognl.OgnlException;

import java.util.ArrayList;
import java.util.List;

public class ParameterTokenHandler implements TokenHandler {

    private Object parameterObject;

    private Configuration configuration;

    private Context context;

    public ParameterTokenHandler(Context context){
        this.configuration = context.getConfiguration();
        this.parameterObject = context.getParameter();
        this.context = context;
    }
    @Override
    public String handleToken(String token) {
        try {
            Object value = OgnlUtils.getValue(token,parameterObject);
            TypeHandler typeHandler = this.configuration.getTypeHandler(value.getClass());
            if(typeHandler==null){
                throw new IllegalStateException("No TypeHandler found for class "+value.getClass());
            }
            ParameterMapping parameterMapping = new ParameterMapping(context.getParameterMapping().size()+1,value,typeHandler);
            context.addParameterMapping(parameterMapping);
        } catch (OgnlException | ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return "?";
    }
}
