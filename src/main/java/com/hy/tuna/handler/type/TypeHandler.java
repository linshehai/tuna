package com.hy.tuna.handler.type;

import com.hy.tuna.mapping.FieldMapping;
import com.hy.tuna.mapping.ObjectMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TypeHandler<T> {

    void setValue(PreparedStatement ps,int index,T value) throws SQLException;

    T getValue(ResultSet rs,FieldMapping fieldMapping) throws SQLException;

}
