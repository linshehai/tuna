package com.hy.tuna.session;

import com.hy.tuna.Configuration;

public class DefaultSessionFactory implements SessionFactory{

    private Configuration configuration;

    public DefaultSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Session openSession() {
        return new DefaultTunaSession(this.configuration);
    }
}
