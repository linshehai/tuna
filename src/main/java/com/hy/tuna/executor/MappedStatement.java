package com.hy.tuna.executor;

import com.hy.tuna.Configuration;
import com.hy.tuna.annotations.Param;
import com.hy.tuna.handler.ParameterTokenHandler;
import com.hy.tuna.handler.ResultMap;
import com.hy.tuna.handler.TextTokenizer;
import com.hy.tuna.handler.TokenHandler;
import com.hy.tuna.mapping.MethodWrapper;
import com.hy.tuna.mapping.ObjectMapping;
import com.hy.tuna.mapping.ParameterMapping;
import com.hy.tuna.sql.Context;
import com.hy.tuna.sql.DynamicContext;
import com.hy.tuna.sql.SqlSource;
import com.hy.tuna.sql.StaticSqlSource;
import com.hy.tuna.xml.elements.ExecutableStatement;
import com.hy.tuna.xml.elements.Node;
import com.hy.tuna.xml.elements.StatementType;

import java.sql.Statement;
import java.util.List;

public class MappedStatement {

    private SqlSource sqlSource;

    private Node statementNode;

    private List<ParameterMapping> parameterMappings;

    private ObjectMapping resultMap;

    public MappedStatement(Node statementNode, MethodWrapper methodWrapper){
        this.statementNode = statementNode;
        Object param = methodWrapper.getParameter();
        Configuration configuration = methodWrapper.getConfiguration();
        Context context = new DynamicContext(configuration,param);
        //collect the sql
        statementNode.apply(context);
        String sql = context.getSql();

        //parse sql text,replace token with question mark(?)
        TokenHandler tokenHandler = new ParameterTokenHandler(context);
        TextTokenizer textTokenizer = new TextTokenizer("#{", "}",tokenHandler);
        String text = textTokenizer.parse(sql);
        sqlSource = new StaticSqlSource(text);
        this.parameterMappings = context.getParameterMapping();
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public List<ParameterMapping> getParameterMapping() {
       return this.parameterMappings;
    }

    public ObjectMapping getResultMap() {
        return resultMap;
    }

    public void setResultMap(ObjectMapping resultMap) {
        this.resultMap = resultMap;
    }

    public StatementType getStatementType() {
        return ((ExecutableStatement)statementNode).getStatementType();
    }
}
