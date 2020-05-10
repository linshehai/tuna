package com.hy.tuna.session;

import javax.sql.DataSource;

public interface SessionFactory {

    Session openSession();

}
