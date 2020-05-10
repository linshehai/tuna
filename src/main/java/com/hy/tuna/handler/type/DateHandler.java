package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateHandler implements TypeHandler<Date> {
    @Override
    public void setValue(PreparedStatement ps, int index, Date value) throws SQLException {
        java.sql.Date date = new java.sql.Date(value.getTime());
        ps.setDate(index,date);
    }

    @Override
    public Date getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getDate(fieldMapping.getColumnName());
    }
}
