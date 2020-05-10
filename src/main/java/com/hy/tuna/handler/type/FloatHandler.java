package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatHandler implements TypeHandler<Float> {
    @Override
    public void setValue(PreparedStatement ps, int index, Float value) throws SQLException {
        ps.setFloat(index,value);
    }

    @Override
    public Float getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getFloat(fieldMapping.getColumnName());
    }
}
