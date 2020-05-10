package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;
import com.hy.tuna.mapping.ObjectMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerHandler implements TypeHandler<Integer> {
    @Override
    public void setValue(PreparedStatement ps,int index,Integer value) throws SQLException {
        ps.setInt(index,value);
    }

    @Override
    public Integer getValue(ResultSet rs,FieldMapping fieldMapping) throws SQLException {
       return rs.getInt(fieldMapping.getColumnName());
    }
}
