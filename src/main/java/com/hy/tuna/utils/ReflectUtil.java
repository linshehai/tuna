package com.hy.tuna.utils;

import com.hy.tuna.exceptions.TunaException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static <T> Class<T> resolve(String className) throws TunaException {
        Class clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new TunaException(e, "no class was found for "+className);
        }
        return clazz;
    }

    public static <T> T create(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void invoke(Method method,Object target,Object[] args) throws InvocationTargetException {
        try {
            if(!method.isAccessible()){
                method.setAccessible(true);
            }
            method.invoke(target,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void setValue(Field field,Object target,Object value){
        if(!field.isAccessible()){
            field.setAccessible(true);
        }
        try {
            field.set(target,value);
        } catch (IllegalAccessException e) {
            //just ignore it
        }
    }
    public static void setValue(String propertyName,Object target,Object value) throws NoSuchFieldException {
        Class current = target.getClass();
        Field field = null;
        do {
            try {
                field = current.getDeclaredField(propertyName);
            }catch (NoSuchFieldException e){
                current = current.getSuperclass();
            }
        }while (current!=null&&field==null);

         if(field!=null){
             if(!field.isAccessible()){
                 field.setAccessible(true);
             }
             try {
                 field.set(target,value);
             } catch (IllegalAccessException e) {
                 //just ignore it
             }
         }
    }

    public static void invoke(String methodName,Object target,Class[] args,Object ...value) throws InvocationTargetException {
        Class current = target.getClass();
        Method method = null;
        do {
            try {
                method = current.getDeclaredMethod(methodName,args);
            }catch ( NoSuchMethodException e){
                current = current.getSuperclass();
            }
        }while (current!=null&&method==null);
        if(method!=null){
            invoke(method,target,value);
        }
    }
}
