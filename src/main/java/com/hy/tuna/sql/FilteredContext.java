package com.hy.tuna.sql;

import com.hy.tuna.Configuration;
import com.hy.tuna.mapping.ParameterMapping;

import java.util.List;

public class FilteredContext extends DynamicContext {

    private Context delegate;

    public FilteredContext(Context delegate){
        this.delegate = delegate;
    }
    public FilteredContext(Configuration configuration, Object parameterObject) {
        super(configuration, parameterObject);
    }


    public void applyAll() {
        String sql = delegate.getSql();
        int index = sql.lastIndexOf("where");
        String thisSql = this.getSql();
        String and = thisSql.substring(thisSql.indexOf("and") + 4);
        if(index!=-1){
            if(!sql.endsWith("where")){
                delegate.append(thisSql);
            }else{
                if(thisSql.startsWith("and")){
                    thisSql = and;
                }
                delegate.append(thisSql);
            }
        }else{
            if(thisSql.startsWith("and")){
                thisSql = and;
            }
            delegate.append("where ").append(thisSql);
        }
    }

    @Override
    public Object getParameter() {
        return delegate.getParameter();
    }

    @Override
    public List<ParameterMapping> getParameterMapping() {
        return delegate.getParameterMapping();
    }
}
