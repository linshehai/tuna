package com.hy.tuna.sql;

import java.util.List;

public class DefaultSqlBuilder implements SqlBuilder {
    @Override
    public SqlBuilder select(List<String> columnList) {
        return null;
    }

    @Override
    public <T> SqlBuilder from(Class<T> domainClass) {
        return null;
    }

    @Override
    public <T> SqlBuilder innerJoin(Class<T> domainClass) {
        return null;
    }

    @Override
    public SqlBuilder on(String column) {
        return null;
    }

    @Override
    public SqlBuilder eq(String column) {
        return null;
    }
}
