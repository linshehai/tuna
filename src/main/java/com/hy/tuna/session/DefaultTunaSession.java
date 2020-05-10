package com.hy.tuna.session;

import com.hy.tuna.Configuration;
import com.hy.tuna.exceptions.TooManyResultException;
import com.hy.tuna.exceptions.TunaException;
import com.hy.tuna.executor.MappedStatement;
import com.hy.tuna.executor.PreparedExecutor;
import com.hy.tuna.executor.SqlExecutor;
import com.hy.tuna.proxy.MapperMethodProxyFactory;
import com.hy.tuna.sql.SqlSource;
import com.hy.tuna.transaction.Transaction;
import com.hy.tuna.transaction.TransactionManager;
import com.hy.tuna.xml.elements.StatementType;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultTunaSession implements Session {

    private Transaction transaction;

    private Configuration configuration;

    private SqlExecutor sqlExecutor;

    public DefaultTunaSession(Configuration configuration) {
            this.configuration = configuration;
            this.sqlExecutor = new PreparedExecutor(configuration);
            this.transaction = new TransactionManager(configuration.getDataSource());
    }

    @Override
    public <T> T getTunaMapper(Class<T> mapperClass) {
        MapperMethodProxyFactory mapperMethodProxyFactory;
        try {
            mapperMethodProxyFactory = configuration.getMapper(mapperClass);
        } catch (TunaException e) {
            throw new RuntimeException("cannot resolve mapper "+mapperClass.getName());
        }
        return mapperMethodProxyFactory.create(mapperClass,this);
    }

    @Override
    public <T> List<T> queryList(MappedStatement mappedStatement) throws SQLException, TunaException {
        SqlSource sqlSource = mappedStatement.getSqlSource();
        sqlSource.setConnection(this.transaction.getDataSource().getConnection());
        Statement statement = sqlSource.getStatement();
        List<T> result =sqlExecutor.queryList(mappedStatement,statement);
        return result;

    }

    @Override
    public <T> T fetchOne(MappedStatement mappedStatement) throws SQLException, TunaException {
        List<T> resultList = queryList(mappedStatement);
        if(resultList==null||resultList.size()<=0){
            return null;
        }
        if(resultList!=null&&resultList.size()>1){
            String message = String.format("expected result count is 1,but get %d",resultList.size());
            throw new TooManyResultException(message);
        }
        return resultList.get(0);
    }

    @Override
    public <T> T update() {
        return null;
    }

    @Override
    public boolean commit() throws SQLException {

        return this.transaction.commit();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.transaction.setAutoCommit(autoCommit);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

}
