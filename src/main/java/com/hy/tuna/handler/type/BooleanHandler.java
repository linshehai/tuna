package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanHandler implements TypeHandler<Boolean> {
    @Override
    public void setValue(PreparedStatement ps, int index, Boolean value) throws SQLException {
        ps.setBoolean(index,value);
    }

    @Override
    public Boolean getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getBoolean(fieldMapping.getColumnName());
    }
}
