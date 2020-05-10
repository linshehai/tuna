package com.hy.tuna.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface Transaction {


    DataSource getDataSource();

    void setDataSource(DataSource dataSource);

    boolean commit() throws SQLException;

    void setAutoCommit(boolean autoCommit) throws SQLException;
}
