package com.hy.tuna.proxy;

import com.hy.tuna.utils.ReflectUtil;

public class BeanFactory implements ObjectFactory {
    @Override
    public <T> T create(Class<T> tClass) {
        return ReflectUtil.create(tClass);
    }
}
