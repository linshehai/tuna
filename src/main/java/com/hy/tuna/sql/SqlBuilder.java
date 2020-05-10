package com.hy.tuna.sql;

import java.util.List;

public interface SqlBuilder {

    SqlBuilder select(List<String> columnList);

    <T> SqlBuilder from(Class<T> domainClass);

    <T> SqlBuilder innerJoin(Class<T> domainClass);

    SqlBuilder on(String column);

    SqlBuilder eq(String column);
}
