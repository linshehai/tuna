package com.hy.tuna.proxy;

import com.hy.tuna.Configuration;
import com.hy.tuna.exceptions.ObjectNotFoundException;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.executor.MappedStatement;
import com.hy.tuna.handler.type.TypeHandler;
import com.hy.tuna.mapping.MethodWrapper;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.session.Session;
import com.hy.tuna.utils.ReflectUtil;
import com.hy.tuna.xml.elements.*;

import java.lang.reflect.*;
import java.util.*;

public class MapperMethodProxy<T> implements InvocationHandler{

    private Class<T> mapperInterface;

    private Session session;

    private Configuration configuration;

    private Map<Method,MethodWrapper> cachedMethod;

    public MapperMethodProxy(Class<T> mapperInterface,Session session) {
        this.mapperInterface = mapperInterface;
        this.session = session;
        this.cachedMethod = new HashMap<>();
        this.configuration = session.getConfiguration();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //if the method is extend from Object,return it;
        if(method.getDeclaringClass()==Object.class){
            return invokeObjectMethod(method);
        }
        MethodWrapper methodWrapper = getMethod(method,args);
        String bindSqlStatementKey = mapperInterface.getCanonicalName()+"."+method.getName();
        Node node = this.configuration.getMapping(bindSqlStatementKey);
        //if this statement is not a sql statement ,then throw a exception
        if(!(node instanceof ExecutableStatement)){
            throw new TunaException("the bind statement is not a sql statement");
        }
        if(node==null){
            throw new TunaException("No statement found for object");
        }

        MappedStatement mappedStatement = new MappedStatement(node,methodWrapper);

        if(node instanceof SelectStatement){
            SelectStatement selectStatement = (SelectStatement) node;
            String resultId =  mapperInterface.getCanonicalName()+"."+selectStatement.getResultMap();
            ResultMapNode resultMapNode = (ResultMapNode) this.configuration.getMapping(resultId);
            List<Node> nodeList = resultMapNode.getNodeList();
            ObjectMapping resultMap = new ObjectMapping();
            resultMap.setObjectType(ReflectUtil.resolve(resultMapNode.getType()));
            for(Node child:nodeList){
                ResultMappingNode rsn = (ResultMappingNode) child;
                Field field = resultMap.getObjectType().getDeclaredField(rsn.getProperty());
                TypeHandler typeHandler = configuration.getTypeHandler(field.getType());
                resultMap.addFieldMapping(rsn.getProperty(),rsn.getColumn(),typeHandler);
            }
            mappedStatement.setResultMap(resultMap);
        }

        if(methodWrapper.isMany()){
            return session.queryList(mappedStatement);
        }else{
            return session.fetchOne(mappedStatement);
        }
    }

    private Object invokeObjectMethod(Method method) {
        Class returnType = method.getReturnType();
        if(returnType==Boolean.class){
            return Boolean.FALSE;
        }else if(String.class==returnType){
            return this.mapperInterface.getCanonicalName();
        }
        return null;
    }

    private MethodWrapper getMethod(Method method,Object[] args) {
        MethodWrapper methodWrapper = this.cachedMethod.get(method);
        if(methodWrapper==null){
            methodWrapper = new MethodWrapper(method,args,this.configuration);
            this.cachedMethod.put(method,methodWrapper);
        }
        return  methodWrapper;
    }
}
