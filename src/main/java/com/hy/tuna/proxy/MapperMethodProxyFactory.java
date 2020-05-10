package com.hy.tuna.proxy;

import com.hy.tuna.session.Session;

import java.lang.reflect.Proxy;

public class MapperMethodProxyFactory {


    public <T> T create(Class<T> mapperInterface, Session session){
        MapperMethodProxy<T> mapperMethodProxy = new MapperMethodProxy(mapperInterface,session);
        T mapper = newInstance(mapperInterface,mapperMethodProxy);
        return mapper;
    }

    private <T> T newInstance(Class<T> mapperInterface, MapperMethodProxy<T> mapperMethodProxy) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        T instance = (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperMethodProxy);
        return instance;
    }
}
