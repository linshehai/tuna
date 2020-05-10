package com.hy.tuna.executor;

import com.hy.tuna.exceptions.TunaException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface SqlExecutor {

    <T> List<T> queryList(MappedStatement mappedStatement, Statement statement) throws TunaException, SQLException;

    /**
     * update ,insert,delete are treat as update
     * @param mappedStatement
     * @param statement
     * @return
     */
    int update(MappedStatement mappedStatement, Statement statement);

}
