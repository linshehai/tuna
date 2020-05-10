package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LongHandler implements TypeHandler<Long> {
    @Override
    public void setValue(PreparedStatement ps, int index, Long value) throws SQLException {
        ps.setLong(index,value);
    }

    @Override
    public Long getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getLong(fieldMapping.getColumnName());
    }
}
