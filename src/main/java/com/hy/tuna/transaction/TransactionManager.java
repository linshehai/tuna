package com.hy.tuna.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TransactionManager implements Transaction {

    private boolean autoCommit = true;

    private DataSource dataSource;


    public TransactionManager(){

    }
    public TransactionManager(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean commit() throws SQLException {
        if(this.dataSource==null){
            throw new NullPointerException("you should provide a dataSource before invoke this method");
        }
        this.dataSource.getConnection().commit();
        return true;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        if(this.dataSource==null){
           throw new NullPointerException("you should provide a dataSource before invoke this method");
        }
        this.autoCommit = autoCommit;
        this.dataSource.getConnection().setAutoCommit(this.autoCommit);
    }
}
