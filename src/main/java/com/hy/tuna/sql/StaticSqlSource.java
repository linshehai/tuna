package com.hy.tuna.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StaticSqlSource implements SqlSource {

    private String sql;

    private Connection connection;

    public StaticSqlSource(String sql){
        this.sql = sql;
    }
    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public Statement getStatement() throws SQLException {
        return connection.prepareStatement(this.sql);
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
