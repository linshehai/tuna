package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoubleHandler implements TypeHandler<Double> {
    @Override
    public void setValue(PreparedStatement ps, int index, Double value) throws SQLException {
        ps.setDouble(index,value);
    }

    @Override
    public Double getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getDouble(fieldMapping.getColumnName());
    }
}
