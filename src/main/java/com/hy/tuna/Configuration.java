package com.hy.tuna;

import com.hy.tuna.exceptions.ObjectNotFoundException;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.executor.SqlExecutor;
import com.hy.tuna.handler.TypeRegistry;
import com.hy.tuna.handler.type.TypeHandler;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.proxy.MapperMethodProxyFactory;
import com.hy.tuna.utils.ReflectUtil;
import com.hy.tuna.xml.elements.Node;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private TypeRegistry typeRegistry = new TypeRegistry();
    /**
     * 类与表的映射关系
     */
    private Map<Class<?>,ObjectMapping> objectMappingMap;

    private Map<Class<?>,MapperMethodProxyFactory> proxyFactoryMap;

    private SqlExecutor sqlExecutor;

    private Map<String, Node> mapperMapping;
    private DataSource dataSource;

    public Configuration(){
        this.mapperMapping = new HashMap<>();
    }

    public <T> TypeHandler<T> getTypeHandler(Class<?> type) throws ObjectNotFoundException {
        return this.typeRegistry.getTypeHandler(type);
    }

    public void setObjectMapping(Map<Class<?>, ObjectMapping> objectMappingMap) {
        this.objectMappingMap = objectMappingMap;
    }
    public ObjectMapping getObjectMapping(Class key) {
        ObjectMapping objectMapping = this.objectMappingMap.get(key);
        return objectMapping;
    }

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public <T> MapperMethodProxyFactory getMapper(Class<T> mapperClass) throws TunaException {
        MapperMethodProxyFactory proxyFactory = proxyFactoryMap.get(mapperClass);
        if(proxyFactory==null){
            throw new TunaException("No mapper factory found for "+mapperClass.getName());
        }
        return proxyFactory;
    }


    public void addMapper(Class<?> mapper) {
        if(proxyFactoryMap==null){
            proxyFactoryMap = new HashMap<>();
        }
        proxyFactoryMap.put(mapper,new MapperMethodProxyFactory());
    }

    public void addMapper(String mapper) {
        try {
            Class mapperClazz = ReflectUtil.resolve(mapper);
            addMapper(mapperClazz);
        } catch (TunaException e) {
            e.printStackTrace();
        }
    }


    public void addMapping(String namespace, Node node) {
        String key = namespace+"."+node.getId();
        if(this.mapperMapping.containsKey(key)){
            throw new IllegalArgumentException("id ["+node.getId()+"] already exist in namespace "+namespace);
        }
        this.mapperMapping.put(key,node);
    }

    public Node getMapping(String key){
        return this.mapperMapping.get(key);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
}
