package com.hy.tuna.proxy;

public interface ObjectFactory {

    <T> T create(Class<T> tClass);
}
