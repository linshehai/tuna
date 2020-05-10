package com.hy.tuna;

import com.hy.tuna.builder.SessionFactoryBuilder;
import com.hy.tuna.session.Session;
import com.hy.tuna.session.SessionFactory;

public class TestBase {
    static SessionFactory sessionFactory = null;
    static {
        SessionFactoryBuilder sessionFactoryBuilder = new SessionFactoryBuilder("/tuna-config.xml");

        try {
            sessionFactory = sessionFactoryBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
