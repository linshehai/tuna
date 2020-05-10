package com.hy.tuna.executor;

import com.hy.tuna.Configuration;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.mapping.*;
import com.hy.tuna.sql.SqlSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseExecutor implements SqlExecutor {

    private Configuration configuration;

    public BaseExecutor(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> queryList(MappedStatement mappedStatement,Statement statement) throws SQLException {
        List<E> resultList = doQuery(mappedStatement,statement);
        return resultList;
    }



    protected abstract <E> List<E> doQuery(MappedStatement mappedStatement,Statement statement) throws SQLException;
}
