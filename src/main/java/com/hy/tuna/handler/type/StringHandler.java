package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringHandler implements TypeHandler<String> {
    @Override
    public void setValue(PreparedStatement ps, int index, String value) throws SQLException {
        ps.setString(index,value);
    }

    @Override
    public String getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getString(fieldMapping.getColumnName());
    }
}
