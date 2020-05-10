package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobHandler implements TypeHandler<InputStream> {

    @Override
    public void setValue(PreparedStatement ps, int index, InputStream value) throws SQLException {
        ps.setBinaryStream(index,value);
    }

    @Override
    public InputStream getValue(ResultSet rs, FieldMapping fieldMapping) throws SQLException {
        return rs.getBinaryStream(fieldMapping.getColumnName());
    }
}
