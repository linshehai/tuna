package com.hy.tuna.session;

import com.hy.tuna.config.Configurable;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.executor.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Session extends Configurable {

    <T> T getTunaMapper(Class<T> mapperClass);

    <T> List<T> queryList(MappedStatement mappedStatement) throws SQLException, TunaException;

    <T> T fetchOne(MappedStatement mappedStatement) throws SQLException, TunaException;

    <T> T update();

    boolean commit() throws SQLException;

    void setAutoCommit(boolean autoCommit) throws SQLException;
}
