package com.hy.tuna.builder;

import com.hy.tuna.Configuration;

import java.util.Map;

public interface ObjectBuilder<T> {

    Map<Class<?>, T> build() throws Exception;
}
