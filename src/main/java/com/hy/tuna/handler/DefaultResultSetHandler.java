package com.hy.tuna.handler;

import com.hy.tuna.mapping.FieldMapping;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.utils.ReflectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler {

    @Override
    public <E> List<E> handleResultSet(Statement statement, ObjectMapping resultMap) throws SQLException {
        //对应bean的Class对象，对应的列和对应的属性
        List<E> resultList = new ArrayList<>();
        ResultSet rs = statement.getResultSet();
        if(rs==null){

        }
        try {
            E elem;
            while (rs.next()) {
                elem = resultMap.create();
                List<FieldMapping> fieldMappingList = resultMap.getFieldMappings();
                for (FieldMapping fieldMapping : fieldMappingList) {
                    ReflectUtil.setValue(fieldMapping.getJavaProperty(), elem, fieldMapping.getTypeHandler().getValue(rs, fieldMapping));
                }
                resultList.add(elem);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("No such field ");
        }
        return resultList;
    }
}
