package com.hy.tuna.builder;

import com.hy.tuna.Configuration;
import com.hy.tuna.annotations.Column;
import com.hy.tuna.annotations.Lazy;
import com.hy.tuna.annotations.Table;
import com.hy.tuna.builder.scanner.AnnotationObjectScanner;
import com.hy.tuna.builder.scanner.ObjectScanner;
import com.hy.tuna.exceptions.EmptyFieldException;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.exceptions.TunaParseException;
import com.hy.tuna.handler.type.TypeHandler;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.utils.ReflectUtil;
import com.hy.tuna.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationObjectBuilder implements ObjectBuilder<ObjectMapping>{

    private List<Class<?>> domainObjects;

    private Configuration configuration;

    private ObjectScanner objectScanner;

    public AnnotationObjectBuilder(Configuration config) {
        this.configuration = config;
        this.domainObjects = new ArrayList<>();
    }

    public AnnotationObjectBuilder(Configuration configuration, String configLocation) {
        this(configuration);
        this.objectScanner = new AnnotationObjectScanner(configLocation);
    }

    @Override
    public Map<Class<?>,ObjectMapping> build() throws Exception {
        //scan the annotated class
        objectScanner.scan();
        ObjectMapping objectMapping;
        Map<Class<?>,ObjectMapping> objectMappingMap = new HashMap<>();
        for(Class<?> domainObject:domainObjects){
            objectMapping = new ObjectMapping();
            objectMapping.setObjectType(domainObject);
            Annotation table = domainObject.getAnnotation(Table.class);
            if(table!=null){
                Method m = table.annotationType().getMethod("name",new Class[]{});
                Object result = m.invoke(table,new Object[]{});
                if(!StringUtils.hasText(result)){
                    throw new TunaParseException("table name can not be null on class "+domainObject.toString());
                }
                String tableName = result.toString();
                objectMapping.setTable(tableName);

                /**
                 * 解析@Column字段
                 */
                Field[] fields = domainObject.getDeclaredFields();
                if(fields==null||fields.length<=0){
                    throw new EmptyFieldException("no field found on class "+domainObject.toString());
                }

                //todo:注意这个类中所有的字段都没有@Column注解的情况
                for (Field field : fields) {
                    Annotation column = field.getAnnotation(Column.class);
                    if(column!=null){
                        String property = field.getName();
                        String columnName = (String) column.annotationType().getMethod("name",new Class[]{}).invoke(column,new Object[]{});
                        if(!StringUtils.hasText(columnName)){
                            throw new TunaParseException("column name can not be null on property "+property+"");
                        }
                        //延迟加载
                        boolean isLazy = false;
                        if(field.getAnnotation(Lazy.class)!=null){
                            isLazy = true;
                        }
                        //todo:解析field的类型，比如不是基本类型，或者是自定义或者list等类型时
                        TypeHandler typeHandler = this.configuration.getTypeHandler(field.getType());
                        objectMapping.addFieldMapping(property,columnName,typeHandler);
                    }
                }
                //如果有字段与数据库表映射，则将它加到映射结果列表中去
                //即，如果这个类中没有@Column注解配置，则该类不添加到映射列表
                if(objectMapping.getFieldMappings().size()>0){
                    objectMappingMap.put(domainObject,objectMapping);
                }else{
                    throw new TunaParseException("No @Column annotation was found,class["+domainObject+"]");
                }
            }

        }
        this.configuration.setObjectMapping(objectMappingMap);
        return objectMappingMap;
    }

    public void addDomainObject(String className) throws TunaException {
        Class domainObject = ReflectUtil.resolve(className);
        addDomainObject(domainObject);
    }

    public void addDomainObject(Class<?> domainObject){
        this.domainObjects.add(domainObject);
    }
}
