package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobHandler implements TypeHandler<Reader> {

    @Override
    public void setValue(PreparedStatement ps, int index, Reader value) throws SQLException {
        ps.setClob(index,value);
    }

    @Override
    public Reader getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getClob(fieldMapping.getColumnName()).getCharacterStream();
    }
}
