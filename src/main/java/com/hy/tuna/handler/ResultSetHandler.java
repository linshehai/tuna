package com.hy.tuna.handler;

import com.hy.tuna.mapping.ObjectMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ResultSetHandler {

    <E> List<E> handleResultSet(Statement statement, ObjectMapping resultMap) throws SQLException;
}
