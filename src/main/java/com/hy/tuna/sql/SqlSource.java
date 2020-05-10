package com.hy.tuna.sql;

import com.hy.tuna.mapping.FieldMapping;
import com.hy.tuna.xml.elements.Node;
import com.hy.tuna.xml.elements.StatementType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public interface SqlSource {

    String getSql();

    Statement getStatement() throws SQLException;

    void setConnection(Connection connection);
}
