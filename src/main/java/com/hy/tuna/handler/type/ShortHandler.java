package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortHandler implements TypeHandler<Short> {

    @Override
    public void setValue(PreparedStatement ps, int index, Short value) throws SQLException {
        ps.setShort(index,value);
    }

    @Override
    public Short getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {

        return rs.getShort(fieldMapping.getColumnName());
    }
}
