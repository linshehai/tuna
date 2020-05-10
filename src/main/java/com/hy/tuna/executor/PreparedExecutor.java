package com.hy.tuna.executor;

import com.hy.tuna.Configuration;
import com.hy.tuna.handler.DefaultResultSetHandler;
import com.hy.tuna.handler.ResultMap;
import com.hy.tuna.handler.ResultSetHandler;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.mapping.ParameterMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PreparedExecutor extends BaseExecutor {

    private ResultSetHandler resultSetHandler = new DefaultResultSetHandler();

    public PreparedExecutor(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement mappedStatement,Statement statement) throws SQLException {
        List<ParameterMapping> parameterMappingList = mappedStatement.getParameterMapping();
        PreparedStatement ps = (PreparedStatement) statement;

        //set parameter
        for(ParameterMapping param:parameterMappingList){
            param.getTypeHandler().setValue(ps,param.getIndex(),param.getValue());
        }

        ps.execute();
        ObjectMapping resultMap = mappedStatement.getResultMap();
        List<E> resultList = resultSetHandler.handleResultSet(ps,resultMap);
        return resultList;
    }

    @Override
    public int update(MappedStatement mappedStatement, Statement statement) {
        return 0;
    }
}
