package com.hy.tuna.mapping;

import com.hy.tuna.Configuration;
import com.hy.tuna.annotations.Param;
import com.hy.tuna.exceptions.ObjectNotFoundException;
import com.hy.tuna.handler.type.TypeHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MethodWrapper implements Wrapper{

    private static final String DEFAULT_PARAM_NAME = "_parameter_";
    private Method targetMethod;

    private Class<?> returnType;

    private Type genericReturnType;


    private Configuration configuration;

    private Object[] values;

    public MethodWrapper(Method method,Object[] values,Configuration configuration) {
        this.targetMethod = method;
        this.returnType = method.getReturnType();
        this.genericReturnType = method.getGenericReturnType();
        this.configuration = configuration;
        this.values = values==null?new Object[0]:values;
    }

    /**
     * wrap the target method's argument,when execute the sql query,
     * this argument will pass to the statement argument
     * @return
     * @throws ObjectNotFoundException
     */
    public Object getParameter(){
        Parameter[] parameters = this.targetMethod.getParameters();
        int len = this.values.length;
        Parameter parameter;
        Object parameterValue;
        String name;
        if(len==1){
            return this.values[0];
        }
        try {
            for (int i = 0; i < len; i++) {
                parameter = parameters[i];
                parameterValue = this.values[i];
                Param parameterAnnotation = parameter.getAnnotation(Param.class);
                if (parameterAnnotation != null) {
                    name = parameterAnnotation.value();
                } else {
                    if(parameterValue instanceof Map){

                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean isMany(){
        return getReturnType().isAssignableFrom(Collection.class);
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Class<?> getReturnType() {
        return returnType;
    }


    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Type getGenericReturnType() {
        return genericReturnType;
    }

    public void setGenericReturnType(Type genericReturnType) {
        this.genericReturnType = genericReturnType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
